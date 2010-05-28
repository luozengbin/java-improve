/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.service;

import examples.ejb.ejb30.domain.*;

import java.util.List;
import java.util.Set;

/**
 * Business interface to operate a service that adds reviews to items.
 *
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>

 */
public interface ReviewService {
  /**
   * Gets entire extent of the given type.
   * @param c
   * @return  a list of object of give type.
   */
  <T> List<T> getAll(Class<T> c);

  /**
   * Gets a rang of object of the item.
   * @param queryClass  the type of item.
   * @param startPosition the start position of the first result, numbered from 0.
   * @param maxResult the maximum number of results to retrieve.
   * @return a rang of object of the given type.
   */
  <T extends Item> List<T> getItemsByType(String queryClass, int startPosition, int maxResult);

  /**
   * Gets the item of given type by its unique identifier.
   *
   * @param itemType item type
   * @param uid unique identifier.
   * @return  the item with the given identifier.
   */
  <T extends Item> T getItem(Class<T> itemType, int uid);

  /**
   * Gets the item of given type by its title.
   * Expects the title to be unique. If the title is non-unique use
   *
   * @param itemType
   * @param title
   * @return
   * @link #getItemsByTitle(Class, String)
   */

  <T extends Item> T getItemByTitle(Class<T> itemType, String title);

  /**
   * Gets zero or more item(s) of given type by the given title.
   *
   * @param <T>
   * @param itemType
   * @param title
   * @return zero or more item(s) of given type with the given title
   */
  <T extends Item> List<T> getItemsByTitle(Class<T> itemType,
                                           String title);

  /**
   * Gets the person of given type by its name.
   *
   * @param name  the person name.
   * @return  Gets the person of given type with given name.
   */
  <T extends Person> T getPerson(Class<T> personType, String name);

  /**
   * Gets a reviewer by its name.
   *
   * @param name
   * @param createNewIfNotFound
   * @return
   */
  Reviewer getReviewer(String name, boolean createNewIfNotFound);

  /**
   *
   * @param itemId
   * @return
   */
  List<Review> getReviewsByItem(int itemId);

  /**
   *
   * @param reviewerName
   * @return
   */
  List<Review> getReviewsByReviewer(String reviewerName);
  /**
   * A generic method to query objects matching the query criteria.
   * @param jpql  a query statement in the Java Persistence query language.
   * @param params the parameter name in the query statement.
   * @param values the bound value for the parameter.
   * @return  a list of objects.
   */
  public List select(String jpql, String[] params, Object[] values);

  /**
   * Find all titles of the given the ItemType matching the given title.
   * @param title parital item title, supporting underscore (_) which stands for any single,
   *  character and percent (%) character which stands for any sequence of characters
   * (including the empty sequence),'.
   * @param itemType supported item type including "Book,Movie,Music,Item"
   * @return  a unique list of item titles.
   */
  public List<String> findItemTitles(String title,String itemType);

  /**
   * Find all names of author who has authored the given the ItemType matching the given name.
   * @param name parital author name, supporting underscore (_) which stands for any single,
   *  character and percent (%) character which stands for any sequence of characters
   * (including the empty sequence),'.
   * @param itemType  the item type.
   * @return  a unique list of authors' name.
   */
  public List<String> findAuthorNames(String name,String itemType);

  /**
   * Find all Items of the given the ItemType and its author's name matches the given name.
   * @param name parital author name, supporting underscore (_) which stands for any single,
   *  character and percent (%) character which stands for any sequence of characters
   * (including the empty sequence),'.
   * @param itemType  the item type.
   * @param startPosition  the start position of the first result, numbered from 0.
   * @param maxResult the maximum number of results to retrieve.
   * @return  a list of Items.
   */
  public List<Item> queryItemsByAuthor(String name, String itemType, int startPosition, int maxResult);

  /**
   * Return a range of Items of the given itemType matching the partial title.
   * @param title parital item title, supporting underscore (_) which stands for any single,
   *  character and percent (%) character which stands for any sequence of characters
   * (including the empty sequence),'.
   * @param itemType  the item type.
   * @param startPosition  the start position of the first result, numbered from 0.
   * @param maxResult the maximum number of results to retrieve.
   * @return a list of Items.
   */
  public List<Item> queryItemsByTitle(String title, String itemType, int startPosition, int maxResult);


  /**
   * Create a new review on behalf of the given reviewer on the item of the
   * given title.
   *
   * @param reviewer
   * @param item
   * @param rating
   * @param comment
   * @return
   */
  Review newReview(Reviewer reviewer, Item item, int rating,
                   String comment);

  /**
   *
   * @param reviewerName
   * @param itemId
   * @param rating
   * @param comment
   * @return
   */
  Review newReview(String reviewerName, int itemId,
                   int rating, String comment);

  /**
   * Return a newly persisted Artist.
   * @param name the name of the Artist.
   * @return a newly persisted Artist.
   */
  Artist newArtist(String name);

  /**
   * Return a newly persisted item.
   * @param itemType the type of item.
   * @param title the tile of the item.
   * @param artistName the creator of the item.
   * @return a newly persisted item.
   */
  Item newItem(String itemType, String title, String artistName);



  //-- methods for subscribition management--

    /**
   * Reviewer subscribe the reviews on given item.
   * New reviews on the given item will push to the reviewer's subscribed review list.
   * @param reviewerName the name of Reviewer who wants to subscribe to reviews on specified item.
   * @param itemId  the unique identifier of item.
   * @return true when subscribe successfully
   */
  boolean subscribe(String reviewerName, int itemId);

  /**
   * Reviewer unsubscribe the reviews on given item.
   * New reviews on the given item will not push to the reviewer's subscribed review list.
   * @param reviewerName the name of Reviewer who wants to unsubscribe to reviews on specified item.
   * @param itemId  the unique identifier of item.
   * @return true when unsubscribe successfully. false when the given item has not been subscribed by the reviewer.
   */
  boolean unSubscribe(String reviewerName, int itemId);

  /**
   * Get the list of items watched by the reviewer.
   * @param reviewerid  the unique identifier of reviewer.
   * @return a list of subscribed items.
   */
  Set<Item> getWatchedItems(String reviewerid);

  /**
   * Get the list of reviews feeded for the reviewer.
   * @param reviewerid  the unique identifier of reviewer.
   * @return a list of feeded reviews.
   */
  List<Review> getFeededReviews(String reviewerid);

  /**
   * Remove a review from the subscribed review list of a given reviewer.
   * @param reviewerid the unique identifier of reivewer.
   * @param reviewid   the unique identifier of review.
   * @return true when the delete successfully.
   */
  boolean removeFeededReview(String reviewerid,int reviewid);
}
