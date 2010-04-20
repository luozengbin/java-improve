package examples.ejb20.relationships.bands;

import java.sql.Date;
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

abstract public class BandBean implements EntityBean 
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
  abstract public String getName();
  abstract public void setName(String val);

  abstract public String getFounder();
  abstract public void setFounder(String val);

  abstract public java.sql.Date getStartDate();
  abstract public void setStartDate(java.sql.Date val);

  abstract public Set getArtists();
  abstract public void setArtists(Set val);

  abstract public Set getRecordings();
  abstract public void setRecordings(Set val);

  abstract public FanClub getFanClub();
  abstract public void setFanClub(FanClub val);

  public void ejbActivate() {}

  public void ejbPassivate() {}

  public void ejbLoad() {}

  public void ejbStore() {}

  public void ejbRemove() {}

  public BandPK ejbCreate(String name, String founder, Date startDate) 
    throws CreateException
  {
    setName(name);
    setFounder(founder);

    if (startDate.getTime()>System.currentTimeMillis()) {
      throw new CreateException("Invalid start date for '" + name + "'.");
    }
    setStartDate(startDate);

    return null;  // See 9.4.2 of the EJB 1.1 specification
  }

  public void ejbPostCreate(String name, String founder, 
    Date startDate) {}

}
