package skillup.java.basic;

import static org.junit.Assert.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import skillup.java.bean.Product;

public class IntrospectorTest {
	
	/*
	 * java.beans.PropertyDescriptor は、
	 * Java Bean が一対のアクセス用メソッドを使ってエクスポートする単一のプロパティを記述します。
	 */
	@Test
	public void testGetSetParoperties() throws Exception {
		
		Product product = new Product(100L, "SUX600", "camera", new int[]{2010, 2011,2012});
		
		System.out.println(product);
		
		String propertyName = "name";
		
		Class<?> clzss = Product.class;
		
		getPropertyValue(product, propertyName, clzss);
		
		String newValue = "nihao";
		
		setPropertyValue(product, propertyName, clzss, newValue);
		
		System.out.println(product);
	}
	
	//属性更新
	private void setPropertyValue(Product product, String propertyName,
			Class<?> clzss, String newValue) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, clzss);
		
		Method writeProperty = propertyDescriptor.getWriteMethod();
		
		System.out.println(writeProperty.invoke(product, newValue));
	}
	
	//属性値取得
	private Object getPropertyValue(Product obj, String propertyName,
			Class<?> clzss) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, clzss);
		
		Method readProperty = propertyDescriptor.getReadMethod();
		
		Object result = readProperty.invoke(obj);
		
		System.out.println(result);
		
		return result;
	}
	
	//apache-commonsのBeanUtilsを利用した例です。
	@Test
	public void testApacheBeanUtils() throws Exception {
		
		Product product = new Product(100L, "SUX600", "camera", new int[]{2010, 2011,2012});
		
		BeanUtils.setProperty(product, "name", "juwuba");
		
		BeanUtils.setProperty(product, "createDate", Calendar.getInstance().getTime());
		
		
		BeanUtils.setProperty(product, "lens.partName", "gaishiwushuang");
		
		BeanUtils.setProperty(product, "lens.productedBy", "china");
		
		//innerBeanもサポートされています（IFを事前に定義する必要がある）
		BeanUtils.setProperty(product, "extendSubPart.partName", "hahaha");
		
		//BeanUtils.describeにbug（仕様かも！）があることを判明しました。
		//配列の解析がうまくできていません、１つ目しか出力されてていない
		System.out.println(BeanUtils.describe(product));
		
		System.out.println(product);
	}
}
