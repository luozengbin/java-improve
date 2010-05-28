/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.exceptions;

import javax.ejb.ApplicationException;

/**
 * This shows how RuntimeException may be marked as ApplicationException in EJB30.
 */
@ApplicationException(rollback = true)
public class ObjectExistedException extends RuntimeException{
  public ObjectExistedException(String string) {
    super(string);
  }
}
