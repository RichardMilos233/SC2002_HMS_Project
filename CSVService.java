import java.io.*;
import java.util.*;

import javax.print.Doc;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class CSVService {
    private static final String DOCTOR_CSV_PATH = "./csv/doctors.csv";
    private static final String PATIENT_CSV_PATH = "./csv/patients.csv";

    // Method to read data from a CSV file
    public static List<List<String>> readCsv(String filePath) {
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

    // Method to write data to a CSV file
    public static void writeCsv(String filePath, List<List<String>> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (List<String> row : data) {
                String line = String.join(",", row);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Method to modify a specific cell in the CSV data
    public static void modifyCsv(String filePath, int rowIndex, int colIndex, String newValue) {
        // Read the existing data
        List<List<String>> data = readCsv(filePath);

        // Modify the specified cell if the row and column indices are valid
        if (rowIndex >= 0 && rowIndex < data.size() && colIndex >= 0 && colIndex < data.get(rowIndex).size()) {
            data.get(rowIndex).set(colIndex, newValue);
        } else {
            System.err.println("Invalid row or column index.");
            return;
        }

        // Write the modified data back to the file
        writeCsv(filePath, data);
    }

    // Write a list of doctors to CSV
    public static void writeDoctorsToCSV(List<Doctor> doctors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTOR_CSV_PATH))) {
            writer.write("hospitalID,password,name,gender,age,role\n"); // Header line
            for (Doctor doctor : doctors) {
                writer.write(doctor.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Read a list of doctors from CSV
    public static List<Doctor> readDoctorsFromCSV(){
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTOR_CSV_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                Doctor doctor = new Doctor().fromCSV(line); // Use Doctor's fromCSV to cast correctly
                doctors.add(doctor);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return doctors;
    }

    // Write a list of Patient objects to CSV
    public static void writePatientsToCSV(List<Patient> patients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_CSV_PATH))) {
            // Write the header line
            writer.write("hospitalID,password,name,gender,age,role,birth,bloodType,email,contactNumber\n");
            for (Patient patient : patients) {
                writer.write(patient.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Read a list of Patient objects from CSV
    public static List<Patient> readPatientsFromCSV() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_CSV_PATH))) {
            reader.readLine(); // Skip header line
            String line;
            while ((line = reader.readLine()) != null) {
                Patient patient = new Patient().fromCSV(line); // Use Patient's fromCSV method to create a Patient object
                patients.add(patient);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return patients;
    }

    public static void main(String[] args) {
        String patientPath = "csv/Patient_List.csv";
        String staffPath = "csv/Staff_List.csv";
        String credentialPath = "csv/credentials.csv";

        List<List<String>> credentials = new ArrayList<>();
        credentials.add(List.of("hospitalId", "password"));

        // Reading from a CSV file
        List<List<String>> data = CSVService.readCsv(patientPath);
        for (List<String> row : data.subList(1, data.size())) {
            credentials.add(List.of(row.get(0), "defaultPatientPassword"));
            // System.out.println(credentials);
        }

        data = CSVService.readCsv(staffPath);
        for (List<String> row : data.subList(1, data.size())){
            credentials.add(List.of(row.get(0), "defaultStaffPassword"));
        }

        // Writing to a CSV file
        CSVService.writeCsv(credentialPath, credentials);

        // List<List<String>> data = new ArrayList<>();
        // data.add(List.of("Staff ID", "Name", "Role", "Gender", "Age"));
        // data.add(List.of("D001", "John Smith", "Doctor", "Male", "45"));
        // data.add(List.of("D002", "Emily Clarke", "Doctor",	"Female", "38"));
        // data.add(List.of("P001",	"Mark Lee",	"Pharmacist",	"Male",	"29"));
        // data.add(List.of("A001",	"Sarah Lee",	"Administrator",	"Female",	"40"));

        // ExcelService.writeCsv(staffPath, data);

        
    }
}
