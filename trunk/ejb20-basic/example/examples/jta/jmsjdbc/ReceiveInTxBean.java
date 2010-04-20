package examples.jta.jmsjdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;
import javax.sql.DataSource;

import org.apache.commons.lang.math.RandomUtils;

/**
 * ReceiveInTxBean is a stateless session bean using bean-managed 
 * transaction. This EJBean illustrates distributed transactions 
 * and two phase commit across two XA resources: JMS queue and 
 * a Pointbase database. It begins a distributed transaction, receives 
 * messages from the JMS queue, updates the database, and then commits
 * the distributed transaction.
 * 
 * @author Copyright (c) 1999-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class ReceiveInTxBean implements SessionBean {

  private static final boolean VERBOSE = true;
  private SessionContext ctx;

  // You might also consider using WebLogic's log service
  private void log(String s) {
    if (VERBOSE) System.out.println(s);
  } 

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   */
  public void ejbActivate() {
    log("ejbActivate called");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   */
  public void ejbPassivate() {
    log("ejbPassivate called");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   */
  public void ejbCreate() throws CreateException {
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   */
  public void ejbRemove() {
    log("ejbRemove called");
  }

  /**
   * Sets the session context.
   *
   * @param ctx               SessionContext Context for session
   */
  public void setSessionContext(SessionContext ctx) {
    log("setSessionContext called");
    this.ctx = ctx;
  }

  /**
   * This method implements the receiveMessages method in the ReceiveInTx
   * remote interface.
   *
   * This method begins a distributed transaction, receives messages from 
   * the JMS queue, updates the database, and then commit the distributed 
   * transaction.
   */
  public void receiveMessages() {
    QueueConnection qcon = null;
    QueueSession qsession = null;
    QueueReceiver qreceiver = null;
    Connection jcon = null;

    try {

      Context ictx = new InitialContext();

      Context env = (Context) ictx.lookup("java:comp/env");
      String queueConnFactoryName = (String)env.lookup("queueConnFactoryName");
      String queueName = (String)env.lookup("queueName");
      String xaDataSrcName = (String)env.lookup("xaDataSrcName");
      String tableName = (String)env.lookup("tableName");

      QueueConnectionFactory qconFactory = 
        (QueueConnectionFactory) ictx.lookup(queueConnFactoryName);
      qcon = qconFactory.createQueueConnection();
      qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
      Queue queue = (Queue) ictx.lookup(queueName);
      qreceiver = qsession.createReceiver(queue);
      qcon.start();

      DataSource xads = (DataSource) ictx.lookup(xaDataSrcName);

      UserTransaction utx = ctx.getUserTransaction();
      utx.begin();
      log("TRANSACTION BEGUN");
      String msgText = null;
      do {
        msgText = queueReceive(qreceiver);
        if (msgText.equalsIgnoreCase("quit")) {
          utx.commit();
          log("TRANSACTION COMMITTED");
        } else {
          updateDatabase(xads, tableName, msgText);
        }
      } while (msgText != null && !msgText.equals("quit"));

    } catch (javax.naming.NamingException nex) {
      log("Naming exception: " + nex);      
    } catch (javax.jms.JMSException jex) {
      log("JMS exception: " + jex);      
    } catch (javax.transaction.NotSupportedException nse) {
      log("TRANSACTION COULD NOT BEGIN DUE TO: " + nse);      
    } catch (javax.transaction.RollbackException rbe) {
      log("TRANSACTION ROLLED BACK DUE TO: " + rbe);
    } catch (javax.transaction.HeuristicRollbackException hre) {
      log("TRANSACTION ROLLED BACK DUE TO: " + hre);
    } catch (javax.transaction.HeuristicMixedException hme) {
      log("TRANSACTION ROLLED BACK DUE TO: " + hme);
    } catch (javax.transaction.SystemException se) {
      log("TRANSACTION EXCEPTION: " + se);
    } finally {
      if (qreceiver != null) {
        try { qreceiver.close(); } catch (JMSException ex) {}
      }
      if (qsession != null) {
        try { qsession.close(); } catch (JMSException ex) {}
      }
      if (qcon != null) {
        try { qcon.close(); } catch (JMSException ex) {}
      }
    }
  }

  /**
   * Receives the message, converts it to a string, and returns it
   */
  private String queueReceive(QueueReceiver qr) {
    String msgText = null;
    try {
      Message msg = qr.receive();
      if (msg != null) {
        if (msg instanceof TextMessage) {
          msgText = ((TextMessage)msg).getText();
        } else {
          msgText = msg.toString();
        }
        log("Message Received: " + msgText);
      }        
    } catch (JMSException jmse) {
      log("Error receiving JMS message: " + jmse);
    }
    return msgText;
  }

  /**
   * Adds String data to String tableName
   */
  private void updateDatabase(DataSource xads, String tableName, String data) 
  {
    Connection jcon = null;
    PreparedStatement stmt = null;
    try {
      jcon = xads.getConnection();
      String sql = "insert into " + tableName + " (id, data) values (?, ?)";
      stmt = jcon.prepareStatement(sql);
      stmt.setInt(1, RandomUtils.nextInt());
      stmt.setString(2, data);
      stmt.executeUpdate();
    } catch (SQLException ex) {
      log("Cannot update database:" + data);
    } finally {
      if (stmt != null) {
        try { stmt.close(); } catch (SQLException ex) {}
      }
      if (jcon != null) {
        try { jcon.close(); } catch (SQLException ex) {}
      }
    }
  }
}
