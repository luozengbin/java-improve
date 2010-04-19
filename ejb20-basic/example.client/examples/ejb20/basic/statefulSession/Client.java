package examples.ejb20.basic.statefulSession;

import java.rmi.RemoteException;
import java.util.Hashtable;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * This class illustrates calling a stateful SessionBean and performing
 * the following exercises:
 * <ul>
 * <li> Create a Trader
 * <li> Buy some shares using the Trader
 * <li> Sell some shares using the Trader
 * <li> Remove the Trader
 * </ul>
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class Client
{
  private static final String JNDI_NAME = "ejb20-statefulSession-TraderHome";
  private String url;
  private TraderHome home;

  public Client(String url) throws NamingException  {
    this.url = url;
    home = lookupHome();
  }

  /**
   * Runs this example from the command line. Example:
   * <p>
   * <tt>java examples.ejb20.basic.statefulSession.Client "t3://localhost:7001"</tt>
   * <p>
   * The parameters are optional, but if any are supplied,
   * they are interpreted in this order:
   * <p>
   * @param args               URL such as "t3://localhost:7001" of Server
   */
  public static void main(String[] args) throws Exception {
    log("\nBeginning statefulSession.Client...\n");
    String url       = "t3://localhost:7001";
    Client client = null;

    // Parse the argument list
     if (args.length != 1) {
      log("Usage: java examples.ejb20.basic.statefulSession.Client t3://hostname:port");
      return;
    } else {
      url = args[0];
    }

    try {
      client = new Client(url);
      client.example();
    } catch (NamingException ne) {
      log("Unable to look up the beans home: " + ne.getMessage());
      throw ne;
    } catch (Exception e) {
      log("There was an exception while creating and using the Trader.");
      log("This indicates that there was a problem communicating with the server: "+e);
      throw e;
    }
    log("\nEnd statefulSession.Client...\n");
  }

  /**
   * Runs the example.
   */
  public void example()
    throws CreateException, ProcessingErrorException, RemoteException,
           RemoveException
  {
    String customerName = "Matt"; 

    // Create a Trader
    log("Creating trader\n");
    Trader trader = (Trader) narrow(home.create(), Trader.class);

    // Sell some stock
    String stockName   = "MSFT";
    int numberOfShares = 200;
    log("Selling " + numberOfShares + " of " + stockName);
    TradeResult tr = trader.sell(customerName, stockName, numberOfShares);
    log(tr.toString());

    // Buy some stock
    stockName      = "BEAS";
    numberOfShares = 500;
    log("Buying " + numberOfShares + " of " + stockName);
    tr = trader.buy(customerName, stockName, numberOfShares);
    log(tr.toString());

    // Get change in Cash Account from EJBean
    log("Change in Cash Account: $" + trader.getBalance()
      + "\n");
    log("Removing trader\n");
    trader.remove();

  }

  /**
   * RMI/IIOP clients should use this narrow function
   */
  private Object narrow(Object ref, Class c) {
    return PortableRemoteObject.narrow(ref, c);
  }

  /**
   * Lookup the EJBs home in the JNDI tree
   */
  private TraderHome lookupHome() throws NamingException {
    // Lookup the beans home using JNDI
    Context ctx = getInitialContext();
    try {
      Object home = ctx.lookup(JNDI_NAME);
      return (TraderHome) narrow(home, TraderHome.class);
    } catch (NamingException ne) {
      log("The client was unable to lookup the EJBHome.  Please make sure ");
      log("that you have deployed the ejb with the JNDI name "+JNDI_NAME+" on the WebLogic server at "+url);
      throw ne;
    }
  }

  /**
   * Using a Properties object will work on JDK 1.1.x and Java2
   * clients
   */
  private Context getInitialContext() throws NamingException {
    try {
      // Get an InitialContext
      Hashtable h = new Hashtable();
      h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
      h.put(Context.PROVIDER_URL, url);
      return new InitialContext(h);
    } catch (NamingException ne) {
      log("We were unable to get a connection to the WebLogic server at "+url);
      log("Please make sure that the server is running.");
      throw ne;
    }
  }

  private static void log(String s) { System.out.println(s);  }

}
