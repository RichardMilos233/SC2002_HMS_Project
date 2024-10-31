import java.time.*;

public class Appointment {

	private Patient patient;
	private Doctor doctor;
	private String status = "unavailable";	//unavailable, available, pending, confirmed, rejected, closed
	//Upon initialization, status = unavailable, status = available after a doctor sets the availability
	//When a patient sends a request, status = pending, then if a doctor approves, status = confirmed
	//Either the patient cancels or the doctor rejects the appointment, status = rejected
	//After the appointment, i.e. appointmentOutcome is added to it, status = closed
	private LocalDate date;
	private LocalTime time;
	private AppointmentOutcome appointmentOutcome;

	// depricated what's below, status seems to be enough
	// private boolean isAvailable = false;	//doctor sets this to true in AppointmentAvailabilitySetter
	// private boolean isScheduled = false;	//doctor sets this to true when he/ she approves the appointment request

	public Appointment() {}

	public Appointment(Patient patient, Doctor doctor, String status, LocalDate date, LocalTime time) {
		this.patient = patient;
		this.doctor = doctor;
		this.status = status;
		this.date = date;
		this.time = time;
	}

	public void displayAppointment() {
		System.out.println();

		if (status.equals("confirmed") || status.equals("closed")){
			System.out.println("Patient ID: " + patient.getHospitalID());
			System.out.println("Doctor ID: " + doctor.getHospitalID());
		}

		System.out.println("Appointment status: " + status);
		System.out.println("Appointment date & time: " + date + ' ' + time);

		if (status.equals("closed")){
			appointmentOutcome.displayAppointmentOutcome();
		}

		System.out.println();
	}

	public void setAppointmentOutcome(AppointmentOutcome appointmentOutcome){
		this.appointmentOutcome = appointmentOutcome;
	}

	public String getStatus(){
		return this.status;
	}

	public void setStatus(String newStatus){
		this.status = newStatus;
	}

	public LocalDate getDate(){
		return this.date;
	}

	public LocalTime getTime(){
		return this.time;
	}

	public Patient getPatient(){
		return this.patient;
	}

	// public boolean isAvailable(){
	// 	return this.isAvailable;
	// }

	// public void setAvailable(boolean isAvailable){
	// 	this.isAvailable = isAvailable;
	// }

	// public boolean isScheduled(){
	// 	return this.isScheduled;
	// }

	// public void setScheduled(boolean isScheduled){
	// 	this.isScheduled = isScheduled;
	// }
}