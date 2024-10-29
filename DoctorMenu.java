import java.util.*;

public class DoctorMenu {
    Scanner scanner = new Scanner(System.in);
    public void displayDoctorMenu(Doctor doctor){
        // only for test
        Patient patient = new Patient("P1001", "pswrd", "Alice Brown", true, 24, 
                                    "1980-05-14", 84320011, "alice.brown@example.com", "A+");
        int choice = 0;
        do{
            System.out.println("Doctor Menu: \n" + //
                                "1 View Patient Medical Records\n" + //
                                "2 Update Patient Medical Records \n" + //
                                "3 View Personal Schedule \n" + //
                                "4 Set Availability for Appointments \n" + //
                                "5 Accept or Decline Appointment Requests \n" + //
                                "6 View Upcoming Appointments \n" + //
                                "7 Record Appointment Outcome \n" + //
                                "8 Logout");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    doctor.viewPatientMedicalRecord(patient);
                    break;
                case 2:
                    doctor.updatePatientMedicalRecord(patient);
                    break;
                case 3:
                    doctor.viewPersonalSchedule();
                    break;
                case 4:
                    doctor.setAvailability();
                    break;
                case 5:
                    doctor.acceptAppointment();
                    break;
                case 6:
                    doctor.viewUpcomingAppointment();
                    break;
                case 7: 
                    doctor.recordAppointmentOutcome(null);//not settled yet
                    break;
                case 8:
                    doctor.logout();
                    break;
                default:
                    break;
            }
        }while (choice != 8);
    }
}
