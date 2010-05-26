package skillup.ejb30.basic.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Person")
@SecondaryTable(name="Password", pkJoinColumns=@PrimaryKeyJoinColumn(name="pid"))
public class Person implements Serializable {
	
	private static final long serialVersionUID = 108207638025636843L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(table="Password")
	private String password;

	@Column(name = "name", nullable=false, length=50)
	private String name;
	
	@Column(name="sex", nullable=false)
	@Enumerated(EnumType.STRING)//列挙型運用例
	private Sex sex;
	
	@Column(name="age", nullable=false)
	private int age;
	
	@Column(name="birthday")
	@Temporal(TemporalType.DATE)//日付のみ保存する
	private Date birthday;

	public Person() {
		super();
	}

	public Person(String name, Sex sex, int age, Date birthday) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [age=").append(age).append(", birthday=")
				.append(birthday).append(", id=").append(id).append(", name=")
				.append(name).append(", password=").append(password).append(
						", sex=").append(sex).append("]");
		return builder.toString();
	}
}
