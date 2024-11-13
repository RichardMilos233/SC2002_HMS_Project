import java.util.*;

public class AppointmentCanceller {
    public static void cancelAppointment(Patient patient){
        Scanner scanner = new Scanner(System.in);
        // display the list of scheduled appointments
        ScheduledAppointmentViewer.viewScheduledAppointment(patient);

        // choose the one to be cancelled, one at a time for now
        int choice = -1;
        System.out.println("Enter your choice: ");
        choice = scanner.nextInt();

        List<Appointment> scheduledAppointment = patient.getScheduledAppointment(); 
        // this list does not contain any rejected status because it is already eliminated above in viewScheduledAppointment
        if (choice < 1 || choice > scheduledAppointment.size()){
            System.out.println("invalid choice");
            return;
        }
        Appointment appointment = scheduledAppointment.get(choice-1);
        appointment.setStatus("cancelled");

        TextService.replaceAppointment(appointment);
        
        Doctor doctor = appointment.getDoctor();
        doctor.removePatient(appointment.getPatient());
    }
}
