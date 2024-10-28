public class PrescribedMedication {

	private String medicationName;
	private int status;// 0 for pending, 1 for completed, for now

	public PrescribedMedication() {}

	public PrescribedMedication(String medicationName, int status){
		this.medicationName = medicationName;
		this.status = status;
	}

	/**
	 * 
	 * @param newStatus
	 */
	public void setStatus(String newStatus) {
		// TODO - implement PrescribedMedication.setStatus
		throw new UnsupportedOperationException();
	}

	public void displayPrescribedMedication(){
		System.out.println("Medication: " + medicationName);
	}
}