package hms.appointment;

import hms.storage.TextService;
import hms.users.Doctor;
import hms.users.Patient;
import hms.utils.Validator;
import java.util.*;
/**
 * Utility class to manage the cancellation of appointments for a given patient.
 * Facilitates the user in selecting an appointment from the list of scheduled appointments to cancel.
 */
public class AppointmentCanceller {
    /**
     * Cancels a selected appointment for the specified patient. This method allows the patient to view
     * and select a scheduled appointment to cancel. Validates the user's input to ensure a valid selection.
     * Updates the appointment status to "cancelled" and removes the patient from the doctor's list.
     * 
     * @param patient The patient whose appointment is to be cancelled.
     */
    public static void cancelAppointment(Patient patient){
        Scanner scanner = new Scanner(System.in);
        List<Appointment> scheduledAppointment = patient.getScheduledAppointments(); 
        // this list does not contain any rejected status because it is already eliminated above in viewScheduledAppointment
        if (scheduledAppointment.isEmpty()){
            System.out.println("There is currently no scheduled appointment");
            return;
        }

        // display the list of scheduled appointments
        ScheduledAppointmentViewer.viewScheduledAppointment(patient);

        // choose the one to be cancelled, one at a time for now
        int choice;
        do { 
            System.out.println("Enter the slot to cancel or 0 to return: ");
            choice = Validator.validateInt(scanner);
            if (choice == 0){
                return;
            }
            if (choice < 0 || choice > scheduledAppointment.size()){
                System.out.println("Invalid choice. ");
            }
        } while (choice < 0 || choice > scheduledAppointment.size());
        
        Appointment appointment = scheduledAppointment.get(choice-1);

        Doctor doctor = appointment.getDoctor();
        doctor.removePatient(appointment.getPatient());

        appointment.setStatus("available");
        appointment.setPatientID("P1000");

        TextService.replaceAppointment(appointment);
        patient.updateTimeTable();
        
        System.out.println("Appointment cancelled successfully");    
    }
}
