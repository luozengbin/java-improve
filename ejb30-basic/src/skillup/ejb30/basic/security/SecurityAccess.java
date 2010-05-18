package skillup.ejb30.basic.security;

public interface SecurityAccess {
	
	String adminMethods();
	
	String developerMethods();
	
	String cunsumerMethods();
	
	String anonymousMethods();
}
