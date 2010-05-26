package skillup.ejb30.basic.jpa;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERITEM")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 7627787117654957271L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private String productName;
	
	private Float price;
	
	//@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.REFRESH})
	//@JoinColumn(name="order_id")
	//private Order order;
	
	public OrderItem() {
		super();
	}

	public OrderItem(String productName, Float price) {
		this.productName = productName;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

//	public Order getOrder() {
//		return order;
//	}
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OrderItem))
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderItem [id=").append(id).append(", price=").append(
				price).append(", productName=").append(productName).append("]");
		return builder.toString();
	}
}
