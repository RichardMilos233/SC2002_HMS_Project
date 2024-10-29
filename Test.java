import java.util.*;
import java.time.*;

public class Test {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice;

        //initialize sample users
        Patient patient = new Patient("P1001", "pswrd", "Alice Brown", "Female", 24, 
                                    LocalDate.of(1990, 5, 14), 84320011, "alice.brown@example.com", "A+");
        Doctor doctor = new Doctor("D001", "docpass", "John Smith", "Male", 45);
        Pharmacist pharmacist = new Pharmacist("P001", "phmpass", "Mark Lee", "Male", 29);
        Administrator administrator = new Administrator("A001", "adminpass", "Sarah Lee", "Male", 40);

        // patient menu
        choice = 0;
        do{
            System.out.println("Patient Menu: \n" + //
                                "1 View Medical Record \n" + //
                                "2 Update Personal Information\n" + //
                                "3 View Available Appointment Slots \n" + //
                                "4 Schedule an Appointment \n" + //
                                "5 Reschedule an Appointment \n" + //
                                "6 Cancel an Appointment \n" + //
                                "7 View Scheduled Appointments \n" + //
                                "8 View Past Appointment Outcome Records \n" + //
                                "9 Logout ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    patient.viewMedicalRecord();
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
                    patient.logout();;
                    break;
                default:
                    break;
            }
        }while(choice != 9);

        //doctor menu
        choice = 0;
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

        //pharmacist menu
        choice = 0;
        do{
            System.out.println("Pharmacist Menu: \n" + //
                                "1 View Appointment Outcome Record \n" + //
                                "2 Update Prescription Status \n" + //
                                "3 View Medication Inventory \n" + //
                                "4 Submit Replenishment Request \n" + //
                                "5 Logout");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    pharmacist.viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    pharmacist.updatePrescriptionStatus();
                    break;
                case 3:
                    pharmacist.viewMedicationInventory();
                    break;
                case 4:
                    pharmacist.submitReplenishmentRequest();
                    break;
                case 5:
                    pharmacist.logout();
                    break;
                default:
                    break;
            }
        }while(choice != 5);

        //admin menu
        choice = 0;
        do{
            System.out.println("Administrator Menu: \n" + //
                                "1 View and Manage Hospital Staff \n" + //
                                "2 View Appointments details \n" + //
                                "3 View and Manage Medication Inventory \n" + //
                                "4 Approve Replenishment Requests \n" + //
                                "5 Logout ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    administrator.displayStaffList();
                    break;
                case 2:
                    administrator.viewAppointmentDetails();
                    break;
                case 3:
                    administrator.viewInventory();
                    break;
                case 4:
                    administrator.approveReplenishmentRequest();
                    break;
                case 5:
                    administrator.logout();
                    break;
                default:
                    break;
            }
        }while (choice != 5);

        scanner.close();
    }
}
