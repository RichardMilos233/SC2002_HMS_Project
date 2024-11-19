/**
 * Provides the functionality to display the medical records of a patient.
 * This class serves as a utility for accessing and presenting a comprehensive view of a patient's medical history,
 * including personal details and past medical diagnoses.
 */
public class PatientMedicalRecordViewer {
	/**
     * Displays the medical record of a specified patient. The medical record includes personal information
     * such as patient ID, name, date of birth, gender, contact information, and blood type,
     * along with a detailed listing of past diagnoses.
     *
     * @param patient The patient whose medical record is to be viewed.
     */
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
