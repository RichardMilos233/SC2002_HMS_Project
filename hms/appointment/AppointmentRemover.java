package hms.appointment;

import hms.account.Doctor;
import hms.storage.TextService;
import java.util.*;

public class AppointmentRemover {
    private static final String APPOINTMENT_TXT_PATH = "appointments.txt"; // Replace with actual file path

    /**
     * Removes all appointments for a specific doctor unless their status is "closed" or "dispensed".
     *
     * @param doctorID The ID of the doctor whose incomplete appointments should be removed.
     */
    public static void removeIncompleteAppointments(String doctorID) {
        // Read all appointments from the file
        List<Appointment> allAppointments = TextService.readAppointmentsFromTxt();
        List<Appointment> filteredAppointments = new ArrayList<>();

        // Filter appointments
        for (Appointment appointment : allAppointments) {
            if (!appointment.getDoctorID().equals(doctorID) || 
                appointment.getStatus().equalsIgnoreCase("closed") || 
                appointment.getStatus().equalsIgnoreCase("dispensed")) {
                filteredAppointments.add(appointment); // Keep these appointments
            }
        }

        // Write the filtered appointments back to the file
        TextService.writeAppointmentsToTxt(filteredAppointments);
    }
}
