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
        String[] fields = data.split(",", 2);
        String outcomeDescription = fields[0];
        List<PrescribedMedication> medications = new ArrayList<>();

        if (fields.length > 1 && !fields[1].isEmpty()) {
            String[] meds = fields[1].split(";");
            for (String med : meds) {
                medications.add(PrescribedMedication.fromCSV(med));
            }
        }
        return new AppointmentOutcome(outcomeDescription, medications);
    }
}