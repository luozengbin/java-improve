package skillup.ejb30.basic.security;

public interface SecurityAccess {
	
	void updateApplication(String applicationName, String version);
	
	void runApplication(String applicationName);
	
	void feedback(String applicationName, String comment);
	
	void deleteApplication(String applicationName);
	
	void createApplication(String applicationName);
	
	String checkApplicationInfo(String applicationName);
}
