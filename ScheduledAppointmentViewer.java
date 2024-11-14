import java.util.*;

public class ScheduledAppointmentViewer {
    public static void viewScheduledAppointment(Patient patient){
        List<Appointment> scheduledAppointment = patient.getScheduledAppointments(); 
        if (scheduledAppointment.size() == 0){
            System.out.println("There is currently no scheduled appointment");
            return;
        }
        int i;
        Appointment appointment;
        for (i = 0; i < scheduledAppointment.size(); i++){
            appointment = scheduledAppointment.get(i);
            System.out.println("Appointment " + (i+1) +": ");
            appointment.displayAppointment();
        }
    }
}