package examples.ejb20.basic.beanManaged;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * This interface is the home interface for the EJBean AccountBean,
 * which in WebLogic is implemented by the code-generated container class
 * AccountBeanC. A home interface may support one or more create methods,
 * which must correspond to methods named "ejbCreate" in the EJBean.
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public interface AccountHome extends EJBHome {

  /**
   * This method corresponds to the ejbCreate method in the bean
   * "AccountBean.java".
   * The parameter sets of the two methods are identical.  When the client calls
   * <code>AccountHome.create()</code>, the container (which in WebLogic EJB is
   * also the factory) allocates an instance of the bean and
   * calls <code>AccountBean.ejbCreate()</code>
   *
   * For bean-managed persistence, <code>create()</code> returns
   * a primary key, unlike the case of container-managed
   * persistence, where it returns a void.
   *
   * @param accountId         String Account ID
   * @param initialBalance    double Initial Balance
   * @return                  Account
   * @exception               javax.ejb.CreateException
   *                          if there is an error creating the bean
   * @exception               java.rmi.RemoteException
   *                          if there is a communications or systems failure
   * @see                     examples.ejb20.basic.beanManaged.AccountBean
   */
  public Account create(String accountId, double initialBalance)
    throws CreateException, RemoteException;

  /**
   * Attempts to find the EJBean with a given Primary Key from
   * the persistent storage.
   *
   * @param primaryKey        String Primary Key
   * @return                  Account
   * @exception               javax.ejb.FinderException
   *                          if there is an error finding the bean
   * @exception               java.rmi.RemoteException
   *                          if there is a communications or systems failure
   * @see                     examples.ejb20.basic.beanManaged.AccountBean
   */
  public Account findByPrimaryKey(String primaryKey)
    throws FinderException, RemoteException;

  /**
   * Finds all EJBeans with a balance greater than a given amount.
   * Returns an Collection of found EJBean primary keys.
   *
   * @param balanceGreaterThan double Test Amount
   * @return                   Collection of Account
   * @exception                javax.ejb.FinderException
   *                           if there is an error
   *                           while accessing the persistent storage
   * @exception                java.rmi.RemoteException
   *                           if there is a communications or systems failure
   * @see                      examples.ejb20.basic.beanManaged.AccountBean
   */
  public Collection findBigAccounts(double balanceGreaterThan)
    throws FinderException, RemoteException;
}
