import java.util.*;

public class AppointmentOutcomeRecorder {
    public static void recordAppointmentOutcome(Appointment appointment) {
        Scanner scanner = new Scanner(System.in);
        String type;
        System.out.println("Enter type: ");
        type = scanner.nextLine();
		String medication;
		System.out.println("Enter medication: ");
		medication = scanner.nextLine();//only 1 med for each appointment, for now
		PrescribedMedication prescribedMedication = new PrescribedMedication(medication, 0);
		String consultationNotes;
		System.out.println("Enter consultation notes: ");
		consultationNotes = scanner.nextLine();
		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.getDate(), type, prescribedMedication, consultationNotes);//haven't thought of a type for consultation
		appointment.setAppointmentOutcome(appointmentOutcome);
		appointment.getPatient().getPastDiagnoses().updatePastDiagnoses(appointmentOutcome);
        appointment.setStatus("closed");
	}
}
