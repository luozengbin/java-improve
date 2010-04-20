package examples.ejb20.relationships.bands;

import java.util.Set;

import javax.ejb.EJBLocalObject;

public interface Artist extends EJBLocalObject {

  public String getName();
  public Integer getId();

  public Set getBands();

}
