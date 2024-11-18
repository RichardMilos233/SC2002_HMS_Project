import java.util.*;

public class MedicalRecordUpdater { 
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

        System.out.println("Choose the appointment outcome to resolve");
        for (i = 0; i < appointmentOutcomes.size(); i++){
            appointmentOutcome = appointmentOutcomes.get(i);
            System.out.println();
            System.out.println("Appointment outcome " + (i+1) + ":");
            appointmentOutcome.displayAppointmentOutcome();
        }

        while (true) {
            System.out.print("Select the appointment number: ");
            try {
                choice = Validator.validateInt(scanner);
                // If input is valid, break the loop
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        if (choice < 1 || choice > appointmentOutcomes.size()){
            System.out.println("invalid choice");
            return;
        }

        //getting the appt outcome we selected 
        List<Appointment> appointments = patient.getTimeTable();
        Appointment appointment;
        appointments.removeIf(apt -> !apt.getStatus().equals("closed"));    // brutally get the same list of appointments that contains appointmentoutcomes
        appointmentOutcome = appointmentOutcomes.get(choice-1);
        appointment = appointments.get(choice-1);

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
        char c;
        do { 
            System.out.println("Has this diagnosis been resolved?\nY Yes\tN No");
            c = Validator.validateCharToUpper(scanner);
        } while (c!='Y' && c!='N');
        appointmentOutcome.setResolved(c=='Y'); 
        TextService.replaceAppointment(appointment);
        System.out.println("Medical record has been updated");


        // appointmentOutcome.setPrescribedMedication(prescribedMedication);
        // appointmentOutcome.setConsultationNotes(consultationNotes);

        // TextService.replaceAppointment(appointment);

        // System.out.println("Appointment outcome has been updated");
    }
}
