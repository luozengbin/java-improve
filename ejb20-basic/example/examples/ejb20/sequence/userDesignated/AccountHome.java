package examples.ejb20.sequence.userDesignated;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * This interface is the home interface for the EJBean AccountBean. A home
 * interface may support one or more create methods,
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
   * For container-managed persistence, <code>ejbCreate()</code>
   * returns a null, unlike the case of bean-managed
   * persistence, where it returns a primary key. See section 9.4.2
   *
   * @param accountID         String Account ID
   * @param initialBalance    double Initial balance
   * @param type              String Account type
   * @return                  Account
   * @exception               javax.ejb.CreateException
   *                          if there is an error creating the bean
   * @exception               java.rmi.RemoteException if there is
   *                          a communications or systems failure
   * @see                     examples.ejb20.sequence.userDesignated.AccountBean
   */
  public Account create(double initialBalance, String type)
    throws CreateException, RemoteException;

  /**
   * Given a Primary Key, refreshes the EJBean from
   * the persistent storage.
   *
   * @param primaryKey        Primary Key
   * @return                  Account
   * @exception               javax.ejb.FinderException
   *                          if there is an error finding the bean
   * @exception               java.rmi.RemoteException if there is
   *                          a communications or systems failure
   * @see                     examples.ejb20.sequence.userDesignated.AccountBean
   */
  public Account findByPrimaryKey(Integer primaryKey)
    throws FinderException, RemoteException;

  /**
   * Finds an EJBean with a balance equal to a given amount.
   * Returns a single EJBean Account.
   *
   * @param balanceEqual       double Test Amount
   * @return                   Account
   * @exception                javax.ejb.FinderException
   *                           if an error occurs while accessing
   *                           the persistent storage
   * @exception                java.rmi.RemoteException if there is
   *                           a communications or systems failure
   * @see                      examples.ejb20.sequence.userDesignated.AccountBean
   */
  public Account findAccount(double balanceEqual)
    throws FinderException, RemoteException;

  /**
   * Finds all EJBeans with a balance greater than a given amount.
   * Returns a Collection of found EJBean Accounts.
   *
   * @param balanceGreaterThan double Test Amount
   * @return                   Collection of Account
   * @exception                javax.ejb.FinderException
   *                           if an error occurs while accessing
   *                           the persistent storage
   * @exception                java.rmi.RemoteException if there is
   *                           a communications or systems failure
   * @see                      examples.ejb20.sequence.userDesignated.AccountBean
   */
  public Collection findBigAccounts(double balanceGreaterThan)
    throws FinderException, RemoteException;

  /**
   * Finds all EJBeans with a type of 'null'.
   *
   * @return                   Collection of Account
   * @exception                javax.ejb.FinderException
   *                           if an error occurs while accessing
   *                           the persistent storage
   * @exception                java.rmi.RemoteException if there is
   *                           a communications or systems failure
   * @see                      examples.ejb20.sequence.userDesignated.AccountBean
   */
  public Collection findNullAccounts()
    throws FinderException, RemoteException;

}
