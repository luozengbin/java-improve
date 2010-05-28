/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

/**
 * Reviewer is a person who reviews items.
 *
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 */
@NamedQuery(name = "Review.findReviewedItems",
    query = "SELECT i "
        + "FROM Item i, IN(i.reviews) r "
        + "WHERE r.reviewer.name=:name")
@Entity
@Table(name = "REVIEWER")
public class Reviewer
    extends Person {
  /**
   * This is an example of enumerated field. The
   * <code>@Enumerated</code> annotation specifies that the
   * {@link Person.Gender gender} holds an enumerated value.
   * The database stores a stringified value for the enumerated constant.
   */
  @Column(name = "GENDER")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String password;

  /*
    * This is an example of bidirectional one-to-many relationship.
    * Reviewer references to collection of Review instances.<BR>
    * Review references a single instance of Reviewer.<BR>
    * Reviewer owns the relationship in the sense if a Reviewer is deleted then
    * all its reviewes are deleted as well (but not vice versa).<BR>
    * The annotation specifies the name of the field in the referred class
    * {@link Review Review} that holds the reference to
    * this particular relationship.
    *
    */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewer")
  @OrderBy("createdDate DESC")
  private List<Review> reviews;

  /*
    *@This is an example of bidirectional many-to-many relationship.
    * Reviewer references to a collection of Review instances.<BR>
    * Review references to a collection of Review instances.<BR>
    * {@link Review Review}<BR>
    */
  @ManyToMany
  private Set<Item> watchedItems;

  /*
    *@This is an example of bidirectional many-to-many relationship.
    * Reviewer references to a collection of Review instances.<BR>
    * Review references to a collection of Review instances.
    * {@link Review Review}<BR>
    */
  @ManyToMany
  @OrderBy("createdDate DESC")
  private List<Review> watchedReviews;

  public Reviewer() {
    super();
  }

  public Reviewer(String name, Person.Gender gender) {
    super(name);
    this.password = name;
    this.gender = gender;
  }

  public Gender getGender() {

    return gender;
  }

  public void setGender(Gender g) {
    gender = g;
  }

  /**
   * Gets the reviews made by this reviewer.
   *
   * @return the list of reviews by this reviewer. Can be null.
   * @see Review#getReviewer()
   */
  public List<Review> getReviews() {
    return reviews;
  }

  /**
   * Gets the items subscribed by this reviewer.
   *
   * @return the list of subscribe by this reviewer. Can be null.
   * @see examples.ejb.ejb30.domain.Item#getWatchers()
   */
  public Set<Item> getWatchedItems() {
    return watchedItems;
  }

  /**
   * Gets the reviews feeded to this reviewer.
   *
   * @return the list of subscribe by this reviewer. Can be null.
   */
  public List<Review> getWatchedReviews() {
    return watchedReviews;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Creates a Review for the given item.
   *
   * @param item    the item being reviewed. Must not be null.
   * @param rating  denotes the number in a 10-point scale.
   * @param comment is the actual review comment
   * @return the newly created Review.
   */
  public Review review(Item item, int rating, String comment) {
    if (item == null)
      throw new IllegalArgumentException(this
          + " can not review null item");

    Review review = new Review(this, item, rating, comment);
    List<Review> reviews = getReviews();
    if (reviews == null)
      reviews = new ArrayList<Review>();
    reviews.add(review);
    item.addReview(review);

    return review;
  }

  /**
   * Creates a Subscribe for the given item.
   *
   * @param item the item being Subscribed. Must not be null.
   * @return return true if the item hasn't watched before by this reviewer.
   */
  public boolean watch(Item item) {
    if (item == null)
      throw new IllegalArgumentException(this
          + " can not subscribe null item");

    if (getWatchedItems() == null) {
      watchedItems = new HashSet<Item>();
    }
    if (!getWatchedItems().contains(item)) {
      getWatchedItems().add(item);
      return true;
    }
    return false;
  }

  /**
   * Feed a review into this reviewer.
   *
   * @param  review the review being feeded. Must not be null.
   */
  public void feedReview(Review review) {
    if (review == null)
      throw new IllegalArgumentException(this
          + " can not subscribe null item");

    if (getWatchedReviews() == null) {
      this.watchedReviews = new ArrayList<Review>();
    }
    if (!getWatchedReviews().contains(review)) {
      getWatchedReviews().add(review);
    }
  }
}
