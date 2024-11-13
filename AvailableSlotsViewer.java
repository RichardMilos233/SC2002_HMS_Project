import java.util.*;

public class AvailableSlotsViewer {
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
