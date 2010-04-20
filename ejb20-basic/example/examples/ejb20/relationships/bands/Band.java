package examples.ejb20.relationships.bands;

import java.sql.Date;
import java.util.Set;

import javax.ejb.EJBLocalObject;


public interface Band extends EJBLocalObject 
{
  public String getName();
  public String getFounder();

  public Date getStartDate();
  public Set getRecordings();

  public FanClub getFanClub();
}
