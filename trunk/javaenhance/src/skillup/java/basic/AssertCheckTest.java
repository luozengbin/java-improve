package skillup.java.basic;

public class AssertCheckTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		printAge(20);
		
		printAge(30);
		
		printAge(300);
		
		printAge(100);
		
	}
	
	public static void printAge(int age){
		
		assert false : "ありえない年齢範囲でした。";
		
		System.out.println("age:" + age);
	}

}
