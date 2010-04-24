package skillup.ejb30.basic.example;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(ProductService.class)
@Local(LocalProductService.class)
public class ProductServiceBean implements LocalProductService, ProductService {
	
	@PersistenceContext(unitName="ejb30-basic")
	EntityManager em;
	
	@Override
	public void delete(String productId) {
		em.remove(em.find(Product.class, productId));
	}

	@Override
	public void delete(Product product) {
		em.remove(product);
	}

	@Override
	public Product getProductById(String productId) {
		return em.find(Product.class, productId);
	}

	@Override
	public List<Product> getProductByName(String productName) {
		//em.createQuery("select o from person o where o.");
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() {
		return em.createQuery("select o from person o").getResultList();
	}

	@Override
	public Product save(Product product) {
		em.persist(product);
		return product;
	}

	@Override
	public void update(Product product) {
		em.merge(product);
	}

}
