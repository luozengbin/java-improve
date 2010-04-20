package examples.ejb20.relationships.bands;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

public interface RecordingHome extends EJBLocalHome {

  public Recording create(String title, String band, String founder,
    Date recDate) 
    throws CreateException;

  public Recording findByPrimaryKey(RecordingPK primaryKey) 
    throws FinderException;
       
  public Collection findBigRecordings(long sales)
    throws FinderException;

  public Collection findByTitle(String title)
    throws FinderException;

  public Collection findNotByBand(Band badBand)
    throws FinderException;
}
