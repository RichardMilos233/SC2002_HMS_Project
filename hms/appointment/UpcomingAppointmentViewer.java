package hms.appointment;

import java.time.LocalDate;
import java.util.*;

import hms.users.Doctor;

public class UpcomingAppointmentViewer {

    /**
     * Displays all upcoming appointments for a specified doctor in a timetable format.
     * Appointments are grouped by date, and appointment numbers increment globally across dates.
     *
     * @param doctor The doctor whose upcoming appointments need to be viewed.
     */
    public static void viewUpcomingAppointment(Doctor doctor) {
        List<Appointment> upcomingAppointments = getUpcomingAppointment(doctor);

        if (upcomingAppointments.isEmpty()) {
            System.out.println("There is no upcoming appointment");
            System.out.println("Maybe you should reflect on why no one wants to consult you");
            return;
        }

        // Find unique dates of upcoming appointments
        List<LocalDate> dates = findSlotDates(upcomingAppointments);

        // Group appointments by date
        List<List<Appointment>> appointmentsByDate = splitAppointmentDates(upcomingAppointments, dates);

        // Print timetable header
        System.out.println("Upcoming Appointments for Dr. " + doctor.getName());
        System.out.println();

        for (LocalDate date : dates) {
            System.out.printf("%-50s|", "Date: " + date.toString());
        }
        System.out.println();

        for (int i = 0; i < dates.size(); i++) {
            System.out.printf("%-50s|", "--------------------------------------------------");
        }
        System.out.println();

        // Find the maximum number of appointments on any single date
        int maxAppointments = appointmentsByDate.stream().mapToInt(List::size).max().orElse(0);

        // Print timetable
        printSchedule(appointmentsByDate, maxAppointments);
    }

    /**
     * Prints upcoming appointments in a timetable format with globally incrementing numbers.
     *
     * @param appointmentsByDate List of appointments grouped by date.
     * @param maxAppointments    Maximum number of appointments on any date.
     */
    public static void printSchedule(List<List<Appointment>> appointmentsByDate, int maxAppointments) {
        int appointmentNumber = 1; // Initialize global appointment counter

        for (int i = 0; i < maxAppointments; i++) {
            // First Row: Appointment Number
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    System.out.printf("%-50s|", "Appointment " + appointmentNumber++);
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to the next row

            // Second Row: Date & Time
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", "Date & Time: " + appointment.getDate() + " " + appointment.getTime());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to the next row

            // Third Row: Status
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", "Status: " + appointment.getStatus());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.println(); // Move to the next row

            // Fourth Row: Patient ID
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                if (i < dailyAppointments.size()) {
                    Appointment appointment = dailyAppointments.get(i);
                    System.out.printf("%-50s|", "Patient ID: " + appointment.getPatientID());
                } else {
                    System.out.printf("%-50s|", " ");
                }
            }
            System.out.printf("\n");
            for (List<Appointment> dailyAppointments : appointmentsByDate) {
                System.out.printf("%-50s|", "--------------------------------------------------");
            }
            System.out.printf("\n");
        }
    }

    /**
     * Retrieves a list of all upcoming appointments for a specified doctor that are confirmed.
     *
     * @param doctor The doctor whose upcoming appointments are to be retrieved.
     * @return A list of Appointment objects that are confirmed and upcoming for the given doctor.
     */
    public static List<Appointment> getUpcomingAppointment(Doctor doctor) {
        List<Appointment> upcomingAppointments = new ArrayList<>();
        for (Appointment appointment : doctor.getTimeTable()) {
            if (appointment.getStatus().equals("confirmed")) {
                upcomingAppointments.add(appointment);
            }
        }
        return upcomingAppointments;
    }

    /**
     * Finds unique dates from a list of appointments.
     *
     * @param appointments The list of appointments.
     * @return A list of unique dates, sorted.
     */
    public static List<LocalDate> findSlotDates(List<Appointment> appointments) {
        Collections.sort(appointments, Comparator.comparing(Appointment::getDate));
        List<LocalDate> dates = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (!dates.contains(appointment.getDate())) {
                dates.add(appointment.getDate());
            }
        }
        return dates;
    }

    /**
     * Groups appointments by date.
     *
     * @param appointments The list of appointments.
     * @param dates        The unique dates.
     * @return A list of appointment lists grouped by date.
     */
    public static List<List<Appointment>> splitAppointmentDates(List<Appointment> appointments, List<LocalDate> dates) {
        List<List<Appointment>> appointmentsByDate = new ArrayList<>();
        int lastIndex = 0;

        for (LocalDate date : dates) {
            List<Appointment> current = new ArrayList<>();
            while (lastIndex < appointments.size() && appointments.get(lastIndex).getDate().compareTo(date) == 0) {
                current.add(appointments.get(lastIndex));
                lastIndex++;
            }
            appointmentsByDate.add(current);
        }
        return appointmentsByDate;
    }
}
