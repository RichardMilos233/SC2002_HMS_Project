public class PrescribedMedication {

	private String medicationName;
	private int status;// 0 for pending, 1 for completed, for now

	public PrescribedMedication() {}

	public PrescribedMedication(String medicationName, int status){
		this.medicationName = medicationName;
		this.status = status;
	}

	public void setStatus(int newStatus) {
		this.status = newStatus;
	}

	public void displayPrescribedMedication(){
		System.out.println("Medication: " + medicationName);
	}
}