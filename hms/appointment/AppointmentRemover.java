package hms.appointment;

import hms.storage.TextService;
import java.util.*;

public class AppointmentRemover {

    /**
     * Removes all appointments for a specific doctor unless their status is "closed" or "dispensed".
     *
     * @param doctorID The ID of the doctor whose incomplete appointments should be removed.
     */
    public static void removeIncompleteAppointments(String doctorID) {
        List<Appointment> allAppointments = TextService.readAppointmentsFromTxt();
        List<Appointment> filteredAppointments = new ArrayList<>();
        // Filter appointments
        for (Appointment appointment : allAppointments) {
            if (!appointment.getDoctorID().equals(doctorID) || (appointment.getStatus().equalsIgnoreCase("closed") || appointment.getStatus().equalsIgnoreCase("dispensed"))) {
                if (appointment.getDoctorID().equals(doctorID)){
                    appointment.setDeletedDoctor();
                }
                filteredAppointments.add(appointment); // Keep these appointments
            } 
        }

        // Write the filtered appointments back to the file
        TextService.writeAppointmentsToTxt(filteredAppointments);
    }
}
