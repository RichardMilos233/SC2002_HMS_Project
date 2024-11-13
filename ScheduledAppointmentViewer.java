import java.util.*;

public class ScheduledAppointmentViewer {
    public static void viewScheduledAppointment(Patient patient){
        List<Appointment> scheduledAppointment = patient.getScheduledAppointment(); 

        // update the list here by removing all the rejected appointments
        scheduledAppointment.removeIf(appointment -> appointment.getStatus().equals("rejected") || appointment.getStatus().equals("cancelled"));
        // getter returns a reference so the list in patient will also get updated
        // TODO shall a patient view rejected/ cancelled apt in this viewer???

        int i;
        Appointment appointment;
        for (i = 0; i < scheduledAppointment.size(); i++){
            appointment = scheduledAppointment.get(i);
            System.out.println("Appointment " + (i+1) +": ");
            appointment.displayAppointment();
        }
    }
}
