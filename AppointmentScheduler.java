import java.util.*;

public class AppointmentScheduler{
    /**
     * Schedules an appointment for a specified patient. This method allows the patient to
     * select a doctor and choose from available appointment slots. It involves several steps:
     * listing all doctors, selecting a doctor, displaying the doctor's available slots, and
     * finally, allowing the patient to select a desired slot. The chosen appointment is then
     * marked as pending and updated in the system.
     * 
     * @param patient the patient who needs to schedule an appointment. This should be an instance
     *                of the Patient class containing patient-specific information.
     */
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
        if (availableSlots.isEmpty()){
            System.out.println("There is currently no available slot for doctor " + doctor.getName());
            return;
        }
        int choice;
        do{
            AvailableSlotsViewer.printAvailableSlots(doctor, availableSlots);
            System.out.println("Enter your desired slot:");
            choice = Validator.validateInt(scanner);
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