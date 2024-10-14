public class AppointmentOutcome {

	private String date;
	private String type;
	private PrescribedMedication prescribedMedication;
	private String consultationNotes;

	public AppointmentOutcome() {
		throw new UnsupportedOperationException();
	}

	public AppointmentOutcome(String date, String type, PrescribedMedication prescribedMedication, String cosultationNotes){
		this.date = date;
		this.type = type;
		this.prescribedMedication = prescribedMedication;
		this.consultationNotes = cosultationNotes;
	}

	public void updatePastDiagnoses() {
		// TODO - implement AppointmentOutcome.updatePastDiagnoses
		throw new UnsupportedOperationException();
	}

	public void displayAppointmentOutcome() {
		System.out.println("Appointment date: " + date);
		System.out.println("Type: " + type);
		prescribedMedication.displayPrescribedMedication();
		System.out.println("Consultation notes: " + consultationNotes);
		throw new UnsupportedOperationException();
	}

}