/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.mdb;

import examples.ejb.ejb30.domain.Review;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.annotation.PreDestroy;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A Stateless session bean get invoked when a review is added. It will
 * push the review into a jms destination.
 * The review added event will be handled asynchronously.
 */
@Stateless
@Remote(ReviewListener.class)
public class ReviewListenerBean implements ReviewListener {
  //The JNDI name of the Queue Connection Factory referenced here is specified by the mappedName attribute of @Resource,
  //which can be overwritten in weblogic-ejb-jar.xml.
  @Resource(name = "jms/mdbQCF", mappedName = "weblogic.examples.ejb30.QueueConnectionFactory")
  private QueueConnectionFactory mdbQCF;
  //The JNDI name of the Queue  referenced here is specified by the mappedName attribute of @Resource,
  //which can be overwritten in weblogic-ejb-jar.xml.
  @Resource(name = "jms/mdbQueue", mappedName = "weblogic.examples.ejb30.ExampleQueue")
  private Queue mdbQueue;
  private QueueConnection qcon = null;
  private QueueSession qsession = null;
  private QueueSender qsender = null;

  @PostConstruct
  private void init() {
    try {
      qcon = mdbQCF.createQueueConnection();
      qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
      qsender = qsession.createSender(mdbQueue);
    } catch (JMSException e) {
      throw new RuntimeException(e);
    }
  }

  public void reviewAdded(Review review) {
    try {
      ObjectMessage msg = qsession.createObjectMessage();
      msg.setIntProperty("reviewid", review.getId());
      qcon.start();
      qsender.send(msg);
    } catch (JMSException e) {
      throw new RuntimeException(e);
    }
  }

  @PreDestroy
  void cleanup() {
    try {
      if (qsender != null)
        qsender.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      qsession.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      qcon.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
