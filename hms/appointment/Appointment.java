package hms.appointment;

import hms.users.Doctor;
import hms.users.Patient;
import java.time.*;
/**
 * Represents an appointment between a patient and a doctor, encapsulating all relevant details
 * such as the participating IDs, date, time, and status of the appointment.
 * The status lifecycle includes various states such as unavailable, available, pending,
 * confirmed, closed, and dispensed, describing the progression of an appointment.
 */
public class Appointment {
	// private Patient patient;
	// private Doctor doctor;
	private String patientID = "P1000";
	private String doctorID;
	private String status = "unavailable";	//unavailable, available, pending, confirmed, closed, dispensed
	//Upon initialization, status = unavailable, status = available after a doctor sets the availability
	//When a patient sends a request, status = pending, then if a doctor approves, status = confirmed, else status = available
	//The patient cancels the appointment, status = available again
	//After the appointment, i.e. appointmentOutcome is added to it, status = closed
	//Pharmacist will dispense medication according to the appointment, after that, status = dispensed
	private LocalDate date;
	private LocalTime time;
	private AppointmentOutcome appointmentOutcome = new AppointmentOutcome();

	/**
	 * Constructs an appointment with the specified doctor's ID, date, and time.
	 * The appointment is initially marked as 'unavailable'.
	 * 
	 * @param doctorID The unique identifier for the doctor associated with this appointment.
	 * @param date The date on which the appointment is scheduled.
	 * @param time The time at which the appointment is scheduled.
	 */
	public Appointment(String doctorID, LocalDate date, LocalTime time) {
		this.doctorID = doctorID;
		this.date = date;
		this.time = time;
	}

	/**
	 * Displays the details of the appointment, including date, time, status, and participant IDs.
	 * Additional details are shown based on the status of the appointment, such as outcomes
	 * if the appointment has concluded.
	 */
	public void displayAppointment() {
		
		System.out.println("Appointment date & time: " + date + ' ' + time);
		System.out.println("Appointment status: " + status);
		System.out.println("Doctor ID: " + doctorID);

		if (status.equals("confirmed") || status.equals("pending") || 
			status.equals("closed") || status.equals("dispensed")){
			System.out.println("Patient ID: " + patientID);
		}

		if (status.equals("closed") || status.equals("dispensed")){
			appointmentOutcome.displayAppointmentOutcome();
		}

		System.out.println();
	}

	// for Appointment, the splitter of which is ,
	/**
	 * Converts the appointment details to a text format for storage, including IDs, status,
	 * date, time, and appointment outcome details.
	 * 
	 * @return A comma-separated string representation of the appointment details.
	 */
	public String toTxt() {
		StringBuilder sb = new StringBuilder();
		sb.append(patientID).append(",");
		sb.append(doctorID).append(",");
		sb.append(status).append(",");
		sb.append(date).append(",");
		sb.append(time).append(",");
		sb.append(appointmentOutcome.toTxt());
		return sb.toString();
	}

	/**
	 * Constructs an appointment object from a text string containing appointment details.
	 * The text string should be formatted as comma-separated values.
	 * 
	 * @param data The text string containing the appointment details.
	 * @return The reconstructed Appointment object.
	 */
	public static Appointment fromTxt(String data) {
		String[] fields = data.split(",", 6);
	
		String patientID = fields[0];        
		String doctorID = fields[1];      
		String status = fields[2];         
		LocalDate date = LocalDate.parse(fields[3]);
		LocalTime time = LocalTime.parse(fields[4]);
		AppointmentOutcome appointmentOutcome = AppointmentOutcome.fromTxt(fields[5]);

		Appointment appointment = new Appointment(doctorID, date, time);
		appointment.setPatientID(patientID);
		appointment.setStatus(status);
		appointment.setAppointmentOutcome(appointmentOutcome);

		return appointment;
	}

	/**
	 * Sets the appointment outcome details.
	 * @param appointmentOutcome The outcome of the appointment to be set.
	 */
	public void setAppointmentOutcome(AppointmentOutcome appointmentOutcome){
		this.appointmentOutcome = appointmentOutcome;
	}

	/**
	 * Retrieves the current status of the appointment.
	 * @return The current status as a string.
	 */
	public String getStatus(){
		return this.status;
	}

	/**
	 * Updates the status of the appointment.
	 * @param newStatus The new status to set for the appointment.
	 */
	public void setStatus(String newStatus){
		this.status = newStatus;
	}

	/**
	 * Retrieves the date of the appointment.
	 * 
	 * @return The date on which the appointment is scheduled.
	 */
	public LocalDate getDate(){
		return this.date;
	}

	/**
	 * Retrieves the time of the appointment.
	 * 
	 * @return The time at which the appointment is scheduled.
	 */
	public LocalTime getTime(){
		return this.time;
	}

	/**
	 * Retrieves the Patient object associated with this appointment by using the patient ID.
	 * 
	 * @return The Patient associated with this appointment.
	 */
	public Patient getPatient(){
		return Patient.getByID(patientID);
	}

	/**
	 * Retrieves the patient ID associated with this appointment.
	 * 
	 * @return The unique identifier for the patient.
	 */
	public String getPatientID(){
		return this.patientID;
	}
	
	/**
	 * Sets the patient ID for this appointment.
	 * 
	 * @param patientID The unique identifier for the patient to be set for this appointment.
	 */
	public void setPatientID(String patientID){
		this.patientID = patientID;
	}

	/**
	 * Retrieves the Doctor object associated with this appointment by using the doctor ID.
	 * 
	 * @return The Doctor associated with this appointment.
	 */
	public Doctor getDoctor(){
		return Doctor.getByID(doctorID);
	}

	/**
	 * Retrieves the doctor ID associated with this appointment.
	 * 
	 * @return The unique identifier for the doctor.
	 */
	public String getDoctorID(){
		return this.doctorID;
	}

	/**
	 * Retrieves the outcome of the appointment, detailing the results and any follow-up actions taken.
	 * 
	 * @return The AppointmentOutcome object containing details of the appointment's outcome.
	 */
	public AppointmentOutcome getAppointmentOutcome(){
		return this.appointmentOutcome;
	}

	public void setDeletedDoctor(){
		this.doctorID = "D000";
	}
}