/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.ejbref;

import examples.ejb.ejb30.domain.Book;
import examples.ejb.ejb30.domain.Person;
import examples.ejb.ejb30.domain.Review;
import examples.ejb.ejb30.domain.Reviewer;
import examples.ejb.ejb30.exceptions.ObjectNotFoundException;
import examples.ejb.ejb30.mdb.ReviewListener;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;

/**
 * This is a simple session bean demoing the usage of ejb reference.
 * The listener instance variables is annotated to indicate dependencies
 * upon ReviewListenerBean in the bean�s environment.
 * <pre>
   @EJB ReviewListener listener;
 * </pre>
 * The container automatically initializes the annotated instance variable listener with the external references
 * to the Session Bean implementing the ReviewListener business interface. This initialization
 * occurs before any business methods are invoked on the bean instance and after the time the the beans
 * EJBContext is set.
 */

@Stateless
@Local(Process.class)
public class ProcessBean {
  //reference name and type inferred from variable.
  @EJB ReviewListener listener;
  @PersistenceContext(unitName = "reviewSession")
  private EntityManager em;

  public Book getRandomBook() {
    List<Book> books = em.createQuery("select i from Book i").getResultList();
    int size = books.size();
    return size == 0 ? null : books.get(new Random().nextInt(size));
  }

  public void addReview(int uid, String reviewerName, int rating, String comments) {
    Book book = em.find(Book.class, uid);
    if (book == null)
        throw new ObjectNotFoundException("Can't find book with id ["+uid+"]");
    Reviewer reviewer = em.find(Reviewer.class, reviewerName);
    if (reviewer == null) {
      reviewer = new Reviewer(reviewerName, Person.Gender.MALE);
    }
    Review review = reviewer.review(book, rating, comments);
    em.persist(reviewer);
    listener.reviewAdded(review);
  }

}
