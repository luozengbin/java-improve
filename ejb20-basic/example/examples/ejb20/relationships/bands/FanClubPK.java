package examples.ejb20.relationships.bands;

public class FanClubPK implements java.io.Serializable 
{
  public String bandName;
  public String bandFounder;

  private int hashCode = -1;

  public FanClubPK() {}

  public FanClubPK(String bandName, String bandFounder) 
  {
    this.bandName = bandName;
    this.bandFounder = bandFounder;
  }

  public boolean equals(Object other) 
  {
    if (other == this) return true;

    if (!(other instanceof FanClubPK))
      return false;

    FanClubPK otherPK = (FanClubPK)other;

    if (otherPK.hashCode() == hashCode()) {
      
      return bandName.equals(otherPK.bandName) &&
        bandFounder.equals(otherPK.bandFounder);
    } else {
      return false;
    }
  }
 
  public int hashCode(){

    if (hashCode == -1) {
      hashCode = bandName.hashCode() ^ bandFounder.hashCode();
    }

    return hashCode;
  }

  public String toString() {
    return "(" + bandName + ", " + bandFounder + ")";
  }
}
