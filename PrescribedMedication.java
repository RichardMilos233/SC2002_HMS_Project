public class PrescribedMedication {

	private String medicationName = "nil";
	private String dosage = "nil";
	private int totalPrescribed;

	public PrescribedMedication() {}

	public PrescribedMedication(String medicationName, String dosage, int totalPrescribed){
		this.medicationName = medicationName;
		this.dosage = dosage;
		this.totalPrescribed = totalPrescribed;
	}

	public void setDosage(String newDosage) {
		this.dosage = newDosage;
	}

	public void setTotalPrescribed(int totalPrescribed) {
		this.totalPrescribed = totalPrescribed;
	}

	public void displayPrescribedMedication(){
		System.out.println("Medication: " + medicationName);
	}

	public void displayMedication(){
		System.out.printf(medicationName);
	}

	public String getPrescription() {
		String medicationNamePlural = medicationName;
		if (totalPrescribed>1){
			medicationNamePlural = medicationName.concat("s");
		}
		return (medicationName.toUpperCase().charAt(0) + medicationName.toLowerCase().substring(1) + 
		" taken " + dosage + ", " + totalPrescribed  + " " + medicationNamePlural + " given to patient");
	}


	// for PrescribedMedication, splitter of which is \
	public String toTxt() {
        return String.format("%s\\%s\\%d", medicationName, dosage, totalPrescribed);
    }

    public static PrescribedMedication fromTxt(String txt) {
        // Assuming txt format is exactly as in toTxt output
		
        String[] lines = txt.split("\\\\");
		
        String medicationName = lines[0];
        String dosage = lines[1];
		int totalPrescribed = Integer.parseInt(lines[2]);
        return new PrescribedMedication(medicationName, dosage, totalPrescribed);
    }

	public String getMedicationName() {
		return medicationName;
	}

	public String getDosage() {
		return dosage;
	}

	public int gettotalPrescribed() {
		return totalPrescribed;
	}
}