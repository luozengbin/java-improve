package examples.ejb.ejb30.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BadCommentsException extends RuntimeException{
  public BadCommentsException(String string) {
    super(string);
  }
}
