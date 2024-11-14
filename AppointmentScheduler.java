import java.util.*;

public class AppointmentScheduler{
    public static void scheduleAppointment(Patient patient){
        Scanner scanner  = new Scanner(System.in);
        //patient sends appointment request to doctor and wait for doctor to approve
        
        //list out all doctors, then select a doctor
        Doctor doctor = Doctor.getDoctor();
        if (doctor == null){
            System.out.println("invalid choice");
            return;
        }

        //sout all the available appointment slots of that doctor
        List<Appointment> availableSlots = AvailableSlotsViewer.getAvailableSlots(doctor);
        if (availableSlots.size() == 0){
            System.out.println("There is currently no available slot for doctor " + doctor.getName());
            return;
        }
        int choice;
        do{
            AvailableSlotsViewer.printAvailableSlots(doctor, availableSlots);
            System.out.println("Enter your desired slot:");
            choice = scanner.nextInt();
        } while(choice < 1 || choice > availableSlots.size());
        Appointment selectedAppointment = availableSlots.get(choice-1);

        //send request
        selectedAppointment.setStatus("pending");
        selectedAppointment.setPatientID(patient.getHospitalID());
        patient.addAppointment(selectedAppointment);
        TextService.replaceAppointment(selectedAppointment);    // this find the old apt in txt (by doc id, date, time), update its info by  refreshing all of its info
        System.out.println("Request submitted");
    }
}