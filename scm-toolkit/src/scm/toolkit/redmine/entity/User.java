package scm.toolkit.redmine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name = "getUserByLastName", query = "SELECT u FROM User u WHERE u.lastname = :lastname")
public class User implements Serializable {
	
	private static final long serialVersionUID = -5756770346469669641L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;


	@Column(name="auth_source_id")
	private int authSourceId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	@Column(nullable=false, length=30)
	private String firstname;

	@Column(name="hashed_password", nullable=false, length=40)
	private String hashedPassword;

	@Column(name="identity_url", length=255)
	private String identityUrl;

	@Column(length=5)
	private String language;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="last_login_on")
	private Date lastLoginOn;

	@Column(nullable=false, length=30)
	private String lastname;

	@Column(nullable=false, length=30)
	private String login;

	@Column(nullable=false, length=60)
	private String mail;

	@Column(nullable=false)
	private int status;

	@Column(length=255)
	private String type;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;

    public User() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthSourceId() {
		return this.authSourceId;
	}

	public void setAuthSourceId(int authSourceId) {
		this.authSourceId = authSourceId;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getHashedPassword() {
		return this.hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getIdentityUrl() {
		return this.identityUrl;
	}

	public void setIdentityUrl(String identityUrl) {
		this.identityUrl = identityUrl;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getLastLoginOn() {
		return this.lastLoginOn;
	}

	public void setLastLoginOn(Date lastLoginOn) {
		this.lastLoginOn = lastLoginOn;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}


	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [").append(lastname).append(
				" ").append(firstname).append("]");
		return builder.toString();
	}

}