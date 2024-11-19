import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Represents a pharmacist within the healthcare system. This class extends the User class,
 * adding specific functionalities that are pertinent to pharmacists, such as managing prescriptions
 * and viewing medication inventories.
 */
public class Pharmacist extends User {//ignore the staff first
	public static List<Pharmacist> pharmacists = new ArrayList<>();

	/**
     * Constructs a Pharmacist with given personal details.
     * 
     * @param staffID The unique identifier for the pharmacist.
     * @param name The name of the pharmacist.
     * @param gender The gender of the pharmacist.
     * @param age The age of the pharmacist.
     */
	public Pharmacist(String staffID, String name, String gender, int age){
		super(staffID, name, gender, age);
		this.role = "pharmacist";
	}

	/**
     * Creates a Pharmacist instance from a CSV formatted string.
     * 
     * @param data A string containing CSV formatted pharmacist data.
     * @return A new Pharmacist object populated with data from the CSV string.
     */
	public static Pharmacist fromCSV(String data) {	// create a new pharmacist then return
		String[] fields = data.split(",");
        String hospitalID = fields[0];
        String name = fields[1];
        String gender = fields[2];
        int age = Integer.parseInt(fields[3]);
        String role = fields[4];
        Pharmacist pharmacist = new Pharmacist(hospitalID, name, gender, age);
        return pharmacist;
    }

	/**
     * Lists all available pharmacists and allows the selection of a specific pharmacist.
     * 
     * @return The selected Pharmacist, or null if the selection is invalid.
     */
	public static Pharmacist getPharmacist(){	//list out all pharmacists, then select a pharmacist
		Scanner scanner = new Scanner(System.in);
		Pharmacist pharmacist;
        int i;
        int choice = -1;

		System.out.println("Choose the pharmacist you want:");
		for (i = 0; i < Pharmacist.pharmacists.size(); i++){
			pharmacist = Pharmacist.pharmacists.get(i);
			System.out.println(i+1 + ": " + pharmacist.getName());
		}

		choice = Validator.validateInt(scanner);
		if (choice <1 || choice > Pharmacist.pharmacists.size()){
			return null;
		}
		
        pharmacist = Pharmacist.pharmacists.get(choice-1);
		return pharmacist;
	}

	/**
     * Retrieves and updates the static list of pharmacists from a CSV file.
     * 
     * @return A list of updated Pharmacist objects.
     */
	public static List<Pharmacist> updatePharmacists(){
		pharmacists = CSVService.readPharmacistsFromCSV();
		return pharmacists;
	}

	/**
     * Provides a list of all pharmacists. If the list is empty, it retrieves it from a CSV file.
     * 
     * @return A list of Pharmacist objects.
     */
	public static List<Pharmacist> getPharmacists(){
		if (pharmacists.isEmpty()){
			pharmacists = CSVService.readPharmacistsFromCSV();
		}
		return pharmacists;
	}

	/**
     * Allows a pharmacist to view appointment outcomes relevant to their role.
     * This method is currently unsupported and serves as a placeholder for future implementation.
     */
	public void viewAppointmentOutcomeRecord() {
		PharmacistAppointmentOutcomeRecordViewer.getAppointmentOutcomes();
		throw new UnsupportedOperationException();
	}

	/* 
	public void updatePrescriptionStatus() {
		// TODO - implement Pharmacist.updatePrescriptionStatus
		throw new UnsupportedOperationException();
	}

	public void viewMedicationInventory() {
		// TODO - implement Pharmacist.viewMedicationInventory
		throw new UnsupportedOperationException();
	}

	public void submitReplenishmentRequest() {
		// TODO - implement Pharmacist.submitReplenishmentRequest
		throw new UnsupportedOperationException();
	}
 	*/
}