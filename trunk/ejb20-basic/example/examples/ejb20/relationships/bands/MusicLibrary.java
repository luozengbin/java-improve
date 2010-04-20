package examples.ejb20.relationships.bands;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Set;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBObject;

public interface MusicLibrary extends EJBObject 
{
  // Add / Remove Bands.  These methods also create the associated
  // Artist and FanClubs

  public void addBand(BandInfo bandInfo)
    throws RemoteException;

  public void addBands(Collection bandInfos)
    throws RemoteException;

  public void removeBand(BandPK bandPK)
    throws RemoteException;

  public void removeBand(BandInfo bandInfo)
    throws RemoteException;

  public void removeBands(Collection bandInfos)
    throws RemoteException;

  public void removeBandsThatExist(Collection bandInfos)
    throws RemoteException;

  public void removeBandsByPK(Collection bandPKs)
    throws RemoteException;


  // Add / Remove Recordings

  public void addRecording(RecordingInfo recInfo)
    throws RemoteException;

  public void addRecordings(Collection recInfos)
    throws RemoteException;

  public void removeRecording(RecordingPK recPK)
    throws RemoteException;

  public void removeRecording(RecordingInfo recInfo)
    throws RemoteException;

  public void removeRecordings(Collection recInfos)
    throws RemoteException;

  public void removeRecordingsThatExist(Collection recInfos)
    throws RemoteException;

  public void removeRecordingsByPK(Collection recPKs)
    throws RemoteException;

  // Utility Methods

  public Collection getBands() throws RemoteException;

  public Collection getRecordings(BandPK band) throws RemoteException;

  public Collection getRecordingsNotByBand(BandPK badBand) 
    throws RemoteException;

  public Set getAfterDateExcludeBand(Date date, BandPK badBandPK) 
    throws RemoteException;

  public String getWriteUpForBand(BandInfo bandInfo) throws RemoteException;
}
