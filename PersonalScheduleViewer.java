import java.util.*;
/**
 * Provides the functionality to view the personal schedule of a doctor. 
 * This class focuses on displaying only confirmed appointments from the doctor's schedule.
 */
public class PersonalScheduleViewer {
    /**
     * Displays the confirmed appointments from a doctor's schedule.
     * It lists all appointments that have been confirmed, showing relevant details for each.
     * If there are no confirmed appointments, a message indicating the absence of such appointments is displayed.
     *
     * @param doctor The doctor whose personal schedule is to be viewed.
     */
    public static void viewPersonalSchedule(Doctor doctor){
        List<Appointment> timeTable = doctor.getTimeTable();
        Appointment appointment;
        int i;
        String status;
        int count = 0;
        for (i = 0; i < timeTable.size(); i++){
            appointment = timeTable.get(i);
            status = appointment.getStatus();
            if (status.equals("confirmed") || status.equals("available")){
                appointment.displayAppointment();
                count ++;
            }
        }
        if (count == 0){
            System.out.println("There is no cofirmed or available appointment");
        }
    }
}
