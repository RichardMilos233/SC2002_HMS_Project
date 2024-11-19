package hms.appointment;

import hms.inventory.Inventory;
import hms.medicalrecords.PrescribedMedication;
import hms.storage.TextService;
import hms.utils.Validator;
import hms.utils.billing.AmoxicillinFee;
import hms.utils.billing.IbuprofenFee;
import hms.utils.billing.MedicationFee;
import hms.utils.billing.ParacetamolFee;
import java.util.*;
/**
 * Provides functionality for pharmacists to view and manage prescriptions based on appointment outcomes.
 * This class interacts with appointment data, primarily focusing on those with a "closed" status, to process
 * and dispense medications as needed.
 */
public class PharmacistAppointmentOutcomeRecordViewer {
	/**
     * Retrieves a list of appointments with a status of "closed" from the text file storage.
     * This method is used to identify which prescriptions need to be dispensed based on closed appointments.
     *
     * @return A list of appointments that have a "closed" status and require action for prescription dispensing.
     */
	public static List<Appointment> getAppointmentOutcomes() {
		Scanner scanner = new Scanner(System.in);
		List<Appointment> appointments = TextService.readAppointmentsFromTxt();
        List<Appointment> appointmentOutcomesList = new ArrayList<>();
    
        for (Appointment appointment: appointments) {
            if (appointment.getStatus().equals("closed")) {
                 appointmentOutcomesList.add(appointment);

					//System.out.println("Amount to Dispense: " + appointment.getAppointmentOutcome().getAmountToDispense());
					
                //System.out.println(appointmentOutcomesList.size() + " - " + appointment.getPatientID() + " - Dispense : " + appointment.getAppointmentOutcome().getPrescribedMedication().getPrescription());
            }
		}
		return appointmentOutcomesList;
	}

	/**
     * Displays the appointment outcomes with prescriptions that need to be dispensed.
     * Pharmacists can select an appointment from the list to dispense the prescribed medication.
     *
     * @param select An indicator to determine if the pharmacist will be selecting an appointment
     *               to dispense medication (1) or just viewing the list (0).
     */
	public static void displayAppointmentOutcomes(int select) {
		Scanner scanner = new Scanner(System.in);
        List<Appointment> appointmentOutcomesList = getAppointmentOutcomes();
    
		if (appointmentOutcomesList.isEmpty()) {
			System.out.println("No Outstanding Prescriptions\n");
		} else {
			int counter = 1;
     	 	for (Appointment appointment: appointmentOutcomesList) {
				System.out.println("Appointment " + counter + ": ");
				System.out.println("Patient ID: " + appointment.getPatientID());
				System.out.println("Outcome: " + appointment.getAppointmentOutcome().getPrescribedMedication().getPrescription() + "\n");
				// Calculate and display the medication fee for the prescribed medication
                double price = calculateMedicationFee(appointment.getAppointmentOutcome());
                System.out.println("Amount to Dispense: " + appointment.getAppointmentOutcome().getPrescribedMedication().gettotalPrescribed());
                System.out.println("Total Medication Cost: " + price + "\n");
				counter++;
				//System.out.println("Amount to Dispense: " + appointment.getAppointmentOutcome().getAmountToDispense());
    			//System.out.println(appointmentOutcomesList.size() + " - " + appointment.getPatientID() + " - Dispense : " + appointment.getAppointmentOutcome().getPrescribedMedication().getPrescription());
			}
		}	
		if (select==1){
			int choice;
			do { 
				System.out.println("Select\n(Appointment Number) Dispense Prescription\n" + "OR 0 to Return");
				choice = Validator.validateInt(scanner);
				if (choice == 0){
					return;
				}
			} while (choice <0 || choice>appointmentOutcomesList.size()+1);
			dispensePrescription(appointmentOutcomesList.get(choice-1));
		}
	}
	/**
     * Calculates the fee for the prescribed medication in the appointment outcome.
     *
     * @param appointmentOutcome The appointment outcome for the medication fee calculation.
     * @return The calculated medication fee.
     */
    public static double calculateMedicationFee(AppointmentOutcome appointmentOutcome) {
        // Get the prescribed medication
        PrescribedMedication prescribedMedication = appointmentOutcome.getPrescribedMedication();
        
        // Get the appropriate MedicationFee implementation
        MedicationFee medicationFee = getMedicationFee(prescribedMedication.getMedicationName());
        
        // Calculate and return the fee based on the prescribed quantity
        return medicationFee.calculateFee(prescribedMedication.gettotalPrescribed());
    }

    /**
     * Retrieves the appropriate MedicationFee implementation based on medication name.
     *
     * @param medicationName The name of the medication.
     * @return The corresponding MedicationFee implementation.
     */
    private static MedicationFee getMedicationFee(String medicationName) {
        switch (medicationName.toLowerCase()) {
            case "ibuprofen":
                return new IbuprofenFee();
            case "amoxicillin":
                return new AmoxicillinFee();
            case "paracetamol":
                return new ParacetamolFee();
            default:
                throw new IllegalArgumentException("No fee calculation available for this medication");
        }
    }






	/**
     * Dispenses the prescribed medication for a given appointment by updating the inventory and changing the
     * status of the appointment in the text file storage to "dispensed".
     *
     * @param appointment The appointment for which the medication is to be dispensed.
     */
	public static void dispensePrescription (Appointment appointment) {
		Inventory inventory = new Inventory();
		boolean dispensed = false;
		if (appointment.getAppointmentOutcome().getPrescribedMedication().getMedicationName().isBlank()){
			System.out.println("Patient does not require medicine");
			appointment.setStatus("dispensed");
			TextService.replaceAppointment(appointment);
			return;
		} else if (appointment.getAppointmentOutcome().getPrescribedMedication().gettotalPrescribed()==0){
			System.out.println("Patient receiving medicine elsewhere");
			appointment.setStatus("dispensed");
			TextService.replaceAppointment(appointment);
			return;
		} else{
			dispensed = inventory.consumeStock(appointment.getAppointmentOutcome().getPrescribedMedication().getMedicationName(), appointment.getAppointmentOutcome().getPrescribedMedication().gettotalPrescribed());
		}
		inventory.checkStockLevels();
		if (dispensed){
			appointment.setStatus("dispensed");
			TextService.replaceAppointment(appointment);
		}
	}
}
