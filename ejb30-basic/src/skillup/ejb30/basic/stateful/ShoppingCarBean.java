package skillup.ejb30.basic.stateful;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import skillup.ejb30.basic.example.Product;

@Stateful
@Remote(ShoppingCar.class)
@Local(LocalShoppingCar.class)
public class ShoppingCarBean implements LocalShoppingCar, ShoppingCar {
	
	private List<Product> productList;
	
	@Override
	public void addNewProduct(Product product) {
		if(productList == null){
			productList = new ArrayList<Product>();
		}
		productList.add(product);
	}

	@Override
	public List<Product> getProductByName(String productName) {
		return null;
	}

	@Override
	public List<Product> getProducts() {
		return productList;
	}
}
