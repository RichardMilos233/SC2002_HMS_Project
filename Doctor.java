import java.util.*;

public class Doctor extends Staff {
	LinkedList<Patient> patients;

	public Doctor() {
		// TODO - implement Doctor.Doctor
		throw new UnsupportedOperationException();
	}

	public void viewPatientMedicalRecord() {
		// TODO - implement Doctor.viewPatientMedicalRecord
		throw new UnsupportedOperationException();
	}

	public void updatePatientMedicalRecord(Patient patient) {
		for (Patient docPatient : patients){
			if (patient.getHospitalID() == docPatient.getHospitalID()){
				patient.viewMedicalRecord();
			}
		}
		throw new UnsupportedOperationException();
	}

	public void viewPersonalSchedule() {
		// TODO - implement Doctor.viewPersonalSchedule
		throw new UnsupportedOperationException();
	}

	public void setAvailability() {
		// TODO - implement Doctor.setAvailability
		throw new UnsupportedOperationException();
	}

	public void acceptAppointment() {
		// TODO - implement Doctor.acceptAppointment
		throw new UnsupportedOperationException();
	}

	public void viewUpcomingAppointment() {
		// TODO - implement Doctor.viewUpcomingAppointment
		throw new UnsupportedOperationException();
	}

	public void recordAppointmentOutcome(Appointment appointment) {
		String medication;
		System.out.println("Enter medication: ");
		medication = scanner.nextLine();//only 1 med for each appointment, for now
		PrescribedMedication prescribedMedication = new PrescribedMedication(medication, 0);
		String consultationNotes;
		System.out.println("Enter consultation notes: ");
		consultationNotes = scanner.nextLine();
		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.getDate(), "face2face", prescribedMedication, consultationNotes);//haven't thought of a type for consultation
		appointment.setAppointmentOutcome(appointmentOutcome);
		throw new UnsupportedOperationException();
	}

}