package hms.appointment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import hms.users.Doctor;
import hms.users.Patient;


public class PrintAppointmentsSchedule {
    public static void printChosenSlots(List<Appointment> availableSlots){
        if (availableSlots.isEmpty()){
            System.err.println("There are currently no appointments");
            return;
        }
        List<LocalDate> dates = findSlotDates(availableSlots);
        List<List<Appointment>> appointmentsByDate = splitAppointmentDates(availableSlots, dates);
        /*
         * DATE !:                  Date 2:                 Date 3:
         * appointment 1:           appointment i+1:          appointment 2i+1:
         *
         */
        int size = 0, newSize;
        System.out.println("Appointments: ");
        for (int i = 0; i<dates.size(); i++){
            System.out.printf("%-20s%-10s%-20s|", " " , dates.get(i).toString(), " ");
            if (i!=0){
                newSize = appointmentsByDate.get(i).size();
                if (newSize > size) size = newSize;
            } else{
                size = appointmentsByDate.get(i).size();
            }
        }
        
        System.out.printf("\n");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("\n");
        printScheduleDoctorAndPatientID(appointmentsByDate, size);
    }

    public static void printPendingSlots(List<Appointment> availableSlots){
        if (availableSlots.isEmpty()){
            System.err.println("There are currently no appointments");
            return;
        }
        List<LocalDate> dates = findSlotDates(availableSlots);
        List<List<Appointment>> appointmentsByDate = splitAppointmentDates(availableSlots, dates);
        /*
         * DATE !:                  Date 2:                 Date 3:
         * appointment 1:           appointment i+1:          appointment 2i+1:
         *
         */
        int size = 0, newSize;
        System.out.println("Appointments: ");
        for (int i = 0; i<dates.size(); i++){
            System.out.printf("%-20s%-10s%-20s|", " " , dates.get(i).toString(), " ");
            if (i!=0){
                newSize = appointmentsByDate.get(i).size();
                if (newSize > size) size = newSize;
            } else{
                size = appointmentsByDate.get(i).size();
            }
        }
        
        System.out.printf("\n");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("\n");
        printSchedulePending(appointmentsByDate, size);
        
        // for (int i = 0; i < availableSlots.size(); i++){
        //     System.out.println("Slot: " + (i+1));
        //     availableSlots.get(i).displayAppointment();
        // }
    }

    public static void printChosenSlotsPatientScheduled(List<Appointment> availableSlots){
        if (availableSlots.isEmpty()){
            System.err.println("There are currently no appointments");
            return;
        }
        List<LocalDate> dates = findSlotDates(availableSlots);
        List<List<Appointment>> appointmentsByDate = splitAppointmentDates(availableSlots, dates);
        /*
         * DATE !:                  Date 2:                 Date 3:
         * appointment 1:           appointment i+1:          appointment 2i+1:
         *
         */
        int size = 0, newSize;
        System.out.println("Appointments: ");
        for (int i = 0; i<dates.size(); i++){
            System.out.printf("%-20s%-10s%-20s|", " " , dates.get(i).toString(), " ");
            if (i!=0){
                newSize = appointmentsByDate.get(i).size();
                if (newSize > size) size = newSize;
            } else{
                size = appointmentsByDate.get(i).size();
            }
        }
        
        System.out.printf("\n");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("\n");
        printScheduleDoctorName(appointmentsByDate, size);
    }

    public static void printChosenSlotsWithPatientName( List<Appointment> availableSlots){
        if (availableSlots.isEmpty()){
            System.err.println("There is currently no appointments");
            return;
        }
        List<LocalDate> dates = findSlotDates(availableSlots);
        List<List<Appointment>> appointmentsByDate = splitAppointmentDates(availableSlots, dates);
        /*
         * DATE !:                  Date 2:                 Date 3:
         * appointment 1:           appointment i+1:          appointment 2i+1:
         *
         */
        int size = 0, newSize;
        System.out.println("Appointments: ");
        for (int i = 0; i<dates.size(); i++){
            System.out.printf("%-20s%-10s%-20s|", " " , dates.get(i).toString(), " ");
            if (i!=0){
                newSize = appointmentsByDate.get(i).size();
                if (newSize > size) size = newSize;
            } else{
                size = appointmentsByDate.get(i).size();
            }
        }
        
        System.out.printf("\n");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("\n");
        printSchedulePatientName(appointmentsByDate, size);
    }

    public static void printChosenSlotsofDoctor(Doctor doctor , List<Appointment> availableSlots){
        if (availableSlots.isEmpty()){
            System.err.println("There is currently no available slots for doctor " + doctor.getName());
            return;
        }
        List<LocalDate> dates = findSlotDates(availableSlots);
        List<List<Appointment>> appointmentsByDate = splitAppointmentDates(availableSlots, dates);
        /*
         * DATE !:                  Date 2:                 Date 3:
         * appointment 1:           appointment i+1:          appointment 2i+1:
         *
         */
        int size = 0, newSize;
        System.out.println("Appointments: ");
        for (int i = 0; i<dates.size(); i++){
            System.out.printf("%-20s%-10s%-20s|", " " , dates.get(i).toString(), " ");
            if (i!=0){
                newSize = appointmentsByDate.get(i).size();
                if (newSize > size) size = newSize;
            } else{
                size = appointmentsByDate.get(i).size();
            }
        }
        
        System.out.printf("\n");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("%-50s|","--------------------------------------------------");
        System.out.printf("\n");
        printSchedule(appointmentsByDate, size);
    }

    public static void printSchedulePending(List<List<Appointment>> appointmentsByDate, int maxAppointments) {
        int appointmentNumber = 1; // Initialize appointment counter

        
        int day1 =0; int day2=0;int day3=0;
        if (appointmentsByDate.size()>0){
            day1 = appointmentsByDate.get(0).size();
        }
        if (appointmentsByDate.size()>1){
            day2 = appointmentsByDate.get(1).size();
        }
        if (appointmentsByDate.size()>2){
            day3 = appointmentsByDate.get(2).size();
        }
        int firstDay = 1;
        int secondDay = day1+1;
        int thirdDay = day1+day2+1;
    
    
        for (int i = 0; i < maxAppointments; i++) {

            // First Row: Appointment Number
            for (int j = 0; j < appointmentsByDate.size(); j++) {
                List<Appointment> dailyAppointments = appointmentsByDate.get(j);
                if (i < dailyAppointments.size()) {
                    if (j==0){
                        System.out.printf("%-50s|", "Appointment " + firstDay++);
                    } else if (j==1){
                        System.out.printf("%-50s|", "Appointment " + secondDay++);
                    } else if (j==2){
                        System.out.printf("%-50s|", "Appointment " + thirdDay++);
                    }
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            
            System.out.println(); // Move to the next row
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", 
                        "Date & Time: " + appointment.getDate() + " " + appointment.getTime());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            
            System.out.println(); // Move to next row
    
            // Fpourth Row: Patient Name
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    if (!appointment.getStatus().equals("available")){
                        System.out.printf("%-50s|", "Patient: " + Patient.getByID(appointment.getPatientID()).getName());
                    } else{
                        System.out.printf("%-50s|", " ");
                    }
                    
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.printf("\n");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("\n");
         }
    }

    public static void printSchedulePatientName(List<List<Appointment>> appointmentsByDate, int maxAppointments) {
        int appointmentNumber = 1; // Initialize appointment counter

        int day1 =0; int day2=0;int day3=0;
        if (appointmentsByDate.size()>0){
            day1 = appointmentsByDate.get(0).size();
        }
        if (appointmentsByDate.size()>1){
            day2 = appointmentsByDate.get(1).size();
        }
        if (appointmentsByDate.size()>2){
            day3 = appointmentsByDate.get(2).size();
        }

        int firstDay = 1;
        int secondDay = day1+1;
        int thirdDay = day1+day2+1;
    
    
        for (int i = 0; i < maxAppointments; i++) {
            
        System.out.println(); // Move to the next row
            // Second Row: Date & Time
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", 
                        "Date & Time: " + appointment.getDate() + " " + appointment.getTime());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row
    
            // Thirf Row: Status
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", "Status: " + appointment.getStatus());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row
    
            // Fpourth Row: Doctor ID
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    if (!appointment.getStatus().equals("available")){
                        System.out.printf("%-50s|", "Patient: " + Patient.getByID(appointment.getPatientID()).getName());
                    } else{
                        System.out.printf("%-50s|", " ");
                    }
                    
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.printf("\n");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("\n");
         }
    }

     /**
     * Prints the available appointment slots in a timetable format 
     * Displays each slot sequentially 
     * If no slots are available, nothing is printed 
     *
     * @param appointmentsByDate list of appointments separated by their date 
     * @param maxAppointments largest number of appointments out of each date
     */
    public static void printSchedule(List<List<Appointment>> appointmentsByDate, int maxAppointments) {
        int day1 =0; int day2=0;int day3=0;
        if (appointmentsByDate.size()>0){
            day1 = appointmentsByDate.get(0).size();
        }
        if (appointmentsByDate.size()>1){
            day2 = appointmentsByDate.get(1).size();
        }
        if (appointmentsByDate.size()>2){
            day3 = appointmentsByDate.get(2).size();
        }
        int firstDay = 1;
        int secondDay = day1+1;
        int thirdDay = day1+day2+1;
    
    
        for (int i = 0; i < maxAppointments; i++) {

            // First Row: Appointment Number
            for (int j = 0; j < appointmentsByDate.size(); j++) {
                List<Appointment> dailyAppointments = appointmentsByDate.get(j);
                if (i < dailyAppointments.size()) {
                    if (j==0){
                        System.out.printf("%-50s|", "Appointment " + firstDay++);
                    } else if (j==1){
                        System.out.printf("%-50s|", "Appointment " + secondDay++);
                    } else if (j==2){
                        System.out.printf("%-50s|", "Appointment " + thirdDay++);
                    }
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            
        System.out.println(); // Move to the next row
            // Second Row: Date & Time
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", 
                        "Date & Time: " + appointment.getDate() + " " + appointment.getTime());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row
    
            // Thirf Row: Status
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", "Status: " + appointment.getStatus());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row
            System.out.printf("\n");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("\n");
         }
    }

    public static void printScheduleDoctorName(List<List<Appointment>> appointmentsByDate, int maxAppointments) {
        int day1 =0; int day2=0;int day3=0;
        if (appointmentsByDate.size()>0){
            day1 = appointmentsByDate.get(0).size();
        }
        if (appointmentsByDate.size()>1){
            day2 = appointmentsByDate.get(1).size();
        }
        if (appointmentsByDate.size()>2){
            day3 = appointmentsByDate.get(2).size();
        }
        int firstDay = 1;
        int secondDay = day1+1;
        int thirdDay = day1+day2+1;
    
    
        for (int i = 0; i < maxAppointments; i++) {

            // First Row: Appointment Number
            for (int j = 0; j < appointmentsByDate.size(); j++) {
                List<Appointment> dailyAppointments = appointmentsByDate.get(j);
                if (i < dailyAppointments.size()) {
                    if (j==0){
                        System.out.printf("%-50s|", "Appointment " + firstDay++);
                    } else if (j==1){
                        System.out.printf("%-50s|", "Appointment " + secondDay++);
                    } else if (j==2){
                        System.out.printf("%-50s|", "Appointment " + thirdDay++);
                    }
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            
        System.out.println(); // Move to the next row
            // Second Row: Date & Time
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", 
                        "Date & Time: " + appointment.getDate() + " " + appointment.getTime());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row
    
            // Thirf Row: Status
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", "Status: " + appointment.getStatus());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row
    
            // Fpourth Row: Doctor ID
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    String id = appointment.getDoctorID();
                    System.out.printf("%-50s|", "Doctor: " + Doctor.getByID(appointment.getDoctorID()).getName());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.printf("\n");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("\n");
         }
    }
    

    public static void printScheduleDoctorAndPatientID(List<List<Appointment>> appointmentsByDate, int maxAppointments) {
        int appointmentNumber = 1; // Initialize appointment counter

        for (int i = 0; i < maxAppointments; i++) {
            // Second Row: Date & Time
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", 
                        "Date & Time: " + appointment.getDate() + " " + appointment.getTime());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row

            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", "Status: " + appointment.getStatus());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row
    
            // Fpourth Row: Doctor ID
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-25s%-25s|", "Doctor ID: " + appointment.getDoctorID(), " Patient ID: " + appointment.getPatientID());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row

            // Appointment outcome
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size() && (dailyAppointments.get(i).getStatus().equals("closed") || dailyAppointments.get(i).getStatus().equals("dispensed"))) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", "Diagnosis: " + appointment.getAppointmentOutcome().getDiagnosis());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to next row
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size() && (dailyAppointments.get(i).getStatus().equals("closed") || dailyAppointments.get(i).getStatus().equals("dispensed"))) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-25s%-25s|", "Medicine: " + appointment.getAppointmentOutcome().getPrescribedMedication().getMedicationName(), "Dosage: " + appointment.getAppointmentOutcome().getPrescribedMedication().getDosage());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.printf("\n");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("%-50s|","--------------------------------------------------");
            System.out.printf("\n");
         }
    }

     /**
     * This method takes a list of appointment slots and returns a sorted list of dates from the earliest to the latest date available.
     * The list includes every consecutive date between the start and end date found in the appointment slots.
     *
     * @param availableSlots the list of appointments from which to extract and sort dates
     * @return a list of {@link LocalDate} objects representing each consecutive date between the earliest and latest dates found in the slots
     */
    public static List<LocalDate> findSlotDates(List<Appointment> availableSlots){
        Collections.sort(availableSlots, Comparator.comparing(Appointment ::getDate));
        List<LocalDate> dates = new ArrayList<LocalDate>();
        LocalDate startDate = availableSlots.get(0).getDate();
        dates.add(startDate);
        int size = availableSlots.size();
        LocalDate endDate = availableSlots.get(size-1).getDate();
        int i = 0;
        while (dates.get(i).compareTo(endDate) != 0){ // 10, 11, 12, 13, 14
            ++i;
            dates.add(startDate.plusDays(i));
        }
        return dates;
    }

    /**
     * This method divides a list of appointments into sublists, each containing all appointments for a particular date.
     * It uses a sorted list of dates to organize appointments into these date-specific lists.
     *
     * @param availableSlots the complete list of appointments to be categorized by date
     * @param dates a sorted list of dates for which appointments need to be organized
     * @return a list of lists, where each sublist contains all appointments for a specific date
     */
    public static List<List<Appointment>> splitAppointmentDates(List<Appointment> availableSlots, List<LocalDate> dates) {
        List<List<Appointment>> appointmentsByDate = new ArrayList<>();
        int lastIndex = 0;
    
        for (LocalDate date : dates) {
            List<Appointment> current = new ArrayList<>();
            while (lastIndex < availableSlots.size() && availableSlots.get(lastIndex).getDate().compareTo(date) == 0) {
                current.add(availableSlots.get(lastIndex));
                lastIndex++;
            }
            appointmentsByDate.add(current); // Add the current list even if it's empty
        }
        return appointmentsByDate;
    }
}
