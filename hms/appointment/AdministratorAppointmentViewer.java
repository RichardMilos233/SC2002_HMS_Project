package hms.appointment;

import hms.storage.TextService;

import java.util.ArrayList;
import java.util.List;
/**
 * Utility class for viewing appointments by an administrator. This class provides
 * a static method to display all available appointments, excluding those marked as
 * 'unavailable'.
 */
public class AdministratorAppointmentViewer {
    /**
     * Displays all appointments from a text file source that are not marked as 'unavailable'.
     * It reads the appointments using the TextService, checks the status of each appointment,
     * and uses the appointment's display method to show details if the status is not 'unavailable'.
     * If no appointments are available, it outputs a message indicating there are no appointments.
     */
	public static void viewAllAppointment() {
		List<Appointment> appointments = TextService.readAppointmentsFromTxt();
        List<Appointment> validAppointments = new ArrayList<>();
    
        for (Appointment appointment: appointments) {
            if (!appointment.getStatus().equals("unavailable")) {
                validAppointments.add(appointment);
				// Print Appointment Details
                // System.out.println("\n--- Appointment ---");
                // System.out.println("Patient ID: " + appointment.getPatientID());
                // System.out.println("Doctor ID: " + appointment.getDoctorID());
                // System.out.println("Status: " + appointment.getStatus());
                // System.out.println("Date and Time: " + appointment.getDate() + " " + appointment.getTime());
                // System.out.println("----------------------");
            }
        }
		if (appointments.isEmpty()) {
			System.out.println("No Appointments\n");
            return;
		} 

        PrintAppointmentsSchedule.printChosenSlots(validAppointments);
	}
}
