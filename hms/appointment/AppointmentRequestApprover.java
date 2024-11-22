package hms.appointment;

import hms.storage.TextService;
import hms.users.Doctor;
import hms.utils.Validator;
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
        char c1;
        // decide them all, for now
            PrintAppointmentsSchedule.printPendingSlots(pendingList);
        do { 
            System.out.println("Select an appointment to Approve or Decline OR 0 to return: ");
            choice = Validator.validateInt(scanner);
            if (choice == 0){
                return;
            }
            if (choice < 1 || choice > pendingList.size()){
                System.out.println("Invalid.");
            } else{
                do { 
                    System.out.println("Approve?\nY Yes\tN No \tC Return");
                    c1 = Validator.validateCharToUpper(scanner);
                    if (c1 == 'C'){
                        return;
                    }
                } while (c1 != 'Y' && c1 != 'N');
                Appointment appointment = pendingList.get(choice - 1);
                if (c1 == 'Y'){
                    appointment.setStatus("confirmed");
                    doctor.addPatient(appointment.getPatient());
                }else if (c1 == 'N'){
                    appointment.setStatus("available");
                }
                TextService.replaceAppointment(appointment);
            }
        } while (choice != 0);
        
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