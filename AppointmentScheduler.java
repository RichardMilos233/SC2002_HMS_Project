import java.util.*;
/**
 * Facilitates the scheduling of appointments for patients by interacting with available system resources
 * to list doctors, their available slots, and handling patient appointment requests.
 * This class ensures that patients can seamlessly schedule appointments with available doctors
 * by choosing from open time slots, and updates the system with the pending status of these appointments.
 */
public class AppointmentScheduler{
    /**
     * Executes the appointment scheduling process for a given patient. The method involves multiple steps:
     * 1. Prompting the patient to choose a doctor from a listed selection.
     * 2. Displaying available appointment slots for the selected doctor.
     * 3. Allowing the patient to select a preferred slot.
     * The chosen appointment slot is then updated to 'pending' status, and the patient's ID is associated
     * with this appointment. The system's records are updated to reflect these changes.
     *
     * If the selected doctor has no available slots or an invalid choice is made, appropriate messages
     * are displayed, and the process is terminated early.
     *
     * @param patient The patient who is scheduling the appointment. It is assumed that this patient
     *                is registered and has valid credentials within the system.
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