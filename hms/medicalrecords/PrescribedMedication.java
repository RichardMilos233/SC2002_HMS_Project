package hms.medicalrecords;

/**
 * Represents a medication that has been prescribed to a patient, including the medication name, dosage, and total quantity prescribed.
 */
public class PrescribedMedication {

	private String medicationName = "nil";
	private String dosage = "nil";
	private int totalPrescribed;

	/**
     * Default constructor initializes the prescribed medication with default values.
     */
	public PrescribedMedication() {}

	/**
     * Constructs a PrescribedMedication with specified details.
     *
     * @param medicationName The name of the medication.
     * @param dosage The dosage of the medication.
     * @param totalPrescribed The total number of medication units prescribed.
     */
	public PrescribedMedication(String medicationName, String dosage, int totalPrescribed){
		this.medicationName = medicationName;
		this.dosage = dosage;
		this.totalPrescribed = totalPrescribed;
	}

	/**
     * Sets the dosage of the medication.
     *
     * @param newDosage The new dosage to set.
     */
	public void setDosage(String newDosage) {
		this.dosage = newDosage;
	}

	/**
     * Sets the total number of medication units prescribed.
     *
     * @param totalPrescribed The total units to set.
     */
	public void setTotalPrescribed(int totalPrescribed) {
		this.totalPrescribed = totalPrescribed;
	}

	/**
     * Displays the name of the medication.
     */
	public void displayPrescribedMedication(){
		System.out.println("Medication: " + medicationName);
	}

	/**
     * Prints the medication name.
     */
	public void displayMedication(){
		System.out.printf(medicationName);
	}

	/**
     * Returns a formatted prescription string including medication name, dosage, and quantity.
     *
     * @return A formatted prescription string.
     */
	public String getPrescription() {
		String medicationNamePlural = medicationName;
		if (totalPrescribed>=1){
               if (totalPrescribed > 1){
                    medicationNamePlural = medicationName.concat("s");
               }
               return (medicationName.toUpperCase().charAt(0) + medicationName.toLowerCase().substring(1) + 
		" taken " + dosage + ", " + totalPrescribed  + " " + medicationNamePlural + " given to patient");
		} else{
               if (medicationName.isEmpty()){
                    return "No medication given to patient";
               } else{
                    return (medicationName + "recommended to patient");
               }
          }
		
	}


	// for PrescribedMedication, splitter of which is \
	/**
     * Serializes the medication data to a text string with fields separated by backslashes.
     *
     * @return The serialized medication data.
     */
	public String toTxt() {
        return String.format("%s\\%s\\%d", medicationName, dosage, totalPrescribed);
    }

	/**
     * Deserializes medication data from a formatted text string.
     *
     * @param txt The text string containing the medication data.
     * @return A new instance of PrescribedMedication with data from the string.
     */
    public static PrescribedMedication fromTxt(String txt) {
        // Assuming txt format is exactly as in toTxt output
		
        String[] lines = txt.split("\\\\");
		
        String medicationName = lines[0];
        String dosage = lines[1];
		int totalPrescribed = Integer.parseInt(lines[2]);
        return new PrescribedMedication(medicationName, dosage, totalPrescribed);
    }

	/**
     * Returns the medication name.
     *
     * @return The medication name.
     */
	public String getMedicationName() {
		return medicationName;
	}

	/**
     * Returns the dosage of the medication.
     *
     * @return The dosage.
     */
	public String getDosage() {
		return dosage;
	}

	/**
     * Returns the total number of medication units prescribed.
     *
     * @return The total prescribed units.
     */
	public int gettotalPrescribed() {
		return totalPrescribed;
	}
}