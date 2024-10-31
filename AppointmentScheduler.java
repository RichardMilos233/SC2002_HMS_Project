import java.util.*;

public class AppointmentScheduler{
    public static void scheduleAppointment(Patient patient){
        Scanner scanner  = new Scanner(System.in);
        //patient sends appointment request to doctor and wait for doctor to approve
        //list out all doctors, then select a doctor
        Doctor doctor;
        int i;
        int choice = 0;
        do{
            System.out.println("Choose the doctor you want to consult:");
            for (i = 0; i < Doctor.doctors.size(); i++){
                doctor = Doctor.doctors.get(i);
                System.out.println(i+1 + ": " + doctor.getName());
            }
            choice = scanner.nextInt();
        } while(choice <1 || choice > Doctor.doctors.size());

        doctor = Doctor.doctors.get(choice-1);
        //sout all the available appointment slots of that doctor
        System.out.println("This is the available time slots of doctor " + doctor.getName());
        List<Appointment> availableSlots = AvailableSlotsGetter.getAvailableSlots(doctor);
        do{
            for (i = 0; i < availableSlots.size(); i++){
                System.out.println("Choice " + i+1);
                availableSlots.get(i).displayAppointment();
            }
            //choose the desired appointment slots
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