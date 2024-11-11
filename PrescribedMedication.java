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


	// for PrescribedMedication, splitter of which is \
	public String toTxt() {
        return String.format("%s\\%s", medicationName, dosage);
    }

    public static PrescribedMedication fromTxt(String txt) {
        // Assuming txt format is exactly as in toTxt output
		
        String[] lines = txt.split("\\\\");
		
        String medicationName = lines[0];
        String dosage = lines[1];
        return new PrescribedMedication(medicationName, dosage);
    }
}