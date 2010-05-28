/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * PersistenceService provides basic support for a transactional application
 * using a {@link javax.persistence.EntityManager EntityManager}. <BR>
 * The service_ can be initialized/constructed in following ways<BR>
 * <LI>providing a configuration file that specifies the concrete class name of
 * the Persistence Provider Runtime implementation of
 * {@link javax.persistence.EntityManagerFactory EntityManagerFactory}.
 * <LI>supplying a {@link javax.persistence.EntityManagerFactory
 * EntityManagerFactory}. <BR>
 * This service uses a session-per-thread model to provide the caller with an
 * EntityManager when the EntityManager is not injected by calling injectEntityManager().
 * This is done by attaching a EntityManager to the calling
 * thread.
 *
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 */
public abstract class PersistenceService {
  private EntityManagerFactory _emf;
  private EntityManager _em;

  private ThreadLocal<EntityManager> _thread = new ThreadLocal<EntityManager>();

  private boolean _isManaged;

  public PersistenceService() {
  }

  public PersistenceService(EntityManagerFactory emf) {
    if (emf == null)
      throw new NullPointerException("null-emf");
    _emf = emf;
  }

  /**
   * Gets a entity manager. The entity manager is one per-calling thread.
   *
   * @return
   */
  public EntityManager getEntityManager() {
    EntityManager em = getEM();
    if (em == null)
      throw new NullPointerException("no-entity-manager");
    return em;
  }

  public void injectEntityManager(EntityManager em) {
    _isManaged = true;
    _em = em;
  }

  private final EntityManager getEM() {
    if (_em != null)
      return _em;
    if (_thread == null)
      _thread = new ThreadLocal<EntityManager>();
    EntityManager em = _thread.get();
    if (em != null)
      return em;
    if (_emf == null)
      return null;
    em = _emf.createEntityManager();
    _thread.set(em);
    return em;
  }

  protected final EntityManager beginTransaction() {
    EntityManager em = getEntityManager();
    if (!_isManaged && !em.getTransaction().isActive())
      em.getTransaction().begin();
    return em;
  }

  protected final void commit() {
    if (_isManaged)
      return;
    EntityManager em = getEntityManager();
    if (em.getTransaction().isActive())
      em.getTransaction().commit();
  }

  protected final void rollback() {
    if (_isManaged)
      return;
    EntityManager em = getEntityManager();
    if (em.getTransaction().isActive())
      em.getTransaction().rollback();
  }

}
