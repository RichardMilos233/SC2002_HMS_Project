package hms.account;

import hms.storage.CSVService;
import hms.storage.TextService;
import hms.utils.Validator;
import hms.appointment.Appointment;

import java.time.*;
import java.util.*;
/**
 * Represents a medical doctor with capabilities to manage patients and appointments.
 * This class extends the User class, adding specific attributes and methods suitable for medical operations.
 */
public class Doctor extends User {
	/**
	 * Keeps track of the number of appointments associated with each patient under the care of a doctor.
	 */
	public class PatientCount{	// this class keeps track of which patient is under which doctor even if a patient has multiple appointments
		Patient patient;
		int numOfAppointment = 1;
		/**
		 * Constructs a PatientCount instance for tracking appointments of a patient.
		 * @param patient The patient associated with this count.
		 */
		public PatientCount(Patient patient){
			this.patient = patient;
			this.numOfAppointment = 1;
		}
		/**
		 * Gets the associated patient.
		 * @return The patient object.
		 */
		public Patient getPatient(){
			return this.patient;
		}
		/**
		 * Gets the number of appointments for this patient.
		 * @return The number of appointments.
		 */
		public int getNumOfAppointment(){
			return this.numOfAppointment;
		}
		/**
		 * Increments the number of appointments by one.
		 */
		public void addNumOfAppointment(){
			this.numOfAppointment += 1;
		}
		/**
		 * Decrements the number of appointments by one. If the count reaches zero, it may indicate removal is necessary.
		 */
		public void minusNumOfAppointment(){
			this.numOfAppointment -= 1;
		}
		 /**
		 * Checks if there are no appointments left for this patient.
		 * @return True if there are no appointments, otherwise false.
		 */
		public boolean isEmpty(){
			return (this.numOfAppointment == 0) ;
		}
	}
	public static List<Doctor> doctors = new ArrayList<>();
	private ArrayList<PatientCount> patientCounts = new ArrayList<>();	// containing patients who have one or multiple appointments of status "closed", "confirmed", "pending"
	private List<Appointment> timeTable = new ArrayList<>();

	/**
	 * Constructor initializes a Doctor with identification and demographic details, loading or initializing a timetable as needed.
	 * @param staffID Unique identifier for the doctor.
	 * @param name Doctor's name.
	 * @param gender Doctor's gender.
	 * @param age Doctor's age.
	 */
	public Doctor(String staffID, String name, String gender, int age){
		super(staffID, name, gender, age);
		this.role = "doctor";
		this.timeTable = getTimeTable();	// read from text first
		if (timeTable.size() == 0){	// if indeed no time table, initialize it
			initializeTimeTable();
		}
	}

	/**
	 * Serializes doctor's information into a CSV format string.
	 * @return CSV string of doctor's data.
	 */
	public String toCSV() {	// directly inherit from User, same for admin and pharmacist
        return super.toCSV();
    }

	/**
	 * Deserializes a string in CSV format to create a Doctor object.
	 * @param data CSV formatted string.
	 * @return Doctor object instantiated from the CSV data.
	 */
    public static Doctor fromCSV(String data) {	// create a new doc then return
		String[] fields = data.split(",");
        String hospitalID = fields[0];
        String name = fields[1];
        String gender = fields[2];
        int age = Integer.parseInt(fields[3]);
        String role = fields[4];
        Doctor doctor = new Doctor(hospitalID, name, gender, age);
        return doctor;
    }

	/**
	 * Updates or reloads the list of doctors from the CSV file.
	 * @return A list of updated doctor objects.
	 */
	public static List<Doctor> updateDoctors(){
		doctors = CSVService.readDoctorsFromCSV();
		return doctors;
	}

	/**
	 * Retrieves a list of all doctors from the system. If the list of doctors is empty,
	 * it fetches the list from the CSV file to ensure it is up-to-date.
	 *
	 * @return A list of all doctors currently registered in the system.
	 */
	public static List<Doctor> getDoctors(){
		if (doctors.isEmpty()){
			doctors = CSVService.readDoctorsFromCSV();
		}
		return doctors;
	}

	/**
	 * Retrieves the doctor's appointment timetable, loading it from storage if not already loaded.
	 * @return A list of the doctor's appointments.
	 */
	public List<Appointment> getTimeTable(){
		if (this.timeTable.isEmpty()){
			this.timeTable = TextService.getDoctorAppointment(this.hospitalID);
		}
		return this.timeTable;
	}

	/**
	 * Initializes a new timetable for the doctor with appointments for the next three days.
	 */
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
				appointment = new Appointment(this.hospitalID, date, time);
				this.timeTable.add(appointment);
				TextService.appendAppointment(appointment);	// either this or above would work alone, but both can coexist?
			}
		}
	}

	/**
	 * Retrieves or calculates the patient counts from the timetable if not already done.
	 * @return A list of patient counts.
	 */
	public List<PatientCount> getPatientCounts(){
		List<Appointment> timeTable = new ArrayList<>();
		Appointment apt;
		String status;
		int i;
		Patient patient;
		boolean exist;
		if (patientCounts.size() == 0){
			timeTable = getTimeTable();
			if (timeTable.size() == 0){
				return patientCounts;
			}
			for (i = 0; i < timeTable.size(); i++){
				apt = timeTable.get(i);
				status = apt.getStatus();
				if (status.equals("closed") || status.equals("confirmed") || status.equals("pending")){
					patient = Patient.getByID(apt.getPatientID());
					exist = false;
					for (PatientCount patientCount : patientCounts){	// addPatient in essence, but directly calling it will cause cyclic dependency issue
						if (patientCount.getPatient().equals(patient)){
							patientCount.addNumOfAppointment();
							exist = true;
							break;
						}
					}
					if (!exist){
						patientCounts.add(new PatientCount(patient));
					}
				}
			}
		}
		return patientCounts;
	}

	/**
	 * Retrieves a list of patients who have appointments with this doctor. This method
	 * compiles a list of patients based on the current count of appointments per patient
	 * that are either closed, confirmed, or pending.
	 *
	 * @return A list of patients who have been seen or are scheduled to be seen by this doctor.
	 */
	public List<Patient> getPatients(){
		List<Patient> patients = new ArrayList<>();
		for (PatientCount patientCount : getPatientCounts()){
			patients.add(patientCount.getPatient());
		}
		return patients;
	}

	/**
	 * Adds a patient to the doctor's list, updating the number of appointments if the patient already exists.
	 * @param patient The patient to add.
	 */
	public void addPatient(Patient patient){
		for (PatientCount patientCount : getPatientCounts()){
			if (patientCount.getPatient().equals(patient)){
				patientCount.addNumOfAppointment();
				return;
			}
		}
		PatientCount newPatientCount = new PatientCount(patient);
		patientCounts.add(newPatientCount);
	}

	/**
	 * Removes a patient from the doctor's list, decrementing the count of appointments or removing entirely if no appointments remain.
	 * @param patient The patient to remove.
	 */
	public void removePatient(Patient patient){
		for (PatientCount patientCount : getPatientCounts()){
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

	/**
	 * Utility method to select and return a doctor from a list by allowing user interaction.
	 * @return Selected doctor or null if none selected.
	 */
	public static Doctor getDoctor(){	//list out all doctors, then select a doctor
		Scanner scanner = new Scanner(System.in);
		Doctor doctor;
        int i;
        int choice = -1;

		doctors = getDoctors();
		if (doctors.isEmpty()){
			System.out.println("There is currently no doctor at work");
			return null;
		}

		System.out.println("Choose the doctor you want:");
		for (i = 0; i < Doctor.doctors.size(); i++){
			doctor = Doctor.doctors.get(i);
			System.out.println(i+1 + ": " + doctor.getName());
		}

		do { 
			System.out.println("Choose the doctor you want or 0 to return:");
			choice = Validator.validateInt(scanner);
			if (choice == 0){
				return null;
			}
		} while (choice <1 || choice > Doctor.doctors.size());
		
        doctor = Doctor.doctors.get(choice-1);
		return doctor;
	}

	/**
	 * Retrieves a doctor by their unique ID from a list.
	 * @param doctorID The ID to search for.
	 * @return Doctor object if found, otherwise null.
	 */
	public static Doctor getByID(String doctorID){
        List<Doctor> doctors;
		doctors = CSVService.readDoctorsFromCSV();
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