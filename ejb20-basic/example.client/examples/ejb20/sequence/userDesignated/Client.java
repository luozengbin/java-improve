package examples.ejb20.sequence.userDesignated;

import java.rmi.RemoteException;
import java.util.Hashtable;
import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * This class illustrates how to set up a CMP EJBean to
 * automatically generate the primary key upon insert.
 * <p>
 * Create accounts and deposit some money into them.  The primary key will
 * be retrieved and printed after each creation.
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class Client
{
  private String url;
  private AccountHome home;

  public Client(String url) throws NamingException {
    this.url = url;
    home = lookupHome();
  }

  /**
   * Runs this example from the command line. Example:
   * <p>
   * <tt>java examples.ejb20.sequence.userDesignated.Client "t3://localhost:7001"</tt>
   * <p>
   * The parameter is optional.  If none is passed in, it t3://localhost:7001 will be used.
   * <p>
   * @param args               URL such as "t3://localhost:7001" of Server
   */
  public static void main(String[] args) throws Exception {
    log("\nBeginning sequence.userDesignated.Client...\n");
    String url       = "t3://localhost:7001";
    Client client = null;

    // Parse the argument list
    switch(args.length) {
      case 1:
        url = args[0];
        break;
    }

    try {
      client = new Client(url);
      client.runExample();
    } catch (NamingException ne) {
      log("Unable to look up the beans home: " + ne.getMessage());
      throw ne;
    } catch (Exception e) {
      log("There was an exception while creating and using the Accounts.");
      log("This indicates that there was a problem communicating with the server: "+e);
      throw e;
    }

    log("\nEnd sequence.userDesignated.Client...\n");
  }

   /**
    * Deposit and print the primary key of the newly created Account.
    */
   public void runExample() throws Exception {
     log("Starting example...\n");
     double initialBalance = 30000;
     String accountType = "Savings";
     Account ac = null;
     try {
       ac = createAccount(initialBalance, accountType);
       log("just created Account with auto-generated primary key " +
         ((Integer)ac.getPrimaryKey()).intValue());
     } catch(Exception e) {
       log("Error creating account: " + e.getMessage());
       throw e;
     }

     initialBalance = 1000;
     accountType = "Checking";
     ac = null;
     try {
       ac = createAccount(initialBalance, accountType);
       log("just created Account with auto-generated primary key " +
         ((Integer)ac.getPrimaryKey()).intValue());
     } catch(Exception e) {
       log("Error creating account: " + e.getMessage());
       throw e;
     }

     log("End example...\n");
   }

  /**
   * Create a new account with the given id and balance
   */
  private Account createAccount(double balance, String accountType)
    throws CreateException, RemoteException
  {
    log ("Creating account with a balance of " +
      balance + " account type " + accountType + "...");
    Account ac = (Account) PortableRemoteObject.narrow(
      home.create(balance, accountType), Account.class);
   log("Account successfully created");
   return ac;
  }

  /**
   * Look up the bean's home interface using JNDI.
   */
  private AccountHome lookupHome() throws NamingException {
    Context ctx = getInitialContext();
    try {
      Object home = (AccountHome) ctx.lookup("ejb20-sequence-userDesignated-AccountHome");
      return (AccountHome) PortableRemoteObject.narrow(home, AccountHome.class);

    } catch (NamingException ne) {
      log("The client was unable to lookup the EJBHome.  Please make sure " +
      "that you have deployed the ejb with the JNDI name " +
      "ejb20-sequence-userDesignated-AccountHome on the WebLogic server at "+url);
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
