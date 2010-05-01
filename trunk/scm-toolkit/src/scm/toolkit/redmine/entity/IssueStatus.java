package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the issue_statuses database table.
 * 
 */
@Entity
@Table(name="issue_statuses")
@NamedQuery(name = "getIssueStatusByName", query = "SELECT i FROM IssueStatus i WHERE i.name = :name")
public class IssueStatus implements Serializable {
	
	private static final long serialVersionUID = -32154602494972792L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="default_done_ratio")
	private int defaultDoneRatio;


	@Column(nullable=false, length=30)
	private String name;

	private int position;

    public IssueStatus() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDefaultDoneRatio() {
		return this.defaultDoneRatio;
	}

	public void setDefaultDoneRatio(int defaultDoneRatio) {
		this.defaultDoneRatio = defaultDoneRatio;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IssueStatus [name=").append(name).append("]");
		return builder.toString();
	}

}