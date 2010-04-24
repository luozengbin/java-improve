package skillup.ejb30.basic.stateless;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
@Remote(Operation.class)
@Local(LocalOperation.class)
public class OperationBean implements Operation, LocalOperation {
	
	private static final Log log = LogFactory.getLog(OperationBean.class);
	
	//Statelessのインスタンスはcontainerが管理してクライアントのリクエストを応答する
	private int a;
	
	@Override
	public int addUp() {
		log.debug("addUp invoke by ejb container:" + this.hashCode());
		return a++;
	}
}
