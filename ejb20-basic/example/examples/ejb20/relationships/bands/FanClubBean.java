package examples.ejb20.relationships.bands;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
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


abstract public class FanClubBean implements EntityBean 
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
  abstract public String getBandName();
  abstract public void setBandName(String val);

  abstract public String getBandFounder();
  abstract public void setBandFounder(String val);

  abstract public String getText();
  abstract public void setText(String val);

  abstract public String getMemberCount();
  abstract public void setMemberCount(String val);

  abstract public Band getBand();
  abstract public void setBand(Band band);

  abstract public Set ejbSelectAfterDateExcludeBand(
    java.sql.Date recordDate,
    Band badBand) throws javax.ejb.FinderException;

  public Set getAfterDateExcludeBand(
    java.sql.Date recordDate,
    Band badBand)
  {
    try {
      Set returnSet = new HashSet();
      
      Iterator result = 
        ejbSelectAfterDateExcludeBand(recordDate, badBand).iterator();
      while (result.hasNext()) {
        Recording rec = (Recording)result.next();
        returnSet.add(rec.getTitle());
      }

      return returnSet;
    }
    catch (Exception e) {
      throw new EJBException(e);
    }
  }

  public void ejbActivate() {}

  public void ejbPassivate() {}

  public void ejbLoad() {}
  public void ejbStore() {}
  public void ejbRemove() {}

  /**
   */
  public FanClubPK ejbCreate(String bandName, String bandFounder, String text) 
  {
    setBandName(bandName);
    setBandFounder(bandFounder);
    setText(text);

    return null;  // See 9.4.2 of the EJB 1.1 specification
  }

  /**
   */
  public void ejbPostCreate(String bandName, String bandFounder, 
    String text) {}

}
