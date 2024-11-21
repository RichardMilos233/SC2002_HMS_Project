package hms.appointment;

import java.util.*;

import hms.users.Patient;
/**
 * This class provides functionality to view all scheduled appointments for a specific patient.
 * It is used within the healthcare system to allow patients to review their upcoming medical appointments.
 */
public class ScheduledAppointmentViewer {
    /**
     * Displays all scheduled appointments for a specified patient. The method checks the patient's scheduled
     * appointments and prints details for each one. If no appointments are scheduled, it informs the user accordingly.
     *
     * @param patient The patient whose scheduled appointments are to be viewed.
     */
    public static void viewScheduledAppointment(Patient patient){
        List<Appointment> scheduledAppointment = patient.getScheduledAppointments(); 
        if (scheduledAppointment.size() == 0){
            System.out.println("There is currently no scheduled appointment");
            return;
        }
        PrintAppointmentsSchedule.printChosenSlotsPatientScheduled(scheduledAppointment);
    }
}