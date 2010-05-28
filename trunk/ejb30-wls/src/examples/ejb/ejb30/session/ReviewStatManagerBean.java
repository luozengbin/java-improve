/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.session;

import java.util.Set;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Local;

import examples.ejb.ejb30.domain.Item;
import examples.ejb.ejb30.domain.Review;
import examples.ejb.ejb30.domain.Reviewer;
import examples.ejb.ejb30.exceptions.NotLoggedInException;
import examples.ejb.ejb30.exceptions.ObjectNotFoundException;
import examples.ejb.ejb30.service.ReviewService;

/**
 * A stateful session bean maintains the number of added comments
 * and added new items within a session.
 * the local JNDI name of the session bean is ReviewStatManager sepcifided
 * by the mappedName annotation element.
 *
 * The methods defined ReviewStatManager interface declared some
 * unchecked Exception which are annotated as application Exception
 * (See @link examples.ejb.ejb30.exceptions.ObjectNotFoundException).
 *
 * Since there is no transaction attribute annotation declared explicitly
 * for business method, the default transaction attribute of Required
 * will be used for all business method.
 */
@Stateful(mappedName = "ReviewStatManager")
@Local(ReviewStatManager.class)
public class ReviewStatManagerBean implements ReviewStatManager {

  @EJB
  ReviewService _manager;
  private int _itemsAdded;
  private int _reviewsAdded;
  private Reviewer _reviwer;
  boolean _logged;

  public int getAddedItemCount() {
    return _itemsAdded;
  }

  public boolean login(String username, String password) {
    if (username == null || password == null)
      return false;
    Reviewer reviewer = _manager.getReviewer(username, false);
    if (reviewer == null)
      return false;
    if (password.equals(reviewer.getPassword())) {
      _reviwer = reviewer;
      _logged = true;
      return true;
    }
    _itemsAdded = 0;
    _reviewsAdded = 0;
    return false;
  }

  public void logOut() {
      _itemsAdded = 0;
      _reviewsAdded = 0;
      _reviwer = null;
      _logged = false;
  }

  public int getAddedReviewCount() {
    return _reviewsAdded;
  }

  public Item newItem(String itemType, String title, String artistName) {
    _itemsAdded++;
    return _manager.newItem(itemType, title, artistName);
  }

  public Review newReview(int itemId, int rating, String comment) {
    _reviewsAdded++;
    String reviewerName = _reviwer == null ? "guest" : _reviwer.getName();
    return _manager.newReview(reviewerName, itemId, rating, comment);
  }

  public List<Review> getFeededReviews() throws NotLoggedInException {
    makeSureLoggedIn();
    return _manager.getFeededReviews(_reviwer.getName());
  }

  public void removeFeededReview(int reviewid) throws NotLoggedInException {
    makeSureLoggedIn();
    _manager.removeFeededReview(_reviwer.getName(),reviewid);

  }

  public boolean isLogged() {
    return _logged;
  }

  public boolean subscribe(int itemId) throws NotLoggedInException {
    makeSureLoggedIn();
    return _manager.subscribe(_reviwer.getName(), itemId);
  }

  public boolean unSubscribe(int itemId) throws NotLoggedInException, ObjectNotFoundException {
    makeSureLoggedIn();
    return _manager.subscribe(_reviwer.getName(), itemId);
  }

  private void makeSureLoggedIn() throws NotLoggedInException {
    if (!_logged)
      throw new NotLoggedInException("you haven't logged in or your session has timed out, please log in! ");
  }

}
