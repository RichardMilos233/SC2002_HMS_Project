package hms.appointment;

import hms.account.users.Doctor;
import hms.inventory.Inventory;
import hms.inventory.Medication;
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
        
        char c;
		String type = "";
        do { 
			System.out.println("Select type:\tC Consultation\tX X-ray\tS Surgery");
			c = Validator.validateCharToUpper(scanner);
		}	while (c != 'C' && c != 'X' && c !='S');
		switch (c){
			case 'C':
				type = "consultation";
				break;
			case 'X':
				type = "xray";
				break;
			case 'S':
				type = "surgery";
				break;
			default:
				break;
		}
		
		int medication;
		String meds;
		Inventory inventoryService = new Inventory();
		List<Medication> medications = inventoryService.retrieveMedications();
		inventoryService.viewInventory();
		do {System.out.println("Select medication OR 0 to prescribe none: ");
			medication = Validator.validateInt(scanner);	//only 1 med for each appointment, for now
		}	while (medication<0 || medication>medications.size());
		if (medication !=0){
			meds = medications.get(medication-1).getMedicationName();
		} else{
			meds = "";
		}


		String dosage = "";
		int totalPrescribed = 0;
		if (medication != 0){
			System.out.println("Enter dosage for patient's reference: ");
			dosage = Validator.validateStringforFile(scanner);
		
			do { 
				System.out.println("Enter total number of bottles/pills for pharmacist to give to the patient: ");
				totalPrescribed = Validator.validateInt(scanner);
			} while (totalPrescribed<1);
		}
		PrescribedMedication prescribedMedication = new PrescribedMedication(meds, dosage, totalPrescribed);
		

		String consultationNotes;
		System.out.println("Enter consultation notes: ");
		consultationNotes = Validator.validateStringforFile(scanner);

		String diagnosis;
		System.out.println("Enter diagnosis: ");
		diagnosis = Validator.validateStringforFile(scanner);

		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.getDate(), type, prescribedMedication, consultationNotes, diagnosis, false);
		appointment.setAppointmentOutcome(appointmentOutcome);
		appointment.getPatient().getPastDiagnoses().updatePastDiagnoses(appointmentOutcome);
        appointment.setStatus("closed");
		TextService.replaceAppointment(appointment);
	}
}
