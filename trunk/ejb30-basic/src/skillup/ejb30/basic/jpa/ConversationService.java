package skillup.ejb30.basic.jpa;

public interface ConversationService {
	
	public Entity001 getEntity(int id);
	
	public void updateName(String name);
	
	public void commit();
}
