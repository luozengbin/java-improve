package examples.ejb20.basic.beanManaged;

/**
 * ProcessingErrorException is thrown if the caller attempts
 * to withdraw more than a certain preset limit from the account.
 *
 * @author Copyright (c) 1998-2006 by BEA Systems, Inc. All Rights Reserved.
 */
public class ProcessingErrorException extends Exception {

  /**
   * Catches exceptions without a specified string
   *
   */
  public ProcessingErrorException() {}

  /**
   * Constructs the appropriate exception with the specified string
   *
   * @param message           String Exception message
   */
  public ProcessingErrorException(String message) {super(message);}
}
