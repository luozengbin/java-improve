/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.domain;

import javax.persistence.*;

/** 
 *  Artist is a Person with no additional property.
 * 
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 *
 */
@Entity
@Table(name = "ARTIST")
public class Artist
	extends Person
{
  /**
   * Default constructor required for enhancement.
   */
  public Artist ()
	{
		super ();
	}

  /**
   * The public constructor constructs with a name.
   * @param name the name of the artist.
   */
  public Artist (String name)
	{
		super (name);
	}


}
