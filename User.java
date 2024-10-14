import java.util.*;

public class User {

	private String hospitalID = '';
	private String password = '114514';
	private String name = '';
	private boolean gender = false; // false for male, true for female
	private int age = -1;

	Scanner scanner = new Scanner(System.in);
	public User() {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

	public User(String hospitalID, String password, String name, boolean gender, int age) {
		// TODO - implement User.User
		this.hospitalID = hospitalID;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		throw new UnsupportedOperationException();
	}

	public void login() {
		// TODO - implement User.login
		String enteredPassword = '';
		
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement User.logout
		throw new UnsupportedOperationException();
	}

	public void changePassword() {
		// TODO - implement User.changePassword
		String newPassword = '';
		System.out.print("Enter new password: ");
		newPassword = scanner.nextLine();
		this.password = newPassword;
		throw new UnsupportedOperationException();
	}

}