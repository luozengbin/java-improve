/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.mdb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * MDB asynchronously feeds the new added reviews to subscribers.
 *
 */

@MessageDriven
    (mappedName = "weblogic.examples.ejb30.ExampleQueue",
        name = "WatchProcessMDB",
        activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
            }
    )
public class WatchProcessMDB implements MessageListener {
  @PersistenceContext(unitName = "reviewSession")
  private EntityManager em;
  private SubscriptionService service;

  @PostConstruct
  private void init(){
    service = new SubscriptionService();
    // inject the EntityManager;
    service.injectEntityManager(em);
  }
  // 
  @TransactionAttribute(value=javax.ejb.TransactionAttributeType.REQUIRED)
  public void onMessage(Message msg) {
    int reviewid = -1;
    try {
      reviewid = msg.getIntProperty("reviewid");
    } catch (JMSException e) {
      throw new RuntimeException(e);
    }
    service.pushWatchedReview(reviewid);
  }

}
