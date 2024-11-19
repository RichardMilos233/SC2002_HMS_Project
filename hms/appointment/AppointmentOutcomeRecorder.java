package hms.appointment;

import hms.account.Doctor;
import hms.medicalrecords.PrescribedMedication;
import hms.storage.TextService;
import hms.utils.Validator;

import java.util.*;
/**
 * Utility class to record the outcomes of appointments for a given doctor.
 * Handles the recording of details such as the type of appointment, medications prescribed,
 * consultation notes, and the final diagnosis.
 */
public class AppointmentOutcomeRecorder {
	/**
	 * Records the outcome of the nearest upcoming appointment for a specified doctor.
	 * Prompts the user to enter details for the appointment type, prescribed medication, consultation notes,
	 * and diagnosis. Updates the appointment status to "closed" after recording these details.
	 *
	 * @param doctor The doctor whose appointment outcome is to be recorded.
	 */
    public static void recordAppointmentOutcome(Doctor doctor) {	// record the nearest appointment outcome of a doctor
		Scanner scanner = new Scanner(System.in);
		List<Appointment> upcomingAppointment = UpcomingAppointmentViewer.getUpcomingAppointment(doctor);
		if (upcomingAppointment.size() == 0){
			System.out.println("There is no upcoming appointment");
			System.out.println("Maybe you should reflect on why no one wants to consult you");
			return;
		}
		Appointment appointment = upcomingAppointment.get(0);

		appointment.displayAppointment();
        
        String type;
        System.out.println("Enter type (consultation, xray, or surgery): ");
        type = Validator.validateStringNoSpace(scanner);
		
		String medication;
		System.out.println("Enter medication: ");
		medication = Validator.validateLine(scanner);	//only 1 med for each appointment, for now
		String dosage;
		System.out.println("Enter dosage: ");
		dosage = Validator.validateLine(scanner);
		System.out.println("Enter total number of bottles/pills to give to the patient: ");
		int totalPrescribed = Validator.validateInt(scanner);
		PrescribedMedication prescribedMedication = new PrescribedMedication(medication, dosage, totalPrescribed);

		String consultationNotes;
		System.out.println("Enter consultation notes: ");
		consultationNotes = Validator.validateLine(scanner);

		String diagnosis;
		System.out.println("Enter diagnosis: ");
		diagnosis = Validator.validateLine(scanner);

		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.getDate(), type, prescribedMedication, consultationNotes, diagnosis, false);
		appointment.setAppointmentOutcome(appointmentOutcome);
		appointment.getPatient().getPastDiagnoses().updatePastDiagnoses(appointmentOutcome);
        appointment.setStatus("closed");
		TextService.replaceAppointment(appointment);
	}
}
