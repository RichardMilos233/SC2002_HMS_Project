import java.util.*;

public class PersonalScheduleViewer {
    public static void viewPersonalSchedule(Doctor doctor){
        List<Appointment> timeTable = doctor.getTimeTable();
        Appointment appointment;
        int i;
        for (i = 0; i < timeTable.size(); i++){
            appointment = timeTable.get(i);
            appointment.displayAppointment();
        }
    }
}
