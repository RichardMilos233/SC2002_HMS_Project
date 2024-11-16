import java.time.*;

public class AppointmentOutcome {

	private LocalDate date = LocalDate.now();
	private String type = "nil";
	private String diagnosis = "nil";
	private PrescribedMedication prescribedMedication = new PrescribedMedication();
	private String consultationNotes = "nil";
	private boolean resolved = false;

	public AppointmentOutcome() {}

	public AppointmentOutcome(LocalDate date, String type, PrescribedMedication prescribedMedication, String cosultationNotes, String diagnosis, boolean resolved){
		this.date = date;
		this.type = type;
		this.prescribedMedication = prescribedMedication;
		this.consultationNotes = cosultationNotes;
		this.diagnosis = diagnosis;
		this.resolved = resolved;
	}

	public void displayAppointmentOutcome() {
		System.out.println("Appointment date: " + date);
		System.out.println("Type: " + type);
		System.out.println("Diagnosis: " + diagnosis);
		prescribedMedication.displayPrescribedMedication();
		System.out.println("Consultation notes: " + consultationNotes);
	}

	public void displayAppointmentDiagnosis() {
			System.out.printf(diagnosis);
		
	}

	public void displayAppointmentPastDiagnosis() {
			System.out.printf(diagnosis);
		
	}

	public void displayAppointmentMedicine() {
			prescribedMedication.displayMedication();
		
	}

	public void displayAppointmentNotes() {
		System.out.printf(consultationNotes);
	}

	public void setPrescribedMedication(PrescribedMedication newPrescribedMedication){
		this.prescribedMedication = newPrescribedMedication;
	}

	public void setConsultationNotes(String newConsultationNotes){
		this.consultationNotes = newConsultationNotes;
	}

	public void setResolved(boolean resolved){
		this.resolved = resolved;
	}

	public boolean getResolved(){
		return this.resolved;
	}

	// for AppointmentOutcome, the splitter of which is |
	// Serialize to txt format
    public String toTxt() {
        StringBuilder sb = new StringBuilder();
        sb.append(date).append("|");
		sb.append(type).append("|");
        sb.append(prescribedMedication.toTxt()).append("|");
		sb.append(consultationNotes).append("|");
		sb.append(diagnosis).append("|");
		sb.append(resolved);
        return sb.toString();
    }

    // Deserialize from txt format
    public static AppointmentOutcome fromTxt(String data) {
		String[] fields = data.split("\\|");

		LocalDate date = LocalDate.parse(fields[0]);  // Parse date
		String type = fields[1];                      // Read type as String

	
		// Parse prescribedMedication using its fromCSV method
		PrescribedMedication prescribedMedication = PrescribedMedication.fromTxt(fields[2]);
		String consultationNotes = fields[3];         // Read consultation notes
		String diagnosis = fields[4];
		boolean resolved = fields[5].equals("true");
	
		return new AppointmentOutcome(date, type, prescribedMedication, consultationNotes, diagnosis, resolved);
	}

    public PrescribedMedication getPrescribedMedication() {
        return prescribedMedication;
    }

	
}