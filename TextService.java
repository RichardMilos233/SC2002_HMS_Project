import java.util.*;
import java.io.*;

public class TextService {
    private static final String APPOINTMENT_TXT_PATH = "./txt/appointments.txt";

    public static void writeAppointmentsToFile(List<Appointment> appointments, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("patientID,doctorID,status,date,time,date-type-medicationName:dosage-consultationNotes");
            for (Appointment appointment : appointments) {
                writer.write(appointment.toTxt() + "\n");
            }
        }
    }
}
