import java.util.*;

public class AvailableSlotsGetter {
    public static List<Appointment> getAvailableSlots(Doctor doctor){
        List<Appointment> availableSlots = new ArrayList<>();
        List<Appointment> timeTable = doctor.getTimeTable();
        for (Appointment appointment: timeTable){
            if (appointment.getStatus().equals("available")){
                availableSlots.add(appointment);
            }
        }
        return availableSlots;
    }
}
