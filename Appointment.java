public class Appointment {

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

	public Appointment(String patientID, String doctorID, String status, String date, String time) {
		// TODO - implement Appointment.Appointment
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.status = status;
		this.date = date;
		this.time = time;
		throw new UnsupportedOperationException();
	}

	public void displayAppointment() {
		// TODO - implement Appointment.displayAppointment
		System.out.println();
		System.out.println("Patient ID: " + patientID);
		System.out.println("Doctor ID: " + doctorID);
		System.out.println("Appointment status: " + status);
		System.out.println("Appointment date & time: " + date + ' ' + time);
		appointmentOutcome.displayAppointmentOutcome();
		System.out.println();
		throw new UnsupportedOperationException();
	}

	public void setAppointmentOutcome(AppointmentOutcome appointmentOutcome){
		this.appointmentOutcome = appointmentOutcome;
	}

	public String getDate(){
		return this.date;
	}
}