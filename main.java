public class main {
    public static void main(String[] args){
        int choice;
        //patient menu
        Patient patient = new Patient("P1001", "pswrd", "Alice Brown", true, 24, 
                                    "1980-05-14", 84320011, "alice.brown@example.com", "A+");
        choice = 0;
        do{
            switch (choice) {
                case 1:
                    patient.viewMedicalRecord();
                    break;
                case 2:
                    patient.updatePersonalInformation();
                    break;
                case 3:
                    patient.viewAvailableAppointmentSlots();
                    break;
                case 4:
                    patient.scheduleAppointment();
                    break;
                case 5:
                    patient.rescheduleAppointment();
                    break;
                case 6:
                    patient.cancelAppointment();
                    break;
                case 7:
                    patient.viewScheduledAppointment();
                case 8:
                    patient.viewPastAppointmentOutcomeRecord();
                    break;
                case 9:
                    patient.logout();;
                    break;
                default:
                    break;
            }
        }while(choice != 9);

        //doctor menu
        Doctor doctor = new Doctor("D001", "docpass", "John Smith", false, 45);
        choice = 0;
        do{
            switch (choice) {
                case 1:
                    doctor.viewPatientMedicalRecord();
                    break;
                case 2:
                    doctor.updatePatientMedicalRecord(patient);
                    break;
                case 3:
                    doctor.viewPersonalSchedule();
                    break;
                case 4:
                    doctor.setAvailability();
                    break;
                case 5:
                    doctor.acceptAppointment();
                    break;
                case 6:
                    doctor.viewUpcomingAppointment();
                    break;
                case 7: 
                    doctor.recordAppointmentOutcome(null);//not settled yet
                    break;
                case 8:
                    doctor.logout();
                    break;
                default:
                    break;
            }
        }while (choice != 8);

        //pharmacist menu
        Pharmacist pharmacist = new Pharmacist("P001", "phmpass", "Mark Lee", false, 29);
        choice = 0;
        do{
            switch (choice) {
                case 1:
                    pharmacist.viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    pharmacist.updatePrescriptionStatus();
                    break;
                case 3:
                    pharmacist.viewMedicationInventory();
                    break;
                case 4:
                    pharmacist.submitReplenishmentRequest();
                    break;
                case 5:
                    pharmacist.logout();
                    break;
                default:
                    break;
            }
        }while(choice != 5);

        //admin menu
        Administrator administrator = new Administrator("A001", "adminpass", "Sarah Lee", true, 40);
        choice = 0;
        do{
            switch (choice) {
                case 1:
                    administrator.displayStaffList();
                    break;
                case 2:
                    administrator.viewAppointmentDetails();
                    break;
                case 3:
                    administrator.viewInventory();
                    break;
                case 4:
                    administrator.approveReplenishmentRequest();
                    break;
                case 5:
                    administrator.logout();
                    break;
                default:
                    break;
            }
        }while (choice != 5);
    }
}
