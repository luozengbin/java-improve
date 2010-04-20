package examples.ejb20.basic.containerManaged;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.transaction.UserTransaction;

/**
 * This class demonstrates calling an entity EJBean, followed by two exercises:
 * <p>
 * Part A: Create an Account and Deposit some money into it. Next attempt to
 * withdraw more than the current account balance. An application-specific
 * exception should be thrown. Finally, remove the account.
 * <p>
 * Part B: Create some new accounts, with different initial balances. Find all
 * the accounts with a balance greater than a specific value. Find the account
 * with a balance of zero. Attempt to find accounts with a null account type.
 * Finally, remove all newly created accounts.
 * <p>
 * This class also illustrates how to lookup an EJB home in the JNDI tree.
 * 
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class Client {
	private String url;
	private String accountId;
	private AccountHome home;

	public Client(String url, String accountId) throws NamingException {
		this.url = url;
		this.accountId = accountId;
		home = lookupHome();
	}

	/**
	 * Runs this example from the command line. Example:
	 * <p>
	 * <tt>java examples.ejb20.basic.containerManaged.Client "t3://localhost:7001" 10020</tt>
	 * <p>
	 * The parameters are optional, but if any are supplied, they are
	 * interpreted in this order:
	 * <p>
	 * 
	 * @param args
	 *            URL such as "t3://localhost:7001" of Server, String Account ID
	 *            to test, default "10020"
	 */
	public static void main(String[] args) throws Exception {
		log("\nBeginning containerManaged.Client...\n");
		Client client = null;
		String url = "t3://localhost:7001";
		String accountId = "10027";

		try {

			UserTransaction tx = (UserTransaction) new InitialContext()
					.lookup("javax.transaction.UserTransaction");

			tx.begin();
			client = new Client(url, accountId);
			client.runExamplePartA();
			client.runExamplePartB();
			tx.commit();

		} catch (NamingException ne) {
			log("Unable to look up the beans home: " + ne.getMessage());
			throw ne;
		} catch (Exception e) {
			log("There was an exception while creating and using the Accounts.");
			log("This indicates that there was a problem communicating with the server: "
					+ e);
			throw e;
		}
		log("\nEnd containerManaged.Client...\n");
	}

	/**
	 * Executes Part A of this example.
	 * 
	 * Part A: Deposit and attempt to withdraw more than the current account
	 * balance. An application-specific exception should be thrown.
	 */
	public void runExamplePartA() throws Exception {
		log("Starting Part A of the example...\n");

		double initialBalance = 3000;
		String accountType = "Savings";
		Account ac = null;
		try {
			ac = findOrCreateAccount(accountId, initialBalance, accountType);
		} catch (DuplicateKeyException dke) {
			// This won't prevent us from continuing the example
			log("An account with id " + accountId + " already exists!");
		} catch (Exception e) {
			log("Error creating account: " + e.getMessage());
			throw e;
		}

		int amount = 2000;
		double balance = 0;
		log("\nPart A: Depositing $" + amount);
		try {
			balance = ac.deposit(amount);
			log("Current balance is $" + balance);
		} catch (Exception e) {
			log("Error during deposit: " + e.getMessage());
			throw e;
		}

		try {
			log("Attempting to withdraw an amount greater than current balance. Expecting an exception...\n");
			balance = ac.withdraw(balance + 1);
			log("Error: expected an exception.");
		} catch (ProcessingErrorException pe) {
			log("Received expected Processing Error:\n" + pe);
		} catch (Exception e) {
			log("Error during withdraw: " + e.getMessage());
			throw e;
		}

		// log("Removing account...\n");
		// try {
		// ac.remove();
		// } catch (Exception e) {
		// log("Error removing account: " + e.getMessage());
		// throw e;
		// }

		log("End Part A of the example...\n");
	}

	/**
	 * Executes Part B of this example.
	 * 
	 * Part B: Create 20 new accounts, with different initial balances. Find all
	 * the accounts with a balance greater than a specific value. When finished,
	 * remove the new accounts.
	 */
	public void runExamplePartB() throws Exception {
		log("Starting Part B of the example...\n");
		int numBeans = 20;

		Account[] accounts = new Account[numBeans];

		// Create 20 accounts with varying balances and some null accountTypes.
		for (int i = 0; i < numBeans; i++) {
			// if (Math.IEEEremainder(i, 5) == 0)
			if (i % 5 == 0) {
				// Create a few of beans with a null accountType
				// (just so we can find them later.)
				accounts[i] = findOrCreateAccount("ID: " + i, i * 1000, null);
			} else {
				accounts[i] = findOrCreateAccount("ID: " + i, i * 1000,
						"Savings");
			}
		}

		// print out the account balances
		for (int i = 0; i < numBeans; i++) {
			log("Account: :" + accounts[i].getPrimaryKey()
					+ " has a balance of " + accounts[i].balance());
		}

		// find all accounts with a balance > 5000
		findBigAccounts(5000.0);
		// find the first account with a balance of 0
		findAccountWithZeroBalance();
		// find all accounts with a null balance
		findNullAccounts();

		// Remove our accounts

//		log("Removing beans...");
//		for (int i = 0; i < numBeans; i++) {
//			accounts[i].remove();
//		}
//		log("End Part B of the example...\n");
	}

	/**
	 * List the first account with a zero balance. This finder illustrates a
	 * Finder Method that only returns one record.
	 */
	private void findAccountWithZeroBalance() throws FinderException,
			RemoteException {
		log("\nQuerying for an account with zero balance...");
		try {
			Account zeroBalance = (Account) PortableRemoteObject.narrow(home
					.findAccount(0), Account.class);
			// Account zeroBalance = home.findAccount(0);
			log("Account " + zeroBalance.getPrimaryKey() + "; balance is zero");
		} catch (ObjectNotFoundException onfe) {
			log("No Account was found with a balance of zero");
		}
	}

	/**
	 * List all accounts with a null account type. This finder illustrates a
	 * Finder Method that returns an Collection.
	 */
	private void findNullAccounts() throws RemoteException, FinderException {
		log("\nQuerying for accounts with a null account type");
		Collection col = home.findNullAccounts();

		if (col.isEmpty()) {
			log("No accounts were found with a null account type");
		}

		Iterator it = col.iterator();
		while (it.hasNext()) {
			Account nullAccount = (Account) PortableRemoteObject.narrow(it
					.next(), Account.class);
			log("Account " + nullAccount.getPrimaryKey() + "; account type is "
					+ nullAccount.accountType());
		}
	}

	/**
	 * List all accounts with a balance greater than the parameterized amount.
	 * This finder illustrates a Finder Method that returns an Collection.
	 */
	private void findBigAccounts(double balanceGreaterThan)
			throws RemoteException, FinderException {
		log("\nQuerying for accounts with a balance greater than "
				+ balanceGreaterThan + "...");

		Collection col = home.findBigAccounts(balanceGreaterThan);

		if (col.isEmpty()) {
			log("No accounts were found!");
		}

		Iterator it = col.iterator();
		while (it.hasNext()) {
			Account bigAccount = (Account) PortableRemoteObject.narrow(it
					.next(), Account.class);

			log("Account " + bigAccount.getPrimaryKey() + "; balance is $"
					+ bigAccount.balance());
		}
	}

	/**
	 * If there already exists an account for this id, we will return it.
	 * Otherwise we will create a new account.
	 */
	private Account findOrCreateAccount(String id, double balance,
			String accountType) throws CreateException, FinderException,
			RemoteException {
		Account remote = null;
		try {
			remote = (Account) PortableRemoteObject.narrow(home
					.findByPrimaryKey(id), Account.class);
		} catch (ObjectNotFoundException onfe) {
			// the account id does not yet exist so create it.
			remote = createAccount(id, balance, accountType);
		}
		return remote;
	}

	/**
	 * Create a new account with the given id and balance
	 */
	private Account createAccount(String id, double balance, String accountType)
			throws CreateException, RemoteException {
		log("Creating account " + id + " with a balance of " + balance
				+ " account type " + accountType + "...");

		Account ac = (Account) PortableRemoteObject.narrow(home.create(id,
				balance, accountType), Account.class);

		log("Account " + id + " successfully created");

		return ac;
	}

	/**
	 * Look up the bean's home interface using JNDI.
	 */
	private AccountHome lookupHome() throws NamingException {
		Context ctx = getInitialContext();

		try {
			Object home = (AccountHome) ctx
					.lookup("ejb20-containerManaged-AccountHome");
			return (AccountHome) PortableRemoteObject.narrow(home,
					AccountHome.class);

		} catch (NamingException ne) {
			log("The client was unable to lookup the EJBHome.  Please make sure "
					+ "that you have deployed the ejb with the JNDI name "
					+ "ejb20-containerManaged-AccountHome on the WebLogic server at "
					+ url);
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
			log("We were unable to get a connection to the WebLogic server at "
					+ url);
			log("Please make sure that the server is running.");
			throw ne;
		}
	}

	private static void log(String s) {
		System.out.println(s);
	}
}
