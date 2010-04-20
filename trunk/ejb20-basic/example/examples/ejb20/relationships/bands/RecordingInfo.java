package examples.ejb20.relationships.bands;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Copyright (c) 2006 by BEA Systems. All Rights Reserved.
 */

public final class RecordingInfo implements Serializable {

  private String title;
  private String band;
  private String founder;
  private Date recDate;

  public RecordingInfo() {}

  public RecordingInfo(String t, String b, String f, Date rd) {
    title   = t;
    band    = b;
    founder = f;
    recDate = rd;
  }

  public String getTitle() { return title; }
  public void setTitle(String t) { title = t; }

  public String getBand() { return band; }
  public void setBand(String b) { band = b; }

  public String getFounder() { return founder; }
  public void setFounder(String f) { founder = f; }

  public Date getRecordingDate() { return recDate; }
  public void setRecordingDate(Date d) { recDate = d; }

  public String toString() {

    return "Recording: Title: "+title + 
      " Band: " + band + " Recording Date: "+recDate;


  }
  

}
