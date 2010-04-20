package examples.ejb20.sequence.userDesignated;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;


/**
 * AccountBean is an EntityBean. This EJBean illustrates:
 * <ul>
 * <li> Container-managed JDBC persistence and transactions;
 *      the code in this file never directly accesses the data storage.
 * <li> Application-defined exceptions.
 * </ul>
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
abstract public class AccountBean implements EntityBean {

  final static boolean VERBOSE = true;

  private EntityContext ctx;

  public AccountBean() {
  };

  /**
   * Sets the EntityContext for the EJBean.
   *
   * @param ctx               EntityContext
   */
  public void setEntityContext(EntityContext ctx) {
    log("setEntityContext called (" + id() + ")");
    this.ctx = ctx;
  }

  /**
   * Unsets the EntityContext for the EJBean.
   *
   */
  public void unsetEntityContext() {
    log("AccountBean.unsetEntityContext (" + id() + ")");
    this.ctx = null;
  }

  /**
   * container managed fields
   */
  abstract public Integer getAccountId();

  abstract public void setAccountId(Integer val);

  abstract public double getBalance();

  abstract public void setBalance(double val);

  abstract public String getAccountType();

  abstract public void setAccountType(String val);

  /**
   * Returns the Primary Key identifying this EJBean.
   *
   * @return                  String Identification
   */
  private String id() {
    return "" + System.identityHashCode(this) + ", PK = " +
      ((ctx == null) ? "nullctx"
      : ((ctx.getPrimaryKey() == null ?
      "null" : ctx.getPrimaryKey().toString())));
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbActivate() {
    log("AccountBean.ejbActivate (" + id() + ")");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbPassivate() {
    log("AccountBean.ejbPassivate (" + id() + ")");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbLoad() {
    log("AccountBean.ejbLoad (" + id() + ")");
  }

  /**
   * Sets the EJBean's modified flag to false.
   * set to false to "reset" the variable for the next transaction.
   *
   */
  public void ejbStore() {
    log("AccountBean.ejbStore (" + id() + ")");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   * @exception               javax.ejb.RemoveException
   *                          if the EJBean does not allow removing the EJBean
   */
  public void ejbRemove()
    throws RemoveException {
    log("AccountBean.ejbRemove (" + id() + ")");
  }

  /**
   * This method corresponds to the create method in the home interface
   * "AccountHome.java".
   * The parameter sets of the two methods are identical.  When the client calls
   * <code>AccountHome.create()</code>, the container (which in WebLogic EJB is
   * also the home) allocates an instance of this EJBean and
   * calls <code>AccountBean.ejbCreate()</code>.
   * <p>
   * For container-managed persistence, <code>ejbCreate()</code> returns
   * a null, unlike the case of bean-managed
   * persistence, where it returns a primary key.
   *
   * @param initialBalance    double Initial Balance
   * @exception               javax.ejb.CreateException
   *                          if there is a problem creating the bean
   */
  public Integer ejbCreate(double initialBalance, String type)
    throws CreateException {
//    log("AccountBean.ejbCreate( id = " + System.identityHashCode(this) +
//				", PK = " +
//				accountId + ", " + "initial balance = $ " + initialBalance + ")");

    setBalance(initialBalance);
    setAccountType(type);

    return null;  // See 9.4.2 of the EJB 1.1 specification
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   * @param initialBalance    double Initial Balance
   * @param type              String Account type
   */
  public void ejbPostCreate(double initialBalance, String type) {
    log("AccountBean.ejbPostCreate");
  }


  // Application defined methods

  /**
   * Adds amount to balance.
   *
   * @param amount            double Amount
   * @return                  double balance
   */
  public double deposit(double amount) {
    log("AccountBean.deposit: Depositing $" +
      amount +
      " into '" +
      getAccountId() + "'");

    setBalance(getBalance() + amount);
    return getBalance();
  }

  /**
   * Subtracts amount from balance.
   *
   * @param amount            double Amount
   * @return                  double Balance
   * @exception               ProcessingErrorException
   *                          if Amount &gt; Balance
   */
  public double withdraw(double amount)
    throws ProcessingErrorException {
    log("AccountBean.withdraw: Withdrawing $" + amount + " from '" +
      getAccountId() + "'");
    if (amount > getBalance()) {
      throw new ProcessingErrorException(
        "Request to withdraw $" + amount +
        "; is more than balance $" + getBalance() +
        " in account " + getAccountId());
    }
    setBalance(getBalance() - amount);
    return getBalance();
  }

  /**
   * Returns current balance.
   *
   * @return                  double Balance
   */
  public double balance() {
    log("AccountBean.balance (" + id() + ")");

    return getBalance();
  }

  /**
   * Returns the account type.
   *
   * @return                  String account Type
   */
  public String accountType() {
    log("AccountBean.accountType (" + id() + ")");

    return getAccountType();
  }

  // You might also consider using WebLogic's log service
  private void log(String s) {
    if (VERBOSE) System.out.println(s);
  }

}











