package examples.ejb20.message;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This class illustrates calling a Message-Driven bean and publishing
 * quotes on a topic.
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */

public class Client
{
  static private String TOPIC_NAME = "quotes";
  private String m_url;
  private Context m_context;
  private TopicConnection m_topicConnection;

  public Client(String url) throws Exception {
    m_url = url;
        
    try {
      // Create a context
      m_context = getInitialContext();
      
      // Create the connection and start it
      TopicConnectionFactory cf =
        (TopicConnectionFactory) m_context.lookup("ExampleConnectionFactory");
      m_topicConnection = cf.createTopicConnection();
      m_topicConnection.start();
    }
    catch(Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

  /**
   * Runs this example from the command line. Example:
   * <p>
   * <tt>java examples.ejb20.message.Client "t3://localhost:7001"</tt>
   * <p>
   * The parameters are optional, but if any are supplied,
   * they are interpreted in this order:
   * <p>
   * @param args               URL such as "t3://localhost:7001" of Server
   */
  public static void main(String[] args) throws Exception {
    log("\nBeginning message.Client...\n");
    String url       = "t3://localhost:7001";
    Client client = null;

    // Parse the argument list
//     if (args.length != 1) {
//      log("Usage: java examples.ejb20.message.Client t3://hostname:port");
//      return;
//    } else {
//      url = args[0];
//    }

    try {
      client = new Client(url);
      client.example();
	  log("Success!  created topic: " + TOPIC_NAME + " and published a message.");
    } catch (NamingException ne) {
      log("Unable to look up the beans home: " + ne.getMessage());
      throw ne;
    } catch (Exception e) {
      log("There was an exception while creating and using the Trader.");
      log("This indicates that there was a problem communicating with the server: "+e);
      throw e;
    } 

    log("\nEnd message.Client...\n");
  }

  /**
   * Runs this example.
   */
  public void example() throws RemoteException, JMSException, NamingException {
    Topic newTopic = null;
    TopicSession session = null;
    try {
      session =
        m_topicConnection.createTopicSession(false,   // non transacted
                                             Session.AUTO_ACKNOWLEDGE);
      
      newTopic = (Topic) m_context.lookup(TOPIC_NAME);
    }
    catch(NamingException ex) {
      newTopic = session.createTopic(TOPIC_NAME);
      m_context.bind(TOPIC_NAME, newTopic);
    }

    TopicPublisher sender = session.createPublisher(newTopic);
    TextMessage tm = session.createTextMessage();
    String[] quotes = new String[] {
      "BEAS 40 1/8", "SUNW 79 1/2", "IBM 82 1/4"
    };
    for (int i = 0; i < quotes.length; i++) {
      tm.setText(quotes[i]);
      sender.publish(tm);
    }
  }

  /**
   * Using a Properties object will work on JDK 1.1.x and Java2
   * clients
   */
  private Context getInitialContext() throws NamingException {
    
    try {
      // Get an InitialContext
      Hashtable h = new Hashtable();
      h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
      h.put(Context.PROVIDER_URL, m_url);
      return new InitialContext(h);
    }
    catch (NamingException ne) {
      log("We were unable to get a connection to the WebLogic server at "+m_url);
      log("Please make sure that the server is running.");
      throw ne;
    }
  }

  private static void log(String s) { System.out.println(s); }
}
