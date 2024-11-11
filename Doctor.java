import java.io.*;
import java.time.*;
import java.util.*;
import java.util.logging.*;

public class Doctor extends User {	//ignore the Staff class first
	public class PatientCount{	// this class keeps track of which patient is under which doctor even if a patient has multiple appointments
		Patient patient;
		int numOfAppointment = 1;
		public PatientCount(Patient patient){
			this.patient = patient;
			this.numOfAppointment = 1;
		}
		public Patient getPatient(){
			return this.patient;
		}
		public int getNumOfAppointment(){
			return this.numOfAppointment;
		}
		public void addNumOfAppointment(){
			this.numOfAppointment += 1;
		}
		public void minusNumOfAppointment(){
			this.numOfAppointment -= 1;
		}
		public boolean isEmpty(){
			return (this.numOfAppointment == 0) ;
		}
	}
	public static List<Doctor> doctors = new ArrayList<>();	//later could read the csv to load the existing doctors first
	private ArrayList<PatientCount> patientCounts = new ArrayList<>();
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

	public String toCSV() {	// directly inherit from user
        return super.toCSV();
    }

    public Doctor fromCSV(String data) {	// downcast to Doctor then return
        Doctor doctor = (Doctor) super.fromCSV(data);
		doctor.role = "doctor";
        return doctor;
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
		// allocate the time table for the next 3 days
		for (int i = 1; i <= 3; i++){
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
		List<Patient> patients = new ArrayList<>();
		for (PatientCount patientCount : patientCounts){
			patients.add(patientCount.getPatient());
		}
		return patients;
	}

	public void addPatient(Patient patient){
		for (PatientCount patientCount : patientCounts){
			if (patientCount.getPatient().equals(patient)){
				patientCount.addNumOfAppointment();
				return;
			}
		}
		PatientCount newPatientCount = new PatientCount(patient);
		patientCounts.add(newPatientCount);
	}

	public void removePatient(Patient patient){
		for (PatientCount patientCount : patientCounts){
			if (patientCount.getPatient().equals(patient)){
				patientCount.minusNumOfAppointment();
				if (patientCount.isEmpty()){
					patientCounts.remove(patientCount);
				}
				return;
			}
		}
		System.out.println("invalid operation");
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
			return null;
		}
		
        doctor = Doctor.doctors.get(choice-1);
		return doctor;
	}

	public static Doctor getByID(String doctorID){
        List<Doctor> doctors;
		try {
			doctors = CSVService.readDoctorsFromCSV();
		} catch (IOException e) {
			Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, "Failed to read doctors from CSV", e);
			return null;
		}
        Doctor doctor;
		int i = 0;
		for (i=0; i<doctors.size(); i++){
			doctor = doctors.get(i);
			if (doctor.hospitalID.equals(doctorID)){
				return doctor;
			}
		}
		return null;
    }

}