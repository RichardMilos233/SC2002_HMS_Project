import java.time.*;
import java.time.format.DateTimeFormatter;

public class Appointment {
	private Patient patient;
	private Doctor doctor;
	private String status = "unavailable";	//unavailable, available, pending, cancelled, confirmed, rejected, closed
	//Upon initialization, status = unavailable, status = available after a doctor sets the availability
	//When a patient sends a request, status = pending, then if a doctor approves, status = confirmed, else status = rejected
	//The patient cancels the appointment, status = cancelled
	//After the appointment, i.e. appointmentOutcome is added to it, status = closed
	private LocalDate date;
	private LocalTime time;
	private AppointmentOutcome appointmentOutcome;

	public Appointment() {}

	public Appointment(Doctor doctor, LocalDate date, LocalTime time) {
		this.doctor = doctor;
		this.date = date;
		this.time = time;
	}

	public void displayAppointment() {
		System.out.println();
		
		System.out.println("Appointment date & time: " + date + ' ' + time);
		System.out.println("Appointment status: " + status);
		
		if (status.equals("confirmed") || status.equals("closed")){
			System.out.println("Patient ID: " + patient.getHospitalID());
			System.out.println("Doctor ID: " + doctor.getHospitalID());
		}

		if (status.equals("closed")){
			appointmentOutcome.displayAppointmentOutcome();
		}

		System.out.println();
	}

	// this is only copy & paste from gpt!!!
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(appointmentId).append(",");
		sb.append(patientId).append(",");
		sb.append(doctorId).append(",");
		sb.append(appointmentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append(",");
		sb.append(status).append(",");
		sb.append(outcome != null ? outcome.toCSV() : ""); // Serialize outcome if it exists
		return sb.toString();
	}

	public static Appointment fromCSV(String data) {
		String[] fields = data.split(",", 6); // Split based on the fields in toCSV
	
		int appointmentId = Integer.parseInt(fields[0]);        // Parse appointmentId
		int patientId = Integer.parseInt(fields[1]);            // Parse patientId
		int doctorId = Integer.parseInt(fields[2]);             // Parse doctorId
		LocalDateTime appointmentDateTime = LocalDateTime.parse(fields[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		String status = fields[4];                              // Read status as String
	
		// Parse outcome if available
		AppointmentOutcome outcome = fields.length > 5 && !fields[5].isEmpty()
									 ? AppointmentOutcome.fromCSV(fields[5]) 
									 : null;
	
		return new Appointment(appointmentId, patientId, doctorId, appointmentDateTime, status, outcome);
	}
	// theft stops here

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

	public void setPatient(Patient patient){
		this.patient = patient;
	}

	public Doctor getDoctor(){
		return this.doctor;
	}
}