package examples.ejb20.message;

import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Demonstrates usage of a Message-Driven EJB.
 *
 * @author Copyright (c) 1999-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class MessageTraderBean implements MessageDrivenBean, MessageListener {

  private static final boolean VERBOSE = true;
  private MessageDrivenContext m_context;
  private int m_tradeLimit;

  /**
   * Sets the session context.
   *
   * @param ctx               MessageDrivenContext Context for session
   */
  public void setMessageDrivenContext(MessageDrivenContext ctx) {
    m_context = ctx;
  }

  /**
   * Retrieve the int value of the TextMessage and
   * increment the RMI counter by that much.
   */
  public void onMessage(Message msg) {
    TextMessage tm = (TextMessage) msg;
    try {
      String text = tm.getText();
      log("Received new quote : " + text);
    }
    catch(JMSException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   */
  public void ejbCreate() {
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   */
  public void ejbRemove() {
    log("ejbRemove called");
  }

  static void p(String s) {
    System.out.println("*** <MessageTraderBean> " + s);
  }

  private void log(String s) {
    if (VERBOSE) System.out.println(s);
  }
}


