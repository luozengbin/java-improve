package skillup.java.bean;

public class Lens implements Part{
	
	private String partName;
	
	private String productedBy;

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getProductedBy() {
		return productedBy;
	}

	public void setProductedBy(String productedBy) {
		this.productedBy = productedBy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Part [partName=").append(partName).append(
				", productedBy=").append(productedBy).append("]");
		return builder.toString();
	}
}
