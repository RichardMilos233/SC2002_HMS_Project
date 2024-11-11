import java.time.*;
import java.util.*;

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
		System.out.println("Appointment date: " + date);
		System.out.println("Type: " + type);
		prescribedMedication.displayPrescribedMedication();
		System.out.println("Consultation notes: " + consultationNotes);
	}

	public void setPrescribedMedication(PrescribedMedication newPrescribedMedication){
		this.prescribedMedication = newPrescribedMedication;
	}

	public void setConsultationNotes(String newConsultationNotes){
		this.consultationNotes = newConsultationNotes;
	}

	// for AppointmentOutcome, the splitter of which is -
	// Serialize to txt format
    public String toTxt() {
        StringBuilder sb = new StringBuilder();
        sb.append(date).append("-");
		sb.append(type).append("-");
        sb.append(prescribedMedication.toTxt()).append("-");
		sb.append(consultationNotes);
        return sb.toString();
    }

    // Deserialize from txt format
    public static AppointmentOutcome fromTxt(String data) {
		String[] fields = data.split("-");
		LocalDate date = LocalDate.parse(fields[0]);  // Parse date
		String type = fields[1];                      // Read type as String
	
		// Parse prescribedMedication using its fromCSV method
		PrescribedMedication prescribedMedication = PrescribedMedication.fromTxt(fields[2]);
	
		String consultationNotes = fields[3];         // Read consultation notes
	
		return new AppointmentOutcome(date, type, prescribedMedication, consultationNotes);
	}
}