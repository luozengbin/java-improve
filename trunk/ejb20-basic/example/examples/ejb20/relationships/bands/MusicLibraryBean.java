package examples.ejb20.relationships.bands;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import javax.ejb.CreateException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.rmi.PortableRemoteObject;

public class MusicLibraryBean implements SessionBean {

  private SessionContext ctx;

  private BandHome bandHome;
  private RecordingHome recordingHome;
  private FanClubHome fanClubHome;
  private ArtistHome artistHome;
	
  //
  // business methods
  //

  // Add / Remove Bands

  public void addBand(BandInfo bandInfo) {

    try {
      Band b = bandHome.create(bandInfo.getName(), bandInfo.getFounder(),
        bandInfo.getStartDate());

      FanClub fc = fanClubHome.create(bandInfo.getName(),
        bandInfo.getFounder(), bandInfo.getName() + " Fan Club");

      Set bands = new HashSet();
      bands.add(b);

      Artist ar = artistHome.create(bandInfo.getName(), bands);

    } catch (CreateException ce) {
      throw new EJBException(ce);
    }
  }

  public void addBands(Collection bandInfos) {

    Iterator it = bandInfos.iterator();

    while (it.hasNext()) {
      addBand((BandInfo) it.next());
    }
  }

  public void removeBand(BandPK bandPK) {
    
    try {
      bandHome.remove(bandPK);
    } catch (RemoveException re) {
      throw new EJBException(re);
    }
  }


  public void removeBand(BandInfo bandInfo) {

    BandPK pk = new BandPK(bandInfo.getName(), bandInfo.getFounder());

    removeBand(pk);
  }


  public void removeBands(Collection bandInfos) {

    Iterator it = bandInfos.iterator();

    while (it.hasNext()) {
      removeBand((BandInfo)it.next());
    }
  }

  public void removeBandsThatExist(Collection bandInfos) {

    Iterator it = bandInfos.iterator();

    while (it.hasNext()) {

      BandInfo bandInfo = (BandInfo) it.next();

      BandPK pk = new BandPK(bandInfo.getName(),
        bandInfo.getFounder());

      try {
        Band b = bandHome.findByPrimaryKey(pk);
        b.remove();
      } catch (FinderException ignore) {
      } catch (RemoveException re) {
        throw new EJBException(re);
      }
    }
  }


  public void removeBandsByPK(Collection bandPKs) {

    Iterator it = bandPKs.iterator();

    while (it.hasNext()) {
      removeBand((BandPK)it.next());
    }
  }    

  // Add / Remove Recordings

  public void addRecording(RecordingInfo recInfo) {
    
    try {
      recordingHome.create(recInfo.getTitle(), recInfo.getBand(),
        recInfo.getFounder(), recInfo.getRecordingDate());
    } catch (CreateException ce) {
      throw new EJBException(ce);
    }
  }

  public void addRecordings(Collection recInfos) {

    Iterator it = recInfos.iterator();

    while (it.hasNext()) {
      addRecording((RecordingInfo)it.next());
    }
  }

  public void removeRecording(RecordingPK recPK) {

    try {
      recordingHome.remove(recPK);
    } catch (RemoveException re) {
      throw new EJBException(re);
    }
  }


  public void removeRecording(RecordingInfo recInfo) {

    RecordingPK pk = new RecordingPK(recInfo.getTitle(),
      recInfo.getBand(), recInfo.getFounder());

    removeRecording(pk);
  }


  public void removeRecordings(Collection recInfos) {

    Iterator it = recInfos.iterator();

    while (it.hasNext()) {

      removeRecording((RecordingInfo)it.next());
    }
  }

  public void removeRecordingsThatExist(Collection recInfos) {

    Iterator it = recInfos.iterator();

    while (it.hasNext()) {

      RecordingInfo recInfo = (RecordingInfo) it.next();

      RecordingPK pk = new RecordingPK(recInfo.getTitle(),
        recInfo.getBand(), recInfo.getFounder());

      try {
        Recording r = recordingHome.findByPrimaryKey(pk);
        r.remove();
      } catch (FinderException ignore) {
      } catch (RemoveException re) {
        throw new EJBException(re);
      }
    }
  }

  public void removeRecordingsByPK(Collection recPKs) {

    Iterator it = recPKs.iterator();

    while (it.hasNext()) {

      removeRecording((RecordingPK)it.next());
    }
  }

  public Collection getBands() {
    try {
      Iterator bands = bandHome.findAll().iterator();
      Collection result = new ArrayList();

      while (bands.hasNext()) {
        Band band = (Band)bands.next();
        result.add(band.getPrimaryKey());
      }

      return result;
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  public void addRecording(String title, String band, String founder,
    Date recDate) 
  {
    try {
      recordingHome.create(title, band, founder, recDate);
    } catch (CreateException ce) {
      throw new EJBException(ce);
    }
  }
  
  public void removeRecording(String title, String band, String founder) 
  {
    try {
      recordingHome.remove(new RecordingPK(title, band, founder));
    } catch (RemoveException re) {
      throw new EJBException(re);
    }
  }

  public Collection getRecordings(BandPK bandPK) {
    try {
      Collection recordings = new ArrayList();
      Band band = bandHome.findByPrimaryKey(bandPK);

      Iterator records = band.getRecordings().iterator();
      while (records.hasNext()) {
        Recording record = (Recording)records.next();
        recordings.add(record.getTitle());
      }

      return recordings;
    } 
    catch (Exception e) {
      throw new EJBException(e);
    }
  }

  public Collection getRecordingsNotByBand(BandPK badBandPK) {
    try {
      Collection recordings = new ArrayList();
      Band band = bandHome.findByPrimaryKey(badBandPK);

      Iterator records = recordingHome.findNotByBand(band).iterator();
      while (records.hasNext()) {
        Recording record = (Recording)records.next();
        recordings.add(record.getTitle());
      }

      return recordings;
    } 
    catch (Exception e) {
      throw new EJBException(e);
    }
  }

  public Set getAfterDateExcludeBand(Date date, BandPK badBandPK) {
    try {
      Collection recordings = new ArrayList();
      Band band = bandHome.findByPrimaryKey(badBandPK);

      FanClub fanClub = fanClubHome.findByPrimaryKey(
        new FanClubPK(badBandPK.name, badBandPK.founder));

      return fanClub.getAfterDateExcludeBand(date, band);
    } 
    catch (Exception e) {
      throw new EJBException(e);
    }
  }


  public String getWriteUpForBand(BandInfo bandInfo) {

    try {
      Band band = bandHome.findByPrimaryKey(new
        BandPK(bandInfo.getName(), bandInfo.getFounder()));
      return band.getFanClub().getText();
    }
    catch (Exception e) {
      throw new EJBException(e);
    }
  }


  //
  // EJB lifecycle methods
  //
  public void setSessionContext(SessionContext c) {

    ctx = c;

    try {
      Context ic    = new InitialContext();

      bandHome      = (BandHome)      ic.lookup("BandEJB.BandHome");
      recordingHome = (RecordingHome) ic.lookup("RecordingEJB.RecordingHome");
      fanClubHome   = (FanClubHome)   ic.lookup("FanClubEJB.FanClubHome");
      artistHome    = (ArtistHome)    ic.lookup("ArtistEJB.ArtistHome");

    } catch (NamingException ne) {
      throw new EJBException(ne);
    }
  }



  public void ejbCreate() {}

  public void ejbActivate() {}

  public void ejbPassivate() {}

  public void ejbRemove() {}


}











