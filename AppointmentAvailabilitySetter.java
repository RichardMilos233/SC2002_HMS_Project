import java.util.*;

public class AppointmentAvailabilitySetter {
    public static void setAppointmentAvailability(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        List<Appointment> unavailableAppointments = getUnavailableAppointments(doctor);
        if (unavailableAppointments.size() == 0){
            System.out.println("You currently have no slot to be set");
        }
        
        Appointment appointment;
        System.out.println("Choose a time slot to be available");
        int i, choice = -1;
        for (i = 0; i < unavailableAppointments.size(); i++){
            appointment = unavailableAppointments.get(i);
            System.out.println("Time slot " + (i+1) +": ");
            appointment.displayAppointment();
        }

        while (true) {
            System.out.print("Enter an integer: ");
            try {
                choice = scanner.nextInt();
                // If input is valid, break the loop
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        if (choice < 1 || choice > unavailableAppointments.size()){
            System.out.println("invalid choice");
            return;
        }
        appointment = unavailableAppointments.get(choice-1);
        appointment.setStatus("available");
        TextService.replaceAppointment(appointment);
        System.out.println("The following slot is set to be available");
        appointment.displayAppointment();
    }

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
