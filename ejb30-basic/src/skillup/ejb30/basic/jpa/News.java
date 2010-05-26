package skillup.ejb30.basic.jpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="News")
public class News implements Serializable {
	
	private static final long serialVersionUID = -4382054418763515868L;
	
	//保持対象外
	@Transient
	private String comments;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	@Lob	//巨大内容
	@Basic(fetch=FetchType.LAZY)
	private String content;
	
	@Embedded
	private Publish publishInfo;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Publish getPublishInfo() {
		return publishInfo;
	}

	public void setPublishInfo(Publish publishInfo) {
		this.publishInfo = publishInfo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("News [comments=").append(comments).append(", id=").append(id).append(
						", publishInfo=").append(publishInfo)
				.append(", title=").append(title).append("]");
		return builder.toString();
	}
	
}
