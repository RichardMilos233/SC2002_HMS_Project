import java.util.*;

public class PastDiagnoses {

	private ArrayList<AppointmentOutcome> appointmentOutcomes;

	public PastDiagnoses() {
		// TODO - implement PastDiagnoses.PastDiagnoses
		throw new UnsupportedOperationException();
	}

	public void displayPastDiagnoses() {
		for (AppointmentOutcome appointmentOutcome: appointmentOutcomes){
			appointmentOutcome.displayAppointmentOutcome();
		}
		throw new UnsupportedOperationException();
	}

}