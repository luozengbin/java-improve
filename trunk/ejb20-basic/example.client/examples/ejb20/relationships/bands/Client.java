package examples.ejb20.relationships.bands;

import java.sql.Date;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * This example demonstrates EJB relationships.
 */
public class Client
{
  private String url;
  private String user;
  private String password;
  private MusicLibraryHome musicLibraryHome;

  public Client(String url, String user, String password) 
    throws NamingException
  {
    this.url       = url;
    this.user      = user;
    this.password  = password;
    musicLibraryHome = lookupMusicLibraryHome();
  }

  /**
   * Runs this example from the command line. Example:
   * <p>
   * <tt>java examples.ejb20.relationships.bands.Client "t3://localhost:7001" </tt>
   * <p>
   * The parameters are optional, but if any are supplied,
   * they are interpreted in this order:
   * <p>
   * @param args              URL such as "t3://localhost:7001" of Server
   *                          User name, default null
   *                          User password, default null
   */
  public static void main(String[] args) throws Exception {
    String url       = "t3://localhost:7001";
    String user      = "weblogic";
    String password  = "Manager123";
    Client client = null;

    // Parse the argument list
//    switch(args.length) {
//      case 3:
//        password  = args[2];
//        // fall through
//      case 2:
//        user      = args[1];
//        // fall through
//      case 1:
//        url       = args[0];
//        break;
//    }

    try {
      client = new Client(url, user, password);
      client.run();
    } catch (NamingException ne) {
      log("Unable to look up the home: " + ne.getMessage());
      throw ne;
    } catch (Exception e) {
      log("There was an exception while running the application." + e);
      throw e;
    }

    System.out.println("\nGood bye...\n");
  }

  /**
   * Example processing
   */
  public void run() throws Exception  {
    log("Welcome to the Hipster Music Library...\n");

    Collection bandInfos = createBandInfos();
    Collection recInfos  = createRecordingInfos();
    MusicLibrary musicLib = musicLibraryHome.create();

    // Remove any old data from the database.  
    musicLib.removeRecordingsThatExist(recInfos);
    musicLib.removeBandsThatExist(bandInfos);

    // Create the Bands in the Database
    musicLib.addBands(bandInfos);

    log("Added these bands...");

    Iterator it = bandInfos.iterator();

    while (it.hasNext()) { log(it.next().toString()); }
    log("\n");
    musicLib.addRecordings(recInfos);
    log("Added these Recordings...");
    it = recInfos.iterator();

    while (it.hasNext()) { log(it.next().toString()); }
    log("\n\n");
    log("Printing out Fan-Club Write-Up for each Band");
    it = bandInfos.iterator();

    while (it.hasNext()) {
      BandInfo bi = (BandInfo) it.next();
      log("Write-up for band: "+bi.getName() + " is: "+
        musicLib.getWriteUpForBand(bi));
    }

  }
  

  private Collection createBandInfos() {
    Collection bi = new ArrayList();

    bi.add(new BandInfo("Genesis", "Peter Gabriel",
      sqlDate(1969, Calendar.DECEMBER, 31)));
    
    bi.add(new BandInfo("Beastie Boys", "Mike Diamond", 
      sqlDate(1981, Calendar.DECEMBER, 31)));

    bi.add(new BandInfo("Britney Spears", "Britney Spears", 
      sqlDate(1997, Calendar.DECEMBER, 31)));

    bi.add(new BandInfo("Seth's Saxophone All-Stars", 
      "Saxophone Seth", sqlDate(1998, Calendar.NOVEMBER, 12)));

    return bi;
  }


  private Collection createRecordingInfos() {
    Collection ri = new ArrayList();

    ri.add(new RecordingInfo("Duke",
      "Genesis", 
      "Peter Gabriel",
      sqlDate(1982, Calendar.MARCH, 05)));

    ri.add(new RecordingInfo("Invisible Touch",
      "Genesis", 
      "Peter Gabriel",
      sqlDate(1985, Calendar.MARCH, 05)));

    ri.add(new RecordingInfo("Sounds of Science",
      "Beastie Boys", 
      "Mike Diamond",
      sqlDate(1997, Calendar.MARCH, 05)));

    ri.add(new RecordingInfo("Licensed To Ill",
      "Beastie Boys", 
      "Mike Diamond",
      sqlDate(1990, Calendar.MARCH, 05)));

    ri.add(new RecordingInfo("Paul's Boutique",
      "Beastie Boys", 
      "Mike Diamond",
      sqlDate(1989, Calendar.MARCH, 05)));

    ri.add(new RecordingInfo("Britney's First Albumn",
      "Britney Spears", 
      "Britney Spears",
      sqlDate(1999, Calendar.MARCH, 05)));

    ri.add(new RecordingInfo("Saxophone Seth Plays the Blues",
      "Seth's Saxophone All-Stars",
      "Saxophone Seth",
      sqlDate(2001, Calendar.MARCH, 23)));

    return ri;

  }

  /**
   * Look up the MusicLibrary bean's home interface using JNDI.
   */
  private MusicLibraryHome lookupMusicLibraryHome() throws NamingException {
    Context ctx = getInitialContext();
    try {
      Object home = 
        (MusicLibraryHome)ctx.lookup("MusicLibraryEJB.MusicLibraryHome");
      return (MusicLibraryHome)PortableRemoteObject.narrow(home,
        MusicLibraryHome.class);
    } catch (NamingException ne) {
      log("The client was unable to lookup the EJBHome.  Please make sure " +
        "that you have deployed the ejb with the JNDI name " + 
        "MusicLibraryEJB.MusicLibraryHome on the WebLogic server at "+url);
      throw ne;
    }
  }

  /**
   * Get an initial context into the JNDI tree.
   */
  private Context getInitialContext() throws NamingException {
    try {
      // Get an InitialContext
      Hashtable h = new Hashtable();
      h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
      h.put(Context.PROVIDER_URL, url);
      if (user != null) {
        log ("user: " + user);
        h.put(Context.SECURITY_PRINCIPAL, user);
        if (password == null) 
          password = "";
        h.put(Context.SECURITY_CREDENTIALS, password);
      } 
      return new InitialContext(h);
    } catch (NamingException ne) {
      log("We were unable to get a connection to the WebLogic server at "+url);
      log("Please make sure that the server is running.");
      throw ne;
    }
  }

  private Date sqlDate(int year, int month, int date)  {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, date);
    return new Date(cal.getTime().getTime());
  }

  private static void log(String s) { System.out.println(s); }
}
