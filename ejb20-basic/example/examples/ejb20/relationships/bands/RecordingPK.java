package examples.ejb20.relationships.bands;

public class RecordingPK implements java.io.Serializable 
{
  public String title;
  public String bandName;
  public String bandFounder;

  private int hashCode = -1;

  public RecordingPK() {}

  public RecordingPK(String title, String bandName, String bandFounder) 
  {
    this.title = title;
    this.bandName = bandName;
    this.bandFounder = bandFounder;
  }

  public boolean equals(Object other) 
  {
    if (other == this) return true;

    if (!(other instanceof RecordingPK))
      return false;
 
    RecordingPK otherPK = (RecordingPK)other;

    if (otherPK.hashCode() == hashCode()) {

      return title.equals(otherPK.title) && 
        bandName.equals(otherPK.bandName) &&
        bandFounder.equals(otherPK.bandFounder);
    } else {
      return false;
    }
  }
 
  public int hashCode(){

    if (hashCode == -1) {

      hashCode = title.hashCode() ^ bandName.hashCode() ^ 
        bandFounder.hashCode();
    }

    return hashCode;
  }

  public String toString() {
    return "(" + title + ", " + bandName + ", " + bandFounder + ")";
  }
}
