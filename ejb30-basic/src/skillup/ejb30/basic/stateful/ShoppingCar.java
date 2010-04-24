package skillup.ejb30.basic.stateful;

import java.util.List;

import skillup.ejb30.basic.example.Product;

public interface ShoppingCar {
	
	public void addNewProduct(Product product);
	
	public List<Product> getProductByName(String productName);
	
	public List<Product> getProducts();
}
