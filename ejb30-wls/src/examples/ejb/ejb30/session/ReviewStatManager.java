/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.session;

import examples.ejb.ejb30.domain.Review;
import examples.ejb.ejb30.domain.Item;
import examples.ejb.ejb30.domain.Artist;
import examples.ejb.ejb30.exceptions.ObjectExistedException;
import examples.ejb.ejb30.exceptions.NotLoggedInException;
import examples.ejb.ejb30.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Set;

/**
 * The business interface for Stateful Session Bean ReviewStatManagerBean.
 */
public interface ReviewStatManager{

  /**
   * Gets the reviews added.
   * @return
   */
  public int getAddedReviewCount();

  /**
   * Gets the items added.
   * @return the number of items has been added.
   */
  public int getAddedItemCount();

  /**
   *
   * @param username  the name of the reviwer.
   * @param password  the password of the reviewer.
   * @return  return true when the a reviewer with the specified username and password.
   */
  public boolean login(String username, String password);

  /**
   * log out.
   */
  public void logOut();

  /**
   * Add a review on a specified Item.
   * @param itemId  the unique identifier of the item.
   * @param rating  the rating.
   * @param comment
   * @return  a newly-created and detached review.
   * @throws ObjectNotFoundException
   */
  public Review newReview(int itemId, int rating, String comment) throws ObjectNotFoundException;

  /**
   * Create and persitent a new Item.
   * @param itemType  the type of item. valid option are "Book","Music","Movie"
   * @param title the title of the item.
   * @param artistName  the name of the creator.
   * @return  a newly-created and detached Item.
   * @throws ObjectExistedException
   */
  public Item newItem(String itemType, String title, String artistName) throws ObjectExistedException;

  /**
   * Return a list of reviews for items which are watched the logged in user.
   * @return
   * @throws NotLoggedInException
   */
  public List<Review> getFeededReviews() throws NotLoggedInException;

  /**
   * Remove a certain review from the watched list.
   * @param reviewid
   * @throws NotLoggedInException
   * @throws ObjectNotFoundException
   */
  public void removeFeededReview(int reviewid) throws NotLoggedInException,ObjectNotFoundException;

  /**
   * whether user has been logged in.
   * @return
   */
  public boolean isLogged();

  /**
   *  subscribe to new reviews on item with the given itemId.
   * @param itemId
   * @return
   * @throws NotLoggedInException user has to login before subscribe to new reviews on certain item.
   * @throws ObjectNotFoundException  Item with the given itemId is not existed.
   */
  public boolean subscribe(int itemId) throws NotLoggedInException,ObjectNotFoundException;

  /**
   *  subscribe to new reviews on item with the given itemId.
   * @param itemId
   * @return
   * @throws NotLoggedInException user has to login before subscribe to new reviews on certain item.
   * @throws ObjectNotFoundException  Item with the given itemId is not existed.
   */
  public boolean unSubscribe(int itemId) throws NotLoggedInException,ObjectNotFoundException;

}
