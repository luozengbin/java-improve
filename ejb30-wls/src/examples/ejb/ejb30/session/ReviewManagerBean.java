/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.session;

import examples.ejb.ejb30.mdb.ReviewListener;
import examples.ejb.ejb30.service.ReviewServiceImpl;
import examples.ejb.ejb30.service.ReviewService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A Stateless Session Bean operating on a persistent domain model.
 * The EJB container registers the bean in JNDI global namespace.
 * The JNDI name for the bean is the <em>simple</em> name (i.e.
 * stripped of package name) of the business interface it implements.
 * For this example, the JNDI name will be <code>ReviewService</code>
 * which is specified by the annotation element mappedName="ReviewService".<p>
 * <B>NOTE</B>:This policy is ambiguous when two beans implements the same
 * business interface.<p>
 * <p/>
 * The EJB contain also injects the bean with a instance of
 * {@link javax.persistence.EntityManager EntityManager} (or rather a proxy).
 * This EntityManager is used to interact with the persistent EJB domain
 * model.
 *
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 */
@Stateless(mappedName="ReviewService")
@Remote(ReviewService.class)
public class ReviewManagerBean extends ReviewServiceImpl
    implements ReviewService {
  @PersistenceContext(unitName = "reviewSession")
  private EntityManager em;
  @EJB ReviewListener listener;

  @PostConstruct
  public void init(){
     // inject the EnityManager.
     injectEntityManager(em);
     addReviewAddedListener(listener);
  }


}
