package examples.ejb20.basic.statelessSession;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

/**
 * The methods in this interface are the public face of TraderBean.
 * The signatures of the methods are identical to those of the EJBean, except
 * that these methods throw a java.rmi.RemoteException.
 * Note that the EJBean does not implement this interface. The corresponding
 * code-generated EJBObject, TraderBeanE, implements this interface and
 * delegates to the bean.
 *
 * @author Copyright (c) 1999-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public interface Trader extends EJBObject {

  /**
   * Buys shares of a stock.
   *
   * @param stockSymbol       String Stock symbol
   * @param shares            int Number of shares to buy
   * @return                  TradeResult Trade Result
   * @exception               RemoteException if there is
   *                          a communications or systems failure
   */
  public  TradeResult buy (String stockSymbol, int shares)
    throws RemoteException;

  /**
   * Sells shares of a stock.
   *
   * @param stockSymbol       String Stock symbol
   * @param shares            int Number of shares to sell
   * @return                  TradeResult Trade Result
   * @exception               RemoteException if there is
   *                          a communications or systems failure
   */
  public TradeResult sell (String stockSymbol, int shares)
    throws RemoteException;
}
