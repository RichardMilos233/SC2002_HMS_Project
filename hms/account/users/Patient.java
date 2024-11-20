package hms.account.users;

import hms.appointment.Appointment;
import hms.medicalrecords.PastDiagnoses;
import hms.storage.CSVService;
import hms.storage.TextService;
import hms.utils.DateConverter;
import hms.utils.Validator;

import java.time.*;
import java.util.*;
/**
 * Represents a patient within the hospital management system. This class extends the User class,
 * adding specific attributes and methods relevant to a patient's interactions and records in the system.
 */
public class Patient extends User {
	public static List<Patient> patients = new ArrayList<>();
	private LocalDate birth = LocalDate.of(2005, 1, 22);
	private int contactNumber = 1919810;
	private String email = "example@xxx.com";
	private String bloodType = "A+";
	private List<Appointment> timeTable = new ArrayList<>();
	private PastDiagnoses pastDiagnoses = new PastDiagnoses();
	// private String latestMedicalStatus = "status";
	// private ArrayList<String> Diagnoses = new ArrayList<>(); 
	//new diagnoses, prescriptions, and treatment plans.

	Scanner scanner = new Scanner(System.in);

	/**
     * Constructor for creating a new patient with detailed information.
     *
     * @param patientID Unique identifier for the patient.
     * @param name Name of the patient.
     * @param gender Gender of the patient.
     * @param age Age of the patient.
     * @param birth Date of birth of the patient.
     * @param contactNumber Contact number of the patient.
     * @param email Email address of the patient.
     * @param bloodType Blood type of the patient.
     */
	public Patient(String patientID, String name, String gender, int age, LocalDate birth, int contactNumber, String email, String bloodType){
		super(patientID, name, gender, age);
		this.birth = birth;
		this.contactNumber = contactNumber;
		this.email = email;
		this.bloodType = bloodType;
		this.age = DateConverter.calculateAge(birth);
		this.role = "patient";
	}

	/**
     * Updates personal information of the patient such as contact number and email.
     */
	public void updatePersonalInformation() {
		int newContactNumber;
		String newEmail;
		System.out.println("Contact Number: " + this.contactNumber);
		System.out.println("Email: " + this.email);
		int choice;
		do {
		System.out.println("\nChoose information to update:");
		System.out.println("1. Contact Number");
		System.out.println("2. Email");
		choice = Validator.validateInt(scanner);
		}while (choice < 1 || choice >2);

		switch (choice) {
			case 1:
				do {
					System.out.println("Enter new contact number:");
					newContactNumber = Validator.validateInt(scanner);
				} while (newContactNumber/9999999 < 1 || newContactNumber/9999999 > 9);
				this.contactNumber = newContactNumber;
				System.out.println("Your new contact number is " + this.getContactNumber());
				break;
			case 2:
				System.out.println("Enter new email:");	// buffer, otherwise it would be "\n"
				newEmail = Validator.validateEmail(scanner);
				this.email = newEmail;
				System.out.println("Your new email is " + this.getEmail());
				break;
	
			default:
				break;
		}
		CSVService.replacePatient(this);
	}

	/**
     * Converts the patient information to a CSV format string.
     * @return A string representing the patient in CSV format.
     */
	public String toCSV(){
		//hospitalID + "," + password + "," + name + "," + gender + "," + age + "," + role;
		return super.toCSV() + "," + birth + "," + bloodType + "," + email + "," + contactNumber;
	}

	/**
     * Static method to create a Patient object from a CSV format string.
     * @param data A string containing the CSV formatted data.
     * @return A new Patient object populated with the data from the CSV string.
     */
	public static Patient fromCSV(String data){
		String[] fields = data.split(",");
		User user = User.fromCSV(data);
		LocalDate birth = LocalDate.parse(fields[5]);
		String bloodType = fields[6];
		String email = fields[7];
		int contactNumber = Integer.parseInt(fields[8]);
		Patient patient = new Patient(user.hospitalID,
										user.name, 
										user.gender, 
										user.age, 
										birth, 
										contactNumber, 
										email, 
										bloodType);
		return patient;
	}

	/**
	 * Gets the date of birth of the patient.
	 * @return The date of birth as a LocalDate object.
	 */
	public LocalDate getBirth(){
		return this.birth;
	}

	/**
	 * Gets the contact number of the patient.
	 * @return The patient's contact number as an integer.
	 */
	public int getContactNumber() {
		return this.contactNumber;
	}

	/**
	 * Sets the contact number of the patient.
	 * @param newContactNumber The new contact number to set for the patient.
	 */
	public void setContactNumber(int newcontactNumber) {
		this.contactNumber = newcontactNumber;
	}

	/**
	 * Gets the email address of the patient.
	 * @return The email address of the patient.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email address of the patient.
	 * @param newEmail The new email address to set for the patient.
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	/**
	 * Gets the blood type of the patient.
	 * @return The blood type of the patient.
	 */
	public String getBloodType(){
		return this.bloodType;
	}

	/**
	 * Gets the past diagnoses of the patient.
	 * This method ensures the past diagnoses list is populated based on the patient's appointment history.
	 * @return The PastDiagnoses object containing the list of past diagnoses.
	 */
	public PastDiagnoses getPastDiagnoses(){
		if (pastDiagnoses.size() == 0){
			for (Appointment appointment : getTimeTable()){
				if (appointment.getPatient().getHospitalID().equals(this.hospitalID) &&
					(appointment.getStatus().equals("closed") || appointment.getStatus().equals("dispensed"))){
					pastDiagnoses.updatePastDiagnoses(appointment.getAppointmentOutcome());
				}
			}
		}
		return this.pastDiagnoses;
	}

	/**
	 * Gets the timetable of appointments for the patient.
	 * This method ensures the timetable is populated if it is currently empty.
	 * @return A list of Appointment objects representing the patient's appointments.
	 */
	public List<Appointment> getTimeTable(){	// all the appointments that have the id of this patient
		if (this.timeTable.size() == 0){
			this.timeTable = TextService.getPatientAppointment(this.hospitalID);
		}
		return this.timeTable;
	}

	/**
	 * Updates the patient's appointment timetable by fetching the latest list of appointments from the data source.
	 * This method ensures that the patient's timetable reflects any changes or new appointments that have been added
	 * since the last update.
	 *
	 * @return The updated list of appointments for the patient.
	 */
	public List<Appointment> updateTimeTable(){
		this.timeTable = TextService.getPatientAppointment(this.hospitalID);
		return this.timeTable;
	}

	/**
	 * Adds a new appointment to the patient's appointment timetable. This method allows for the direct insertion
	 * of an appointment into the patient's current list of appointments without needing to fetch the entire list
	 * from the data source again.
	 *
	 * @param appointment The appointment object to be added to the timetable. This appointment should contain
	 *                    all necessary details such as the date, time, and status.
	 */
	public void addAppointment(Appointment appointment){
		this.timeTable.add(appointment);
	}

	/**
	 * Gets a list of scheduled appointments for the patient.
	 * Scheduled appointments include only those with statuses 'pending' and 'confirmed'.
	 * @return A list of scheduled Appointment objects.
	 */
	public List<Appointment> getScheduledAppointments(){	// scheduled appointments only contain pending and confirmed appointments
		List<Appointment> scheduledAppointments = new ArrayList<>();
		for (Appointment appointment : getTimeTable()){
			if (appointment.getStatus().equals("pending") || appointment.getStatus().equals("confirmed")){
				scheduledAppointments.add(appointment);
			}
		}
		return scheduledAppointments;
	}

	/**
     * Retrieves a list of all patients from the database.
     * @return A list of all patients.
     */
	public static List<Patient> getPatients(){
		if (patients.size() == 0) {
            patients = CSVService.readPatientsFromCSV();
        }
        return patients;
	}

	/**
     * Displays patient's details including the extended attributes like blood type and contact information.
     */
	public void display(){
		super.display();
		System.out.println("Date of Birth: " + this.birth);
		System.out.println("Contact Number: " + this.contactNumber);
		System.out.println("Email: " + this.email);
		System.out.println("Blood Type: " + this.bloodType);
	}

	/**
     * Static method to select a Patient from a list of patients.
     * @param patients A list of patients to select from.
     * @return The selected patient.
     */
	public static Patient getPatient(List<Patient> patients){
		Scanner scanner = new Scanner(System.in);
        Patient patient;
        int i, choice = -1;

        System.out.println("Select a patient");
        for (i=0; i<patients.size(); i++){
            patient = patients.get(i);
            System.out.println(i+1 + ": " + patient.getName());
        }
		
		do { 
			System.out.println("Select a patient: ");
			choice = Validator.validateInt(scanner);
		} while (choice < 1 || choice > patients.size());

        patient = patients.get(choice-1);
		return patient;
	}

	/**
     * Retrieves a Patient object by its unique identifier.
     * @param patientID The unique identifier of the patient.
     * @return The Patient object if found, otherwise null.
     */
	public static Patient getByID(String patientID){
		patients = getPatients();
        Patient patient;
		int i = 0;
		for (i=0; i<patients.size(); i++){
			patient = patients.get(i);
			if (patient.hospitalID.equals(patientID)){
				return patient;
			}
		}
		System.out.println("patient not found, return null");
		return null;
    }
}