import java.time.*;
import java.util.*;

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
	public static List<Doctor> doctors = new ArrayList<>();
	private ArrayList<PatientCount> patientCounts = new ArrayList<>();	// containing patients who have one or multiple appointments of status "closed", "confirmed", "pending"
	private List<Appointment> timeTable = new ArrayList<>();


	public Doctor(String staffID, String name, String gender, int age){
		super(staffID, name, gender, age);
		this.role = "doctor";
		this.timeTable = getTimeTable();	// read from text first
		if (timeTable.size() == 0){	// if indeed no time table, initialize it
			initializeTimeTable();
		}
	}

	public String toCSV() {	// directly inherit from User, same for admin and pharmacist
        return super.toCSV();
    }

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
	public static List<Doctor> updateDoctors(){
		doctors = CSVService.readDoctorsFromCSV();
		return doctors;
	}

	public static List<Doctor> getDoctors(){
		if (doctors.isEmpty()){
			doctors = CSVService.readDoctorsFromCSV();
		}
		return doctors;
	}

	public List<Appointment> getTimeTable(){
		if (this.timeTable.isEmpty()){
			this.timeTable = TextService.getDoctorAppointment(this.hospitalID);
		}
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
				appointment = new Appointment(this.hospitalID, date, time);
				this.timeTable.add(appointment);
				TextService.appendAppointment(appointment);	// either this or above would work alone, but both can coexist?
			}
		}
	}

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

	public List<Patient> getPatients(){
		List<Patient> patients = new ArrayList<>();
		for (PatientCount patientCount : getPatientCounts()){
			patients.add(patientCount.getPatient());
		}
		return patients;
	}

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

		choice = scanner.nextInt();
		if (choice <1 || choice > Doctor.doctors.size()){
			return null;
		}
		
        doctor = Doctor.doctors.get(choice-1);
		return doctor;
	}

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