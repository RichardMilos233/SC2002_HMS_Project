import java.util.*;

public class PatientMenu {
    public static void displayPatientMenu(Patient patient){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Patient Menu: \n" + 
                                "1 View Medical Record \n" + 
                                "2 Update Personal Information\n" + 
                                "3 View Available Appointment Slots \n" + 
                                "4 Schedule an Appointment \n" + 
                                "5 Reschedule an Appointment \n" + 
                                "6 Cancel an Appointment \n" + 
                                "7 View Scheduled Appointments \n" + 
                                "8 View Past Appointment Outcome Records \n" + 
                                "9 Logout ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1://both methods work, see which one yall prefer
                    // patient.viewMedicalRecord();
                    PatientMedicalRecordViewer.viewPatientMedicalRecord(patient);
                    break;
                case 2:
                    patient.updatePersonalInformation();
                    break;
                case 3:
                    patient.viewAvailableAppointmentSlots();
                    break;
                case 4:
                    patient.scheduleAppointment();
                    break;
                case 5:
                    patient.rescheduleAppointment();
                    break;
                case 6:
                    patient.cancelAppointment();
                    break;
                case 7:
                    patient.viewScheduledAppointment();
                    break;
                case 8:
                    patient.viewPastAppointmentOutcomeRecord();
                    break;
                case 9:
                    patient.logout();
                    break;
                default:
                    break;
            }
        }while(choice != 9);
    }
}
