package skillup.java.bean;

import java.util.Arrays;

public class Product {
	
	private long id;
	
	private String name;
	
	private String category;
	
	private int[] serviceLife;
	
	
	public Product() {
		super();
	}

	public Product(long id, String name, String category, int[] serviceLife) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.serviceLife = serviceLife;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int[] getServiceLife() {
		return serviceLife;
	}

	public void setServiceLife(int[] serviceLife) {
		this.serviceLife = serviceLife;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(serviceLife);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(serviceLife, other.serviceLife))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [category=").append(category).append(", id=")
				.append(id).append(", name=").append(name).append(
						", serviceLife=").append(Arrays.toString(serviceLife))
				.append("]");
		return builder.toString();
	}
	
}
