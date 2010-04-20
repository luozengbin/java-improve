package examples.ejb20.relationships.bands;

import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface ArtistHome extends EJBLocalHome {

  public Artist create(String name, Set bands)
    throws CreateException;

  public Artist findByPrimaryKey(Integer pk)
    throws FinderException;
}
