import java.util.*;

public class AppointmentOutcomeRecorder {
    public static void recordAppointmentOutcome(Doctor doctor) {	// record the nearest appointment outcome of a doctor
		Scanner scanner = new Scanner(System.in);
		List<Appointment> upcomingAppointment = UpcomingAppointmentViewer.getUpcomingAppointment(doctor);
		if (upcomingAppointment.size() == 0){
			System.out.println("There is no upcoming appointment");
			System.out.println("Maybe you should refelct on why no one wants to consult you");
			return;
		}
		Appointment appointment = upcomingAppointment.get(0);
        
        String type;
        System.out.println("Enter type: ");
        type = scanner.nextLine();
		String medication;
		System.out.println("Enter medication: ");
		medication = scanner.nextLine();	//only 1 med for each appointment, for now
		PrescribedMedication prescribedMedication = new PrescribedMedication(medication, 0);
		String consultationNotes;
		System.out.println("Enter consultation notes: ");
		consultationNotes = scanner.nextLine();
		AppointmentOutcome appointmentOutcome = new AppointmentOutcome(appointment.getDate(), type, prescribedMedication, consultationNotes);
		appointment.setAppointmentOutcome(appointmentOutcome);
		appointment.getPatient().getPastDiagnoses().updatePastDiagnoses(appointmentOutcome);
        appointment.setStatus("closed");
	}
}
