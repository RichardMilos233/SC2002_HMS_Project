import java.time.*;

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

	// for Appointment, the splitter of which is ,
	public String toTxt() {
		StringBuilder sb = new StringBuilder();
		sb.append(patient.getHospitalID()).append(",");
		sb.append(doctor.getHospitalID()).append(",");
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

		Doctor doctor = Doctor.getByID(doctorID);
		Patient patient = Patient.getByID(patientID);
		Appointment appointment = new Appointment(doctor, date, time);
		appointment.setPatient(patient);
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
		return this.patient;
	}

	public void setPatient(Patient patient){
		this.patient = patient;
	}

	public Doctor getDoctor(){
		return this.doctor;
	}
}