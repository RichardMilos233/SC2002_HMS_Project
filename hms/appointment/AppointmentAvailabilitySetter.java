package hms.appointment;

import hms.storage.TextService;
import hms.users.Doctor;
import hms.utils.Validator;

import java.time.LocalDate;
import java.util.*;
/**
 * Utility class to manage the availability of appointments for a given doctor.
 * Allows setting appointments from an "unavailable" status to an "available" status.
 */
public class AppointmentAvailabilitySetter {
    /**
     * Sets the availability of an appointment for the specified doctor. Allows the user to select an 
     * unavailable appointment slot to change its status to "available". Provides feedback on the action taken.
     * 
     * @param doctor The doctor whose appointments are to be managed for availability settings.
     */
    public static void setAppointmentAvailability(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        List<Appointment> unavailableAppointments = getUnavailableAppointments(doctor);
        if (unavailableAppointments.size() == 0){
            System.out.println("You currently have no slot to be set");
        }
        
        Appointment appointment;
        System.out.println("Choose a time slot to be available");
        int choice = -1;
        // for (i = 0; i < unavailableAppointments.size(); i++){
        //     appointment = unavailableAppointments.get(i);
        //     System.out.println("Time slot " + (i+1) +": ");
        //     appointment.displayAppointment();
        // }
        PrintAppointmentsSchedule.printChosenSlotsofDoctor(doctor, unavailableAppointments);
        

        
        do { 
            System.out.print("Choose a slot or 0 to return: ");
            choice = Validator.validateInt(scanner);
            if (choice == 0){
                return;
            }
            if (choice < 1 || choice > unavailableAppointments.size()){
                System.out.print("Invalid. Choose a valid slot: ");
            }
            appointment = unavailableAppointments.get(choice-1);
            appointment.setStatus("available");
            TextService.replaceAppointment(appointment);
            System.out.println("The following slot is set to be available");
            appointment.displayAppointment();
        } while (choice != 0);

    }

    /**
     * Retrieves a list of appointments for a specific doctor that are currently marked as "unavailable".
     * 
     * @param doctor The doctor whose unavailable appointments are to be retrieved.
     * @return A list of appointments that are currently marked as unavailable.
     */
    public static List<Appointment> getUnavailableAppointments(Doctor doctor){
        List<Appointment> timeTable = doctor.getTimeTable();
        List<Appointment> unavailableAppointments = new ArrayList<>();
        for (Appointment appointment : timeTable){
            if (appointment.getStatus().equals("unavailable")){
                unavailableAppointments.add(appointment);
            }
        }
        return unavailableAppointments;
    }
}
