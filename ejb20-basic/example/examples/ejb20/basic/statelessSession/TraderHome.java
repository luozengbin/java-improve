package examples.ejb20.basic.statelessSession;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * This interface is the home interface for the TraderBean.java,
 * which in WebLogic is implemented by the code-generated container
 * class TraderBeanC. A home interface may support one or more create
 * methods, which must correspond to methods named "ejbCreate" in the EJBean.
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public interface TraderHome extends EJBHome {

  /**
   * This method corresponds to the ejbCreate method in the bean
   * "TraderBean.java".
   * The parameter sets of the two methods are identical. When the client calls
   * <code>TraderHome.create()</code>, the container
   * allocates an instance of the EJBean and calls <code>ejbCreate()</code>.
   *
   * @return                  Trader
   * @exception               RemoteException if there is
   *                          a communications or systems failure
   * @exception               CreateException
   *                          if there is a problem creating the bean
   * @see                     examples.ejb20.basic.statelessSession.TraderBean
   */
  Trader create() throws CreateException, RemoteException;
}
