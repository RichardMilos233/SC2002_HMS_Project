import java.util.*;

public class User {

	protected String hospitalID = "U42";
	protected String password = "user";
	protected String name = "nobody";
	protected String gender = "Male";
	protected int age = -1;
	protected String role = "user";

	Scanner scanner = new Scanner(System.in);
	public User() {
	}

	public User(String hospitalID, String password, String name, String gender, int age) {
		this.hospitalID = hospitalID;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.role = "user";
	}

	public void login() {
		String enteredPassword = "";
		enteredPassword = scanner.nextLine();
		if (enteredPassword.equals(this.password)){
			System.out.println("Logged in successfully");// only for illustration, haven't settled how to tell the role yet
		}
		else{
			System.out.println("Try again");
		}
	}

	public void logout() {
		// TODO - implement User.logout
		throw new UnsupportedOperationException();
	}

	public void changePassword() {
		String newPassword = "";
		System.out.print("Enter new password: ");
		newPassword = scanner.nextLine();
		this.password = newPassword;
		throw new UnsupportedOperationException();
	}

	public String getHospitalID(){
		return this.hospitalID;
	}

	public String getName(){
		return this.name;
	}

	public String getGender(){
		return this.gender;
	}

	public int getAge(){
		return this.age;
	}

	public String getRole(){
		return this.role;
	}

	public void setHospitalID(String newHospitalID){
		this.hospitalID = newHospitalID;
	}

	public void display(){
		System.out.println("Hospital ID: " + this.hospitalID);
		System.out.println("Name: " + this.name);
		System.out.println("Password: " + this.password);
		System.out.println("Role: " + this.role);
		System.out.println("Age: " + this.age);
		System.out.println("Gender: " + this.gender);
	}
}