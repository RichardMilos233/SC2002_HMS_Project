import java.time.*;

public class AppointmentOutcome {

	private LocalDate date;
	private String type;
	private PrescribedMedication prescribedMedication;
	private String consultationNotes;

	public AppointmentOutcome() {}

	public AppointmentOutcome(LocalDate date, String type, PrescribedMedication prescribedMedication, String cosultationNotes){
		this.date = date;
		this.type = type;
		this.prescribedMedication = prescribedMedication;
		this.consultationNotes = cosultationNotes;
	}

	

	public void displayAppointmentOutcome() {
		System.out.println();
		System.out.println("Appointment date: " + date);
		System.out.println("Type: " + type);
		prescribedMedication.displayPrescribedMedication();
		System.out.println("Consultation notes: " + consultationNotes);
	}

}