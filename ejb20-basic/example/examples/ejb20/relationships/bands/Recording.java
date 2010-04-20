package examples.ejb20.relationships.bands;

import javax.ejb.EJBLocalObject;

/**
 */
public interface Recording extends EJBLocalObject 
{
  public String getTitle();
  public void setTitle(String val);

  public String getBandName();
  public void setBandName(String val);

  public String getBandFounder();
  public void setBandFounder(String val);

  public double getSales();
  public void setSales(double val);

  public long getNumberSold();
  public void setNumberSold(long val);

  public Band getBand();
  public void setBand(Band band);
}
