import java.util.*;

public class AppointmentCanceller {
    public static void cancelAppointment(Patient patient){
        Scanner scanner = new Scanner(System.in);
        List<Appointment> scheduledAppointment = patient.getScheduledAppointments(); 
        // this list does not contain any rejected status because it is already eliminated above in viewScheduledAppointment
        if (scheduledAppointment.isEmpty()){
            System.out.println("There is currently no scheduled appointment");
            return;
        }

        // display the list of scheduled appointments
        ScheduledAppointmentViewer.viewScheduledAppointment(patient);

        // choose the one to be cancelled, one at a time for now
        int choice;
        /*System.out.print("Choose a slot: ");
        do { 
            choice = Validator.validateInt(scanner);
            if (choice < 1 || choice > unavailableAppointments.size()){
                System.out.println("invalid choice");
            }
        } while (choice < 1 || choice > unavailableAppointments.size());
         */
        do { 
            System.out.println("Enter the slot to cancel or 0 to return: ");
            choice = Validator.validateInt(scanner);
            if (choice == 0){
                return;
            }
            if (choice < 0 || choice > scheduledAppointment.size()){
                System.out.println("Invalid choice. ");
            }
        } while (choice < 0 || choice > scheduledAppointment.size());
        
        Appointment appointment = scheduledAppointment.get(choice-1);
        appointment.setStatus("available");
        appointment.setPatientID("P1000");

        TextService.replaceAppointment(appointment);
        patient.updateTimeTable();
        
        Doctor doctor = appointment.getDoctor();
        doctor.removePatient(appointment.getPatient());
    }
}
