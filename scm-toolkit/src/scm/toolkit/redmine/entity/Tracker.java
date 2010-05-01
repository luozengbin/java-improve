package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the trackers database table.
 * 
 */
@Entity
@Table(name = "trackers")
@NamedQuery(name = "getTrackerByName", query = "SELECT t FROM Tracker t WHERE t.name = :name")
public class Tracker implements Serializable {
	
	private static final long serialVersionUID = 7553248809582920797L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;

	private int position;

	public Tracker() {
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

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tracker [name=").append(name).append("]");
		return builder.toString();
	}

}