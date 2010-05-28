/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.ejb21client;

import examples.ejb.ejb30.domain.Book;
import examples.ejb.ejb30.domain.Artist;

import javax.ejb.Local;
import javax.ejb.LocalHome;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

/**
 * This ItemManagerBean session bean is annotated with a business
 *  interface and Home interface, thereafore it can be invoked by
 * client code following the EJB2.1 spec.
 */
@Stateless
@Local(ItemManager.class)
@LocalHome(ItemManagerHome.class)
public class ItemManagerBean{
  @PersistenceContext(unitName = "reviewSession")
  private EntityManager em;

  public Book addBook(String title, String creatator) {
    Artist artist = em.find(Artist.class, creatator);
    if (artist == null) {
      artist = new Artist(creatator);
      em.persist(artist);
    }
    Book book = new Book(title);
    book.setArtist(artist);
    em.persist(book);
    return book;
  }
}
