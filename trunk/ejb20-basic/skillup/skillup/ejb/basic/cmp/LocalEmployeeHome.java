package skillup.ejb.basic.cmp;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface LocalEmployeeHome extends EJBLocalHome {
	
	/* 必須 */
	public LocalEmployee create(String empNo, String name)
			throws CreateException;
	/* 必須 */
	public LocalEmployee findByPrimaryKey(String empNo) throws FinderException;
	
//	public Collection<LocalEmployee> findByName(String name)
//			throws FinderException;
}
