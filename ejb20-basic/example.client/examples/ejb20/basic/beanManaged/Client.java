package examples.ejb20.basic.beanManaged;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * This class demonstrates calling an entity EJBean.
 * <p>
 * <p>
 * This class also illustrates how to lookup an EJB home in the JNDI tree. 
 * 
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */

public class Client
{
  private String url;
  private AccountHome home;

  public Client(String url) throws NamingException  {
    this.url       = url;
    home = lookupHome();
  }

 /**
  * Runs this example from the command line. Example:
  * <p>
  * <tt>java examples.ejb20.basic.beanManaged.Client "t3://localhost:7001"</tt>
  * <p>
  * The parameters are optional, but if any are supplied,
  * they are interpreted in this order:
  * <p>
  * @param args               URL such as "t3://localhost:7001" of Server
  */
  public static void main(String[] args) throws Exception {
    log("\nBeginning beanManaged.Client...\n");
    Client client = null;
    String url= "t3://localhost:7001";
         
    // Parse the argument list 
//     if (args.length != 1) {
//      log("Usage: java examples.ejb20.basic.beanManaged.Client t3://hostname:port");
//      return;
//    } else {
//      url = args[0];
//    }

    try {
      client = new Client(url);
      client.example();
    } catch (NamingException ne) {
      log("Unable to look up the beans home: " + ne.getMessage());
      throw ne;
    } catch (Exception e) {
      log("There was an exception while creating and using the Accounts.");
      log("This indicates that there was a problem communicating with the server: "+e);
      throw e;
    }
    log("\nEnd beanManaged.Client...\n");
  }

 /**
  *
  * @throws CreateException
  * @throws RemoteException
  * @throws FinderException
  * @throws RemoveException
  */
  public void example()
    throws CreateException, RemoteException, FinderException, RemoveException
  {
    int numBeans = 20;
    Account [] accounts = new Account [numBeans];

    // Create 20 accounts
    for (int i=0; i<numBeans; i++) {
      accounts [i] = findOrCreateAccount("ID: "+i, i * 1000);
    }

    // print out the account balances
    for (int i=0; i<numBeans; i++) {
      log("Account: :"+accounts[i].getPrimaryKey()+
        " has a balance of "+accounts[i].balance());
    }

    // find all accounts with a balance > 5000
    findBigAccounts(5000.0);

    // Remove our accounts
//    log("Removing beans...");
//    for (int i=0; i<numBeans; i++) {
//      accounts[i].remove();
//    }
  }

  /**
   * List all accounts with a balance greater than the parameterized amount.
   * This finder illustrates a Finder Method that returns a Collection.
   */
  private void findBigAccounts(double balanceGreaterThan)
    throws RemoteException, FinderException
  {
    log("\nQuerying for accounts with a balance greater than " +
      balanceGreaterThan + "...");

    Collection col = home.findBigAccounts(balanceGreaterThan);
    if(col.isEmpty()) {
      log("No accounts were found with a balance greater that "+balanceGreaterThan);
    }
    
    Iterator it = col.iterator();
    while (it.hasNext()) {
      Account accountGT =
        (Account) PortableRemoteObject.narrow(it.next(),Account.class);
      log("Account " + accountGT.getPrimaryKey() +
        "; balance is $" + accountGT.balance());
    }
  }

  /**
   * If there already exists an account for this id, we will return
   * it.  Otherwise we will create a new account.
   */
  private Account findOrCreateAccount(String id, double balance)
    throws CreateException, RemoteException, FinderException
  {
    try {
      log("Trying to find account with id: "+id);
      return (Account) PortableRemoteObject.narrow(home.findByPrimaryKey(id),
                                                         Account.class);
    } catch (ObjectNotFoundException onfe) {
      // the account id does not yet exist so create it.
      return (Account) PortableRemoteObject.narrow(home.create(id, balance),
                                                         Account.class);
    } 
  }

  /**
   * Create a new account with the given id and balance
   */
  private Account createAccount(String id, double balance)
    throws CreateException, RemoteException
  {
    log("Creating account " + id + " with a balance of " +
      balance + "...");
    Account ac = (Account) PortableRemoteObject.narrow(home.create(id, balance),
                                                       Account.class);
    log("Account " + id + " successfully created");
    return ac;
  }

  /**
   * Look up the bean's home interface using JNDI.
   */
  private AccountHome lookupHome() throws NamingException {
    Context ctx = getInitialContext();
    try {
      Object home = ctx.lookup("ejb20-beanManaged-AccountHome");
      return (AccountHome) PortableRemoteObject.narrow(home, AccountHome.class);

    } catch (NamingException ne) {
      log("The client was unable to lookup the EJBHome.  Please make sure " +
        "that you have deployed the ejb with the JNDI name " +
        "ejb20-beanManaged-AccountHome on the WebLogic server at "+url);
      throw ne;
    }
  }

  /**
   * Get an initial context into the JNDI tree.
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

  private static void log(String s) { System.out.println(s); }
}
