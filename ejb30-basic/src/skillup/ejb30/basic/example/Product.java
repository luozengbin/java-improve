package skillup.ejb30.basic.example;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 8934069499655873002L;

	@Id @Column(name="id") @GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	@Column(name="name", nullable=false, length=50)
	private String name;
	
	@Column(name="comment",nullable=true, length=1000)
	private String comment;
	
	@Column(name="create_dt", nullable=false)
	private Timestamp create_dt;
	
	@Column(name="update_dt", nullable=false)
	private Timestamp update_dt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreate_dt() {
		return create_dt;
	}

	public void setCreate_dt(Timestamp createDt) {
		create_dt = createDt;
	}

	public Timestamp getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Timestamp updateDt) {
		update_dt = updateDt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result
				+ ((create_dt == null) ? 0 : create_dt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((update_dt == null) ? 0 : update_dt.hashCode());
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
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (create_dt == null) {
			if (other.create_dt != null)
				return false;
		} else if (!create_dt.equals(other.create_dt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (update_dt == null) {
			if (other.update_dt != null)
				return false;
		} else if (!update_dt.equals(other.update_dt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductBean [comment=").append(comment).append(
				", create_dt=").append(create_dt).append(", id=").append(id)
				.append(", name=").append(name).append(", update_dt=").append(
						update_dt).append("]");
		return builder.toString();
	}
}
