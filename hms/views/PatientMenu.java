package hms.views;

import hms.appointment.*;
import hms.medicalrecords.PatientMedicalRecordViewer;
import hms.users.Patient;
import hms.utils.Validator;
import java.util.*;
/**
 * Handles the display and functionality of the patient menu in the healthcare management system.
 * This class provides various options for a patient to manage their profile and interact with
 * the system to book, reschedule, or cancel appointments, view medical records, and update personal details.
 */
public class PatientMenu {
    /**
     * Displays the main menu for patient interactions and handles user input to navigate
     * through different functionalities based on the patient's choices.
     *
     * @param patient The patient who is interacting with the system.
     */
    public static void displayPatientMenu(Patient patient){
        Scanner scanner = new Scanner(System.in);
        int choice;
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
                                "9 Change Password \n" + 
                                "10 Logout\n" + 
                                "Enter your choice:");
            choice = Validator.validateInt(scanner);
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
                    patient.changePassword();
                    break;
                case 10:
                    patient.logout();
                    break;

                default:
                    break;
            }
        }while(choice != 10);
    }
}