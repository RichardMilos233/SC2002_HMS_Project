import java.util.*;
import java.time.*;

public class Doctor extends User {//ignore the Staff class first
	public static List<Doctor> doctors = new ArrayList<>();	//later could read the csv to load the existing doctors first
	private ArrayList<Patient> patients = new ArrayList<>();
	private List<Appointment> timeTable = new ArrayList<>();

	public Doctor() {
		super();
		this.role = "doctor";
		doctors.add(this);
		initializeTimeTable();
	}

	public Doctor(String staffID, String password, String name, String gender, int age){
		super(staffID, password, name, gender, age);
		this.role = "doctor";
		doctors.add(this);
		initializeTimeTable();
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

	public void initializeTimeTable(){
		LocalDate currentDate = LocalDate.now();
		LocalTime startTime = LocalTime.of(9, 0);
		LocalDate date;
		LocalTime time;
		Appointment appointment;
		// allocate the time table for the next 7 days
		for (int i = 1; i <= 7; i++){
			date = currentDate.plusDays(i);
			// working hours 0900-1700, 1h for each slot
			for (int j = 0; j <= 8; j++){
				time = startTime.plusHours(j);
				appointment = new Appointment(this, date, time);
				this.timeTable.add(appointment);
			}
		}
	}

	public List<Patient> getPatients(){
		return this.patients;
	}

	public static Doctor getDoctor(){	//list out all doctors, then select a doctor
		Scanner scanner = new Scanner(System.in);
		Doctor doctor;
        int i;
        int choice = -1;

		System.out.println("Choose the doctor you want:");
		for (i = 0; i < Doctor.doctors.size(); i++){
			doctor = Doctor.doctors.get(i);
			System.out.println(i+1 + ": " + doctor.getName());
		}

		choice = scanner.nextInt();
		if (choice <1 || choice > Doctor.doctors.size()){
			System.out.println("invalid choice");
			return null;
		}
		
        doctor = Doctor.doctors.get(choice-1);
		return doctor;
	}
}