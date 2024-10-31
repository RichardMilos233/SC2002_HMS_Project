import java.util.*;

public class AppointmentRequestApprover{
    public static void approveAppointmentRequest(Doctor doctor){
        // doctor approves the appointment request from patient
        // get the pending list
        List<Appointment> pendingList = pendingListGetter(doctor);
        // decide them all, for now
    }

    private static List<Appointment> pendingListGetter(Doctor doctor){
        List<Appointment> pendingList = new ArrayList<>();
        List<Appointment> timeTable = doctor.getTimeTable();
        Appointment appointment;
        for (appointment : timeTable){
            if ()
        }
        return pendingList;
    }
}