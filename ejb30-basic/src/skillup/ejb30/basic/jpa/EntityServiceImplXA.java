package skillup.ejb30.basic.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
@Remote(EntityService.class)
public class EntityServiceImplXA implements EntityService {
	
	@Resource(mappedName="java:XADerbyDS")
	private DataSource ds;
	
	@Override
	public <T> T getEntity(Class<T> clzss, Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getEntityList(Class<T> clzss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persistEntity(Object... entitys){
		
		Connection conn;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into person (age, name, sex) values (?, ?, ?)");
			ps.setInt(1, 20);
			//ps.setDate(2, (java.sql.Date) new Date());
			ps.setString(2, "####");
			ps.setString(3, "Boy");
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			
			System.out.println("ZZZZ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("OK");
		
	}

	@Override
	public void sync() {
		// TODO Auto-generated method stub
		
	}

}
