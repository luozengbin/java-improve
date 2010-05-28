/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.web;

import examples.ejb.ejb30.service.ReviewServiceImpl;
import examples.ejb.ejb30.service.SeedDatabase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Ensures that database is prepoulated with sample data before any
 * other operations.
 */
public class ContextInitializer implements ServletContextListener {
  //EntityManagerFactory instances are heavyweight objects which should be cached once created.
 // @PersistenceUnit(unitName = "reviewSession")
  private EntityManagerFactory emf;

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    emf = Persistence.createEntityManagerFactory("reviewService");
    SeedDatabase populator = new SeedDatabase(emf);
    populator.ensureDatabaseInitialzed();
    ReviewServiceImpl service = new ReviewServiceImpl(emf);
    ServletContext context = servletContextEvent.getServletContext();
    context.setAttribute("reviewService",emf);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    //close EMF to free these resources maintained by heavyweight EntityManagerFactory instances.
    emf.close();
  }
}
