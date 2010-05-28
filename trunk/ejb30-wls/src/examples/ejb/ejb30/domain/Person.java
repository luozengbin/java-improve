/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.domain;

import java.io.Serializable;
import javax.persistence.*;

/** 
 * Person is a generic and abstract description of someone who has a name.
 * Person is the root of an inheritance hierarchy. This inheritance hierarchy
 * uses a strategy different than that of {@link examples.ejb.ejb30.domain.Item
 * Item} hierarchy. Here, each subclass is store in separate table.<BR>
 * Primary identifier for Person is <code>name</code>. 
 * 
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 *
 */

@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person
	implements Serializable
{
	public static enum Gender {
		MALE, FEMALE
	}

  /**
   * The unique identifier for a Person is <code>name</code>.<BR>
   * <B>NOTE:</B> This is an example of what should <em>not</em> be done in
   * real systems. Firstly, meaningful attributes such as name of a Person
   * is not the right candidate for the primary key or unique identifier.
   * Secondly, a String is a not a right choice for a primary key column.
   *
   */
  @Id
  @Column(name = "NAME")
  private String name;

  /**
   * default constructor required by enhancement.
   */
  protected Person ()
	{

	}
  /**
   * The public constructor constructs with a name.
   * @param name the name of the person.
   */

	public Person (String name)
	{
		this.name = name;
	}

	/** 
	 * Gets the name of this person. This is the unique identifier.
	 * 
	 * @return return the name of this person.
	 */
	public String getName ()
	{

		return name;
	}

	public void setName (String name)
	{
		if (name == null || name.trim ().length () == 0)
			throw new IllegalArgumentException (
					"null or empty name not allowed");
		this.name = name;
	}

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Person person = (Person) o;

    return !(name != null ? !name.equals(person.name) : person.name != null);

  }

  public int hashCode() {
    return (name != null ? name.hashCode() : 0);
  }
}
