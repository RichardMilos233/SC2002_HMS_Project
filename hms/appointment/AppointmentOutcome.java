package hms.appointment;

import hms.medicalrecords.PrescribedMedication;

import java.time.*;
/**
 * Represents the outcome of a medical appointment, detailing the diagnosis, medication prescribed,
 * and consultation notes. Also includes a resolved status to indicate whether the appointment's
 * concerns were resolved.
 */
public class AppointmentOutcome {

	private LocalDate date = LocalDate.now();
	private String type = "nil";
	private String diagnosis = "nil";
	private PrescribedMedication prescribedMedication = new PrescribedMedication();
	private String consultationNotes = "nil";
	private boolean resolved = false;

	/**
	 * Default constructor for creating an empty outcome with default values.
	 */
	public AppointmentOutcome() {}

	/**
	 * Constructs an AppointmentOutcome with specified details.
	 * 
	 * @param date The date of the appointment.
	 * @param type The type of appointment or treatment.
	 * @param prescribedMedication Medication details prescribed during the appointment.
	 * @param consultationNotes Notes made during the consultation.
	 * @param diagnosis The medical diagnosis given during the appointment.
	 * @param resolved Boolean flag indicating if the issues addressed in the appointment are resolved.
	 */
	public AppointmentOutcome(LocalDate date, String type, PrescribedMedication prescribedMedication, String cosultationNotes, String diagnosis, boolean resolved){
		this.date = date;
		this.type = type;
		this.prescribedMedication = prescribedMedication;
		this.consultationNotes = cosultationNotes;
		this.diagnosis = diagnosis;
		this.resolved = resolved;
	}

	/**
	 * Displays the complete details of the appointment outcome.
	 */
	public void displayAppointmentOutcome() {
		System.out.println("Appointment date: " + date);
		System.out.println("Type: " + type);
		System.out.println("Diagnosis: " + diagnosis);
		prescribedMedication.displayPrescribedMedication();
		System.out.println("Consultation notes: " + consultationNotes);
	}

	/**
	 * Displays the medical diagnosis of the appointment.
	 */
	public void displayAppointmentDiagnosis() {
			System.out.printf(diagnosis);
		
	}

	/**
	 * Displays the past medical diagnosis of the appointment. (This method might need more context or renaming for clarity)
	 */
	public void displayAppointmentPastDiagnosis() {
			System.out.printf(diagnosis);
	}

	/**
	 * Displays the medications prescribed during the appointment.
	 */
	public void displayAppointmentMedicine() {
			prescribedMedication.displayMedication();
	}

	/**
	 * Displays the consultation notes from the appointment.
	 */
	public void displayAppointmentNotes() {
		System.out.printf(consultationNotes);
	}

	/**
	 * Sets the medication prescribed during the appointment.
	 * @param newPrescribedMedication The new prescribed medication details.
	 */
	public void setPrescribedMedication(PrescribedMedication newPrescribedMedication){
		this.prescribedMedication = newPrescribedMedication;
	}

	/**
	 * Sets new consultation notes for the appointment.
	 * @param newConsultationNotes The new notes to set.
	 */
	public void setConsultationNotes(String newConsultationNotes){
		this.consultationNotes = newConsultationNotes;
	}

	/**
	 * Sets the resolution status of the appointment.
	 * @param resolved New resolution status.
	 */
	public void setResolved(boolean resolved){
		this.resolved = resolved;
	}

	/**
	 * Gets the resolution status of the appointment.
	 * @return The resolution status.
	 */
	public boolean getResolved(){
		return this.resolved;
	}

	// for AppointmentOutcome, the splitter of which is |
	// Serialize to txt format
	/**
	 * Serializes the appointment outcome to a text format for storage or transfer.
	 * @return A string representing the serialized form of the appointment outcome.
	 */
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
	/**
	 * Deserializes the appointment outcome from a text format.
	 * @param data The string containing the serialized appointment outcome data.
	 * @return The deserialized AppointmentOutcome object.
	 */
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

	/**
	 * Retrieves the prescribed medication details.
	 * @return The prescribed medication.
	 */
    public PrescribedMedication getPrescribedMedication() {
        return prescribedMedication;
    }

}