/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.ejb21client;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

/**
 * Home interface for the session bean.
 */
public interface ItemManagerHome extends EJBLocalHome {
  public ItemManagerLocal create() throws CreateException;
}
