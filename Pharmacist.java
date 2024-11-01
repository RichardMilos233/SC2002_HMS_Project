public class Pharmacist extends User {//ignore the staff first

	public Pharmacist() {
		super();
	}

	public Pharmacist(String staffID, String password, String name, String gender, int age){
		super(staffID, password, name, gender, age);
		this.role = "pharmacist";
	}

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

}