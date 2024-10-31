import java.util.*;

public class AppointmentScheduler{
    public static void scheduleAppointment(Patient patient){
        Scanner scanner  = new Scanner(System.in);
        //patient sends appointment request to doctor and wait for doctor to approve
        
        //list out all doctors, then select a doctor
        Doctor doctor = Doctor.getDoctor();

        //sout all the available appointment slots of that doctor
        List<Appointment> availableSlots = AvailableSlotsGetter.getAvailableSlots(doctor);
        int choice;
        do{
            AvailableSlotsViewer.printAvailableSlots(doctor, availableSlots);
            System.out.println("Enter your desired slot:");
            choice = scanner.nextInt();
        } while(choice < 1 || choice >= availableSlots.size());
        Appointment selectedAppointment = availableSlots.get(choice-1);

        //send request
        selectedAppointment.setStatus("pending");
        selectedAppointment.setPatient(patient);
        patient.addScheduledAppointment(selectedAppointment);
        System.out.println("Request submitted");
    }
}