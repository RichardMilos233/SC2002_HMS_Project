import java.util.*;

public class User {

	private String hospitalID = "P1000";
	private String password = "11414";
	private String name = "Richard Milos";
	private boolean gender = false; // false for male, true for female
	private int age = -1;

	Scanner scanner = new Scanner(System.in);
	public User() {
		throw new UnsupportedOperationException();
	}

	public User(String hospitalID, String password, String name, boolean gender, int age) {
		this.hospitalID = hospitalID;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		throw new UnsupportedOperationException();
	}

	public void login() {
		// TODO - implement User.login
		String enteredPassword = "";
		enteredPassword = scanner.nextLine();
		if (enteredPassword.equals(this.password)){
			System.out.println("Logged in successfully");// only for illustration, haven't settled how to tell the role yet
		}
		else{
			System.out.println("Try again");
		}
		throw new UnsupportedOperationException();
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

	public boolean getGender(){
		return this.gender;
	}

	public int getAge(){
		return this.age;
	}
}