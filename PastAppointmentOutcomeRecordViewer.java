public class PastAppointmentOutcomeRecordViewer{
    public static void viewPastAppointmentOutcomeRecord(Patient patient){
        if (patient.getPastDiagnoses().size() == 0){
            System.out.println("There is currently no record");
            return;
        }
        patient.getPastDiagnoses().displayPastDiagnoses();
    }
}