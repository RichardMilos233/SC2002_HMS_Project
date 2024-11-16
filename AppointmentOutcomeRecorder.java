import java.util.*;

public class AppointmentOutcomeRecorder {
    public static void recordAppointmentOutcome(Doctor doctor) {	// record the nearest appointment outcome of a doctor
		Scanner scanner = new Scanner(System.in);
		List<Appointment> upcomingAppointment = UpcomingAppointmentViewer.getUpcomingAppointment(doctor);
		if (upcomingAppointment.size() == 0){
			System.out.println("There is no upcoming appointment");
			System.out.println("Maybe you should reflect on why no one wants to consult you");
			return;
		}
		Appointment appointment = upcomingAppointment.get(0);

		appointment.displayAppointment();
        
        String type;
        System.out.println("Enter type (consultation, xray, or surgery): ");
        type = Validator.validateStringNoSpace(scanner);
		
		String medication;
		System.out.println("Enter medication: ");
		medication = Validator.validateNotes(scanner);	//only 1 med for each appointment, for now
		String dosage;
		System.out.println("Enter dosage: ");
		dosage = Validator.validateNotes(scanner);
		PrescribedMedication prescribedMedication = new PrescribedMedication(medication, dosage);

		String consultationNotes;
		System.out.println("Enter consultation notes: ");
		consultationNotes = Validator.validateNotes(scanner);

		String diagnosis;
		System.out.println("Enter diagnosis: ");
		diagnosis = Validator.validateNotes(scanner);

		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.getDate(), type, prescribedMedication, consultationNotes, diagnosis, false);
		appointment.setAppointmentOutcome(appointmentOutcome);
		appointment.getPatient().getPastDiagnoses().updatePastDiagnoses(appointmentOutcome);
        appointment.setStatus("closed");
		TextService.replaceAppointment(appointment);
	}
}
