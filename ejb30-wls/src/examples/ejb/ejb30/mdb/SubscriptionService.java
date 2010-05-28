/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.mdb;

import examples.ejb.ejb30.service.PersistenceService;
import examples.ejb.ejb30.domain.Review;
import examples.ejb.ejb30.domain.Reviewer;
import examples.ejb.ejb30.exceptions.ObjectNotFoundException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import java.util.List;

/**
 *  A POJO with the enjected EntityManager feeds new reivews into subscribers.
 */                       
public class SubscriptionService extends PersistenceService {
  public SubscriptionService() {
    super();
  }


  public SubscriptionService(EntityManagerFactory emf) {
    super(emf);
  }

  public void pushWatchedReview(int reviewid) {
    EntityManager entityManager = getEntityManager();
    beginTransaction();
    Review review = entityManager.find(Review.class, reviewid);
    if (review == null)
      throw new ObjectNotFoundException("no review with id" + reviewid);
    List<Reviewer> watchers = review.getReviewed().getWatchers();
    for (Reviewer watcher : watchers) {
      watcher.feedReview(review);
    }
    commit();
  }



}
