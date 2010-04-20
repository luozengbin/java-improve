package skillup.ejb.basic.mdb;

import java.io.Serializable;

public class MailPojo implements Serializable {

	private static final long serialVersionUID = -3048220809268651647L;
	
	private String title;
	
	private String to;
	
	private String from;
	
	private String subject;
	
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MailPojo [content=").append(content).append(", from=")
				.append(from).append(", subject=").append(subject).append(
						", title=").append(title).append(", to=").append(to)
				.append("]");
		return builder.toString();
	}
}
