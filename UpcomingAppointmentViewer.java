import java.util.*;
/**
 * Provides functionality to view and manage upcoming appointments for a specific doctor.
 */
public class UpcomingAppointmentViewer {
    /**
     * Displays all upcoming appointments for a specified doctor. If there are no upcoming appointments,
     * an appropriate message is displayed.
     *
     * @param doctor The doctor whose upcoming appointments need to be viewed.
     */
    public static void viewUpcomingAppointment(Doctor doctor){
        List<Appointment> upcomingAppointment = getUpcomingAppointment(doctor);
        if (upcomingAppointment.size() == 0){
            System.out.println("There is no upcoming appointment");
            System.out.println("Maybe you should refelct on why no one wants to consult you");
            return;
        }
        for (Appointment appointment: upcomingAppointment){
            appointment.displayAppointment();
        }
    }

    /**
     * Retrieves a list of all upcoming appointments for a specified doctor that are confirmed.
     * This method checks the doctor's timetable for appointments that have a status of "confirmed."
     *
     * @param doctor The doctor whose upcoming appointments are to be retrieved.
     * @return A list of Appointment objects that are confirmed and upcoming for the given doctor.
     */
    public static List<Appointment> getUpcomingAppointment(Doctor doctor){
        List<Appointment> upcomingAppointment = new ArrayList<>();
        for (Appointment appointment : doctor.getTimeTable()){
            if (appointment.getStatus().equals("confirmed")){
                upcomingAppointment.add(appointment);
            }
        }
        return upcomingAppointment;
    }
}
