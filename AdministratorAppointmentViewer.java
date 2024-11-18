import java.util.List;

public class AdministratorAppointmentViewer {
	public static void viewAllAppointment() {
		List<Appointment> appointments = TextService.readAppointmentsFromTxt();
    
        for (Appointment appointment: appointments) {
            if (!appointment.getStatus().equals("unavailable")) {
				// Print Appointment Details
                // System.out.println("\n--- Appointment ---");
                // System.out.println("Patient ID: " + appointment.getPatientID());
                // System.out.println("Doctor ID: " + appointment.getDoctorID());
                // System.out.println("Status: " + appointment.getStatus());
                // System.out.println("Date and Time: " + appointment.getDate() + " " + appointment.getTime());
                // System.out.println("----------------------");
                appointment.displayAppointment();
            }
        }

		if (appointments.isEmpty()) {
			System.out.println("No Appointments\n");
		} 
	}
}
