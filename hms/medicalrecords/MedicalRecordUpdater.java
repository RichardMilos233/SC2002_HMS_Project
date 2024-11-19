package hms.medicalrecords;

import hms.storage.TextService;
import hms.appointment.Appointment;
import hms.appointment.AppointmentOutcome;
import hms.account.Doctor;
import hms.account.Patient;
import hms.utils.Validator;

import java.util.*;
/**
 * Provides functionality for doctors to update medical records of their patients.
 * This class allows doctors to choose a specific patient and update the outcomes of their appointments,
 * such as resolving diagnoses or updating treatment details.
 */
public class MedicalRecordUpdater { 
    /**
     * Updates the medical record of a selected patient under a doctor's care. It allows the doctor to choose a specific
     * appointment outcome to update, such as setting the diagnosis as resolved or adjusting treatment details.
     *
     * @param doctor The doctor performing the update operation.
     */
    public static void updateMedicalRecord(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // choose which patient to update
        List<Patient> patients = doctor.getPatients();
        if (patients.isEmpty()){
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
        if (appointmentOutcomes.size() == 0){
            System.out.println("Patient has no past medical record under your care");
            return;
        }
        AppointmentOutcome appointmentOutcome;
        int choice = -1;
        int i;

        System.out.println("Choose the appointment outcome to udpate");
        for (i = 0; i < appointmentOutcomes.size(); i++){
            appointmentOutcome = appointmentOutcomes.get(i);
            System.out.println();
            System.out.println("Appointment outcome " + (i+1) + ":");
            appointmentOutcome.displayAppointmentOutcome();
        }

        do {
            System.out.print("Select the appointment number: ");
            choice = Validator.validateInt(scanner);
            if (choice == 0){
                return;
            }
        } while (choice < 1 || choice > appointmentOutcomes.size());

        //getting the appt outcome we selected 
        List<Appointment> appointments = patient.getTimeTable();
        Appointment appointment;
        appointments.removeIf(apt -> !(apt.getStatus().equals("closed") || apt.getStatus().equals("dispensed")));    // brutally get the same list of appointments that contains appointmentoutcomes
        appointmentOutcome = appointmentOutcomes.get(choice-1);
        appointment = appointments.get(choice-1);

        String newDiagnosis;
        String diagnosis = appointmentOutcome.getDiagnosis();
        System.out.println("Enter a new diagnois:");
        newDiagnosis = scanner.nextLine();
        diagnosis = diagnosis + " " + newDiagnosis;

        appointmentOutcome.setDiagnosis(diagnosis);
        appointment.setAppointmentOutcome(appointmentOutcome);
        TextService.replaceAppointment(appointment);
        System.out.println("Medical record has been updated.");


		// String medication;
		// System.out.println("Enter medication: ");
        // scanner.nextLine(); // buffer
		// medication = scanner.nextLine();	//only 1 med for each appointment, for now
        // String dosage;
        // System.out.println("Enter dosage: ");
        // dosage = scanner.nextLine();
		// PrescribedMedication prescribedMedication = new PrescribedMedication(medication, dosage);
		// String consultationNotes;
		// System.out.println("Enter consultation notes: ");
		// consultationNotes = scanner.nextLine();

        // char c;
        // do { 
        //     System.out.println("Has this diagnosis been resolved?\nY Yes\tN No");
        //     c = Validator.validateCharToUpper(scanner);
        // } while (c!='Y' && c!='N');
        // appointmentOutcome.setResolved(c=='Y'); 
        // TextService.replaceAppointment(appointment);
        

        // appointmentOutcome.setPrescribedMedication(prescribedMedication);
        // appointmentOutcome.setConsultationNotes(consultationNotes);

        // TextService.replaceAppointment(appointment);

        // System.out.println("Appointment outcome has been updated");
    }
}
