import java.util.*;

public class MedicalRecordUpdater { 
    // TODO - Doctors can update the medical records of patients by adding new diagnoses, prescriptions, and treatment plans
    public static void updateMedicalRecord(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // choose which patient to update
        List<Patient> patients = doctor.getPatients();
        if (patients.size()==0){
            System.out.println("There is currently no patient under your care");
            System.out.println("Maybe you should reflect on why no one wants to consult you");
            return;
        }
        Patient patient = Patient.getPatient(patients);
        if (patient == null){
            System.out.println("invalid choice");
            return;
        }
        // select which appointment outcome to update
        PastDiagnoses pastDiagnoses = patient.getPastDiagnoses();
        List<AppointmentOutcome> appointmentOutcomes = pastDiagnoses.getAppointmentOutcomes();
        AppointmentOutcome appointmentOutcome;
        int choice = -1;
        int i;

        System.out.println("Choose the appointment outcome to update");
        for (i = 0; i < appointmentOutcomes.size(); i++){
            appointmentOutcome = appointmentOutcomes.get(i);
            System.out.println();
            System.out.println("Appointment outcome " + (i+1) + ":");
            appointmentOutcome.displayAppointmentOutcome();
        }
        choice = scanner.nextInt();
        if (choice < 1 || choice > appointmentOutcomes.size()){
            System.out.println("invalid choice");
            return;
        }
        appointmentOutcome = appointmentOutcomes.get(choice-1);

		String medication;
		System.out.println("Enter medication: ");
        scanner.nextLine(); // buffer
		medication = scanner.nextLine();	//only 1 med for each appointment, for now
        String dosage;
        System.out.println("Enter dosage: ");
        dosage = scanner.nextLine();
		PrescribedMedication prescribedMedication = new PrescribedMedication(medication, dosage);
		String consultationNotes;
		System.out.println("Enter consultation notes: ");
		consultationNotes = scanner.nextLine();

        appointmentOutcome.setPrescribedMedication(prescribedMedication);
        appointmentOutcome.setConsultationNotes(consultationNotes);

        System.out.println("Appointment outcome has been updated");
    }
}
