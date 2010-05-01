package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the journal_details database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="journal_details")
public class JournalDetail implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="old_value", length=255)
	private String oldValue;

	@Column(name="prop_key", nullable=false, length=30)
	private String propKey;

	@Column(name="property", nullable=false, length=30)
	private String property;

	@Column(name="value", length=255)
	private String value;

	//bi-directional many-to-one association to Journal
    @ManyToOne
	@JoinColumn(name="journal_id", nullable=false)
	private Journal journal;

    public JournalDetail() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOldValue() {
		return this.oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getPropKey() {
		return this.propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JournalDetail [id=").append(id).append(", oldValue=")
				.append(oldValue).append(", propKey=").append(propKey).append(
						", property=").append(property).append(", value=")
				.append(value).append("]");
		return builder.toString();
	}
	
	
}