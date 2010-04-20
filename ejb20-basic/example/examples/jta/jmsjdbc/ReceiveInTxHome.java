package examples.jta.jmsjdbc;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for a ReceiveInTx EJB. 
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public interface ReceiveInTxHome extends EJBHome {
  
  ReceiveInTx create() throws CreateException, RemoteException;

}
