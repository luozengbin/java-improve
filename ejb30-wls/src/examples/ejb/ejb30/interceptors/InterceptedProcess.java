/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.interceptors;

import examples.ejb.ejb30.domain.Book;
import examples.ejb.ejb30.exceptions.BadCommentsException;

/**
 * Business interface for ejb interceptor example Session Bean.
 */
public interface InterceptedProcess {

  /**
   * select a book randomly.
   * @return randomly selected book.
   */
  Book getRandomBook();

  /**
   * create and persist a review.
   * @param bookId  the unique identifier of ebook.
   * @param reviewerName th name of the reviewer.
   * @param rating  the rating of the review.
   * @param comments comments of the review.
   */
  void addReview(int bookId, String reviewerName, int rating, String comments);

}
