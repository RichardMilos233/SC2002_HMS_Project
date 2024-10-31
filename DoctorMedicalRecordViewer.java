import java.util.*;

public class DoctorMedicalRecordViewer {
    public static void viewMedicalRecord(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // choose the patient the doctor want to view
        List<Patient> patients = doctor.getPatients();
        Patient patient;
        int i, choice = -1;
        System.out.println("Select a patient to view the medical record");
        for (i=0; i<patients.size(); i++){
            patient = patients.get(i);
            System.out.println(i+1 + ": " + patient.getName());
        }
        choice = scanner.nextInt();
        if (choice < 1 || choice > patients.size()){
            System.out.println("invalid choice");
            return;
        }
        patient = patients.get(choice-1);
        // then view
        patient.getPastDiagnoses().displayPastDiagnoses();
    }
}
