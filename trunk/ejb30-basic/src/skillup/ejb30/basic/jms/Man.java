package skillup.ejb30.basic.jms;

import java.io.Serializable;

public class Man implements Serializable {
	
	private static final long serialVersionUID = 7563919498790440275L;

	private String name;
	
	private String address;
	
	public Man() {
		super();
	}

	public Man(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Man [address=").append(address).append(", name=")
				.append(name).append("]");
		return builder.toString();
	}
	
	
}
