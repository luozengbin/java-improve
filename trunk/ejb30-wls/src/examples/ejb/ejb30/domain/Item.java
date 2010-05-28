/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/** 
 * Item, a Pure Old Java Object (POJO), is an abstract description of a thing  
 * that a {@link Reviewer} can review. 
 * Item is annotated as an Entity Java Bean by <code>@Entity</code> annotation,
 * but it does not require to extend any any framework classes or implement any 
 * particular interface according to EJB 3.0 Specification. <BR>
 * This class and its fields are annotated with O-R Mapping annotations
 * to describe the database schema that would contain the persisted state of
 * the instances. This class annotates the fields. A class can also employ
 * property based annotations by annotatating its getter methods. 
 * Alternatively, the O-R mapping can be configured via 
 * deployment descriptors. The deployment descriptor specification always
 * overrides any annotations.<BR> 
 * Item is also the root of an inheritance hierarchy for 
 * {@link examples.ejb.ejb30.domain.Book Book}, 
 * {@link examples.ejb.ejb30.domain.Music Music} and
 * {@link examples.ejb.ejb30.domain.Movie Movie}. The EJB 3.0
 * domain model allows an abstract (or even non-persistent) class to be
 * superclass of persistent domain classes. <BR>
 * The inheritance strategy i.e. how the subclasses be represnted in the
 * database table(s), are specified in this base class.<BR>
 * <BR>
 * Following is the class-level annotation.<BR>
 * <pre>
  @Entity
  @Table(name = "ITEM")
  @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
  @DiscriminatorColumn(name = "ITEM_TYPE",
  discriminatorType = DiscriminatorType.STRING,length = 8)
 * </pre>
 *  <code>@Entity</code> marks the Item class as a persistent entity.<BR>
 *  <code>@Table</code> specifies the relatonal database table name that 
 *  would store the records of Item and its subclasses.<BR>
 *  <code>@Inheritance</code> qualifies that the instances of all the classes
 *  derived from Item would be stored in a <code>SINGLE TABLE</code><BR>
 *  <code>@DiscriminatorColumn</code> is used to identify the database column 
 *  that will contain values to distinguish different subclasses as all of them 
 *  are stored in the same table. Here we use a column <code>ITEM_TYPE</code>
 *  of variable character length of 8.<BR>
 *  
 *  This class also uses annotation to descibe a simple named query in 
 *  Java Persistence Query Language (JPQL).<BR>
 *  <pre>
 *  @NamedQuery(name = "Item.findByTitle",
 *  query = "SELECT i FROM Item i WHERE i.title LIKE :title")
 *  </pre>
 * This named query finds Items whose name matches with the a given title. 
 * The parameter <code>:title</code> will be bound to this query at runtime.
 * The query names are globally scoped and hence we follow a naming convention
 * to avoid possible name clash by appending the class name.<code> 
 *
 */
@NamedQuery(name = "Item.findByTitle", 
		query = "SELECT i FROM Item i WHERE i.title LIKE :title")
@Entity
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ITEM_TYPE", discriminatorType = DiscriminatorType.STRING, length = 8)
public class Item
	implements Serializable
{
	/** 
	 * Unique identifier for each item. 
	 * The field is annotated as
	 * <pre>
	 @Id
	 @GeneratedValue
	 * </pre>
	 * <code>@Id</code> specifies that the field value would uniquely identify an
	 * instance.
	 * <code>@GeneratedValue</code> designates that the Persistence Runtime Provider
	 * would generate unique values.
	 */
	@Id
	@GeneratedValue
	private int id;

	/** 
	 * A title for this item.
	 * The field is annotated with <code>@Column</code> and would be stored in 
	 * database column named <code>TITLE</code> by default convention.
	 * 
	 */
	@Column
	private String title;

	/** Average rating of this item. 
	 *  This field is annotated as <code>@Transient</code> meaning that the value
	 *  of this field is not stored in the database. The rating of an Item
	 *  is computed by averaging ratings of all the reviews for this Item. This is
	 *  an example of transient field whose value depends on other field values.
	 * 
	 * @see #calculateRating() on how and <em>when</em> to compute this field value. 
	 */
	@Transient
	private float rating;

	/** 
	 * Refers to the {@link Artist Artist} who created this item. Based on concrete
	 * Item type, this field would imply a Author of Book, Singer for Music or 
	 * Director for Movie.<BR>
	 * An Item refers to one and only one Artist. Many Items can refer to the same
	 * Artist. Artist <em>does not</em> refer to the Item. Hence, this is an 
	 * example to unidirectional single-valued relationship.</BR>
	 * This relationship is annotated as 
	 * <pre>
	 @ManyToOne(cascade = CascadeType.PERSIST)
	 * </pre>
	 * The unidirectional single-valued relationship can be specified as either
	 * a unidirectional <code>@OneToOne</code> or as a unidirectional 
	 * <code>@ManyToOne</code> annotation. As there can be more than one item 
	 * created by the same Artist, we use a <code>@ManyToOne</code> annotation.
	 * <BR>
	 * The <code>cascade</code> qualifies that the linked Artist will be persisted
	 * when an Item is persisted.   
	 * 
	 * 
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@Column(name = "ARTIST")
	private Artist artist;

	/** 
	 * Refers to all the {@link Review reviews} on this item.
	 * An Item can have many {@link Review Reviewes}. Each Review refers to its
	 * Item. This is an example of bidirectional, one-to-many relationship.<BR>
	 * This relationship is annotated as 
	 * <pre>
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewed")
	 * </pre> 
	 * Item owns the relationship in the sense if an item is deleted so all its
	 * reviewes are deleted as well (but not vice versa).<BR>
	 * The annotation specifies the name of the field in the referred class 
	 * {@link Review Review} that holds the other terminal of this particular 
	 * relationship.
	 * Also a <code>@OrderBy("createdDate DESC")</code> annotation is used to sort
	 * the reviews by their date of creation. 
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewed")
	@OrderBy("createdDate DESC")
	private List<Review> reviews;


	/** 
   * Refers to all the {@link Reviewer reviewers} who have subsrcibed on reviews
   * on this item. An Item can have many subscribers. Each Reviewer can subscriber
   * to many Items. This is an example of bidirectional, many-to-many relationship.<BR>
   * This relationship is annotated as
   * <pre>
   *@ManyToMany(mappedBy = "watchedItems")
   * </pre>
	 * The annotation specifies the name of the field in the referred class
	 * {@link Reviewer Reviewer} that holds the other terminal of this particular
	 * relationship.
	 */
	@ManyToMany(mappedBy = "watchedItems")
  private List<Reviewer> watchers;

	/**
	 * A no-arg constructor is required for enhancement.
	 *
	 */
	protected Item ()
	{
		super ();
	}

	/** 
	 * The public constructor constructs with a title.
	 * 
	 * @param title the title of the item.
	 */
	public Item (String title)
	{
		super ();
		this.title = title;
	}

	/** 
	 * Gets the unique identifier of this receiver.
	 * There is no corresponding <code>setId()</code> method as the identifier
	 * value is generated by the Persistence Provider Runtime. 
	 * 
	 * @return unique identifier of this instance.
	 */
	public int getId ()
	{
		return id;
	}

	/** 
	 * Gets the title of this item.
	 * @return return the tile of the item.
	 */
	public String getTitle ()
	{
		return title;
	}

	/**
	 * Sets the title of this receiver.
	 * 
	 * @param title must not be null or empty.
	 */
	public void setTitle (String title)
	{
		if (title == null || title.trim ().length () == 0)
			throw new IllegalArgumentException (
					"null or empty title not allowed");
		this.title = title;
	}

	/** 
	 * Gets the average rating of this item. 
	 *  This is an example of transient property. Such property values are not
	 *  stored in the database.<BR>
	 *  There is no corresponding <code>setRating()</code> method as
	 *  rating is calculated by averaging the individual rating
	 *  of all reviews for this item. This computation can be done one the
	 *  review instances are loaded and hence carried out at a 
	 *  {@link #calculateRating() life cycle} event callback method.
	 *  
	 * 
	 * @return the average rating of all reviews for this item.
	 */
	public float getRating ()
	{
		return rating;
	}

	/** 
	 * Gets the artist who created this item.
	 * This is an example of unidirectional single-valued relationship.
	 * 
	 * @return the artist who created this item.
	 */
	public Artist getArtist ()
	{
		return artist;
	}

	/**
	 * Sets the artist who created this Item.
	 * 
	 * @param artist must not be null.
	 */
	public void setArtist (Artist artist)
	{
		if (artist == null)
			throw new IllegalArgumentException ("null Artist for " + this);

		this.artist = artist;
	}

	/** 
	 * Gets all the reviews on this item.
	 * 
	 * @see Review#getReviewed()
	 * 
	 * @return the list of reviews for this item. Can be null. 
	 */
	public List<Review> getReviews ()
	{
		return reviews;
	}

	/**
	 * Sets the reviews. The caller can modify the list of reviews only through
	 * {@link #addReview(Review) addReview()} method. 
	 * 
	 * @param reviews list of reviews for this receiver. 
	 */
	private void setReviews (List<Review> reviews)
	{
		this.reviews = reviews;
	}

	/** 
	 * Adds a review to this item. Updates the average rating on the item by 
	 * the rating of the new review.<BR>
	 * This is an example of a <em>business</em>
	 * method that modifies properties of the domainn instance.
	 * The EJB3.0 specification recommends <em>not<em> to use direct access 
	 * to field values within a <em>business</code> method but to access them 
	 * via the accessors. 
	 * 
	 * @param r review that would be added. 
	 * @return true if the given review has been added to this item. false
	 * otherwise. 
	 */
	public boolean addReview (Review r)
	{
		if (r == null)
			return false;
		if (!this.equals (r.getReviewed ()))
			return false;

		if (getReviews () == null)
			setReviews (new ArrayList<Review> ());

		int ratedBy = getReviews ().size ();
		rating = (rating * ratedBy + r.getRating ()) / ratedBy + 1;

		getReviews ().add (0, r);
		return true;
	}

	/** 
	 * Gets all the reviews on this item.
	 * 
	 * @see Review#getReviewed()
	 * 
	 * @return the list of reviews for this item. Can be null. 
	 */
	public List<Reviewer> getWatchers ()
	{
		return watchers;
	}

	/**
	 * Calculates the rating for this receiver by averaging the rating for each
	 * associated Reviews. <BR> 
	 * Callback methods in entity classes are called in different points in
	 * life cycle of a managed entity. This method is annotated with one such
	 * <code>@PostLoad</code> callback. This method will be invoked by the 
	 * Persistence Provider Runtime <em>after</em> the instance state is loaded 
	 * from the database.<BR>
	 * In this method, we calculate one property: the average
	 * rating. This is a transient property and is calculated as the
	 * average the ratings over all the reviews on this item.<BR>   
	 * This average can be calculated only when all the review ratings are 
	 * loaded. When value of a property depends on the values
	 * of some other properties, it should only be calculated when other
	 * properties have been loaded. As Persistence Provider Runtime can
	 * load the persistent properties at an unspecified order, these callback
	 * methods are the best place to warranty that the persistent properties
	 * of the instance have been loaded.  
	 * 
	 *
	 */
	@PostLoad
	public void calculateRating ()
	{
		if (getReviews () == null || getReviews ().isEmpty ())
		{
			return;
		}
		float totalRating = 0f;
		for (Review r : getReviews ())
			totalRating += r.getRating ();
		rating = totalRating / getReviews ().size ();
	}

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Item item = (Item) o;

    return id == item.id;
  }

  public int hashCode() {
    return id;
  }
}
