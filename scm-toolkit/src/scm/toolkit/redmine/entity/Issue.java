package scm.toolkit.redmine.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the issues database table.
 * 
 */
@Entity
@Table(name = "issues")
@NamedQuery(name = "getIssuesByManageNo", query = "SELECT i FROM Issue AS i JOIN i.customValues AS c  WHERE c.value = :manageNo")
public class Issue implements Serializable {

	private static final long serialVersionUID = 4483016103810673992L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on")
	private Date createdOn;

	@Lob()
	private String description;

	@Column(name = "done_ratio", nullable = false)
	private int doneRatio;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	// @Column(name="estimated_hours")
	// private float estimatedHours;

	@Column(name = "lock_version", nullable = false)
	private int lockVersion;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Column(nullable = false, length = 255)
	private String subject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on")
	private Date updatedOn;

	// bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	// uni-directional many-to-one association to IssueStatus
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private IssueStatus issueStatus;

	// uni-directional many-to-one association to Tracker
	@ManyToOne
	@JoinColumn(name = "tracker_id", nullable = false)
	private Tracker tracker;

	// uni-directional many-to-one association to IssueCategory
	@ManyToOne
	@JoinColumn(name = "category_id")
	private IssueCategory issueCategory;

	// uni-directional many-to-one association to Version
	@ManyToOne
	@JoinColumn(name = "fixed_version_id")
	private Version fixVersion;

	// uni-directional many-to-one association to Enumeration
	@ManyToOne
	@JoinColumn(name = "priority_id", nullable = false)
	private Enumeration priority;

	// uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	// uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "assigned_to_id")
	private User assigned;

	// bi-directional many-to-one association to CustomValue
	@OneToMany(mappedBy = "issue", cascade={CascadeType.ALL})
	private List<CustomValue> customValues;
	
	@OneToMany(mappedBy = "watchableIssue", cascade={CascadeType.ALL})
	private List<Watcher> watchers;
	
	public Issue() {
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

	public int getDoneRatio() {
		return this.doneRatio;
	}

	public void setDoneRatio(int doneRatio) {
		this.doneRatio = doneRatio;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	// public float getEstimatedHours() {
	// return this.estimatedHours;
	// }
	//
	// public void setEstimatedHours(float estimatedHours) {
	// this.estimatedHours = estimatedHours;
	// }

	public int getLockVersion() {
		return this.lockVersion;
	}

	public void setLockVersion(int lockVersion) {
		this.lockVersion = lockVersion;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public IssueStatus getIssueStatus() {
		return this.issueStatus;
	}

	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}

	public Tracker getTracker() {
		return this.tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	public IssueCategory getIssueCategory() {
		return this.issueCategory;
	}

	public void setIssueCategory(IssueCategory issueCategory) {
		this.issueCategory = issueCategory;
	}

	public Version getFixVersion() {
		return this.fixVersion;
	}

	public void setFixVersion(Version fixVersion) {
		this.fixVersion = fixVersion;
	}

	public Enumeration getPriority() {
		return this.priority;
	}

	public void setPriority(Enumeration priority) {
		this.priority = priority;
	}

	public User getAuthor() {
		return this.author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getAssigned() {
		return this.assigned;
	}

	public void setAssigned(User assigned) {
		this.assigned = assigned;
	}

	public List<CustomValue> getCustomValues() {
		return customValues;
	}

	public void setCustomValues(List<CustomValue> customValues) {
		this.customValues = customValues;
	}

	public List<Watcher> getWatchers() {
		return watchers;
	}

	public void setWatchers(List<Watcher> watchers) {
		this.watchers = watchers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Issue [assigned=").append(assigned).append(", author=")
				.append(author).append(", createdOn=").append(createdOn)
				.append(", customValues=").append(customValues).append(
						", description=").append(description).append(
						", doneRatio=").append(doneRatio).append(", dueDate=")
				.append(dueDate).append(", fixVersion=").append(fixVersion)
				.append(", id=").append(id).append(", issueCategory=").append(
						issueCategory).append(", issueStatus=").append(
						issueStatus).append(", lockVersion=").append(
						lockVersion).append(", priority=").append(priority)
				.append(", project=").append(project).append(", startDate=")
				.append(startDate).append(", subject=").append(subject).append(
						", tracker=").append(tracker).append(", updatedOn=")
				.append(updatedOn).append(", watchers=").append(watchers)
				.append("]");
		return builder.toString();
	}

}