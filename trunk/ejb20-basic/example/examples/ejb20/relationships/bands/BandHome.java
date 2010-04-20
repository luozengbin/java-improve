package examples.ejb20.relationships.bands;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

public interface BandHome extends EJBLocalHome 
{

  public Band create(String name, String founder, Date startDate) 
    throws CreateException;

  public Band findByPrimaryKey(BandPK primaryKey) 
    throws FinderException;

  public Collection findByName(String name)
    throws FinderException;

  public Collection findAll()
    throws FinderException;
}
