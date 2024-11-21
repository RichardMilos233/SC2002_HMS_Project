package hms.users;

import hms.storage.CSVService;
import hms.utils.Validator;
import hms.utils.cryptography.Hasher;
import hms.utils.cryptography.SimpleAdditiveHash;
import java.util.*;
/**
 * Base class for all user types in the system including patients, doctors, administrators, and pharmacists.
 */
public class User {

    public String hospitalID = "U42";
    // protected String password = "user";
    protected String name = "nobody";
    protected String gender = "Male";
    protected int age = -1;
    protected String role = "user"; 
    static public List<User> users = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
	/**
     * Default constructor, initializes user with default values.
     */
    public User() {}
	/**
     * Constructor with parameters to set the user's basic attributes.
     *
     * @param hospitalID The unique identifier for the user.
     * @param name       The name of the user.
     * @param gender     The gender of the user.
     * @param age        The age of the user.
     */
    public User(String hospitalID, String name, String gender, int age) {
        this.hospitalID = hospitalID;
        // this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.role = "user";
    }

    // public void login() {
    //  String enteredPassword = "";
    //  enteredPassword = scanner.nextLine();
    //  if (enteredPassword.equals(this.password)){ // can get the user id and password, then check what the user id format is to get the role
    //      System.out.println("Logged in successfully");// only for illustration, haven't settled how to tell the role yet
    //  }
    //  else{
    //      System.out.println("Try again");
    //  }
    // }

	/**
     * Logs out the user from the system.
     */
    public void logout() {
        System.out.println("Logged out successfully");
    }

    /**
     * Changes the user's password in the system.
     */
    public void changePassword() {
        CSVService csvService = new CSVService();
        Hasher hasher = new SimpleAdditiveHash();
        String newPassword = "defaultPassword";
        do { 
            System.out.print("Enter new password (at least 8 characters): ");
            newPassword = Validator.validateStringNoSpace(scanner);
        } while (newPassword.length()<7);
        // this.password = newPassword;
        int newHash = hasher.hash(newPassword, csvService.getSalt(getHospitalID()));
        csvService.changePassword(hospitalID, newHash);
        // switch (role) {
        //  case "patient":
        //      CSVService.replacePatient((Patient)this);
        //      break;
        //  case "doctor":
        //      CSVService.replaceDoctor((Doctor)this);
        //      break;
        //  case "administrator":
        //      CSVService.replaceAdmin((Administrator)this);
        //      break;
        //  case "pharmacist":
        //      CSVService.replacePharmacist((Pharmacist)this);
        //      break;

        //  default:
        //      break;
        // }
    }

	/** 
	 * Converts user details to a CSV format string.
	 * @return A string represnting the user details in CSV format
	 */
	public String toCSV() {
        return hospitalID + "," + name + "," + gender + "," + age + "," + role;
    }

	/**
     * Creates a User instance from a CSV formatted string.
     *
     * @param data CSV string containing user details.
     * @return A new User instance with the details provided in the CSV.
     */
    public static User fromCSV(String data) {
        String[] fields = data.split(",");
        String hospitalID = fields[0];
        String name = fields[1];
        String gender = fields[2];
        int age = Integer.parseInt(fields[3]);
        String role = fields[4];
        return new User(hospitalID, name, gender, age);
    }

	/**
     * Gets the hospital ID of the user.
     *
     * @return The hospital ID as a string.
     */
    public String getHospitalID(){
        return this.hospitalID;
    }

	/**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName(){
        return this.name;
    }

	/**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
    public String getGender(){
        return this.gender;
    }

	/**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public int getAge(){
        return this.age;
    }

	/**
     * Gets the role of the user within the system.
     *
     * @return The role of the user.
     */
    public String getRole(){
        return this.role;
    }

	/**
     * Sets the hospital ID for the user.
     *
     * @param newHospitalID The new hospital ID to be set.
     */
    public void setHospitalID(String newHospitalID){
        this.hospitalID = newHospitalID;
    }

	/**
     * Sets the name for the user.
     *
     * @param name The new name to be set.
     */
    public void setName(String name){
        this.name = name;
    }
	
	/**
     * Sets the gender for the user.
     *
     * @param gender The new gender to be set.
     */
    public void setGender(String gender){
        this.gender = gender;
    }

	/**
     * Sets the age for the user.
     *
     * @param age The new age to be set.
     */
    public void setAge(int age){
        this.age = age;
    }

	/**
     * Sets the role for the user.
     *
     * @param role The new role to be set.
     */
    public void setRole(String role){
        this.role = role;
    }

	/**
     * Retrieves all users from the system.
     *
     * @return A list of all users.
     */
    public static List<User> getUsers(){
        if (users.isEmpty()){
            users = CSVService.readUsersFromCSV();
        }
        return users;
    }

	/**
     * Updates the list of all users by reading from the CSV.
     */
    public static void updateUsers(){
        users = CSVService.readUsersFromCSV();
    }

	/**
     * Displays user information.
     */
    public void display(){
        System.out.println("Hospital ID: " + this.hospitalID);
        System.out.println("Name: " + this.name);
        // System.out.println("Password: " + this.password);
        System.out.println("Role: " + this.role);
        System.out.println("Gender: " + this.gender);
        System.out.println("Age: " + this.age + "\n");
    }
}
