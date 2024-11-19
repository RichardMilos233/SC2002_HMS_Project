import java.util.*;
/**
 * Handles the approval process of pending appointment requests for a doctor.
 * This class provides methods to list pending appointments and allow the doctor to approve or reject each one.
 */
public class AppointmentRequestApprover{
    /**
     * Guides the doctor through the process of approving or rejecting pending appointment requests.
     * If there are no pending appointments, a message is displayed indicating the empty state.
     * Otherwise, each pending appointment is presented to the doctor with the option to approve or reject.
     * Confirming an appointment changes its status to 'confirmed' and adds the patient to the doctor's patient list,
     * while rejecting changes the status to 'rejected'.
     *
     * This method updates the appointment records accordingly and ensures the changes are saved via TextService.
     *
     * @param doctor The doctor who needs to approve or reject pending appointment requests.
     */
    public static void approveAppointmentRequest(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // doctor approves the appointment request from patient
        // get the pending list
        List<Appointment> pendingList = pendingListGetter(doctor);
        if (pendingList.size() == 0){
            System.out.println("The pending list is empty");
            System.out.println("Maybe you should refelct on why no one wants to consult you");
            return;
        }
        int choice = -1;
        // decide them all, for now
        for (Appointment appointment : pendingList){
            appointment.displayAppointment();
            
            do { 
                System.out.println("Approve?\n1 Yes\t0 No");
                choice = Validator.validateInt(scanner);
            } while (choice != 1 && choice != 0);

            if (choice == 1){
                appointment.setStatus("confirmed");
                doctor.addPatient(appointment.getPatient());
            }else if (choice == 0){
                appointment.setStatus("available");
            }
            TextService.replaceAppointment(appointment);
        }
    }

    /**
     * Retrieves a list of pending appointments for a specified doctor.
     * Filters through the doctor's timetable to find appointments that are marked as 'pending'.
     *
     * @param doctor The doctor whose pending appointments are to be retrieved.
     * @return A list of pending appointments.
     */
    private static List<Appointment> pendingListGetter(Doctor doctor){
        List<Appointment> pendingList = new ArrayList<>();
        List<Appointment> timeTable = doctor.getTimeTable();
        
        for (Appointment appointment : timeTable){
            if (appointment.getStatus().equals("pending")){
                pendingList.add(appointment);
            }
        }
        return pendingList;
    }
}