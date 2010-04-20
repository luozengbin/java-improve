package examples.ejb20.sequence.userDesignated;

import java.rmi.RemoteException;
import javax.ejb.*;

/**
 * The methods in this interface are the public face of AccountBean.
 * The signatures of the methods are identical to those of the bean, 
 * except that these methods throw a java.rmi.RemoteException.
 * Note that the EJBean does not implement this interface. 
 * The corresponding code-generated EJBObject implements this interface
 * and delegates to the EJBean.
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public interface Account extends EJBObject {

  /**
   * Deposits an amount.
   *
   * @param amount            double Amount to deposit
   * @return                  double Account Balance
   * @exception               java.rmi.RemoteException if there is
   *                          a communications or systems failure
   */
  public double deposit(double amount)
    throws RemoteException;

  /**
   * Withdraws an amount.
   *
   * @param amount            double Amount to withdraw
   * @return                  double Account Balance
   * @exception               examples.ejb20.sequence.userDesignated.ProcessingErrorException
   *                          if there is an error while depositing
   * @exception               java.rmi.RemoteException if there is
   *                          a communications or systems failure
   */
  public double withdraw(double amount)
    throws ProcessingErrorException, RemoteException;

  /**
   * Balance in account.
   *
   * @return                  double Account Balance
   * @exception               java.rmi.RemoteException if there is
   *                          a communications or systems failure
   */
  public double balance() 
    throws RemoteException;

  /**
   * Type of account.
   *
   * @return                  String account Type
   * @exception               java.rmi.RemoteException if there is
   *                          a communications or systems failure
   */
  public String accountType() 
    throws RemoteException;

}
