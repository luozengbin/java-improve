/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.exceptions;

public class NotLoggedInException extends Exception{
  public NotLoggedInException() {
    super();
  }

  public NotLoggedInException(String string) {
    super(string);
  }

  public NotLoggedInException(String string, Throwable throwable) {
    super(string, throwable);
  }

  public NotLoggedInException(Throwable throwable) {
    super(throwable);
  }
}
