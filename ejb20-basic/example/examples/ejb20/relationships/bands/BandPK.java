package examples.ejb20.relationships.bands;

public class BandPK implements java.io.Serializable 
{
  public String name;
  public String founder;

  private int hashCode = -1;

  public BandPK() {}

  public BandPK(String name, String founder) 
  {
    this.name = name;
    this.founder = founder;
  }

  public boolean equals(Object other) 
  {
    if (other == this) return true;

    if (!(other instanceof BandPK))
      return false;
    
    BandPK otherPK = (BandPK)other;

    if (otherPK.hashCode() == hashCode()) {
      return name.equals(otherPK.name) &&
        founder.equals(otherPK.founder);
    } else {
      return false;
    }
  }
  
  public int hashCode() {
    if (hashCode == -1) {
      hashCode = name.hashCode() ^ founder.hashCode();
    }

    return hashCode;
  }

  public String toString() {
    return "(" + name + ", " + founder + ")";
  }
}
