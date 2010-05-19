package skillup.ejb30.basic.jpa;

public interface GenerationService {
	
	String insert(int count);
	
	GenerationBall update(int id, String newComment);
	
	GenerationBall find(int id);
	
}
