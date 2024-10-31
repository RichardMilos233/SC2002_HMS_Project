public class AppointmentRescheduler {
    public static void rescheduleAppointment(Patient patient){
        // cancel one first
        AppointmentCanceller.cancelAppointment(patient);

        // then schedule another one
        AppointmentScheduler.scheduleAppointment(patient);
    }
}
