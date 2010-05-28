/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Review is a comment on an {@link Item} by a {@link Person}. Review is
 * an example of immutable entity, its properties can not be updated once
 * it has been created. That is why it has only accessor methods but no
 * mutator methods.<BR>
 * Review maintains two relationships: the item reviewed and the person who
 * reviewed it. 
 * 
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 *
 */

@Entity
@Table(name = "REVIEW")
public class Review implements Serializable
{
  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private int rating;

	/**
	 * Specify an explict column name because default column name in this
	 * case will be <code>'COMMENT'</code> which may be a reserved word
	 * for many databases.
	 */
	@Column(name="USER_COMMENT")
	private String comment;

	/*
	 * This is an example of @Temporal
	 * property annotation that specifies how a temporal value 
	 * (Date, Time, Timestamp) be stored in the database.
	 */
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	/*	
	 * This is an example of bidirectional one-to-many relationship.
	 * Review references a single instance of Reviewer.<BR>
	 * Reviewer references a collection of Review.<BR>
	 *
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "REVIEWER")
	private Reviewer reviewer;

	/*
	 * This is an example of bidirectional one-to-many relationship.
	 * Review references a single instance of Item.<BR>
	 * Item references a collection of Review.<BR>
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "REVIEWED")
	private Item reviewed;

	public static final int LOWEST_RATING = 0;

	public static final int HIGHEST_RATING = 5;

	/** 
	 * 
	 *
	 */
	public Review ()
	{

	}
  
	public int getId(){
		return id;
	}
	/** 
	 *  A review is created with a rating, the detailed text, who did the review
	 *  and on which item. All these properties can be accessed but not modified
	 *  once the instance has been constructed.
	 *  
	 * @param reviewer
	 * @param reviewed
	 * @param rating
	 * @param comment
	 */
	public Review (Reviewer reviewer, Item reviewed, int rating, String comment)
	{
		if (reviewer == null)
			throw new NullPointerException (
					"Can not create review with no reviewer");
		if (reviewed == null)
			throw new NullPointerException (
					"Can not create review with no reviewed item");
		if (rating < LOWEST_RATING || rating > HIGHEST_RATING)
			throw new IllegalArgumentException ("Wrong rating " + rating
					+ ". Must be between " + LOWEST_RATING + " to "
					+ HIGHEST_RATING);

		this.reviewer = reviewer;
		this.reviewed = reviewed;
		this.rating = rating;
		this.comment = comment;
		this.createdDate = new Date ();
	}

	public String getComment ()
	{
		return comment;
	}

	public int getRating ()
	{
		return rating;
	}

	/** 
	 * Gets the reviewer who {@link Reviewer#review(Item, int, String) created}
	 * this review.
	 * @see Reviewer#getReviews()
	 * 
	 * @return the reviwer has created this review.
	 */
	public Reviewer getReviewer ()
	{
		return reviewer;
	}

	/** 
	 * Gets the item reviewed.
	 * 
	 * @see Item#getReviews()
	 * 
	 * @return the item on which this review is placed on.
	 */
	public Item getReviewed ()
	{
		return reviewed;
	}

	/** 
	 * Gets the date when this review is created. 
	 * 
	 * @return return the date when this review is created.
	 */
	public Date getCreatedDate ()
	{
		return createdDate;
	}

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Review review = (Review) o;

    return id == review.id;

  }

  public int hashCode() {
    return id;
  }
}
