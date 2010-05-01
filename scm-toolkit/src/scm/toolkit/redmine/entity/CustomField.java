package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the custom_fields database table.
 * 
 */
@Entity
@Table(name = "custom_fields")
@NamedQuery(name = "getCustomFieldByName", query = "SELECT c FROM CustomField c WHERE c.name = :name")
public class CustomField implements Serializable {
	
	private static final long serialVersionUID = 9210620006831695785L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Lob()
	@Column(name = "default_value")
	private String defaultValue;

	@Column(name = "field_format", nullable = false, length = 30)
	private String fieldFormat;

	@Column(name = "max_length", nullable = false)
	private int maxLength;

	@Column(name = "min_length", nullable = false)
	private int minLength;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 30)
	private String type;

	public CustomField() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getFieldFormat() {
		return this.fieldFormat;
	}

	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMinLength() {
		return this.minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomField [name=").append(name).append("]");
		return builder.toString();
	}

}