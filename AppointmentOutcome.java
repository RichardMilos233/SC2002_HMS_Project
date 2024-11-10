import java.time.*;
import java.util.ArrayList;
import java.util.List;

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

	// Serialize to CSV format
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(date).append(",");
		sb.append(type).append(",");
        sb.append(prescribedMedication.toCSV()).append(",");
		sb.append(consultationNotes);
        return sb.toString();
    }

    // Deserialize from CSV format
    public static AppointmentOutcome fromCSV(String data) {
		String[] fields = data.split(",", 4); // Split into 4 parts based on toCSV structure
		LocalDate date = LocalDate.parse(fields[0]);  // Parse date
		String type = fields[1];                      // Read type as String
	
		// Parse prescribedMedication using its fromCSV method
		PrescribedMedication prescribedMedication = PrescribedMedication.fromCSV(fields[2]);
	
		String consultationNotes = fields[3];         // Read consultation notes
	
		return new AppointmentOutcome(date, type, prescribedMedication, consultationNotes);
	}
}