import java.util.*;

public class PersonalScheduleViewer {
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
