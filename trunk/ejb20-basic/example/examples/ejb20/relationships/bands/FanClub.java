package examples.ejb20.relationships.bands;

import java.sql.Date;
import java.util.Collection;
import java.util.Set;

import javax.ejb.EJBLocalObject;

public interface FanClub extends EJBLocalObject {

  public String getBandName();
  public String getBandFounder();
  public String getText();

  public Band getBand();

  public Set getAfterDateExcludeBand(Date recordDate, Band badBand);
}
