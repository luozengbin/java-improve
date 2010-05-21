package skillup.ejb30.basic.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class Publish implements Serializable {

	private static final long serialVersionUID = 1869834028431871106L;
	
	@Column(nullable=true)
	private Date publishDt;
	
	@Column(nullable=true)
	private String publisherId;

	public Date getPublishDt() {
		return publishDt;
	}

	public void setPublishDt(Date publishDt) {
		this.publishDt = publishDt;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((publishDt == null) ? 0 : publishDt.hashCode());
		result = prime * result
				+ ((publisherId == null) ? 0 : publisherId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Publish))
			return false;
		Publish other = (Publish) obj;
		if (publishDt == null) {
			if (other.publishDt != null)
				return false;
		} else if (!publishDt.equals(other.publishDt))
			return false;
		if (publisherId == null) {
			if (other.publisherId != null)
				return false;
		} else if (!publisherId.equals(other.publisherId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Publish [publishDt=").append(publishDt).append(
				", publisherId=").append(publisherId).append("]");
		return builder.toString();
	}
	
}
