import java.util.*;
/**
 * Manages and stores past medical appointment outcomes for a patient. 
 * This class provides methods to display and update the list of past diagnoses.
 */
public class PastDiagnoses {	// a list of appointmentOutcome
	private ArrayList<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();
	/**
     * Constructs an instance of PastDiagnoses. This constructor initializes the list of 
     * appointment outcomes but does not pre-fill it with any data.
     */
	public PastDiagnoses() {}//nothing to initialize

	/**
     * Displays the current unresolved diagnoses, past resolved diagnoses, current medicines prescribed,
     * and consultation notes for each appointment outcome stored in this instance.
     */
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

	/**
     * Adds a new appointment outcome to the list of past diagnoses.
     *
     * @param appointmentOutcome The appointment outcome to be added to the list.
     */
	public void updatePastDiagnoses(AppointmentOutcome appointmentOutcome) {
		appointmentOutcomes.add(appointmentOutcome);
	}

	/**
     * Retrieves the list of all appointment outcomes.
     *
     * @return A list of {@link AppointmentOutcome} instances.
     */
	public List<AppointmentOutcome> getAppointmentOutcomes(){
		return this.appointmentOutcomes;
	}

	/**
     * Returns the number of appointment outcomes stored.
     *
     * @return The size of the appointment outcomes list.
     */
	public int size(){
		return appointmentOutcomes.size();
	}
}