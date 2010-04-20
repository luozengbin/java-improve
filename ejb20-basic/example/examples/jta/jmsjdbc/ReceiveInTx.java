package examples.jta.jmsjdbc;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

/**
 * Interface for an EJB that receives messages from a JMS queue. 
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public interface ReceiveInTx extends EJBObject {

  void receiveMessages() throws RemoteException;

}
