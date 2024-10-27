public class Appointment {

	private Patient patient;
	private Doctor doctor;
	private String patientID;
	private String doctorID;
	private String status;
	private String date;
	private String time;
	private AppointmentOutcome appointmentOutcome;

	public Appointment() {
		// TODO - implement Appointment.Appointment
		throw new UnsupportedOperationException();
	}

	public Appointment(Patient patient, Doctor doctor, String status, String date, String time) {
		// TODO - implement Appointment.Appointment
		this.patient = patient;
		this.doctor = doctor;
		this.patientID = this.patient.hospitalID;
		this.doctorID = this.doctor.hospitalID;
		this.status = status;
		this.date = date;
		this.time = time;
	}

	public void displayAppointment() {
		System.out.println();
		System.out.println("Patient ID: " + patientID);
		System.out.println("Doctor ID: " + doctorID);
		System.out.println("Appointment status: " + status);
		System.out.println("Appointment date & time: " + date + ' ' + time);
		appointmentOutcome.displayAppointmentOutcome();
		System.out.println();
	}

	public void setAppointmentOutcome(AppointmentOutcome appointmentOutcome){
		this.appointmentOutcome = appointmentOutcome;
	}

	public String getDate(){
		return this.date;
	}

	public Patient getPatient(){
		return this.patient;
	}
}