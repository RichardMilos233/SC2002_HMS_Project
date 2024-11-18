import java.time.*;
import java.util.*;

public class Patient extends User {
	public static List<Patient> patients = new ArrayList<>();
	private LocalDate birth = LocalDate.of(2005, 1, 22);
	private int contactNumber = 1919810;
	private String email = "example@xxx.com";
	private String bloodType = "A+";
	private List<Appointment> timeTable = new ArrayList<>();
	private PastDiagnoses pastDiagnoses = new PastDiagnoses();
	private String latestMedicalStatus = "status";
	private ArrayList<String> Diagnoses = new ArrayList<>(); //
	//new diagnoses, prescriptions, and treatment plans.

	Scanner scanner = new Scanner(System.in);

	public Patient(String patientID, String name, String gender, int age, LocalDate birth, int contactNumber, String email, String bloodType){
		super(patientID, name, gender, age);
		this.birth = birth;
		this.contactNumber = contactNumber;
		this.email = email;
		this.bloodType = bloodType;
		this.age = DateConverter.calculateAge(birth);
		this.role = "patient";
	}

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

	public String toCSV(){
		//hospitalID + "," + password + "," + name + "," + gender + "," + age + "," + role;
		return super.toCSV() + "," + birth + "," + bloodType + "," + email + "," + contactNumber;
	}

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

	public LocalDate getBirth(){
		return this.birth;
	}

	public int getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(int newcontactNumber) {
		this.contactNumber = newcontactNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	public String getBloodType(){
		return this.bloodType;
	}

	public PastDiagnoses getPastDiagnoses(){
		if (pastDiagnoses.size() == 0){
			for (Appointment appointment : getTimeTable()){
				if (appointment.getPatient().getHospitalID().equals(this.hospitalID) && appointment.getStatus().equals("closed")){
					pastDiagnoses.updatePastDiagnoses(appointment.getAppointmentOutcome());
				}
			}
		}
		return this.pastDiagnoses;
	}

	public List<Appointment> getTimeTable(){	// all the appointments that have the id of this patient
		if (this.timeTable.size() == 0){
			this.timeTable = TextService.getPatientAppointment(this.hospitalID);
		}
		return this.timeTable;
	}

	public void addAppointment(Appointment appointment){
		this.timeTable.add(appointment);
	}

	public List<Appointment> getScheduledAppointments(){	// scheduled appointments only contain pending and confirmed appointments
		List<Appointment> scheduledAppointments = new ArrayList<>();
		for (Appointment appointment : getTimeTable()){
			if (appointment.getStatus().equals("pending") || appointment.getStatus().equals("confirmed")){
				scheduledAppointments.add(appointment);
			}
		}
		return scheduledAppointments;
	}

	public static List<Patient> getPatients(){
		if (patients.size() == 0) {
            patients = CSVService.readPatientsFromCSV();
        }
        return patients;
	}

	public void display(){
		super.display();
		System.out.println("Date of Birth: " + this.birth);
		System.out.println("Contact Number: " + this.contactNumber);
		System.out.println("Email: " + this.email);
		System.out.println("Blood Type: " + this.bloodType);
	}

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