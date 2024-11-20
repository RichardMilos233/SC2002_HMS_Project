package hms.appointment;

import hms.account.users.Doctor;
import java.util.*;
/**
 * Provides functionality to view and display available appointment slots for doctors.
 * This class handles the retrieval and presentation of available or cancelled slots for selected doctors,
 * aiding in the scheduling and rescheduling of appointments.
 */
public class AvailableSlotsViewer {
    /**
     * Views and prints the available appointment slots for a doctor selected by the user.
     * Begins by allowing the user to select a doctor and then proceeds to fetch and display the doctor's available or
     * previously cancelled slots that can be rebooked. This method uses console output to list each available slot,
     * providing details such as time and status, helping patients or administrative staff make informed decisions
     * about booking appointments.
     */
    public static void viewAvailableSlots(){
        // choose the doctor first
        Doctor doctor = Doctor.getDoctor();
        if (doctor == null){
            System.out.println("Unavailable service");
            return;
        }
        
        // view his/ her thing
        List<Appointment> availableSlots = getAvailableSlots(doctor);
        printAvailableSlots(doctor, availableSlots);
    }

    /**
     * Retrieves a list of available appointment slots for a specified doctor.
     * This method filters through the doctor's timetable to find slots that are marked as "available" or "cancelled",
     * implying they can be booked or rebooked.
     *
     * @param doctor The doctor whose available slots are to be retrieved.
     * @return A list of available appointments for the doctor.
     */
    public static List<Appointment> getAvailableSlots(Doctor doctor){
        List<Appointment> availableSlots = new ArrayList<>();
        List<Appointment> timeTable = doctor.getTimeTable();
        for (Appointment appointment: timeTable){
            if (appointment.getStatus().equals("available") || appointment.getStatus().equals("cancelled")){
                availableSlots.add(appointment);
            }
        }
        return availableSlots;
    }

    /**
     * Prints the available appointment slots for a specific doctor.
     * Displays each slot sequentially and details about the appointment if slots are available.
     * If no slots are available, an error message is displayed.
     *
     * @param doctor The doctor whose available slots are to be printed.
     * @param availableSlots The list of available appointment slots to be printed.
     */
    public static void printAvailableSlots(Doctor doctor, List<Appointment> availableSlots){
        if (availableSlots.size() == 0){
            System.err.println("There is currently no available slots for doctor " + doctor.getName());
            return;
        }
        int i;
        System.out.println("These are available time slots for doctor " + doctor.getName());
        for (i = 0; i < availableSlots.size(); i++){
            System.out.println("Slot: " + (i+1));
            availableSlots.get(i).displayAppointment();
        }
    }
}
