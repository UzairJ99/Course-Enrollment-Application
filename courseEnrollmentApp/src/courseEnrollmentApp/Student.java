package courseEnrollmentApp;

public class Student {
	private String firstName, lastName, macid, password;
	
	public Student(String firstName, String lastName, String macid, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.macid = macid;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getMacId() {
		return macid;
	}
	
	public String getName() {
		String name = firstName + " " + lastName;
		return name;
	}
}
