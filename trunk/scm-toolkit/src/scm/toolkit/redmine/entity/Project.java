package scm.toolkit.redmine.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the projects database table.
 * 
 */
@Entity
@Table(name="projects")
@NamedQuery(name = "getProjectByName", query = "SELECT p FROM Project p WHERE p.name = :name")
public class Project implements Serializable {
	
	private static final long serialVersionUID = 8946137508388305095L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

    @Lob()
	private String description;

	@Column(length=255)
	private String homepage;

	@Column(length=20)
	private String identifier;

	private int lft;

	@Column(nullable=false, length=30)
	private String name;

	@Column(name="parent_id")
	private int parentId;

	private int rgt;

	@Column(nullable=false)
	private int status;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="project")
	private List<Issue> issues;

    public Project() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public int getLft() {
		return this.lft;
	}

	public void setLft(int lft) {
		this.lft = lft;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getRgt() {
		return this.rgt;
	}

	public void setRgt(int rgt) {
		this.rgt = rgt;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Project [name=").append(name).append("]");
		return builder.toString();
	}
	
}