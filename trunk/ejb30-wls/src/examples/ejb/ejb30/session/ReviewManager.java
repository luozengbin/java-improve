/**
 * 
 */
package examples.ejb.ejb30.session;

import java.util.List;

import javax.persistence.EntityManager;

import examples.ejb.ejb30.domain.*;

/**
 * Business interface to operate a service that adds reviews to items.
 * 
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 *
 */
public interface ReviewManager
{

	/** 
	 * Gets the item of given type by its unique identifier.
	 * 
	 * @param <T>
	 * @param itemType class of a concrete item type.
	 * @param uid unique identifier
	 * @return
	 */
	<T extends Item> T getItem (Class<T> itemType, int uid);

	/** 
	 * Gets the item of given type by its title.
	 * Expects the title to be unique. If the title is non-unique use
	 * @link #getItemsByTitle(Class, String)
	 * 
	 * @param <T>
	 * @param itemType class of a concrete item type.
	 * @param title title of the item.
	 * @return
	 */
	<T extends Item> T getItemByTitle (Class<T> itemType, String title);

	/** 
	 * Gets any item of by its title.
	 * Expects the title to be unique. If the title is non-unique use
	 * @link #getItemsByTitle(String)
	 * 
	 * @param <T>
	 * @param title
	 * @return
	 */
	Item getItemByTitle (String title);

	/** 
	 * Gets zero or more item(s) of given type by the given title.
	 * 
	 * @param <T>
	 * @param itemType
	 * @param title
	 * @return
	 */
	<T extends Item> List<T> getItemsByTitle (Class<T> itemType, String title);

	/** 
	 * Gets item of any type of given title.
	 * 
	 * @param title
	 * @return
	 */
	List<Item> getItemsByTitle (String title);

	/** 
	 * Gets the person of given type by its name.
	 * 
	 * @param name
	 * @return
	 */
	<T extends Person> T getPerson (Class<T> personType, String name);

	/** 
	 * Gets a reviewer by its name.
	 * 
	 * @param name
	 * @return
	 */
	Reviewer getReviewer (String name);

	Reviewer getOrCreateReviewer (String name);

	/** Gets entire extent of the given type.
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 */
	<T> List<T> getAll (Class<T> c);

	List getAllByType (String queryClass);

	/** 
	 * Create a new review on behalf of the given reviewer on the item of the
	 *  given title.
	 * 
	 * @param reviewer
	 * @param title
	 * @param rating
	 * @param comment
	 * @return
	 */
	Review newReview (Reviewer reviewer, Item item, int rating, String comment);

	Review newReview (String reviewerName, int itemId,
			int rating, String comment);

	Artist newArtist (String name);

	Item newItem (String itemType, String title, String artistName);

	List select (String jpql, String[] params, Object[] values);
}
