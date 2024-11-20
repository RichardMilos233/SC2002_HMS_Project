package hms.medicalrecords;

import hms.users.Doctor;
import hms.users.Patient;
import hms.utils.Validator;
import java.util.*;
/**
 * Provides functionality for doctors to view the medical records of patients under their care.
 * This class enables the doctor to select a patient from their list and displays comprehensive
 * medical information about the chosen patient.
 */
public class DoctorMedicalRecordViewer {
    /**
     * Displays the medical record of a selected patient for a given doctor. The method prompts the
     * doctor to choose one of their patients and displays detailed information such as name, gender,
     * blood type, age, and past diagnoses.
     *
     * @param doctor The doctor who wants to view the medical record of a patient.
     */
    public static void viewMedicalRecord(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // choose the patient the doctor want to view
        List<Patient> patients = doctor.getPatients();
        
        if (patients.isEmpty()){
            System.out.println("There is currently no patient under your care");
            System.out.println("Maybe you should reflect on why no one wants to consult you");
            return;
        }
        Patient patient;
        int i, choice = -1;

        System.out.println("Select a patient to view the medical record");
        for (i=0; i<patients.size(); i++){
            patient = patients.get(i);
            System.out.println(i+1 + ": " + patient.getName());
        }

        do {
            System.out.print("Select a valid Patient or 0 to cancel: ");
            choice = Validator.validateInt(scanner);
            if (choice == 0){
                return;
            }
        } while(choice < 1 || choice > patients.size());

        patient = patients.get(choice-1);
        // then view
        System.out.println("Name: " + patient.getName());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Age: " +patient.getAge());
        patient.getPastDiagnoses().displayPastDiagnoses();
    }
}
