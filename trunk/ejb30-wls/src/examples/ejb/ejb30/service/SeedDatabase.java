/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.service;

import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.ejb.ejb30.domain.*;

/**
 * Populates a database with some sample instances of Book, Music, Movie,
 * Reviewer and their review comments.<BR>
 * The input to this standalone program is a properties file that specifies the
 * detail sof each instance to be created.
 *
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 */
public class SeedDatabase extends PersistenceService {

  public SeedDatabase(EntityManagerFactory emf) {
    super(emf);
  }

  /**
   * Create Books. Total number of Book to create is specified by
   * <p/>
   * <p/>
   * <pre>
   * Book.Total = N
   * </pre>
   * <p/>
   * where <code>N</code> is a positive number.
   * <p/>
   * Each Book is specified as
   * <p/>
   * <p/>
   * <pre>
   *      Book[&lt;n&gt;] = title,author,[,pageCount]
   * </pre>
   * <p/>
   * where <code>n</code> is a integer between 1 and <code>Book.Total</code>
   * both inclusive. Ignores if named Book exists.
   */

  void createBooks(Properties dataFile) {
    int nBook = getIntProperty(dataFile, "Book.Total", 0);
    if (nBook <= 0) {
      warn("No instance of Book. Use [Book.Total] to specify");
      return;
    }
    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    for (int i = 1; i <= nBook; i++) {
      String key = "Book[" + i + "]";
      String details = dataFile.getProperty(key, "");
      String[] fields = details.split(",");
      if (fields.length < 2) {
        warn("Property ["+key+"="+details+"] must have at least 2 fields");
        continue;
      }
      String title = fields[0].trim();
      String artistName = fields[1].trim();
      int pageCount = (fields.length > 2) ? Integer.parseInt(fields[2].trim())
          : 0;

      Artist artist = em.find(Artist.class, artistName);
      if (artist == null) {
        warn("Property ["+key+"="+details+"] specifies unknown Artist ["
            + artistName + "]");
        continue;
      }
      List result = em.createQuery("SELECT i FROM Book i WHERE i.title=:title")
          .setParameter("title", title).getResultList();
      Book book = (result.isEmpty()) ? null : (Book) result.get(0);
      if (book == null) {
        book = new Book(title);
        book.setArtist(artist);
        book.setPageCount(pageCount);
        em.persist(book);
      } else {
        warn("Property ["+key+"="+details+"] specifies an existing item");
      }
    }
    em.getTransaction().commit();
  }

  void createMusics(Properties dataFile) {
    int nItem = getIntProperty(dataFile, "Music.Total", 0);
    if (nItem <= 0) {
      warn("No instance of Music. Use [Music.Total] to specify");
      return;
    }
    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    for (int i = 1; i <= nItem; i++) {
      String key = "Music[" + i + "]";
      String details = dataFile.getProperty(key, "");
      String[] fields = details.split(",");
      if (fields.length < 2) {
        warn("Property ["+key+"="+details+"] must have at least 2 fields");
        continue;
      }
      String title = fields[0].trim();
      String artistName = fields[1].trim();
      int duration = (fields.length > 2) ? Integer.parseInt(fields[2].trim())
          : 0;

      Artist artist = em.find(Artist.class, artistName);
      if (artist == null) {
        warn("Property ["+key+"="+details+"] specifies unknown Artist ["
            + artistName + "]");
        continue;
      }
      List result = em
          .createQuery("SELECT i FROM Music i WHERE i.title=:title")
          .setParameter("title", title).getResultList();
      Music music = (result.isEmpty()) ? null : (Music) result.get(0);
      if (music == null) {
        music = new Music(title);
        music.setArtist(artist);
        music.setDuration(duration);
        em.persist(music);
      } else {
        warn("Property ["+key+"="+details+"] specifies an existing item");
      }
    }
    em.getTransaction().commit();
  }

  void createMovies(Properties dataFile) {
    int nItem = getIntProperty(dataFile, "Movie.Total", 0);
    if (nItem <= 0) {
      warn("No instance of Movie. Use [Movie.Total] to specify");
      return;
    }
    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    for (int i = 1; i <= nItem; i++) {
      String key = "Movie[" + i + "]";
      String details = dataFile.getProperty(key, "");
      String[] fields = details.split(",");
      if (fields.length < 2) {
        warn("Property ["+key+"="+details+"] must have at least 2 fields");
        continue;
      }
      String title = fields[0].trim();
      String artistName = fields[1].trim();
      int duration = (fields.length > 2) ? Integer.parseInt(fields[2].trim())
          : 0;

      Artist artist = em.find(Artist.class, artistName);
      if (artist == null) {
        warn("Property ["+key+"="+details+"] specifies unknown Artist ["
            + artistName + "]");
        continue;
      }
      List result = em
          .createQuery("SELECT i FROM Movie i WHERE i.title=:title")
          .setParameter("title", title).getResultList();
      Movie movie = (result.isEmpty()) ? null : (Movie) result.get(0);
      if (movie == null) {
        movie = new Movie(title);
        movie.setArtist(artist);
        movie.setDuration(duration);
        em.persist(movie);
      } else {
        warn("Property ["+key+"="+details+"] specifies an existing item");
      }
    }
    em.getTransaction().commit();
  }

  /**
   * Create Reviewers. Total number of Reviewer to create is specified by
   * <p/>
   * <p/>
   * <pre>
   * Reviewer.Total = N
   * </pre>
   * <p/>
   * where <code>N</code> is a positive number.
   * <p/>
   * Each Reviewer is specified as
   * <p/>
   * <p/>
   * <pre>
   *      Reviewer[&lt;n&gt;] = name[,MALE|FEMALE]
   * </pre>
   * <p/>
   * where <code>n</code> is a integer between 1 and
   * <code>Reviewer.Total</code> both inclusive. Ignores if named Reviewer
   * exists.
   */
  void createReviewers(Properties dataFile) {
    int nReviewer = getIntProperty(dataFile, "Reviewer.Total", 0);
    if (nReviewer <= 0) {
      warn("No instance of Reviewer. Use [Reviewer.Total] to specify");
      return;
    }

    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    for (int i = 1; i <= nReviewer; i++) {
      String key = "Reviewer[" + i + "]";
      String details = dataFile.getProperty(key);
      String[] fields = details.split(",");
      if (fields.length < 1) {
        warn("Property ["+key+"="+details+"] must have at least 1 field");
        continue;
      }
      String name = fields[0].trim();
      Person.Gender gender = (fields.length > 1) ? Person.Gender
          .valueOf(fields[1].trim()) : Person.Gender.MALE;

      Reviewer reviewer = em.find(Reviewer.class, name);
      if (reviewer == null) {
        reviewer = new Reviewer(name, gender);
        em.persist(reviewer);
      } else {
        warn("Property ["+key+"="+details+"] specifies an existing Reviewer");
      }

    }
    em.getTransaction().commit();
  }

  /**
   * Create artists. Total number of artist to create is specified by
   * <p/>
   * <p/>
   * <pre>
   * Artist.Total = N
   * </pre>
   * <p/>
   * where <code>N</code> is a positive number.
   * <p/>
   * Each artist is specified as
   * <p/>
   * <p/>
   * <pre>
   *      Artist[&lt;n&gt;] = name
   * </pre>
   * <p/>
   * where <code>n</code> is a integer between 1 and <code>Artist.Total</code>
   * both inclusive. Ignores if named artist exists.
   */
  void createArtists(Properties dataFile) {
    int nArtist = getIntProperty(dataFile, "Artist.Total", 0);
    if (nArtist <= 0) {
      warn("No instance of Artist. Use [Artist.Total] to specify");
      return;
    }

    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    for (int i = 1; i <= nArtist; i++) {
      String key = "Artist[" + i + "]";
      String details = dataFile.getProperty(key);
      String[] fields = details.split(",");
      if (fields.length < 1) {
        warn("Property ["+key+"="+details+"] must have at least 1 field");
        continue;
      }
      String name = fields[0].trim();

      Artist artist = em.find(Artist.class, name);
      if (artist == null) {
        artist = new Artist(name);
        em.persist(artist);
      } else {
        warn("Property ["+key+"="+details+"] specifies an existing Artist");
      }
    }
    em.getTransaction().commit();
  }

  void createReviews(Properties dataFile) {
    int nReview = Integer.parseInt(dataFile.getProperty("Review.Total", "0"));
    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    for (int i = 1; i <= nReview; i++) {
      String key = "Review[" + i + "]";
      String details = dataFile.getProperty(key, "");
      String[] fields = details.split(",");
      if (fields.length != 4) {
        warn("Property ["+key+"="+details+"] must have at least 4 fields");
        continue;
      }
      String reviewerName = fields[0].trim();
      String reviewedName = fields[1].trim();
      int rating = Integer.parseInt(fields[2].trim());
      String comment = fields[3].trim();

      Reviewer reviewer = em.find(Reviewer.class, reviewerName);
      if (reviewer == null) {
        warn("Property [" + key + "=" + details + "] specified an unknown " +
            " Reviewer [" + reviewerName + "]");
        continue;
      }
      List result = em.createQuery("SELECT i FROM Item i WHERE i.title=:title")
          .setParameter("title", reviewedName).getResultList();
      Item reviewed = (result.isEmpty()) ? null : (Item) result.get(0);
      if (reviewed == null) {
        warn("Property [" + key + "=" + details + "] specified an unknown " +
            " Item [" + reviewedName + "]");
        continue;
      }

      Review review = new Review(reviewer, reviewed, rating, comment);

      em.persist(review);
    }
    em.getTransaction().commit();
  }

  /**
   * Deletes all known classes from the database. JPQL allows
   * <code>DELETE</code> statement in query, so that the database records can
   * be deleted without being first fetched to memory as object instances.
   */
  public void clearDatabase() {
    warn("Deleting the records from the database");
    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    String[] classes = {"Review","Item", "Artist", "Reviewer"};
    for (String className : classes) {
      Query delete = em.createQuery("DELETE FROM " + className + " i");
      delete.executeUpdate();
    }
    em.getTransaction().commit();

  }

  /**
   * Detects if the database is empty by querying for any item.
   *
   * @return false if the query returns item(s), true otherwise
   */
  private boolean isEmpty() {
    EntityManager em = getEntityManager();
    return em.createQuery("SELECT i FROM Item i").getResultList().isEmpty();
  }

  /**
   * Populates the database with data specification available in the supplied
   * properties file.
   *
   * @param inputFile is a text-based properties file that lists some data for Items,
   *              Artists, Reviewers and their Reviews.
   */
  public void generateData(String inputFile) {
    warn("Creating new records from " + inputFile);
    Properties data = createFromFile(inputFile);

    createReviewers(data);
    createArtists(data);

    createBooks(data);
    createMusics(data);
    createMovies(data);

    createReviews(data);
  }

  private void warn(String s) {
    System.err.println("WARN: " + s);
  }

  private int getIntProperty(Properties data, String key, int defaultVal) {
    String value = data.getProperty(key);
    if (value == null)
      return defaultVal;
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return defaultVal;
    }
  }

  protected Properties createFromFile(String file) {
    Properties result = new Properties();
    try {
      InputStream is = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream(file);
      if (is == null)
        is = getClass().getResourceAsStream(file);
      if (is == null)
        throw new RuntimeException("Can not load from [" + file + "]. "
            + "Make sure the file exists in the classpath");
      result.load(is);
    } catch (IOException e) {
      throw new RuntimeException("Error loading properties from [" + file + "]");
    }
    return result;
  }

  /**
   * Ensures that database is populated. First executes a query to check if any
   * item exists. If any item exists then assumes that database is populated and
   * returns. Otherwise populates from the default data file
   * <code>META-INF/data.properties</code>.
   * <p/>
   * This is provided for out-of-the-box samples to work in uninitialized
   * database environment.
   */
  public void ensureDatabaseInitialzed() {
    if (!isEmpty())
      return;
    generateData("/META-INF/data.properties");
  }

  /**
   * Populates a database. Usage: java java
   * examples.ejb.ejb30.service_.SeedDatabase [-f datafile] [-clean] if
   * <code>-f dataFile</code> is specified then reads data from that file.
   * Otherwise takes <code>META-INF/data.properties</code> as default.
   * <p/>
   * if <code>-clean</code>, then deletes all records before populating. if
   * <code>-clean</code> and no <code>-f datafile</code> is specified, then
   * only cleans.
   *
   * @param args [-f dataFile] [-clean]
   */
  public static void main(String[] args) throws Exception {
    System.out.println("Usage: java examples.ejb.ejb30.service_.SeedDatabase "
        + "[-f datafile] [-clean]");
    boolean clean = isOptionPresent("-clean", args);
    String dataFile = getOptionValue("-f", args);
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("reviewService");
    try {
      SeedDatabase dataGenerator = new SeedDatabase(entityManagerFactory);
      if (clean) {
        System.out.println("Deleting all records...");
        dataGenerator.clearDatabase();
      }
      if (dataFile == null) {
        dataFile = "/META-INF/data.properties";
        System.out.println("Populating from default " + dataFile);
      } else {
        System.out.println("Populating from " + dataFile);
      }
      dataGenerator.generateData(dataFile);
    } finally {
      entityManagerFactory.close();
    }
  }

  static boolean isOptionPresent(String option, String[] args) {
    if (args == null || args.length == 0)
      return false;
    for (String arg : args) {
      if (arg.equals(option)) {
        return true;
      }
    }
    return false;
  }

  static String getOptionValue(String option, String[] args) {
    if (args == null || args.length == 0)
      return null;
    for (int i = 0; i < args.length - 1; i++)
      if (args[i].equals(option))
        return args[i + 1];
    return null;

	}
}
