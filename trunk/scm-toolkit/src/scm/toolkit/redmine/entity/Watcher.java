package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the watchers database table.
 * 
 */
@Entity
@Table(name="watchers")
public class Watcher implements Serializable {
	
	private static final long serialVersionUID = -533699490696146124L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@ManyToOne
	@JoinColumn(name = "watchable_id")
	private Issue watchableIssue;

	@Column(name="watchable_type", nullable=false, length=255)
	private String watchableType;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
    public Watcher() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWatchableType() {
		return this.watchableType;
	}

	public void setWatchableType(String watchableType) {
		this.watchableType = watchableType;
	}

	public Issue getWatchableIssue() {
		return watchableIssue;
	}

	public void setWatchableIssue(Issue watchableIssue) {
		this.watchableIssue = watchableIssue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Watcher [id=").append(id).append(", user=")
				.append(user).append(", watchableType=").append(watchableType)
				.append("]");
		return builder.toString();
	}
	
	
}