/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Entity;

import examples.ejb.ejb30.domain.*;
import examples.ejb.ejb30.exceptions.ObjectExistedException;
import examples.ejb.ejb30.exceptions.ObjectNotFoundException;
import examples.ejb.ejb30.mdb.ReviewListener;

/**
 * A service class that operates on the {@link examples.ejb.ejb30.domain domain
 * model}. A persistent domain model can be directly worked on with the
 * {@link javax.persistence.EntityManager  EntityManager} interface. However,
 * this receiver provides a stateless service_ to operate on the persistent
 * domain objects. <BR>
 * This service_ closely resembles what a Stateless Session Bean that operates on
 * the persistent domain model.
 *
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 */
public class ReviewServiceImpl extends PersistenceService implements ReviewService {

  private CopyOnWriteArrayList<ReviewListener> listerners = new CopyOnWriteArrayList<ReviewListener>();

  public ReviewServiceImpl() {
  }

  /**
   * Construct a service from the given EntityManagerFactory.
   *
   * @param emf
   */
  public ReviewServiceImpl(EntityManagerFactory emf) {
    super(emf);
  }

  public <T extends Item> T getItem(Class<T> itemType, int uid) {
    EntityManager em = getEntityManager();
    return em.find(itemType, uid);
  }

  public <T extends Item> T getItemByTitle(Class<T> itemType, String title) {
    EntityManager em = getEntityManager();
    return (T) em.createNamedQuery("Item.findByTitle").setParameter("title",
        title).getSingleResult();
  }

  public <T extends Item> List<T> getItemsByTitle(Class<T> itemType,
                                                  String title) {
    EntityManager em = getEntityManager();
    return (List<T>) em.createNamedQuery("Item.findByTitle").setParameter(
        "title", title).getResultList();
  }


  public <T extends Item> List<T> getItemsByType(String queryClass, int first, int max) {
    EntityManager em = getEntityManager();
    Query query = em.createQuery("SELECT i FROM " + queryClass + " i order by i.id" );
    if(first>=0)
      query.setFirstResult(first);
    if(max>0)
      query.setMaxResults(max);
    return query.getResultList();
  }

  public <T extends Person> T getPerson(Class<T> personType, String name) {
    EntityManager em = getEntityManager();
    return em.find(personType, name);
  }

  public Reviewer getReviewer(String name, boolean createNewIfNotFound) {
    EntityManager em = getEntityManager();
    Reviewer reviewer = em.find(Reviewer.class, name);
    if (reviewer == null &&createNewIfNotFound) {
      beginTransaction();
      reviewer = new Reviewer(name, Person.Gender.MALE);
      em.persist(reviewer);
      commit();
    }
    return reviewer;
  }

  public List<Review> getReviewsByItem(int itemId) {
    EntityManager em = getEntityManager();
    Item item = em.find(Item.class, itemId);
    if (item == null)
      throw new ObjectNotFoundException("No Item found with ID " + itemId);
    List<Review> reviews = item.getReviews();
    for (Review review : reviews) {
      review.getReviewed();
      review.getReviewer();
    }
    return reviews;
  }

  public List<Review> getReviewsByReviewer(String reviewerId) {
    EntityManager em = getEntityManager();
    Reviewer reviewer = em.find(Reviewer.class, reviewerId);
    if (reviewer == null)
      throw new ObjectNotFoundException("No Item found with ID " + reviewerId);
    List<Review> reviews = reviewer.getReviews();
    for (Review review : reviews) {
      review.getReviewed();
      review.getReviewer();
    }
    return reviews;
  }

  public <T> List<T> getAll(Class<T> c) {
    EntityManager em = getEntityManager();
    String queryClass = "";
    if (c == null ||!c.isAnnotationPresent(Entity.class))
      throw new RuntimeException("unknown-type");
    queryClass = c.getName().substring(c.getName().lastIndexOf(".")+1);
    return (List<T>) em.createQuery("SELECT i FROM " + queryClass + " i")
        .getResultList();
  }


  public Review newReview(Reviewer reviewer, Item item, int rating,
                          String comment) {
    EntityManager em = getEntityManager();
    beginTransaction();
    item = em.merge(item);
    reviewer = em.merge(reviewer);
    Review review = reviewer.review(item, rating, comment);
    em.persist(review);
    commit();
    for (ReviewListener listener : listerners) {
       listener.reviewAdded(review);
    }
    return review;
  }


  public Review newReview(String reviewerName, int itemId,
                          int rating, String comment) {
    EntityManager em = getEntityManager();
    Item item = em.find(Item.class, itemId);
    if (item == null)
      throw new ObjectNotFoundException("No item with id " + itemId);
    Reviewer reviewer = em.find(Reviewer.class, reviewerName);
    if (reviewer == null)
      throw new ObjectNotFoundException(reviewerName + " is not registered");
    return newReview(reviewer, item, rating, comment);
  }

  public Artist newArtist(String name) {
    if (name == null || name.trim().length() == 0)
      throw new RuntimeException("[" + name + "] can not be an Artist's name");
    EntityManager em = getEntityManager();
    beginTransaction();
    Artist artist = getPerson(Artist.class, name);
    if (artist == null) {
      artist = new Artist(name);
      em.persist(artist);
      commit();
    } else {
      rollback();
      throw new ObjectExistedException("Artist named [" + name + "] exists");
    }
    return artist;
  }

  public Item newItem(String itemType, String title, String artistName) {
    EntityManager em = getEntityManager();
    beginTransaction();
    Item item = null;
    if ("Book".equals(itemType))
      item = new Book(title);
    else if ("Music".equals(itemType))
      item = new Music(title);
    else if ("Movie".equals(itemType))
      item = new Movie(title);
    else
      throw new RuntimeException("Unrecognized item type " + itemType);
    Artist artist = getPerson(Artist.class, artistName);
    if (artist == null)
      artist = new Artist(artistName);
    item.setArtist(artist);
    em.persist(item);
    commit();

    return item;

  }

  public boolean subscribe(String reviewerName, int itemId) {
    EntityManager em = getEntityManager();
    beginTransaction();
    Item item = em.find(Item.class, itemId);
    if (item == null)
      throw new ObjectNotFoundException("No item with id " + itemId);
    Reviewer reviewer = em.find(Reviewer.class, reviewerName);
    if (reviewer == null)
      throw new ObjectNotFoundException("reviewer" + reviewerName + " is not registered");
    boolean added = reviewer.watch(item);
    commit();
    return added;
  }

  public boolean unSubscribe(String reviewerName, int itemId) {
    EntityManager em = getEntityManager();
    beginTransaction();
    Item item = em.find(Item.class, itemId);
    if (item == null)
      throw new ObjectNotFoundException("No item with id " + itemId);
    Reviewer reviewer = em.find(Reviewer.class, reviewerName);
    if (reviewer == null)
      throw new ObjectNotFoundException("reviewer" + reviewerName + " is not registered");
    boolean removed = reviewer.getWatchedItems().remove(item);
    commit();
    return removed;
  }

  public Set<Item> getWatchedItems(String reviewerid) {
    EntityManager em = getEntityManager();
    Reviewer reviewer = em.find(Reviewer.class, reviewerid);
    if (reviewer == null)
      throw new ObjectNotFoundException("reviewer" + reviewerid + " is not registered");
    return reviewer.getWatchedItems();
  }

  public List<Review> getFeededReviews(String reviewerid) {
    EntityManager em = getEntityManager();
    Reviewer reviewer = em.find(Reviewer.class, reviewerid);
    if (reviewer == null)
      throw new ObjectNotFoundException("reviewer" + reviewerid + " is not registered");
    List<Review> watches = reviewer.getWatchedReviews();
    for (Review review : watches) {
      review.getReviewed();
      review.getReviewer();
    }
    return watches;
  }

  public boolean removeFeededReview(String reviewerid, int reviewid) {
    EntityManager em = getEntityManager();
    beginTransaction();
    Reviewer reviewer = em.find(Reviewer.class, reviewerid);
    if (reviewer == null)
      throw new ObjectNotFoundException("reviewer" + reviewerid + " is not registered");
    List<Review> watches = reviewer.getWatchedReviews();
    Review toBeRemoved = null;
    for (Review review : watches) {
      if (review.getId() == reviewid) {
        toBeRemoved = review;
        break;
      }
    }
    watches.remove(toBeRemoved);
    commit();
    return toBeRemoved == null;
  }


  protected void addReviewAddedListener(ReviewListener listener){
    listerners.add(listener);
  }

  protected void removeReviewListener(ReviewListener listener){
    listerners.remove(listener);
  }

  public List select(String jpql, String[] params, Object[] values) {
    if (params == null || values == null || params.length != values.length)
      throw new RuntimeException("Mismatched param-value list");
    Query query = getEntityManager().createQuery(jpql);
    for (int i = 0;  i < params.length && params[i] != null; i++)
      query.setParameter(params[i], values[i]);

    return query.getResultList();
  }

  public List<String> findItemTitles(String title, String itemType) {
    String jpql = "select i from "+itemType+" i where UPPER(i.title) like :title";
    Query query = getEntityManager().createQuery(jpql);
    query.setParameter("title",title.toUpperCase());
    List<Item> items = query.getResultList();
    List<String> titles = new ArrayList<String>();
    for(Item item:items){
      if (!titles.contains(item.getTitle()))
        titles.add(item.getTitle());
    }
    return titles;
  }

  public List<String> findAuthorNames(String name, String itemType) {
    String jpql = "select i from "+itemType+" i  where UPPER(i.artist.name) like :name";
    Query query = getEntityManager().createQuery(jpql);
    query.setParameter("name",name.toUpperCase());
    List<Item> items = query.getResultList();
    List<String> names = new ArrayList<String>();
    for (Item item : items) {
      String artistName = item.getArtist().getName();
      if(!names.contains(artistName))
         names.add(artistName);
    }
    return names;
  }

  public List<Item> queryItemsByAuthor(String name, String itemType, int startPosition, int maxResult) {
    String jpql = "select i from "+itemType+" i where UPPER(i.artist.name) like :name order by i.id";
    Query query = getEntityManager().createQuery(jpql);
    if (startPosition >= 0)
      query.setFirstResult(startPosition);
    if (maxResult > 0)
      query.setMaxResults(maxResult);
    query.setParameter("name",name.toUpperCase());
    return query.getResultList();
  }

  public List<Item> queryItemsByTitle(String title, String itemType, int first, int max) {
    String jpql = "select i from "+itemType+" i where UPPER(i.title) like :title order by i.id";
    Query query = getEntityManager().createQuery(jpql);
    if (first >= 0)
      query.setFirstResult(first);
    if (max > 0)
      query.setMaxResults(max);
    query.setParameter("title",title.toUpperCase());
    return query.getResultList();
  }

}
