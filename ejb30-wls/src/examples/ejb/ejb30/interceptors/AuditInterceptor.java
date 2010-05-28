package examples.ejb.ejb30.interceptors;

import examples.ejb.ejb30.exceptions.BadCommentsException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.annotation.PreDestroy;
import java.lang.reflect.Method;

/**
 * Interceptor class which includes a business method interceptor method
 * annotated with the @AroundInvoke annotation as well as lifecycle callback
 * interceptor method annotated with @PreDestroy.
 */
public class AuditInterceptor {

  public AuditInterceptor() {
  }

  /**
   * a business method interceptor method.
   */
  @AroundInvoke
  public Object audit(InvocationContext ic) throws Exception {
    Method method = ic.getMethod();
    if (method.getName().equals("addReview")) {
      Object[] parameters = ic.getParameters();
      String comments = (String) parameters[parameters.length - 1];
      if (comments == null || comments.trim().length() == 0) {
        throw new BadCommentsException("empty comments is not allowed, please enter your comments");
      }
      if (comments.indexOf("bitch") > -1) {
        throw new BadCommentsException("the comments entered includes impolite word, please remove it.");
      }
    }
    return ic.proceed();
  }

  /**
   * lifecycle callback interceptor method.
   */
  @PreDestroy
  public void preDestroy(InvocationContext ic) throws Exception {
    System.out.println("Invoking method: " + ic.getMethod());
  }
}
