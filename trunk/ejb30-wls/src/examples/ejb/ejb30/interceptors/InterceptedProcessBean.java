/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.interceptors;

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
import javax.interceptor.Interceptors;
import javax.interceptor.ExcludeClassInterceptors;
import java.util.List;
import java.util.Random;

/**
 * A simple EJB 3.0 stateless session bean with a Local business interface
 * that uses EJB 3.0 interceptor mechanism to validate the comments entered
 * by reviewer.
 * @Interceptors({AuditInterceptor.class}) annotation specifies the
 *  interceptor class that is associated with the bean file.
 */
@Stateless
@Interceptors( { AuditInterceptor.class })
@Local(InterceptedProcess.class)
public class InterceptedProcessBean {

  //reference name and type inferred from variable.
  @EJB ReviewListener listener;
  @PersistenceContext(unitName = "reviewSession")
  private EntityManager em;

  /**
   * The method-level @ExcludeClassInterceptors annotation specifies that
   * the business method specified in the associated interceptor class
   * (AuditInterceptor) should not be invoked for the getRandomBook method.
   */
 @ExcludeClassInterceptors
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
