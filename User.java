import java.util.*;

public class User {

	protected String hospitalID = "U42";
	protected String password = "user";
	protected String name = "nobody";
	protected String gender = "Male";
	protected int age = -1;
	protected String role = "user"; 
	static public List<User> users = new ArrayList<>();

	Scanner scanner = new Scanner(System.in);
	public User() {}

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
		if (enteredPassword.equals(this.password)){ // can get the user id and password, then check what the user id format is to get the role
			System.out.println("Logged in successfully");// only for illustration, haven't settled how to tell the role yet
		}
		else{
			System.out.println("Try again");
		}
	}

	public void logout() {}	//do nothing, jump out of the while-switch loop

	public void changePassword() {
		String newPassword = this.password;
		System.out.print("Enter new password: ");
		newPassword = scanner.nextLine();
		this.password = newPassword;
		CSVService.changePassword(hospitalID, newPassword);
		switch (role) {
			case "patient":
				CSVService.replacePatient((Patient)this);
				break;
			case "doctor":
				CSVService.replaceDoctor((Doctor)this);	// complete all 3 utils in CSVService, implementation is highly similar to the patient one
				break;
			case "administrator":
				CSVService.replaceAdmin((Administrator)this);
				break;
			case "pharmacist":
				CSVService.replacePharmacist((Pharmacist)this);
				break;

			default:
				break;
		}
	}

	public String toCSV() {
        return hospitalID + "," + password + "," + name + "," + gender + "," + age + "," + role;
    }

	public static User fromCSV(String data) {
        String[] fields = data.split(",");
        String hospitalID = fields[0];
        String password = fields[1];
        String name = fields[2];
        String gender = fields[3];
        int age = Integer.parseInt(fields[4]);
        String role = fields[5];
        return new User(hospitalID, password, name, gender, age);
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
	public void setName(String name){
		this.name = name;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
	public void setAge(int age){
		this.age = age;
	}
	public void setRole(String role){
		this.role = role;
	}

	public static List<User> getUsers(){
		if (users.size() == 0){
			users = CSVService.readUsersFromCSV();
		}
		return users;
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