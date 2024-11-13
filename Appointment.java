import java.time.*;

public class Appointment {
	// private Patient patient;
	// private Doctor doctor;
	private String patientID = "P1000";
	private String doctorID;
	private String status = "unavailable";	//unavailable, available, pending, cancelled, confirmed, rejected, closed
	//Upon initialization, status = unavailable, status = available after a doctor sets the availability
	//When a patient sends a request, status = pending, then if a doctor approves, status = confirmed, else status = rejected
	//The patient cancels the appointment, status = cancelled
	//After the appointment, i.e. appointmentOutcome is added to it, status = closed
	private LocalDate date;
	private LocalTime time;
	private AppointmentOutcome appointmentOutcome = new AppointmentOutcome();

	public Appointment(String doctorID, LocalDate date, LocalTime time) {
		this.doctorID = doctorID;
		this.date = date;
		this.time = time;
	}

	public void displayAppointment() {
		System.out.println();
		
		System.out.println("Appointment date & time: " + date + ' ' + time);
		System.out.println("Appointment status: " + status);
		
		if (status.equals("confirmed") || status.equals("closed")){
			System.out.println("Patient ID: " + patientID);
			System.out.println("Doctor ID: " + doctorID);
		}

		if (status.equals("closed")){
			appointmentOutcome.displayAppointmentOutcome();
		}

		System.out.println();
	}

	// for Appointment, the splitter of which is ,
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
		return Patient.getByID(patientID);
	}

	public String getPatientID(){
		return this.patientID;
	}
	
	public void setPatientID(String patientID){
		this.patientID = patientID;
	}

	public Doctor getDoctor(){
		return Doctor.getByID(doctorID);
	}

	public String getDoctorID(){
		return this.doctorID;
	}

	public AppointmentOutcome getAppointmentOutcome(){
		return this.appointmentOutcome;
	}
}