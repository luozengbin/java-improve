package examples.ejb20.relationships.bands;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.CreateException;

public interface MusicLibraryHome extends EJBHome {

  public MusicLibrary create() 
    throws CreateException, RemoteException;
}
