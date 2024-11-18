import java.util.List;

public class AppointmentRescheduler {
    public static void rescheduleAppointment(Patient patient){
        List<Appointment> scheduledAppointment = patient.getScheduledAppointments();
        if (scheduledAppointment.size() == 0){
            System.out.println("There is currently no scheduled appointment");
            return;
        }
        // cancel one first
        AppointmentCanceller.cancelAppointment(patient);

        // then schedule another one
        AppointmentScheduler.scheduleAppointment(patient);
    }
}
