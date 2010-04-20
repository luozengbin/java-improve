package examples.iiop.ejb.stateless.server.wls;

import java.io.Serializable;

/**
 * This class reflects the results of a buy/sell transaction.
 *
 * @author Copyright (c) 1999-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public final class TradeResult implements Serializable {

  // Number of shares really bought or sold.
  public final int    numberTraded; 

  public final String stockSymbol;

  public TradeResult(int nt, String ss) {
    numberTraded = nt;
    stockSymbol  = ss;
  }

  public int getNumberTraded() { return numberTraded; }
  public String getStockSymbol() { return stockSymbol; }
}
