import java.util.List;
/**
 * Facilitates the rescheduling of appointments for a patient. 
 * This class provides a method to cancel an existing appointment and immediately schedule a new one.
 */
public class AppointmentRescheduler {
    /**
    * Executes the rescheduling process for a patient's appointment. Checks for any existing scheduled appointments.
    * If no appointments are found, it informs the user and exits. If there are scheduled appointments,
    * it proceeds by calling the {@link AppointmentCanceller#cancelAppointment(Patient)} method to cancel the current appointment
    * followed by {@link AppointmentScheduler#scheduleAppointment(Patient)} to set up a new appointment.
    * 
    * This method ensures that the patient's appointment is seamlessly transitioned from an old slot to a new one without
    * manual intervention, enhancing the user experience by automating the cancellation and rescheduling process.
    *
    * @param patient The patient whose appointment needs to be rescheduled.
    */
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
