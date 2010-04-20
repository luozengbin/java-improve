package examples.ejb20.relationships.bands;

import java.util.Collection;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

/**
 */
public interface FanClubHome extends EJBLocalHome 
{
  /**
   */
  public FanClub create(String name, String founder, String text) 
    throws CreateException;

  /**
   */
  public FanClub findByPrimaryKey(FanClubPK primaryKey) 
    throws FinderException;

  /**
   */
  public Collection findBigClubs(int minMembers) 
    throws FinderException;
}
