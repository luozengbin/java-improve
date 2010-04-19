package examples.ejb20.basic.statefulSession;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * TraderBean is a stateful SessionBean. This EJBean illustrates:
 * <ul>
 * <li> Automatic persistence of state between calls to the SessionBean
 * <li> The ability to look up values from the beans environment
 * <li> The use of Application-defined exceptions
 * </ul>
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class TraderBean implements SessionBean {

  static final boolean VERBOSE = true;

  private SessionContext ctx;
  private Context environment;
  private double tradingBalance;

  /**
   * Sets the session context.
   *
   * @param ctx               SessionContext Context for session
   */
  public void setSessionContext(SessionContext ctx) {
    log("setSessionContext called");
    this.ctx = ctx;
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbActivate() {
    log("ejbActivate called");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbPassivate() {
    log("ejbPassivate called");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbRemove() {
    log("ejbRemove called");
  }

  /**
   * This method corresponds to the create method in the home interface
   * "TraderHome.java".
   * The parameter sets of the two methods are identical. When the client calls
   * <code>TraderHome.create()</code>, the container allocates an instance of 
   * the EJBean and calls <code>ejbCreate()</code>.
   *
   * @exception               javax.ejb.CreateException
   *                          if there is a problem creating the bean
   * @see                     examples.ejb20.basic.statefulSession.Trader
   */
  public void ejbCreate() throws CreateException {
    log("ejbCreate called");
    try {
      InitialContext ic = new InitialContext();
      environment = (Context) ic.lookup("java:comp/env");
    } catch (NamingException ne) {
      throw new CreateException("Could not look up context");
    }
    this.tradingBalance = 0.0;
  }

  /**
   * Buys shares of a stock for a named customer.
   *
   * @param customerName      String Customer name
   * @param stockSymbol       String Stock symbol
   * @param shares            int Number of shares to buy
   * @return                  TradeResult Trade Result
   * @exception               examples.ejb20.basic.statefulSession.ProcessingErrorException
   *                          if there is an error while buying the shares
   */
  public TradeResult buy(String customerName, String stockSymbol, int shares)
    throws ProcessingErrorException
  {
    log("buy (" + customerName + ", " + stockSymbol + ", " + shares + ")");

    double price = getStockPrice(stockSymbol);
    
    if(tradingBalance < shares * price){
    	throw new ProcessingErrorException("あなたのアカウントの残額が足りない！ 残り金額：  $" + tradingBalance);
    }
    
    tradingBalance -= (shares * price); // subtract purchases from cash account

    return new TradeResult(shares, price, TradeResult.BUY);
  }

  /**
   * Sells shares of a stock for a named customer.
   *
   * @param customerName      String Customer name
   * @param stockSymbol       String Stock symbol
   * @param shares            int Number of shares to buy
   * @return                  TradeResult Trade Result
   * @exception               examples.ejb20.basic.statefulSession.ProcessingErrorException
   *                          if there is an error while selling the shares
   */
  public TradeResult sell(String customerName, String stockSymbol, int shares)
    throws ProcessingErrorException
  {
    log("sell (" + customerName + ", " + stockSymbol + ", " + shares + ")");

    double price = getStockPrice(stockSymbol);
    tradingBalance += (shares * price);

    return new TradeResult(shares, price, TradeResult.SELL);
  }

  /**
   * Returns the current balance of a trading session.
   *
   * @return                  double Balance
   */
  public double getBalance() {
    return tradingBalance;
  }

  /**
   * Returns the stock price for a given stock.
   *
   * @param stockSymbol       String Stock symbol
   * @return                  double Stock price
   * @exception               examples.ejb20.basic.statefulSession.ProcessingErrorException
   *                          if there is an error while checking the price
   */
  public double getStockPrice(String stockSymbol) 
    throws ProcessingErrorException 
  {
    try {
      return ((Double) environment.lookup(stockSymbol)).doubleValue();
    } catch (NamingException ne) {
      throw new ProcessingErrorException ("Stock symbol " + stockSymbol + 
                                          " does not exist");
    } catch (NumberFormatException nfe) {
      throw new ProcessingErrorException("Invalid price for stock "+stockSymbol);
    }
  }

  private void log(String s) {
    if(VERBOSE) {
      System.out.println(s);
    }
  }
}





