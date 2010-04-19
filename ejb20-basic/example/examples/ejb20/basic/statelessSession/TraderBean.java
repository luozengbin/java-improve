package examples.ejb20.basic.statelessSession;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * TraderBean is a stateless Session Bean. This bean illustrates:
 * <ul>
 * <li> No persistence of state between calls to the Session Bean
 * <li> Looking up values from the Environment
 * </ul>
 *
 * @author Copyright (c) 1999-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class TraderBean implements SessionBean {

  private static final boolean VERBOSE = true;
  private SessionContext ctx;
  private int tradeLimit;

  // You might also consider using WebLogic's log service
  private void log(String s) {
    if (VERBOSE) System.out.println(s);
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
  public void ejbRemove() {
    log("ejbRemove called");
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
   * Sets the session context.
   *
   * @param ctx               SessionContext Context for session
   */
  public void setSessionContext(SessionContext ctx) {
    log("setSessionContext called");
    this.ctx = ctx;
  }

  /**
   * This method corresponds to the create method in the home interface
   * "TraderHome.java".
   * The parameter sets of the two methods are identical. When the client calls
   * <code>TraderHome.create()</code>, the container tries to find an instance
   * in the pool. If an instance is found in the pool, it returns its remote
   * reference. Otherwise, it allocates an instance of the EJBean and calls
   * <code>ejbCreate()</code>.
   *
   * @exception               javax.ejb.CreateException if there is
   *                          a communications or systems failure
   * @see                     examples.ejb20.basic.statelessSession.Trader
   */
  public void ejbCreate () throws CreateException {
    log("ejbCreate called");

    try {
      InitialContext ic = new InitialContext();

      Integer tl = (Integer) ic.lookup("java:/comp/env/tradeLimit");

      tradeLimit = tl.intValue();
    } catch (NamingException ne) {
      throw new CreateException("Failed to find environment value "+ne);
    }
  }

  /**
   * Buys shares of a stock for a named customer.
   *
   * @param stockSymbol       String Stock symbol
   * @param shares            int Number of shares to buy
   * @return                  TradeResult Trade Result
   *                          if there is an error while buying the shares
   */
  public TradeResult buy(String stockSymbol, int shares) {

    if (shares > tradeLimit) {
      log("Attempt to buy "+shares+" is greater than limit of "+tradeLimit);
      shares = tradeLimit;
    }

    log("Buying "+shares+" shares of "+stockSymbol);

    return new TradeResult(shares, stockSymbol);
  }

  /**
   * Sells shares of a stock for a named customer.
   *
   * @param stockSymbol       String Stock symbol
   * @param shares            int Number of shares to buy
   * @return                  TradeResult Trade Result
   *                          if there is an error while selling the shares
   */
  public TradeResult sell(String stockSymbol, int shares) {

    if (shares > tradeLimit) {
      log("Attempt to sell "+shares+" is greater than limit of "+tradeLimit);
      shares = tradeLimit;
    }

    log("Selling "+shares+" shares of "+stockSymbol);

    return new TradeResult(shares, stockSymbol);
  }

}








