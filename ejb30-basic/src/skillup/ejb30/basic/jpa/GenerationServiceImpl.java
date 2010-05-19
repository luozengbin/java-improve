package skillup.ejb30.basic.jpa;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(GenerationService.class)
public class GenerationServiceImpl implements GenerationService {
	
	@PersistenceContext(unitName="ejb30-basic")
	private EntityManager em;
	
	@Override
	public String insert(int count) {
		
		StringBuilder sb = new StringBuilder();
		
		GenerationBall ball = null;
		
		for (int i = 0; i < count; i++) {
			ball = new GenerationBall();
			ball.setComments("ball:" + i);
			em.persist(ball);
			
			sb.append(ball.getBallId() + "\n");
		}
		
		return sb.toString();
	}

	@Override
	public GenerationBall update(int id, String newComment) {

		GenerationBall result = em.find(GenerationBall.class, id);
		
		result = em.find(GenerationBall.class, id);
		
		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		result.setComments(newComment);
		
		return result;
	}

	@Override
	public GenerationBall find(int id) {
		
		return em.find(GenerationBall.class, id);
	}

}
