/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.web;

import uk.ltd.getahead.dwr.create.NewCreator;

import javax.naming.InitialContext;

/**
  * Bean creator for integrating Session Bean with DWR.
  */
public class SessionBeanCreator extends NewCreator {
  private String jndiName;

  public String getJndiName() {
    return jndiName;
  }                                 

  public void setJndiName(String jndiName) {
    this.jndiName = jndiName;
  }

  public Object getInstance() throws InstantiationException {
    try{
      InitialContext ctx = new InitialContext();
      return ctx.lookup(jndiName);
    }catch(Exception ex){
      InstantiationException newEx = new InstantiationException();
      newEx.initCause(ex);
      throw newEx;
    }
  }
}
