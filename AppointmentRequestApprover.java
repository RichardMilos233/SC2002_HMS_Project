import java.util.*;

public class AppointmentRequestApprover{
    public static void approveAppointmentRequest(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // doctor approves the appointment request from patient
        // get the pending list
        List<Appointment> pendingList = pendingListGetter(doctor);
        int choice = -1;
        // decide them all, for now
        for (Appointment appointment : pendingList){
            appointment.displayAppointment();
            System.out.println("Approve or not: (1 for yes, 0 for no)");
            choice = scanner.nextInt(); // no input check, i.e. do-while loop
            if (choice == 1){
                appointment.setStatus("confirmed");
            }
            if (choice == 0){
                appointment.setStatus("rejected");
            }
        }
    }

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