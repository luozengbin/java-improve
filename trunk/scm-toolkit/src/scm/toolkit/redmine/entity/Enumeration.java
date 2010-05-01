package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the enumerations database table.
 * 
 */
@Entity
@Table(name="enumerations")
@NamedQuery(name = "getEnumerationByName", query = "SELECT e FROM Enumeration e WHERE e.name = :name")
public class Enumeration implements Serializable {
	
	private static final long serialVersionUID = 3605035773967970166L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=30)
	private String name;

	@Column(name="parent_id")
	private int parentId;

	private int position;

	@Column(name="project_id")
	private int projectId;

	@Column(length=255)
	private String type;

    public Enumeration() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
		builder.append("Enumeration [name=").append(name).append("]");
		return builder.toString();
	}

}