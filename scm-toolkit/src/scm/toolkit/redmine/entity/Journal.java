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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the journals database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="journals")
public class Journal implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_on", nullable=false)
	private Date createdOn;

	@Column(name="journalized_type", nullable=false, length=30)
	private String journalizedType;

    @Lob()
	private String notes;

	//bi-directional many-to-one association to JournalDetail
	@OneToMany(mappedBy="journal", cascade={CascadeType.ALL})
	private List<JournalDetail> journalDetails;
	
	
	@ManyToOne
	@JoinColumn(name = "journalized_id")
	private Issue issue;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

    public Journal() {
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

	public String getJournalizedType() {
		return this.journalizedType;
	}

	public void setJournalizedType(String journalizedType) {
		this.journalizedType = journalizedType;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<JournalDetail> getJournalDetails() {
		return journalDetails;
	}

	public void setJournalDetails(List<JournalDetail> journalDetails) {
		this.journalDetails = journalDetails;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
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
		builder.append("Journal [createdOn=").append(createdOn).append(", id=")
				.append(id).append(", journalDetails=").append(journalDetails)
				.append(", journalizedType=").append(journalizedType).append(
						", notes=").append(notes).append(", user=")
				.append(user).append("]");
		return builder.toString();
	}
	
	
	
}