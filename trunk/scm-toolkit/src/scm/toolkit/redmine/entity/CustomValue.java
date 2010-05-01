package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the custom_values database table.
 * 
 */
@Entity
@Table(name = "custom_values")
@NamedQuery(name = "getCustomValueByManageNo", query = "SELECT c FROM CustomValue c WHERE c.value = :manageNo AND c.customField.name = :customFieldName")
public class CustomValue implements Serializable {

	private static final long serialVersionUID = -7340092249460130043L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "customized_type", nullable = false, length = 30)
	private String customizedType;

	// bi-directional many-to-one association to Issue
	@ManyToOne
	@JoinColumn(name = "customized_id")
	private Issue issue;

	@Lob()
	private String value;

	// uni-directional many-to-one association to CustomField
	@ManyToOne
	@JoinColumn(name = "custom_field_id", nullable = false)
	private CustomField customField;

	public CustomValue() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomizedType() {
		return this.customizedType;
	}

	public void setCustomizedType(String customizedType) {
		this.customizedType = customizedType;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CustomField getCustomField() {
		return this.customField;
	}

	public void setCustomField(CustomField customField) {
		this.customField = customField;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomValue [customField=").append(customField).append(
				", customizedType=").append(customizedType).append(", id=")
				.append(id).append(", value=").append(value).append("]");
		return builder.toString();
	}
}