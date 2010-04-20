package examples.ejb20.relationships.bands;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Copyright (c) 2006 by BEA Systems. All Rights Reserved.
 */

public final class BandInfo implements Serializable {

  private String name;
  private String founder;
  private Date startDate;

  public BandInfo() {}

  public BandInfo(String n, String f, Date sd) {
    name      = n;
    founder   = f;
    startDate = sd;
  }

  public String getName() { return name; }
  public void setName(String n) { name = n; }

  public String getFounder() { return founder; }
  public void setFounder(String f) { founder = f; }

  public Date getStartDate() { return startDate; }
  public void setStartDate(Date sd) { startDate = sd; }

  public String toString() {

    return "Band Name: " + name 
      + " Band Founder: "+founder+ " startDate: "+startDate;
  }

}
