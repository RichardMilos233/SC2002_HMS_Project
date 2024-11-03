import java.util.*;

public class PatientMenu {
    public static void displayPatientMenu(Patient patient){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("---------------Patient Menu---------------\n" + 
                                "1 View Medical Record \n" + 
                                "2 Update Personal Information\n" + 
                                "3 View Available Appointment Slots \n" + 
                                "4 Schedule an Appointment \n" + 
                                "5 Reschedule an Appointment \n" + 
                                "6 Cancel an Appointment \n" + 
                                "7 View Scheduled Appointments \n" + 
                                "8 View Past Appointment Outcome Records \n" + 
                                "9 Logout \n" +
                                "Type in the service index to choose the service");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    PatientMedicalRecordViewer.viewPatientMedicalRecord(patient);
                    break;
                case 2:
                    patient.updatePersonalInformation();
                    break;
                case 3:
                    AvailableSlotsViewer.viewAvailableSlots();
                    break;
                case 4:
                    AppointmentScheduler.scheduleAppointment(patient);
                    break;
                case 5:
                    AppointmentRescheduler.rescheduleAppointment(patient);
                    break;
                case 6:
                    AppointmentCanceller.cancelAppointment(patient);
                    break;
                case 7:
                    ScheduledAppointmentViewer.viewScheduledAppointment(patient);
                    break;
                case 8:
                    PastAppointmentOutcomeRecordViewer.viewPastAppointmentOutcomeRecord(patient);
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
