import java.util.*;

public class MedicalRecordUpdater { 
    // TODO - Doctors can update the medical records of patients by adding new diagnoses, prescriptions, and treatment plans
    public static void updateMedicalRecord(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // choose which patient to update
        Patient patient = Patient.getPatient(doctor.getPatients());
    }
}
