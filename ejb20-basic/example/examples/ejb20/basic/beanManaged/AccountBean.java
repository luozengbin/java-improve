package examples.ejb20.basic.beanManaged;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * AccountBean is an EntityBean. This EJBean illustrates:
 * <ul>
 * <li> EJBean-managed persistence and transactions;
 *      the code in this file directly accesses the data storage.
 * <li> Application-defined exceptions.
 * </ul>
 *
 * @author Copyright (c) 1998-2002 by BEA Systems, Inc. All Rights Reserved.
 */
public class AccountBean implements EntityBean {

  final static private boolean VERBOSE = true;

  private EntityContext ctx;
  private String accountId; // also the primary Key
  private double balance;

  /**
   * Sets the EntityContext for the EJBean.
   *
   * @param ctx               EntityContext
   */
  public void setEntityContext(EntityContext ctx) {
    log("setEntityContext called");
    this.ctx = ctx;
  }

  /**
   * Unsets the EntityContext for the EJBean.
   */
  public void unsetEntityContext() {
    log("unsetEntityContext");
    this.ctx = null;
  }

  /**
   * Required by the EJB specification, this method is not used
   * by this example.
   */
  public void ejbActivate() {
    log("ejbActivate (" + id() + ")");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   */
  public void ejbPassivate() {
    log("ejbPassivate (" + id() + ")");
  }

  /**
   * Loads the EJBean from the persistent storage.
   *
   * @exception               javax.ejb.NoSuchEntityException
   *                          if the bean is not found in the database
   * @exception               javax.ejb.EJBException
   *                          if there is a communications or systems failure
   */
  public void ejbLoad() {
    log("ejbLoad: (" + id() +  ")");

    Connection con = null;
    PreparedStatement ps = null;
    accountId = (String) ctx.getPrimaryKey();

    try {
      con = getConnection();
      ps  = con.prepareStatement("select bal from ejbAccounts where id = ?");
      ps.setString(1, accountId);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next()) {
        balance = rs.getDouble(1);
      } else {
        String error = "ejbLoad: AccountBean (" + accountId + ") not found";
        log(error);
        throw new NoSuchEntityException (error);
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
      throw new EJBException(sqe);
    } finally {
      cleanup(con, ps);
    }
  }

  /**
   * Stores the EJBean in the persistent storage.
   *
   * @exception               javax.ejb.NoSuchEntityException
   *                          if the bean is not found in the database
   * @exception               javax.ejb.EJBException
   *                          if there is a communications or systems failure
   */
  public void ejbStore() {
    log("ejbStore (" + id() + ")");

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps = con.prepareStatement("update ejbAccounts set bal = ? where id = ?");
      ps.setDouble(1, balance);
      ps.setString(2, accountId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "ejbStore: AccountBean (" + accountId + ") not updated";
        log(error);
        throw new NoSuchEntityException (error);
      }
    } catch(SQLException sqe) {
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
      cleanup(con, ps);
    }
  }


  /**
   * This method corresponds to the create method in the home interface
   * "AccountHome.java".
   * The parameter sets of the two methods are identical. When the client calls
   * <code>AccountHome.create()</code>, the container allocates an instance
   * of this bean and calls <code>AccountBean.ejbCreate()</code>.
   * <p>
   * For bean-managed persistence, <code>ejbCreate()</code> returns
   * a primary key, unlike the case of container-managed
   * persistence, where it returns a void.
   *
   * @param accountId         String Account ID
   * @param initialBalance    double Initial Balance
   * @return                  String Primary Key
   * @exception               javax.ejb.CreateException
   *                          if there is a problem creating the bean
   * @exception               javax.ejb.DuplicateKeyException
   *                          if a create is attempted using a Primary Key
   *                          already in the database
   * @exception               javax.ejb.EJBException
   *                          if there is a communications or systems failure
   */
  public String ejbCreate(String accountId, double initialBalance)
    throws CreateException
  {
    log("AccountBean.ejbCreate( id = " + accountId + ", " + "initial balance = $ " + initialBalance + ")");
    this.accountId = accountId;
    this.balance = initialBalance;

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps = con.prepareStatement("insert into ejbAccounts (id, bal) values (?, ?)");
      ps.setString(1, accountId);
      ps.setDouble(2, balance);
      if (ps.executeUpdate() != 1) {
        String error = "JDBC did not create any row";
        log(error);
        throw new CreateException (error);
      }

      return accountId;
    } catch (SQLException sqe) {
      // Check to see if this SQLException is due to a unique constraint
      // violation on our database table (ie. there is already a pk with the
      // value of accountId in the table).  If so, throw a
      // DuplicateKeyException else throw a CreateException.
      try {
        ejbFindByPrimaryKey(accountId);
      } catch(ObjectNotFoundException onfe) {
        String error = "SQLException: " + sqe;
        log(error);
        throw new CreateException (error);
      }
      String error = "An Account already exists in the database with Primary Key " + accountId;
      log(error);
      throw new DuplicateKeyException(error);
    } finally {
      cleanup(con, ps);
    }
  }

  /**
   * Required by the EJB specification, this method is not used
   * by this example.
   *
   * @param accountId         String Account Identification
   * @param initialBalance    double Initial Balance
   */
  public void ejbPostCreate(String accountId, double initialBalance) {
    log("ejbPostCreate (" + id() + ")");
  }

  /**
   * Deletes the EJBean from the persistent storage.
   *
   * @exception               javax.ejb.NoSuchEntityException
   *                          if the bean is not found in the database
   * @exception               javax.ejb.EJBException
   *                          if there is a communications or systems failure
   */
  public void ejbRemove() {
    log("ejbRemove (" + id() + ")");
    // we need to get the primary key from the context because
    // it is possible to do a remove right after a find, and
    // ejbLoad may not have been called.

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      accountId = (String) ctx.getPrimaryKey();
      ps = con.prepareStatement("delete from ejbAccounts where id = ?");
      ps.setString(1, accountId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "AccountBean (" + accountId + " not found";
        log(error);
        throw new NoSuchEntityException (error);
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
      cleanup(con, ps);
    }
  }

  /**
   * Attempts to find the EJBean with a given Primary Key from
   * the persistent storage.
   *
   * @param pk                String Primary Key
   * @return                  String Primary Key
   * @exception               javax.ejb.ObjectNotFoundException
   *                          thrown if the EJBean cannot be found
   * @exception               javax.ejb.EJBException
   *                          if there is a communications or systems failure
   */
  public String ejbFindByPrimaryKey(String pk)
    throws ObjectNotFoundException
  {
    log("ejbFindByPrimaryKey (" + pk + ")");

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps  = con.prepareStatement("select bal from ejbAccounts where id = ?");
      ps.setString(1, pk);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        balance = rs.getDouble(1);
      } else {
        String error = "ejbFindByPrimaryKey: AccountBean (" + pk + ") not found";
        log(error);
        throw new ObjectNotFoundException (error);
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
      cleanup(con, ps);
    }

    log("ejbFindByPrimaryKey (" + pk + ") found");
    return pk;
  }

  /**
   * Finds all EJBeans with a balance greater than a given amount.
   * Returns an Collection of found EJBean primary keys.
   *
   * @param balanceGreaterThan double Test Amount
   * @return                   Collection EJBean Primary Keys
   * @exception                javax.ejb.EJBException
   *                           if there is a communications or systems failure
   */
  public Collection ejbFindBigAccounts(double balanceGreaterThan) {
    log("ejbFindBigAccounts (balance > " + balanceGreaterThan + ")");
    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps = con.prepareStatement("select id from ejbAccounts where bal > ?");
      ps.setDouble(1, balanceGreaterThan);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      Vector v = new Vector();
      String pk;
      while (rs.next()) {
        pk = rs.getString(1);
        v.addElement(pk);
      }
      return v;
    } catch (SQLException sqe) {
      log("SQLException: " + sqe);
      throw new EJBException (sqe);
    } finally {
      cleanup(con, ps);
    }
  }

  /**
   * Adds amount to balance.
   *
   * @param amount            double Amount
   * @return                  double balance
   */
  public double deposit(double amount) {
    log("Depositing $" + amount + " into '" + accountId + "'");
    balance += amount;
    return balance;
  }

  /**
   * Subtracts amount from balance.
   *
   * @param amount            double Amount
   * @return                  double Balance
   * @exception               ProcessingErrorException
   *                          if Amount &gt; Balance.
   */
  public double withdraw(double amount)
    throws ProcessingErrorException
  {
    log("Withdrawing $" + amount + " from '" + accountId + "'");
    if (amount > balance) {
      String error = "Request to withdraw $" + amount +
        " more than balance " + balance + " in account " + accountId;
      log(error);
      throw new ProcessingErrorException(error);
    }

    balance -= amount;
    return balance;
  }

  /**
   * Returns current balance.
   *
   * @return                  double Balance
   */
  public double balance() {
    return balance;
  }

  /**
   * Gets current connection to the connection pool.
   *
   * @return                  Connection
   * @exception               javax.ejb.EJBException
   *                          if there is a communications or systems failure
   */
  private Connection getConnection()
    throws SQLException
  {
    InitialContext initCtx = null;
    try {
      initCtx = new InitialContext();
      DataSource ds = (javax.sql.DataSource)
        initCtx.lookup("java:comp/env/jdbc/demoPool");
      return ds.getConnection();
    } catch(NamingException ne) {
      log("Failed to lookup JDBC Datasource. Please double check that");
      log("the JNDI name defined in the resource-description of the ");
      log("EJB's weblogic-ejb-jar.xml file is the same as the JNDI name ");
      log("for the Datasource defined in your config.xml.");
      throw new EJBException(ne);
    } finally {
      try {
        if(initCtx != null) initCtx.close();
      } catch(NamingException ne) {
        log("Error closing context: " + ne);
        throw new EJBException(ne);
      }
    }
  }

  // You might also consider using WebLogic's log service
  private void log(String s) {
    if (VERBOSE) System.out.println(s);
  }

  // Return a String that contains this beans id
  private String id() {
    return "PK = " + (String) ctx.getPrimaryKey();
  }

  private void cleanup(Connection con, PreparedStatement ps) {
    try {
      if (ps != null) ps.close();
    } catch (Exception e) {
      log("Error closing PreparedStatement: "+e);
      throw new EJBException (e);
    }

    try {
      if (con != null) con.close();
    } catch (Exception e) {
      log("Error closing Connection: " + e);
      throw new EJBException (e);

    }
  }
}

