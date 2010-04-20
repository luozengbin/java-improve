package examples.ejb20.relationships.bands;

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


abstract public class ArtistBean implements EntityBean 
{
  private EntityContext ctx;

  public void setEntityContext(EntityContext c) {
    ctx = c;
  }

  public void unsetEntityContext() {
    ctx = null;
  }

  /**
   * container managed fields
   */
  abstract public Integer getId();
  abstract public void setId(Integer val);

  abstract public String getName();
  abstract public void setName(String val);

  abstract public Set getBands();
  abstract public void setBands(Set val);

  public void ejbActivate() {}
  public void ejbPassivate() {}

  public void ejbLoad() {}
  public void ejbStore() {}

  public void ejbRemove() {}

  public Integer ejbCreate(String name, Set bands) {
    setName(name);

    return null;  // See 9.4.2 of the EJB 1.1 specification
  }


  public void ejbPostCreate(String name, Set bands) {
    setBands(bands);
  }

}
