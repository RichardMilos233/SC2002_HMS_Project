import java.util.*;
import java.time.LocalDate;
import java.time.Period;

public class Patient extends User {
	public static List<Patient> patients = new ArrayList<>();
	private LocalDate birth = LocalDate.of(1990, 5, 14);
	private int contactNumber = 1919810;
	private String email = "";
	private String bloodType = "";
	private PastDiagnoses pastDiagnoses = new PastDiagnoses();
	private TimeTable scheduledAppointment = new TimeTable();

	Scanner scanner = new Scanner(System.in);

	public Patient() {
		super();
		this.role = "patient";
		patients.add(this);
	}

	public Patient(String patientID, String password, String name, String gender, int age, LocalDate birth, int contactNumber, String email, String bloodType){
		super(patientID, password, name, gender, age);
		this.birth = birth;
		this.contactNumber = contactNumber;
		this.email = email;
		this.bloodType = bloodType;
		this.age = DateConverter.calculateAge(birth);
		this.role = "patient";
		patients.add(this);
	}

	public void updatePersonalInformation() {    // not safe & can add more constraints for input
		int newcontactNumber;
		String newEmail;
		System.out.println("Enter new contact number:");
		newcontactNumber = scanner.nextInt();
		this.contactNumber = newcontactNumber;
		System.out.println("Enter new email:");
		newEmail = scanner.nextLine();
		this.email = newEmail;
	}

	public void viewAvailableAppointmentSlots() {
		// TODO - implement Patient.viewAvailableAppointmentSlots
		throw new UnsupportedOperationException();
	}

	public void viewMedicalRecord() {
		System.out.println("---------------Medical Record---------------");
		System.out.println("Patient ID: " + this.getHospitalID());
		System.out.println("Name: " + this.getName());
		System.out.println("Date of birth: " + this.birth);
		System.out.println("Gender: " + this.getGender());
		System.out.println("Contact number: " + this.contactNumber);
		System.out.println("Email: " + this.email);
		System.out.println("Blood type: " + this.bloodType);
		pastDiagnoses.displayPastDiagnoses();
		System.out.println("---------------------End---------------------");
	}

	public void scheduleAppointment() {
		// TODO - implement Patient.scheduleAppointment
		throw new UnsupportedOperationException();
	}

	public void rescheduleAppointment() {
		// TODO - implement Patient.rescheduleAppointment
		throw new UnsupportedOperationException();
	}

	public void cancelAppointment() {
		// TODO - implement Patient.cancelAppointment
		throw new UnsupportedOperationException();
	}

	public void viewScheduledAppointment() {
		// TODO - implement Patient.viewScheduledAppointment
		throw new UnsupportedOperationException();
	}

	public void viewPastAppointmentOutcomeRecord() {
		// TODO - implement Patient.viewPastAppointmentOutcomeRecord
		throw new UnsupportedOperationException();
	}


	public LocalDate getBirth(){
		return this.birth;
	}

	public int getContactNumber() {
		return this.contactNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public String getBloodType(){
		return this.bloodType;
	}

	public PastDiagnoses getPastDiagnoses(){
		return this.pastDiagnoses;
	}




	private void setContactNumber(int newcontactNumber) {
		this.contactNumber = newcontactNumber;
	}

	private void setEmail(String newEmail) {
		this.email = newEmail;
	}

	public void display(){
		super.display();
		System.out.println("Date of Birth: " + this.birth);
		System.out.println("Contact Number: " + this.contactNumber);
		System.out.println("Email: " + this.email);
		System.out.println("Blood Type: " + this.bloodType);
	}

}