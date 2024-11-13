import java.util.*;

public class AppointmentRequestApprover{
    public static void approveAppointmentRequest(Doctor doctor){
        Scanner scanner = new Scanner(System.in);
        // doctor approves the appointment request from patient
        // get the pending list
        List<Appointment> pendingList = pendingListGetter(doctor);
        if (pendingList.size() == 0){
            System.out.println("The pending list is empty");
            System.out.println("Maybe you should refelct on why no one wants to consult you");
            return;
        }
        int choice = -1;
        // decide them all, for now
        for (Appointment appointment : pendingList){
            appointment.displayAppointment();
            System.out.println("Approve or not: (1 for yes, 0 for no)");

            while (true) {
                System.out.print("Enter an integer: ");
                try {
                    choice = scanner.nextInt();
                    // If input is valid, break the loop
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                }
            }
            if (choice != 1 && choice != 0){
                System.out.println("invalid choice");
                return;
            }

            if (choice == 1){
                appointment.setStatus("confirmed");
                doctor.addPatient(appointment.getPatient());
            }
            if (choice == 0){
                appointment.setStatus("rejected");
            }
            TextService.replaceAppointment(appointment);
        }
    }

    private static List<Appointment> pendingListGetter(Doctor doctor){
        List<Appointment> pendingList = new ArrayList<>();
        List<Appointment> timeTable = doctor.getTimeTable();
        
        for (Appointment appointment : timeTable){
            if (appointment.getStatus().equals("pending")){
                pendingList.add(appointment);
            }
        }
        return pendingList;
    }
}