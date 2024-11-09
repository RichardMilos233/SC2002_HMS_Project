import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pharmacist extends User {//ignore the staff first

	public static List<Pharmacist> pharmacists = new ArrayList<>();

	public Pharmacist() {
		super();
		pharmacists.add(this);
	}

	public Pharmacist(String staffID, String password, String name, String gender, int age){
		super(staffID, password, name, gender, age);
		this.role = "pharmacist";
		pharmacists.add(this);
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

	/* 
	public void viewAppointmentOutcomeRecord() {
		// TODO - implement Pharmacist.viewAppointmentOutcomeRecord
		throw new UnsupportedOperationException();
	}

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