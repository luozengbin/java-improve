package scm.alert.mail;

import java.io.Serializable;

public class SimpleMailEntity implements Serializable {
	
	private static final long serialVersionUID = -4094265184930323243L;
	
	private Object titile;
	
	private Object body;

	public Object getTitile() {
		return titile;
	}

	public void setTitile(Object titile) {
		this.titile = titile;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimpleMailEntity [body=").append(body).append(
				", titile=").append(titile).append("]");
		return builder.toString();
	}

}
