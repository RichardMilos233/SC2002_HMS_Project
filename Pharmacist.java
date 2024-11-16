import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pharmacist extends User {//ignore the staff first
	public static List<Pharmacist> pharmacists = new ArrayList<>();


	public Pharmacist(String staffID, String name, String gender, int age){
		super(staffID, name, gender, age);
		this.role = "pharmacist";
	}

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

		choice = scanner.nextInt();
		if (choice <1 || choice > Pharmacist.pharmacists.size()){
			return null;
		}
		
        pharmacist = Pharmacist.pharmacists.get(choice-1);
		return pharmacist;
	}

	public static List<Pharmacist> getPharmacists(){
		if (pharmacists.size() == 0){
			pharmacists = CSVService.readPharmacistsFromCSV();
		}
		return pharmacists;
	}

	
	public void viewAppointmentOutcomeRecord() {
		// TODO - implement Pharmacist.viewAppointmentOutcomeRecord
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