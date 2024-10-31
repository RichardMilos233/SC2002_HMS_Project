import java.util.*;

public class UpcomingAppointmentViewer {
    public static void viewUpcomingAppointment(Doctor doctor){
        List<Appointment> timeTable = doctor.getTimeTable();
        for (Appointment appointment: timeTable){
            if (appointment.getStatus().equals("confirmed")){
                appointment.displayAppointment();
            }
        }
    }
}
