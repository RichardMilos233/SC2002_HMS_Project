package hms.storage;

import hms.appointment.Appointment;
import hms.appointment.AppointmentOutcome;
import hms.medicalrecords.PrescribedMedication;
import hms.users.Doctor;
import hms.users.Patient;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
/**
 * Handles all operations related to reading from and writing to the appointment text file.
 * This includes CRUD operations for appointments based on unique identifiers such as doctor ID, patient ID, date, and time.
 */
public class TextService implements IReadable, IWritable {
    private static final String APPOINTMENT_TXT_PATH = "./txt/appointments.txt";

    /**
     * Reads data from a specified file path and returns it as a list of list of strings.
     * Each sublist represents a line in the file, and each string within the sublist represents a field in that line.
     *
     * @param filePath The path to the file to be read.
     * @return A list of lists, where each inner list represents a row of the file.
     */
    @Override
    public List<List<String>> read(String filePath){
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value.trim());
                }
                data.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return data;
    }

    /**
     * Writes data from a list of list of strings to a specified file path.
     * Each sublist is written as a line in the file, with each string in the sublist separated by a comma.
     *
     * @param filePath The path to the file where the data should be written.
     * @param data The list of lists representing the data to write, where each inner list represents a row to be written to the file.
     */
    @Override
    public void write(String filePath, List<List<String>> data){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (List<String> row : data) {
                String line = String.join(",", row);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to txt file: " + e.getMessage());
        }
    }

    /**
     * Writes a list of appointments to a text file.
     *
     * @param appointments The list of Appointment objects to write to the file.
     */
    public static void writeAppointmentsToTxt(List<Appointment> appointments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_TXT_PATH))) {
            writer.write("patientID,doctorID,status,date,time,date|type|medicationName\\dosage\\totalPrescribed|consultationNotes|diagnosis|resolved\n");
            for (Appointment appointment : appointments) {
                writer.write(appointment.toTxt() + "\n");
            }
        } catch (IOException e){
            System.err.println("Error writing to Txt file: " + e.getMessage());
        }
    }

    /**
     * Reads appointments from a text file and returns them as a list of Appointment objects.
     *
     * @return A list of Appointment objects read from the file.
     */
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

    /**
     * Retrieves all appointments associated with a specific patient ID.
     *
     * @param patientID The patient ID to filter appointments by.
     * @return A list of Appointment objects matching the given patient ID.
     */
    public static List<Appointment> getPatientAppointment(String patientID){
        List<Appointment> appointments = new ArrayList<>();
        List<Appointment> timeTable = readAppointmentsFromTxt();
        for (Appointment appointment : timeTable){
            if (appointment.getPatientID().equals(patientID)){
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    /**
     * Retrieves all appointments associated with a specific doctor ID.
     *
     * @param doctorID The doctor ID to filter appointments by.
     * @return A list of Appointment objects matching the given doctor ID.
     */
    public static List<Appointment> getDoctorAppointment(String doctorID){
        List<Appointment> appointments = new ArrayList<>();
        List<Appointment> timeTable = readAppointmentsFromTxt();
        for (Appointment appointment : timeTable){
            if (appointment.getDoctorID().equals(doctorID)){
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    /**
     * Replaces an existing appointment in the text file with new data.
     *
     * @param apt_new The updated Appointment object to replace the old one.
     */
    public static void replaceAppointment(Appointment apt_new){ // this find the old apt in txt (by doc id, date, time), update its info by  refreshing all of its info
        int index = findAppointment(apt_new);
        writeAppointment(apt_new, index);
    }

    /**
     * Finds the index of a specific appointment in the text file based on multiple attributes.
     *
     * @param apt_new The Appointment object to find in the text file.
     * @return The index of the appointment in the list, or -1 if not found.
     */
    public static int findAppointment(Appointment apt_new){
        int i;  // # of row of appointment in txt
        Appointment apt_old;
        String doctorID = apt_new.getDoctor().getHospitalID();
        LocalDate date = apt_new.getDate();
        LocalTime time = apt_new.getTime();
        List<Appointment> appointments = readAppointmentsFromTxt();
        for (i = 0; i < appointments.size(); i++){
            apt_old = appointments.get(i);
            if (doctorID.equals(apt_old.getDoctor().getHospitalID()) &&
                date.equals(apt_old.getDate()) &&
                time.equals(apt_old.getTime())){
                return i;
            }
        }
        System.out.println("Appointment not found");
        return -1;  // -1 indicates not found
    }

    /**
     * Writes a specific Appointment object to the text file at a specified index.
     *
     * @param apt_new The new Appointment object to write to the file.
     * @param index The index at which to write the appointment.
     */
    public static void writeAppointment(Appointment apt_new, int index){
        List<Appointment> appointments = readAppointmentsFromTxt();
        int size = appointments.size();
        if (index < 0 || index >= size){
            System.out.println("index out of range");
            return;
        }
        appointments.set(index, apt_new);   // replace an existing apt
        writeAppointmentsToTxt(appointments);
        }

    /**
     * Appends a new Appointment object to the end of the text file.
     *
     * @param apt The new Appointment object to add to the file.
     */
    public static void appendAppointment(Appointment apt){
        List<Appointment> appointments = readAppointmentsFromTxt();
        appointments.add(apt);
        writeAppointmentsToTxt(appointments);
    }

    /**
     * Clears all appointment data from the text file while keeping the header line intact.
     */
    public static void clearTxt() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_TXT_PATH))) {
            // Write the header back into the file
            writer.write("patientID,doctorID,status,date,time,date|type|medicationName\\dosage\\totalPrescribed|consultationNotes|diagnosis|resolved\n");
        } catch (IOException e) {
            System.err.println("Error clearing the text file: " + e.getMessage());
        }
    }

    /**
     * Main method for testing purposes, simulating file writing and reading operations.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args){
        PrescribedMedication p1 = new PrescribedMedication("panadol", "2/day", 30);
        PrescribedMedication p2 = new PrescribedMedication("meth", "5/day", 10);

        AppointmentOutcome a = new AppointmentOutcome(LocalDate.of(2024, 10, 27),
                                                "X-ray", p1, "drink more hot water", "Diabetes", false);
        AppointmentOutcome b = new AppointmentOutcome(LocalDate.of(2023, 5, 8),
                                                "consultation", p2, "sleep more", "possible depression",false);

        Doctor doctor = new Doctor("D001", "John Smith", "Male", 45);
        Patient patient = new Patient("P1001", "Alice Brown", "Female", 24,
                                    LocalDate.of(1990, 5, 14), 84320011, "alice.brown@example.com", "A+");
        String status = "cancelled";

        Appointment apt = new Appointment(doctor.hospitalID, LocalDate.now(), LocalTime.of(21, 55));
        apt.setAppointmentOutcome(a);
        apt.setPatientID(patient.hospitalID);
        apt.setStatus(status);

        Appointment bpt = new Appointment(doctor.hospitalID, LocalDate.now(), LocalTime.of(21, 55));
        bpt.setAppointmentOutcome(b);
        bpt.setPatientID(patient.hospitalID);
        bpt.setStatus(status);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(apt);
        appointments.add(bpt);

        writeAppointmentsToTxt(appointments);
        appointments = null;
        appointments = readAppointmentsFromTxt();
        for (Appointment appointment : appointments){
            appointment.displayAppointment();
        }
    }
}