import java.util.*;

public class UpcomingAppointmentViewer {
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
