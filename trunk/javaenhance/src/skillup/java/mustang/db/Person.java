package skillup.java.mustang.db;

public class Person {
	public String name;

	public int age;

	@Override
	public String toString() {
		return String.format("[name=%s, age=%d]", name, age);
	}
}
