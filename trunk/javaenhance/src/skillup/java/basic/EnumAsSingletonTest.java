package skillup.java.basic;

import static org.junit.Assert.*;

import org.junit.Test;


public class EnumAsSingletonTest {
	
	@Test
	public void testSingleton() throws Exception {
		
		System.out.println(アキラ.妻.getValue());
		
		System.out.println(アキラ.妻.getValue());
		
		assertEquals(アキラ.妻, アキラ.妻);
		
	}
	
	
	//インスタンス１つしかありえないので
	public enum アキラ{
		
		妻;
		
		アキラ(){
			_妻 = new EnumAsSingletonTest().new Person(20, "米蘭", Sex.女性);
		}
		
		private Person _妻;
		
		public Person getValue(){
			return _妻;
		}
	}
	
	public enum Sex{
		男性,
		女性,
		ニューハフ;
	}
	
	public class Person{
		
		
		private int age;
		
		private String name;
		
		private Sex sex;
		
		public Person() {
			super();
		}

		public Person(int age, String name, Sex sex) {
			super();
			this.age = age;
			this.name = name;
			this.sex = sex;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
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
	}
}
