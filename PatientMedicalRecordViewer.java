public class PatientMedicalRecordViewer {
    public static void viewPatientMedicalRecord(Patient patient){
        System.out.println("---------------Medical Record---------------");
		System.out.println("Patient ID: " + patient.getHospitalID());
		System.out.println("Name: " + patient.getName());
		System.out.println("Date of birth: " + patient.getBirth());
		System.out.println("Gender: " + patient.getGender());
		System.out.println("Contact number: " + patient.getContactNumber());
		System.out.println("Email: " + patient.getEmail());
		System.out.println("Blood type: " + patient.getBloodType());
		patient.getPastDiagnoses().displayPastDiagnoses();
		System.out.println("---------------------End---------------------");
    }
}
