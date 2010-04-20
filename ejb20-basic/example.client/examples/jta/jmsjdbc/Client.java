package examples.jta.jmsjdbc;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.ejb.CreateException;
import javax.jms.JMSException;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

/** 
 * This example shows how to use JTA to perform distributed transactions 
 * and two phase commit across two XA resources: JMS queue and 
 * Pointbase database. 
 * <p>
 * This example does the following:
 * <ul>
 * <li> The Client sends messages to the JMS queue, initializes the database 
 *      table, and calls ReceiveInTxBean EJBean. 
 * <li> ReceiveInTxBean EJBean in turn begins a distributed transaction, 
 *      receives messages from the JMS queue, updates the database, and then 
 *      commits the distributed transaction. 
 * <li> The Client then verifies that the data is updated in the database, and 
 *      cleans up the database table.
 * </ul>
 *
 * @author Copyright (c) 1999-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class Client {

  private static final String queueConnFactoryName="ExampleQueueConnectionFactory";
  private static final String queueName="ExampleQueue";
  private static final String homeJNDIName = "jta-jmsjdbc-ReceiveInTxHome"; 
  private static final String dataSrcName = "examples-dataSource-demoPool";
  private static final String tableName = "jtaSamples";

  private static String url       = "t3://localhost:7001";

  public static void main(String[] args)
    throws Exception 
  {
//    if (args.length != 1) {
//      System.out.println("Usage: java examples.jta.jmsjdbc.Client WebLogicURL");
//      System.out.println("Example: java examples.jta.jmsjdbc.Client t3://localhost:7001");
//      return;
//    }

    url = "t3://localhost:7001";

    InitialContext ic = getInitialContext(url);
    
    QueueSend qs = new QueueSend(ic, queueConnFactoryName, queueName);
    qs.init();
    readAndSend(qs);
    qs.close();

//    DataSource ds = (DataSource) ic.lookup(dataSrcName);
//    initTable(ds, tableName);

    ReceiveInTx recv = lookupBean(ic, homeJNDIName);
    recv.receiveMessages();

//    queryDatabase(ds, tableName);
//    dropTable(ds, tableName);
  }

  /**
   * Gets a message from System.in and sends it to the queue
   */
  private static void readAndSend(QueueSend qs) 
    throws IOException, JMSException
  {
    BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
    String line=null;
    boolean quitNow = false;
    do {
      System.out.print("Enter message (\"quit\" to quit): \n");
      line = msgStream.readLine();
      if (line != null && line.trim().length() != 0) {
        qs.send(line);
        log("JMS Message Sent: "+line+"\n");
        quitNow = line.equalsIgnoreCase("quit");
      }
    } while (! quitNow);
  }

  /**
   * Sets up the database connection and tables
   */
  private static void initTable(DataSource ds, String tableName) 
  {
    Connection jcon = null;
    Statement stmt = null;
    try {
      jcon = ds.getConnection();
      jcon.setAutoCommit(true);
      stmt = jcon.createStatement();
      try { 
        stmt.execute("drop table " + tableName); 
      } catch (SQLException ex) {}
      stmt.execute("create table " + tableName + "(data varchar(40))");
    } catch (SQLException ignore) {
    } finally {
      if (stmt != null) {
        try { stmt.close(); } catch (SQLException ex) {}
      }
      if (jcon != null) {
        try { jcon.close(); } catch (SQLException ex) {}
      }
    }
  }

  /**
   * Querys the database to see if there is any data
   */
  private static void queryDatabase(DataSource ds, String tableName) 
  {
    Connection jcon = null;
    Statement stmt = null;
    try {
      jcon = ds.getConnection();
      stmt = jcon.createStatement();
      stmt.execute("select * from " + tableName);
      ResultSet rs = stmt.getResultSet();
      String data = null;
      log("Data found in database:");
      while (rs.next()) {
        data = rs.getString(1);
        log(data);
      }
    } catch (SQLException ex) {
      log("Got SQLException while querying database: " + ex);
    } finally {
      if (stmt != null) {
        try { stmt.close(); } catch (SQLException ex) {}
      }
      if (jcon != null) {
        try { jcon.close(); } catch (SQLException ex) {}
      }
    }
  }

  /**
   * Drops the table tableName from the database
   */
  private static void dropTable(DataSource ds, String tableName) 
  {
    Connection jcon = null;
    Statement stmt = null;
    try {
      jcon = ds.getConnection();
      stmt = jcon.createStatement();
      stmt.execute("drop table " + tableName);
    } catch (SQLException ignore) {
    } finally {
      if (stmt != null) {
        try { stmt.close(); } catch (SQLException ex) {}
      }
      if (jcon != null) {
        try { jcon.close(); } catch (SQLException ex) {}
      }
    }
  }

  /**
   * Looks up and returns the bean
   */
  private static ReceiveInTx lookupBean(InitialContext ictx, String jndiName) {
    try {
      Object home = ictx.lookup(jndiName);
      ReceiveInTxHome rhome = (ReceiveInTxHome) 
        PortableRemoteObject.narrow(home, ReceiveInTxHome.class);
      ReceiveInTx bean = (ReceiveInTx) 
        PortableRemoteObject.narrow(rhome.create(), ReceiveInTx.class);
      return bean;
    } catch (NamingException ne) {
      log("The client was unable to lookup the EJBHome.  Please make sure ");
      log("that you have deployed the ejb with the JNDI name "+jndiName+" on the WebLogic server at "+url);
    } catch (CreateException ce) {
      log("Creation failed: " + ce);
    } catch (RemoteException ex) {
      log("Creation failed: " + ex);
    }
    return null;
  }

  /**
   * Returns the InitialContext
   */
  private static InitialContext getInitialContext(String url)
    throws NamingException
  {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, 
            "weblogic.jndi.WLInitialContextFactory");
    env.put(Context.PROVIDER_URL, url);
    return new InitialContext(env);
  }

  /**
   * Simple log method to spit messages to System.out
   * You may want to consider using WebLogic's log service
   */
  private static void log(String s) {
    System.out.println(s);
  }
}

/** 
 *  Utility class uses to create JMS objects and send a message to a queue.
 */
class QueueSend
{
  private InitialContext ictx;
  private String queueConnFactoryName;
  private String queueName;
  private QueueConnectionFactory qconFactory;
  private QueueConnection qcon;
  private QueueSession qsession;
  private QueueSender qsender;
  private Queue queue;
  private TextMessage msg;

  public QueueSend(InitialContext ictx, 
                   String queueConnFactoryName, String queueName) 
  {
    this.ictx = ictx;
    this.queueConnFactoryName = queueConnFactoryName;
    this.queueName = queueName;
  }

   /**
    * Create all the necessary objects for sending
    * messages to a JMS queue.
    *
    * @param ctx JNDI initial context.
    * @param queueName Name of queue.
    * @exception NamingException if operation cannot be performed
    * @exception JMSException if JMS fails to initialize due to internal error
    */
  public void init()
    throws NamingException, JMSException
  {
    qconFactory = (QueueConnectionFactory) ictx.lookup(queueConnFactoryName);
    qcon = qconFactory.createQueueConnection();
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (Queue) ictx.lookup(queueName);
    qsender = qsession.createSender(queue);
    msg = qsession.createTextMessage();
    qcon.start();
  }

  /**
   * Send a message to a JMS queue.
   *
   * @param message  Message to be sent.
   * @exception JMSException if JMS fails to send message due to internal error
   */
  public void send(String message)
    throws JMSException
  {
    msg.setText(message);
    qsender.send(msg);
  }

  /**
   * Close JMS objects.
   * @exception JMSException if JMS fails to close objects due to internal error
   */
  public void close()
    throws JMSException
  {
    qsender.close();
    qsession.close();
    qcon.close();
  }
}
