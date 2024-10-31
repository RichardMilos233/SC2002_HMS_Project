import java.util.*;

public class ScheduledAppointmentViewer {
    public void viewScheduledAppointment(Patient patient){
        List<Appointment> scheduledAppointment = patient.getScheduledAppointment();
        // update the list here by removing all the rejected appointments
        scheduledAppointment.removeIf(appointment -> appointment.getStatus().equals("rejected"));
        // getter returns a reference so the list in patient will also get updated

        for (Appointment appointment : scheduledAppointment){
            if (appointment.getStatus().equals("confirmed")){
                appointment.displayAppointment();
            }
        }
    }
}
