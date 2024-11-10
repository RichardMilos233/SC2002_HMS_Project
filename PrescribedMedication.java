public class PrescribedMedication {

	private String medicationName;
	private String dosage;

	public PrescribedMedication() {}

	public PrescribedMedication(String medicationName, String dosage){
		this.medicationName = medicationName;
		this.dosage = dosage;
	}

	public void setDosage(String newDosage) {
		this.dosage = newDosage;
	}

	public void displayPrescribedMedication(){
		System.out.println("Medication: " + medicationName);
	}

	// Serialize to CSV format
    public String toCSV() {
        return medicationName + "|" + dosage;
    }

    // Deserialize from CSV format
    public static PrescribedMedication fromCSV(String data) {
        String[] fields = data.split("\\|");
        String medicationName = fields[0];
        String dosage = fields[1];
        return new PrescribedMedication(medicationName, dosage);
    }
}