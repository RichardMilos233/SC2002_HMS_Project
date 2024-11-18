import java.util.*;

public class PharmacistAppointmentOutcomeRecordViewer {
    

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
			System.out.println((appointmentOutcomesList.size()+1) + " Back\n");

			int choice = Validator.validateInt(scanner);
			if (choice == appointmentOutcomesList.size()+1) return;
	
			dispensePrescription(appointmentOutcomesList.get(choice-1));
		}
	}

	public static void dispensePrescription (Appointment appointment) {
		Inventory inventory = new Inventory();
		boolean dispensed = inventory.consumeStock(appointment.getAppointmentOutcome().getPrescribedMedication().getMedicationName(), appointment.getAppointmentOutcome().getPrescribedMedication().getDosage());
		if (dispensed) {
			appointment.setStatus("dispensed");
			TextService.replaceAppointment(appointment);
			System.out.println("Medication has been dispensed\n");
		}
	}
}
