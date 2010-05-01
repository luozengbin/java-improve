package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the issue_categories database table.
 * 
 */
@Entity
@Table(name="issue_categories")
@NamedQuery(name = "getIssueCategoryByName", query = "SELECT i FROM IssueCategory i WHERE i.name = :name")
public class IssueCategory implements Serializable {
	
	private static final long serialVersionUID = -9180156941767556473L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="assigned_to_id")
	private int assignedToId;

	@Column(nullable=false, length=30)
	private String name;

	@Column(name="project_id", nullable=false)
	private int projectId;

    public IssueCategory() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAssignedToId() {
		return this.assignedToId;
	}

	public void setAssignedToId(int assignedToId) {
		this.assignedToId = assignedToId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IssueCategory [name=").append(name).append("]");
		return builder.toString();
	}

}