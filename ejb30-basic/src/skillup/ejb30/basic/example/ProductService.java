package skillup.ejb30.basic.example;

import java.util.List;

public interface ProductService {

	public Product save(Product product);

	public void update(Product product);

	public void delete(String productId);

	public void delete(Product product);

	public Product getProductById(String productId);

	public List<Product> getProductByName(String productName);

	public List<Product> getProducts();

}
