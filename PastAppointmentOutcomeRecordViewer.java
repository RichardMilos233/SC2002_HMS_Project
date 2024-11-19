import java.util.*;

/**
 * Provides functionality to view past appointment outcomes for patients. 
 * This class is responsible for accessing and displaying the historical medical records of a patient.
 */
public class PastAppointmentOutcomeRecordViewer{
    /**
     * Displays the past appointment outcomes for a given patient. If the patient has no recorded 
     * past diagnoses, a message indicating the absence of records is displayed.
     *
     * @param patient The patient whose past appointment outcomes are to be viewed.
     */
    public static void viewPastAppointmentOutcomeRecord(Patient patient){
        List<Appointment> pastAppointments = new ArrayList<>();
        for (Appointment appointment : patient.getTimeTable()){
            if (appointment.getStatus().equals("closed") || appointment.getStatus().equals("dispensed")){
                pastAppointments.add(appointment);
            }
        }
        if (pastAppointments.size() == 0){
            System.out.println("There is currently no record");
            return;
        }
        for (Appointment appointment : pastAppointments){
            appointment.displayAppointment();
        }
    }
}