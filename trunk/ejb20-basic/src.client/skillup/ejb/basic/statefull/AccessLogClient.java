package skillup.ejb.basic.statefull;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AccessLogClient {
	
	private final static Log log = LogFactory.getLog(AccessLogClient.class);
	
	public static void main(String[] args) throws NamingException, RemoteException, CreateException, RemoveException {
		Context ctx = new InitialContext();
		AccessLogHome home = (AccessLogHome) PortableRemoteObject.narrow(ctx.lookup("AccessLog"), AccessLogHome.class);
		ctx.close();
		
		AccessLog accessLog_1 = home.createWithURL("http://www.google.com");
		AccessLog accessLog_2 = home.createWithURL("http://www.google.com");
		
		log.debug("isIdentical:" + accessLog_1.isIdentical(accessLog_2));
		
		accessLog_1.setLastURL("http://www.yahoo.co.jp");
		
		log.debug("accessLog_1.getAccessHistory:" + accessLog_1.getAccessHistory());
		log.debug("accessLog_2.getAccessHistory:" + accessLog_2.getAccessHistory());
		
		//beanのliftcycle
		
		//beanのresource管理
		
		//bean-cache設定の効果
		
		List<AccessLog> accessLogList = new ArrayList<AccessLog>(); 
		
		for (int i = 0; i < 10; i++) {
			accessLogList.add(home.createWithURL("http://www.google.com"));
			
		}
		
		for (AccessLog accessLog : accessLogList) {
			log.debug(accessLog.getAccessHistory());
		}
	}
}
