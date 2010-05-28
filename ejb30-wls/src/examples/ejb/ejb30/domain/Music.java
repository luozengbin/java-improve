/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.domain;

import javax.persistence.*;

/** 
 *  Music is a kind of Item. 
 *  The target table for Music is decided by the inheritance strategey 
 *  specified in its {@link examples.ejb.ejb30.domain.Item superclass}. In this 
 *  case, <code>SINGLE_TABLE</code>
 *  inheritance is used -- that stores all subclasses of Item including
 *  Music to a single table. <BR>
 *  Hence it is required to discriminate the records of Music from records
 *  of other types. The <code>@DiscriminatorValue</code> annotation specifies 
 *  that
 *  all records of Music will carry the value <code>"MUSIC"</code> in the
 *  <code>@DiscriminatorColumn</code> specified with the 
 *  {@link Item inheritance strategy}. 
 *   
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 *
 */

@Entity
@DiscriminatorValue(value = "MUSIC")
public class Music
	extends Item
{

	/* 
	 * An additional field to specify duration of this music in minutes.
	 * The same column name <code>DURATION</code> is used for the field
	 * of sibling class {@link Movie Movie}. Check the database 
	 * schema to see how these two properties are represnted in the table. 
	 * 
	 */
	@Column(name = "DURATION")
	private Integer duration;

	/** 
	 * A no-arg constructor is required for enhancement.
	 * 
	 *
	 */
	public Music ()
	{
		super ();
	}

	/** 
	 * The public constructor constructs with a title.
	 * 
	 * @param title
	 */
	public Music (String title)
	{
		super (title);
	}

	public Integer getDuration ()
	{
		return duration;
	}

	public void setDuration (int d)
	{
		if (d < 0)
			throw new IllegalArgumentException ("Invalid duration " + d
					+ " for " + this);

		duration = d;
	}


}
