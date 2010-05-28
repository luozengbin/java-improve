/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */

package examples.ejb.ejb30.domain;

import javax.persistence.*;

/** 
 *  Book is a kind of Item. 
 *  The target table for Book is decided by the inheritance strategey 
 *  specified in its {@link examples.ejb.ejb30.domain.Item superclass}. In this 
 *  case, <code>SINGLE_TABLE</code>
 *  inheritance is used -- that stores all subclasses of Item including
 *  Book to a single table. <BR>
 *  Hence it is required to discriminate the records of Book from records
 *  of other types. The <code>@DiscriminatorValue</code> annotation specifies 
 *  that
 *  all records of Book will carry the value <code>"BOOK"</code> in the
 *  <code>@DiscriminatorColumn</code> specified with the 
 *  {@link Item inheritance strategy}. 
 *   
 * @author <A HREF="mailto:ppoddar@bea.com>Pinaki Poddar</A>
 *
 */

@Entity
@DiscriminatorValue(value = "BOOK")
public class Book
	extends Item
{
	@Column(name = "PAGE_COUNT")
	private int pageCount;

	/** A no-arg constructor is required for enhancement.
	 * 
	 *
	 */
	public Book ()
	{
		super ();
	}

	/** The public constructor constructs with a title.
	 * 
	 * @param title the title of the book.
	 */
	public Book (String title)
	{
		super (title);
	}

	/** 
	 * A single additional value to designate the number of pages in this
	 * book.
	 * 
	 * @return the page count of the book.
	 */
	public int getPageCount ()
	{
		return pageCount;
	}

	/**
	 * Sets the page count of this receiver.
	 * 
	 * @param d a non-negative number of pages.
	 */
	public void setPageCount (int d)
	{
		if (d < 0)
			throw new IllegalArgumentException ("Invalid page count " + d
					+ " for " + this);

		pageCount = d;
	}

}
