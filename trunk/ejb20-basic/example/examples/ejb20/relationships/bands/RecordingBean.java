package examples.ejb20.relationships.bands;

import java.io.Serializable;
import java.sql.Date;
import java.util.Enumeration;
import java.util.Vector;

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


/**
 */
abstract public class RecordingBean implements EntityBean 
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
  abstract public String getTitle();
  abstract public void setTitle(String val);

  abstract public String getBandName();
  abstract public void setBandName(String val);

  abstract public String getBandFounder();
  abstract public void setBandFounder(String val);

  abstract public double getSales();
  abstract public void setSales(double val);

  abstract public long getNumberSold();
  abstract public void setNumberSold(long val);

  abstract public Date getRecordingDate();
  abstract public void setRecordingDate(Date val);

  abstract public Band getBand();
  abstract public void setBand(Band band);


  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void ejbLoad() {}
  public void ejbStore() {}

  public void ejbRemove() {}

  public RecordingPK ejbCreate(String title, String band, String founder,
    Date recDate) 
  {
    setTitle(title);
    setBandName(band);
    setBandFounder(founder);
    setSales(0d);
    setNumberSold(0l);
    setRecordingDate(recDate);

    return null;  // See 9.4.2 of the EJB 1.1 specification
  }

  public void ejbPostCreate(String title, String band, String founder,
    Date recDate) {}

}
































