public class PastAppointmentOutcomeRecordViewer{
    public static void viewPastAppointmentOutcomeRecord(Patient patient){
        patient.getPastDiagnoses().displayPastDiagnoses();
    }
}