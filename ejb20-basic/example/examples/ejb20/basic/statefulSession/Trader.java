package examples.ejb20.basic.statefulSession;

import javax.ejb.*;
import java.rmi.RemoteException;

/**
 * The methods in this interface are the public face of TraderBean.
 * The signatures of the methods are identical to those of the EJBean, except
 * that these methods throw a java.rmi.RemoteException.
 * Note that the EJBean does not implement this interface. The corresponding
 * code-generated EJBObject, TraderBeanE, implements this interface and
 * delegates to the bean.
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public interface Trader extends EJBObject {

  /**
   * Buys shares of a stock for a named customer.
   *
   * @param customerName      String Customer name
   * @param stockSymbol       String Stock symbol
   * @param shares            int Number of shares to buy
   * @return                  TradeResult Trade Result
   * @exception               ProcessingErrorException
   *                          if there is an error while buying the shares
   * @exception               RemoteException if there is
   *                          a communications or systems failure
   */
  public  TradeResult buy(String customerName, String stockSymbol, int shares)
    throws ProcessingErrorException, RemoteException;

  /**
   * Sells shares of a stock for a named customer.
   *
   * @param customerName      String Customer name
   * @param stockSymbol       String Stock symbol
   * @param shares            int Number of shares to buy
   * @return                  TradeResult Trade Result
   * @exception               ProcessingErrorException
   *                          if there is an error while selling the shares
   * @exception               RemoteException if there is
   *                          a communications or systems failure
   */
  public TradeResult sell(String customerName, String stockSymbol, int shares)
    throws ProcessingErrorException, RemoteException;

  /**
   * Returns the current balance of a trading session.
   *
   * @return                  double Balance
   * @exception               RemoteException if there is
   *                          a communications or systems failure
   */
  public double getBalance() 
    throws RemoteException;

}
