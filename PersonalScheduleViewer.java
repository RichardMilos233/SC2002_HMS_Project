import java.util.*;

public class PersonalScheduleViewer {
    public static void viewPersonalSchedule(Doctor doctor){
        List<Appointment> timeTable = doctor.getTimeTable();
        Appointment appointment;
        int i;
        int count = 0;
        for (i = 0; i < timeTable.size(); i++){
            appointment = timeTable.get(i);
            if (appointment.getStatus().equals("confirmed")){
                appointment.displayAppointment();
                count ++;
            }
        }
        if (count == 0){
            System.out.println("There is no upcoming cofirmed appointment");
        }
    }
}
