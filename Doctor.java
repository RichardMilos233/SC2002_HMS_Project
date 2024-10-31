import java.util.*;

public class Doctor extends User {//ignore the Staff class first
	public static List<Doctor> doctors = new ArrayList<>();	//later could read the csv to load the existing doctors first
	private ArrayList<Patient> patients = new ArrayList<>();
	private List<Appointment> timeTable = new ArrayList<>();
	private String role = "doctor";

	public Doctor() {
		super();
		this.role = "doctor";
		doctors.add(this);
	}

	public Doctor(String staffID, String password, String name, String gender, int age){
		super(staffID, password, name, gender, age);
		this.role = "doctor";
		doctors.add(this);
	}

	public void viewPatientMedicalRecord(Patient patient) {
		if (patients.contains(patient)){
			patient.viewMedicalRecord();
		}
	}

	public void updatePatientMedicalRecord(Patient patient) {
		// TODO - implement Doctor.viewPatientMedicalRecord
		throw new UnsupportedOperationException();
	}

	public void viewPersonalSchedule() {
		// TODO - implement Doctor.viewPersonalSchedule
		throw new UnsupportedOperationException();
	}

	public void setAvailability() {
		// TODO - implement Doctor.setAvailability
		throw new UnsupportedOperationException();
	}

	public void acceptAppointment() {
		// TODO - implement Doctor.acceptAppointment
		throw new UnsupportedOperationException();
	}

	public void viewUpcomingAppointment() {
		// TODO - implement Doctor.viewUpcomingAppointment
		throw new UnsupportedOperationException();
	}

	public void recordAppointmentOutcome(Appointment appointment) {
		String medication;
		System.out.println("Enter medication: ");
		medication = scanner.nextLine();//only 1 med for each appointment, for now
		PrescribedMedication prescribedMedication = new PrescribedMedication(medication, 0);
		String consultationNotes;
		System.out.println("Enter consultation notes: ");
		consultationNotes = scanner.nextLine();
		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.getDate(), "face2face", prescribedMedication, consultationNotes);//haven't thought of a type for consultation
		appointment.setAppointmentOutcome(appointmentOutcome);
		appointment.getPatient().getPastDiagnoses().updatePastDiagnoses(appointmentOutcome);
	}
	
	public List<Appointment> getTimeTable(){
		return this.timeTable;
	}
}