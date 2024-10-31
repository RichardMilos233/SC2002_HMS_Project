import java.util.*;
import java.time.*;

public class PastDiagnoses {
	//only for debugging, intialization should be an empty list
	PrescribedMedication p1 = new PrescribedMedication("penadol", 2);
	PrescribedMedication p2 = new PrescribedMedication("meth", 5);
	AppointmentOutcome a = new AppointmentOutcome(LocalDate.of(2024, 10, 27), "face-to-face", p1, "drink more hot water");
	AppointmentOutcome b = new AppointmentOutcome(LocalDate.of(2023, 5, 8), "online", p2, "sleep more");
	private ArrayList<AppointmentOutcome> appointmentOutcomes = new ArrayList<>(Arrays.asList(a,b));
	
	public PastDiagnoses() {}//nothing to initialize

	public void displayPastDiagnoses() {
		for (AppointmentOutcome appointmentOutcome: appointmentOutcomes){
			appointmentOutcome.displayAppointmentOutcome();
		}
	}

	public void updatePastDiagnoses(AppointmentOutcome appointmentOutcome) {
		appointmentOutcomes.add(appointmentOutcome);
	}

}