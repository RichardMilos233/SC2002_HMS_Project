import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class TextService {
    private static final String APPOINTMENT_TXT_PATH = "./txt/appointments.txt";

    public static void writeAppointmentsToTxt(List<Appointment> appointments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_TXT_PATH))) {
            writer.write("patientID,doctorID,status,date,time,date-type-medicationName:dosage-consultationNotes\n");
            for (Appointment appointment : appointments) {
                writer.write(appointment.toTxt() + "\n");
            }
        } catch (IOException e){
            System.err.println("Error writing to Txt file: " + e.getMessage());
        }
    }

    public static List<Appointment> readAppointmentsFromTxt() {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_TXT_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                Appointment appointment = Appointment.fromTxt(line);
                appointments.add(appointment);
            }
        } catch (IOException e){
            System.err.println("Error reading Txt file: " + e.getMessage());
        }
        return appointments;
    }

    public static List<Appointment> getPatientAppointment(String patientID){
        List<Appointment> appointments = new ArrayList<>();
        List<Appointment> timeTable = readAppointmentsFromTxt();
        for (Appointment appointment : timeTable){
            if (appointment.getPatient().getHospitalID().equals(patientID)){
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    public static List<Appointment> getDoctorAppointment(String doctorID){
        List<Appointment> appointments = new ArrayList<>();
        List<Appointment> timeTable = readAppointmentsFromTxt();
        for (Appointment appointment : timeTable){
            if (appointment.getDoctor().getHospitalID().equals(doctorID)){
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    public static void main(String[] args){
        PrescribedMedication p1 = new PrescribedMedication("panadol", "2/day");
        PrescribedMedication p2 = new PrescribedMedication("meth", "5/day");

        AppointmentOutcome a = new AppointmentOutcome(LocalDate.of(2024, 10, 27), 
                                                "X-ray", p1, "drink more hot water");
        AppointmentOutcome b = new AppointmentOutcome(LocalDate.of(2023, 5, 8), 
                                                "consultation", p2, "sleep more");

        Doctor doctor = new Doctor("D001", "defaultStaffPassword", "John Smith", "Male", 45);
        Patient patient = new Patient("P1001", "pswrd", "Alice Brown", "Female", 24, 
                                    LocalDate.of(1990, 5, 14), 84320011, "alice.brown@example.com", "A+");
        String status = "unavailable";

        Appointment apt = new Appointment(doctor, LocalDate.now(), LocalTime.of(21, 55));
        apt.setAppointmentOutcome(a);
        apt.setPatient(patient);
        apt.setStatus(status);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(apt);

        writeAppointmentsToTxt(appointments);
    }
}