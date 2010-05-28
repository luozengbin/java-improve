/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.ejb21client;

import examples.ejb.ejb30.domain.Item;
import examples.ejb.ejb30.domain.Book;


/**
 * Business interface for the session bean.
 */
public interface ItemManager {
  Book addBook(String title,String creatator);
}
