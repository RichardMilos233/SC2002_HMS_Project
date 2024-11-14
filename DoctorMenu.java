import java.util.*;

public class DoctorMenu {
    public static void displayDoctorMenu(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // // only for test
        // Patient patient = new Patient("P1001", "pswrd", "Alice Brown", "Female", 24, 
        //                             LocalDate.of(1990, 5, 14), 84320011, "alice.brown@example.com", "A+");
        int choice = 0;
        do{
            System.out.println("---------------Doctor Menu---------------\n" + 
                                "1 View Patient Medical Records\n" + 
                                "2 Update Patient Medical Records \n" + 
                                "3 View Personal Schedule \n" + 
                                "4 Set Availability for Appointments \n" + 
                                "5 Accept or Decline Appointment Requests \n" + 
                                "6 View Upcoming Appointments \n" + 
                                "7 Record Appointment Outcome \n" + 
                                "8 Change Password \n" + 
                                "9 Logout");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    DoctorMedicalRecordViewer.viewMedicalRecord(doctor);
                    break;
                case 2:
                    MedicalRecordUpdater.updateMedicalRecord(doctor);
                    break;
                case 3:
                    PersonalScheduleViewer.viewPersonalSchedule(doctor);
                    break;
                case 4:
                    AppointmentAvailabilitySetter.setAppointmentAvailability(doctor);
                    break;
                case 5:
                    AppointmentRequestApprover.approveAppointmentRequest(doctor);
                    break;
                case 6:
                    UpcomingAppointmentViewer.viewUpcomingAppointment(doctor);
                    break;
                case 7: 
                    AppointmentOutcomeRecorder.recordAppointmentOutcome(doctor);
                    break;
                case 8:
                    doctor.changePassword();
                    break;
                case 9:
                    doctor.logout();
                    break;

                default:
                    break;
            }
        }while (choice != 9);
    }

    public static void viewMedicalRecord(Doctor d){
        // TO DO - DoctorMedicalRecordViewer.
    }
    
    public static void updateMedicalRecord(Doctor d){
        // TO DO - MedicalRecordUpdater.
    }

    public static void viewPersonalSchedule(Doctor d){
        // TO DO - PersonalScheduleViewer.
    }

    public static void setAppointmentAvailability(Doctor d){
        // TO DO - AppointmentAvailabilitySetter.
    }

    public static void approveAppointmentRequest(Doctor d){
        // TO DO - AppointmentRequestApprover.
    }

    public static void viewUpcomingAppointment(Doctor d){
        // TO DO - UpcomingAppointmentViewer.
    }
    
    public static void recordAppointmentOutcome(Doctor d){
        // TO DO - AppointmentOutcomeRecorder.
    }

}
