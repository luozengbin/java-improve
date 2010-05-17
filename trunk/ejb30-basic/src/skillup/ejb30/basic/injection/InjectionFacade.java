package skillup.ejb30.basic.injection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;


@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@Remote(Injection.class)
@EJB(name="ejb/InjectionBean001", beanName="InjectionBean001", beanInterface=InjectionLocal.class)
public class InjectionFacade implements Injection {
	
	@EJB(name="ejb/InjectionBean003", beanName="InjectionBean003", beanInterface=InjectionLocal.class)
	private Injection injectionBean;
	
	@Resource(name="ds/mysql", mappedName="java:/MySqlDS")
	private DataSource ds;
	
	//@Resource private UserTransaction utx;
	@Resource private TimerService tms;
	@Resource private SessionContext ctx;
	
	@Override
	public String sayHello(String name) {
		
		try {
			
			StringBuilder sb = new StringBuilder();
			
			InitialContext ctx = new InitialContext();
			
			Injection helloWorld = (Injection)ctx.lookup("java:comp/env/ejb/InjectionBean001");
			
			sb.append("using environment annotation on class : " + helloWorld.sayHello(name) + "\n");
			
			helloWorld = (Injection)ctx.lookup("java:comp/env/ejb/InjectionBean002");
			
			sb.append("using description in ejb-jar.xml : " + helloWorld.sayHello(name) + "\n");
			
			sb.append("using environment annotation on private field : " + injectionBean.sayHello(name) + "\n");
			
			Connection conn = ds.getConnection();
			
			ResultSet rs = conn.prepareStatement("select sysdate() from dual").executeQuery();
			
			rs.next();
			
			sb.append("sysdate of mysql server : " + rs.getTimestamp(1) + "\n");
			
			rs.close();
			
			conn.close();
			
			return sb.toString();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
