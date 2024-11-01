import java.util.*;

public class AppointmentAvailabilitySetter {
    public static void setAppointmentAvailability(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        List<Appointment> unavailableAppointment = getUnavailableAppointment(doctor);
        Appointment appointment;
        System.out.println("Choose a time slot to be available");
        int i, choice = -1;
        for (i = 0; i < unavailableAppointment.size(); i++){
            appointment = unavailableAppointment.get(i);
            System.out.println("Time slot " + (i+1) +": ");
            appointment.displayAppointment();
        }
        choice = scanner.nextInt();
        if (choice < 1 || choice > unavailableAppointment.size()){
            System.out.println("invalid choice");
            return;
        }
        appointment = unavailableAppointment.get(choice-1);
        appointment.setStatus("available");
    }

    public static List<Appointment> getUnavailableAppointment(Doctor doctor){
        List<Appointment> timeTable = doctor.getTimeTable();
        List<Appointment> unavailableAppointment = new ArrayList<>();
        for (Appointment appointment : timeTable){
            if (appointment.getStatus().equals("unavailable")){
                unavailableAppointment.add(appointment);
            }
        }
        return unavailableAppointment;
    }
}
