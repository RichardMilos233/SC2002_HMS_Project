package hms.account;

import hms.storage.CSVService;
import hms.utils.Validator;

import java.util.*;

/**
 * Represents an administrator in a system, extending the user functionalities. 
 * Administrators can be created, retrieved, and listed from a CSV data source.
 */
public class Administrator extends User { //ignore Staff first
	/**
     * A list of all administrators maintained in memory for quick access.
     */
	public static List<Administrator> administrators = new ArrayList<>();

	/**
     * Constructs an Administrator with specified details.
     *
     * @param staffID The unique identifier of the staff member.
     * @param name The name of the administrator.
     * @param gender The gender of the administrator.
     * @param age The age of the administrator.
     */
	public Administrator(String staffID, String name, String gender, int age){
		super(staffID, name, gender, age);
		this.role = "administrator";
	}

	/**
     * Creates an Administrator instance from a CSV formatted string.
     *
     * @param data The comma-separated values string containing administrator data.
     * @return The Administrator instance created from parsed CSV data.
     */
	public static Administrator fromCSV(String data) {	// create a new admin then return
		String[] fields = data.split(",");
        String hospitalID = fields[0];
        String name = fields[1];
        String gender = fields[2];
        int age = Integer.parseInt(fields[3]);
        String role = fields[4];
        Administrator admin = new Administrator(hospitalID, name, gender, age);
        return admin;
    }

	/**
     * Lists all administrators and allows the user to select one.
     *
     * @return The selected Administrator, or null if an invalid choice is made.
     */
	public static Administrator getAdministrator(){	//list out all admins, then select an admin
		Scanner scanner = new Scanner(System.in);
		Administrator administrator;
        int i;
        int choice = -1;

		System.out.println("Choose the administrator you want:");
		for (i = 0; i < Administrator.administrators.size(); i++){
			administrator = Administrator.administrators.get(i);
			System.out.println(i+1 + ": " + administrator.getName());
		}

		choice = Validator.validateInt(scanner);
		if (choice <1 || choice > Administrator.administrators.size()){
			return null;
		}
		
        administrator = Administrator.administrators.get(choice-1);
		return administrator;
	}

	/**
     * Updates the list of administrators by reading from a CSV file.
     */
	public static void updateAdministrators(){
		administrators = CSVService.readAdminsFromCSV();
	}

	/**
     * Retrieves a list of all administrators, refreshing from a CSV file if the list is empty.
     *
     * @return A list of Administrator objects.
     */
	public static List<Administrator> getAdministrators(){
		if (administrators.isEmpty()){
			administrators = CSVService.readAdminsFromCSV();
		}
		return administrators;
	}
}