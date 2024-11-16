import java.util.*;

public class PastDiagnoses {	// a list of appointmentOutcome
	private ArrayList<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();
	
	public PastDiagnoses() {}//nothing to initialize

	public void displayPastDiagnoses() {
		System.out.printf("\nCurrent Diagnoses: ");
		for (AppointmentOutcome appointmentOutcome: appointmentOutcomes){
			if (!appointmentOutcome.getResolved()){
				appointmentOutcome.displayAppointmentDiagnosis();
				System.out.printf(", ");
			}
		}
		System.out.printf("\nPast Diagnoses: ");
		for (AppointmentOutcome appointmentOutcome: appointmentOutcomes){
			if (appointmentOutcome.getResolved()){
				appointmentOutcome.displayAppointmentPastDiagnosis();
				System.out.printf(", ");
			}
		}
		System.out.printf("\nMedicines: ");
		for (AppointmentOutcome appointmentOutcome: appointmentOutcomes){
			if (!appointmentOutcome.getResolved()){
				appointmentOutcome.displayAppointmentMedicine();
				System.out.printf(", ");
			}
		}
		System.out.printf("\nConsultation Notes: ");
		for (AppointmentOutcome appointmentOutcome: appointmentOutcomes){
			appointmentOutcome.displayAppointmentNotes();
			System.out.printf(", ");
		}
		System.out.println();
	}

	public void updatePastDiagnoses(AppointmentOutcome appointmentOutcome) {
		appointmentOutcomes.add(appointmentOutcome);
	}

	public List<AppointmentOutcome> getAppointmentOutcomes(){
		return this.appointmentOutcomes;
	}

	public int size(){
		return appointmentOutcomes.size();
	}
}