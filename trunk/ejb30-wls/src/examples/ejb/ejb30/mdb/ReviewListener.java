/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.mdb;

import examples.ejb.ejb30.domain.Review;
/**
 *
 * The listener interface for receiving review added event.
 *
 */
public interface ReviewListener {
  /**
   * invoked when a review is added.
   * @param review
   */
  public void reviewAdded(Review review);
}
