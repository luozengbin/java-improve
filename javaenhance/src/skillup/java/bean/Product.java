package skillup.java.bean;

import java.util.Arrays;
import java.util.Date;

public class Product {
	
	private long id;
	
	private String name;
	
	private String category;
	
	private int[] serviceLife;
	
	private Date createDate;
	
	private Lens lens = new Lens();
	
	private Part extendSubPart = new Part(){
		private String partName;
		
		public String getPartName() {
			return partName;
		}
		
		public void setPartName(String partName) {
			this.partName = partName;
		}
		
		@Override
		public String toString(){
			return "extendSubPart[partName = "+ partName + "]";
		}
	};
	
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Lens getLens() {
		return lens;
	}

	public void setLens(Lens lens) {
		this.lens = lens;
	}

	public Part getExtendSubPart() {
		return extendSubPart;
	}

	public void setExtendSubPart(Part extendSubPart) {
		this.extendSubPart = extendSubPart;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [category=").append(category).append(
				", createDate=").append(createDate).append(", extendSubPart=")
				.append(extendSubPart).append(", id=").append(id).append(
						", lens=").append(lens).append(", name=").append(name)
				.append(", serviceLife=").append(Arrays.toString(serviceLife))
				.append("]");
		return builder.toString();
	}
	
	
}
