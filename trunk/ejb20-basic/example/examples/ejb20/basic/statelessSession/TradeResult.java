package examples.ejb20.basic.statelessSession;

import java.io.Serializable;

/**
 * This class reflects the results of a buy/sell transaction.
 *
 * @author Copyright (c) 1999-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public final class TradeResult implements Serializable {

  // Number of shares really bought or sold.
  private final int    numberTraded; 

  private final String stockSymbol;

  public TradeResult(int nt, String ss) {
    numberTraded = nt;
    stockSymbol  = ss;
  }

  public int getNumberTraded() { return numberTraded; }
  public String getStockSymbol() { return stockSymbol; }
}
