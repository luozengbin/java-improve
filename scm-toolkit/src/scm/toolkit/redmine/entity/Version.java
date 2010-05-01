package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the versions database table.
 * 
 */
@Entity
@Table(name="versions")
@NamedQuery(name = "getVersionByName", query = "SELECT v FROM Version v WHERE v.name = :name")
public class Version implements Serializable {
	
	private static final long serialVersionUID = 7216441216353899580L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	@Column(length=255)
	private String description;

    @Temporal( TemporalType.DATE)
	@Column(name="effective_date")
	private Date effectiveDate;

	@Column(nullable=false, length=255)
	private String name;

	@Column(name="project_id", nullable=false)
	private int projectId;

	@Column(nullable=false, length=255)
	private String sharing;

	@Column(length=255)
	private String status;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;

	@Column(name="wiki_page_title", length=255)
	private String wikiPageTitle;

    public Version() {
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

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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

	public String getSharing() {
		return this.sharing;
	}

	public void setSharing(String sharing) {
		this.sharing = sharing;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getWikiPageTitle() {
		return this.wikiPageTitle;
	}

	public void setWikiPageTitle(String wikiPageTitle) {
		this.wikiPageTitle = wikiPageTitle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Version [name=").append(name).append("]");
		return builder.toString();
	}

}