import java.util.*;
import java.io.*;

public class TextService {
    private static final String APPOINTMENT_TXT_PATH = "./txt/appointments.txt";

    public static void writeAppointmentsToTxt(List<Appointment> appointments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_TXT_PATH))) {
            writer.write("patientID,doctorID,status,date,time,date-type-medicationName:dosage-consultationNotes");
            for (Appointment appointment : appointments) {
                writer.write(appointment.toTxt() + "\n");
            }
        }
    }

    public static List<Appointment> readAppointmentsFromTxt() throws IOException {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_TXT_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                Appointment appointment = Appointment.fromTxt(line);
                appointments.add(appointment);
            }
        }
        return appointments;
    }
}