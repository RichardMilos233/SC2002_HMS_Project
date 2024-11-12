import java.util.*;

public class PastDiagnoses {	// a list of appointmentOutcome
	private ArrayList<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();
	
	public PastDiagnoses() {}//nothing to initialize

	public void displayPastDiagnoses() {
		for (AppointmentOutcome appointmentOutcome: appointmentOutcomes){
			appointmentOutcome.displayAppointmentOutcome();
		}
	}

	public void updatePastDiagnoses(AppointmentOutcome appointmentOutcome) {
		appointmentOutcomes.add(appointmentOutcome);
	}

	public List<AppointmentOutcome> getAppointmentOutcomes(){
		return this.appointmentOutcomes;
	}
}