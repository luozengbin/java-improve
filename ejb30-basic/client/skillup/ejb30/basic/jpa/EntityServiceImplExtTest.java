package skillup.ejb30.basic.jpa;

import java.util.Calendar;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.Test;

import skillup.ejb30.basic.client.util.ContextUtils;

public class EntityServiceImplExtTest {

	@Test
	public void testPersistEntity() throws InterruptedException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		UserTransaction tx = ContextUtils.lookup("UserTransaction");
		
		EntityService service = ContextUtils.lookup("skillup_ejb30_basic/EntityServiceImplXA/remote");
		
		tx.begin();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1984);
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DAY_OF_MONTH, 29);
		service.persistEntity(new Person("akira", Sex.Boy, 25, calendar.getTime()));
		
		//Thread.sleep(10000);
		
		service.sync();
		
		tx.rollback();
	}
}
