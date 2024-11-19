import java.util.*;
/**
 * Provides functionality for pharmacists to view and manage prescriptions based on appointment outcomes.
 * This class interacts with appointment data, primarily focusing on those with a "closed" status, to process
 * and dispense medications as needed.
 */
public class PharmacistAppointmentOutcomeRecordViewer {
	/**
     * Displays all closed appointments with their respective prescription outcomes.
     * Allows the pharmacist to select an appointment for which to dispense medication.
     */
	public static void getAppointmentOutcomes() {
		Scanner scanner = new Scanner(System.in);
		List<Appointment> appointments = TextService.readAppointmentsFromTxt();
        List<Appointment> appointmentOutcomesList = new ArrayList<>();
    
        for (Appointment appointment: appointments) {
            if (appointment.getStatus().equals("closed")) {
                 appointmentOutcomesList.add(appointment);

				 
					System.out.println("Appointment " + appointmentOutcomesList.size() + ": ");
					System.out.println("Patient ID: " + appointment.getPatientID());
					System.out.println("Outcome: " + appointment.getAppointmentOutcome().getPrescribedMedication().getPrescription());

					//System.out.println("Amount to Dispense: " + appointment.getAppointmentOutcome().getAmountToDispense());
					
                //System.out.println(appointmentOutcomesList.size() + " - " + appointment.getPatientID() + " - Dispense : " + appointment.getAppointmentOutcome().getPrescribedMedication().getPrescription());
            }
	}

		if (appointmentOutcomesList.isEmpty()) {
			System.out.println("No Outstanding Prescriptions\n");
		} else {
			System.out.println(("Enter appointment number to dispense prescription or " + appointmentOutcomesList.size()+1) + " to return\n");

			int choice = Integer.parseInt(scanner.nextLine());
			if (choice == appointmentOutcomesList.size()+1) return;
	
			dispensePrescription(appointmentOutcomesList.get(choice-1));
		}
	}

	/**
     * Dispenses the prescribed medication for a selected appointment.
     * This method updates the inventory and the appointment status to "dispensed".
     *
     * @param appointment The appointment for which medication is to be dispensed.
     */
	public static void dispensePrescription (Appointment appointment) {
		Inventory inventory = new Inventory();
		boolean dispensed = inventory.consumeStock(appointment.getAppointmentOutcome().getPrescribedMedication().getMedicationName(), appointment.getAppointmentOutcome().getPrescribedMedication().gettotalPrescribed());
		if (dispensed) {
			appointment.setStatus("dispensed");
			TextService.replaceAppointment(appointment);
			System.out.println("Medication has been dispensed\n");
		}
	}
}
