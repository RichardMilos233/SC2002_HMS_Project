package hms.storage;

import hms.appointment.AppointmentRemover;
import hms.users.Administrator;
import hms.users.Doctor;
import hms.users.Patient;
import hms.users.Pharmacist;
import hms.users.User;
import java.io.*;
import java.util.*;

/**
 * Provides services for handling CSV file operations. This class supports reading, writing,
 * and modifying CSV files associated with different user roles such as doctors, patients,
 * administrators, and pharmacists. It also manages user credentials through CSV files.
 */
public class CSVService implements IReadable, IWritable{
    private static final String DOCTOR_CSV_PATH = "./csv/doctors.csv";
    private static final String PATIENT_CSV_PATH = "./csv/patients.csv";
    private static final String ADMIN_CSV_PATH = "./csv/administrators.csv";
    private static final String PHARMACIST_CSV_PATH = "./csv/pharmacists.csv";
    private static final String CREDENTIAL_CSV_PATH = "./csv/credentials.csv";

    private static final String ROLE_HEADER = "hospitalID,name,gender,age,role";

    @Override
    // Method to read data from a CSV file
    /**
     * Reads data from a specified CSV file and returns it as a list of lists of strings.
     * Each inner list represents a row from the CSV file.
     *
     * @param filePath The path to the CSV file to be read.
     * @return A list containing each row of the CSV as a list of strings.
     */
    public List<List<String>> read(String filePath) {
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

    @Override
    // Method to write data to a CSV file
    /**
     * Writes data to a specified CSV file. Each inner list of strings represents a row to be written to the file.
     *
     * @param filePath The path to the CSV file to be written.
     * @param data The data to write to the file, represented as a list of rows, each a list of strings.
     */
    public void write(String filePath, List<List<String>> data) {
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
    /**
     * Modifies a specific cell in a CSV file based on provided row and column indices and a new value.
     *
     * @param filePath The path to the CSV file to be modified.
     * @param rowIndex The row index of the cell to modify.
     * @param colIndex The column index of the cell to modify.
     * @param newValue The new value to set in the specified cell.
     */
    public void modifyCsv(String filePath, int rowIndex, int colIndex, String newValue) {
        // Read the existing data
        List<List<String>> data = read(filePath);

        // Modify the specified cell if the row and column indices are valid
        if (rowIndex >= 0 && rowIndex < data.size() && colIndex >= 0 && colIndex < data.get(rowIndex).size()) {
            data.get(rowIndex).set(colIndex, newValue);
        } else {
            System.err.println("Invalid row or column index.");
            return;
        }

        // Write the modified data back to the file
        write(filePath, data);
    }

    /**
     * Updates the record of a user in the system by identifying their role and executing role-specific
     * write operations to the appropriate CSV file. If the user is a doctor, pharmacist, or administrator,
     * the method locates their existing record by hospital ID, replaces the record with updated data,
     * and refreshes the list of all users of that type in the system.
     * 
     * If no valid role is identified (i.e., the role does not start with 'd' for doctors, 'p' for pharmacists,
     * or 'a' for administrators), an error message is displayed.
     *
     * After updating the specific user type list, the global user list cache is also refreshed to reflect
     * these changes.
     *
     * @param user The user instance with updated information that needs to be written to the CSV.
     *             The method handles type casting based on the user's role to call the correct write method.
     */
    public static void replaceUser(User user){
        String role = user.getRole();
        if (role.startsWith("d")){
            writeDoctor((Doctor) user, findDoctor(user.getHospitalID()));
            Doctor.updateDoctors();
        } else if (role.startsWith("p")){
            writePharmacist((Pharmacist) user, findPharmacist(user.getHospitalID()));
            Pharmacist.updatePharmacists();
        } else if (role.startsWith("a")){
            writeAdmin((Administrator) user, findAdmin(user.getHospitalID()));
            Administrator.updateAdministrators();
        } else{
            System.out.println("Error");
        }
        User.updateUsers();
    }

    /**
     * Retrieves a cryptographic salt associated with a user ID from the credentials CSV.
     *
     * @param ID The hospital ID of the user whose salt is to be retrieved.
     * @return The salt as a string, or an empty string if the user is not found.
     */
    public String getSalt(String ID){
        String salt;
        List<List<String>> accountList = read(CREDENTIAL_CSV_PATH);
        for (int i = 0; i < accountList.size(); i++){
            if (accountList.get(i).get(0).equals(ID)){
                salt = accountList.get(i).get(2);
                return salt;
            }
        }
        System.out.println("User not found");
        return "";
    }

    // Write a list of doctors to CSV
    /**
     * Writes a list of Doctor objects to a CSV file, overwriting any existing data.
     * This method serializes Doctor instances into CSV format according to predefined schemas.
     *
     * @param doctors A list of Doctor objects to be written to the CSV file.
     */
    public static void writeDoctorsToCSV(List<Doctor> doctors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTOR_CSV_PATH))) {
            writer.write(ROLE_HEADER + "\n"); // Header line
            for (Doctor doctor : doctors) {
                writer.write(doctor.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Read a list of doctors from CSV
    /**
     * Reads a list of Doctor objects from a CSV file, deserializing each row into a Doctor instance.
     * Assumes the first row of the CSV file is a header row.
     *
     * @return A list of Doctor objects read from the file.
     */
    public static List<Doctor> readDoctorsFromCSV(){
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTOR_CSV_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                Doctor doctor = Doctor.fromCSV(line); // call methods this way because fromCSV cannot be static due to some technical issues
                doctors.add(doctor); 
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return doctors;
    }

    // Write a list of Patient objects to CSV
    /**
     * Writes a list of Patient objects to a CSV file, including a header row followed by rows for each Patient.
     *
     * @param patients A list of Patient objects to be serialized and written to the CSV file.
     */
    public static void writePatientsToCSV(List<Patient> patients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_CSV_PATH))) {
            // Write the header line
            writer.write(ROLE_HEADER+ ",dateOfBirth,bloodType,email,contactNumber\n");
            for (Patient patient : patients) {
                writer.write(patient.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Read a list of Patient objects from CSV
    /**
     * Reads a list of Patient objects from a CSV file, deserializing each row into a Patient instance.
     *
     * @return A list of Patient objects read from the file.
     */
    public static List<Patient> readPatientsFromCSV() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_CSV_PATH))) {
            reader.readLine(); // Skip header line
            String line;
            while ((line = reader.readLine()) != null) {
                Patient patient = Patient.fromCSV(line); // Use Patient's fromCSV method to create a Patient object
                patients.add(patient);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return patients;
    }

    // Write a list of admins to CSV
    /**
     * Writes a list of Administrator objects to a CSV file specified by the ADMIN_CSV_PATH. This method
     * starts by writing a header line followed by one line per Administrator in CSV format as returned by
     * the Administrator's toCSV method.
     *
     * @param admins A list of Administrator objects to be serialized and written to the CSV file.
     *               Each Administrator is converted to a CSV string format which includes their hospital ID,
     *               name, gender, age, and role.
     */
    public static void writeAdminsToCSV(List<Administrator> admins) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_CSV_PATH))) {
            writer.write(ROLE_HEADER + "\n"); // Header line
            for (Administrator admin : admins) {
                writer.write(admin.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Read a list of admins from CSV
    /**
     * Reads a list of Administrator objects from a CSV file. Assumes the first line of the CSV contains headers
     * and skips it. Each subsequent line is processed to create an Administrator object using the Administrator.fromCSV method.
     *
     * @return A list of Administrator objects populated from the CSV file.
     */
    public static List<Administrator> readAdminsFromCSV(){
        List<Administrator> admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_CSV_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                Administrator admin = Administrator.fromCSV(line);
                admins.add(admin);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return admins;
    }

    // Write a list of pharmacists to CSV
    /**
     * Writes a list of Pharmacist objects to a CSV file. Starts by writing a header line, followed by one line
     * per Pharmacist in CSV format as returned by the Pharmacist's toCSV method.
     *
     * @param pharmacists A list of Pharmacist objects to be serialized and written to the CSV file.
     */
    public static void writePharmacistsToCSV(List<Pharmacist> pharmacists) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PHARMACIST_CSV_PATH))) {
            writer.write(ROLE_HEADER + "\n"); // Header line
            for (Pharmacist pharmacist : pharmacists) {
                writer.write(pharmacist.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Read a list of pharmacists from CSV
    /**
     * Reads a list of Pharmacist objects from a CSV file, skipping the header line. Each line is processed to create
     * a Pharmacist object using the Pharmacist.fromCSV method, which should parse the CSV line into appropriate fields.
     *
     * @return A list of Pharmacist objects populated from the CSV file.
     */
    public static List<Pharmacist> readPharmacistsFromCSV(){
        List<Pharmacist> pharmacists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PHARMACIST_CSV_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                Pharmacist pharmacist = Pharmacist.fromCSV(line);
                pharmacists.add(pharmacist);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return pharmacists;
    }

    /**
     * Compiles a list of all users in the system across various roles by reading their details from respective CSV files.
     * It aggregates lists of patients, doctors, administrators, and pharmacists into a single list of users.
     *
     * @return A comprehensive list of all users including Patients, Doctors, Administrators, and Pharmacists.
     */

    public static List<User> readUsersFromCSV(){
        List<User> users = new ArrayList<>();
        List<Patient> patients = readPatientsFromCSV();
        List<Doctor> doctors = readDoctorsFromCSV();
        List<Administrator> admins = readAdminsFromCSV();
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();

        users.addAll(patients);
        users.addAll(doctors);
        users.addAll(admins);
        users.addAll(pharmacists);

        return users;
    }

    /**
     * Replaces a patient's information in the CSV file by first finding the patient's index
     * using their hospital ID and then writing the updated patient data back to the CSV.
     *
     * @param patient The patient object containing updated data to be stored.
     */
    public static void replacePatient(Patient patient){
        int index = findPatient(patient.getHospitalID());
        writePatient(patient, index);
    }

    /**
     * Searches for a patient by their hospital ID in the CSV file and returns their index.
     * This method reads the patient CSV to find a matching hospital ID and returns the index
     * of the found patient or -1 if the patient is not found.
     *
     * @param patientID The hospital ID of the patient to find.
     * @return The index of the patient in the list if found, otherwise -1.
     */
    public static int findPatient(String patientID){
        int i;
        List<Patient> patients = readPatientsFromCSV();
        for (i = 0; i < patients.size(); i++){
            if (patients.get(i).getHospitalID().equals(patientID)){
                return i;
            }
        }
        System.out.println("patient not found");
        return -1;
    }

    /**
     * Writes a single patient's updated information to the CSV file at a specified index.
     * If the index is out of range, a warning message is displayed and the operation is aborted.
     *
     * @param patient The patient object containing updated data.
     * @param index The index in the CSV file where the patient data should be updated.
     */
    public static void writePatient(Patient patient, int index){
        List<Patient> patients = readPatientsFromCSV();
        int size = patients.size();
        if (index < 0 || index >= size){
            System.out.println("index out of range");
            return;
        }
        patients.set(index, patient);
        writePatientsToCSV(patients);
    }

    /**
     * Replaces a doctor's information in a CSV file. This method finds a doctor by ID, replaces the doctor's information
     * with updated data, and writes the updated list back to the CSV file.
     *
     * @param doctor The Doctor object containing updated information to be written to the CSV.
     */
    public static void replaceDoctor(Doctor doctor){
        int index = findDoctor(doctor.getHospitalID());
        writeDoctor(doctor, index);
    }

    /**
     * Removes a doctor's record from the CSV file by swapping it with the last record and then deleting the last record.
     * This method ensures that the CSV file is compacted after removal to avoid any gaps in the data.
     *
     * @param doctor The doctor object to be removed from the CSV file.
     * calls Appointment remover to ensure all pending and future appointments are also removed
     */ 
    public static void removeDoctor(Doctor doctor){
        String hospitalID = doctor.getHospitalID();
        int index = findDoctorIndex(hospitalID);
        if (index == -1){
            System.out.println("Error removing doctor.");
            return;
        }
        Doctor lastDoc = Doctor.getLastDoctor();
        writeDoctor(lastDoc, index);
        removeLastDoctor();
        Doctor.updateDoctors();
        AppointmentRemover.removeIncompleteAppointments(hospitalID);
    }

    /**
     * Finds the index of a doctor by their hospital ID in the CSV file.
     *
     * @param doctorID The hospital ID of the doctor to find.
     * @return The index of the doctor if found, otherwise -1.
     */
    public static int findDoctor(String doctorID){
        int i;
        List<Doctor> doctors = readDoctorsFromCSV();
        for (i = 0; i < doctors.size(); i++){
            if (doctors.get(i).getHospitalID().equals(doctorID)){
                return i;
            }
        }
        System.out.println("doctor not found");
        return -1;
    }

    

    /**
     * Reads a list of Doctor objects from a CSV file
     * Assumes the first row of the CSV file is a header row.
     *
     * @return the index of the doctor
     */
    public static int findDoctorIndex(String doctorID){
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTOR_CSV_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(doctorID)){
                    return index;
                }
                ++index;
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Removes the last doctor from the CSV file, effectively deleting the record.
     */
    public static void removeLastDoctor(){
        List<Doctor> doctors = readDoctorsFromCSV();
        doctors.remove(doctors.size() - 1);
        writeDoctorsToCSV(doctors);
    }

    /**
     * Writes a doctor's data at a specific index in the CSV file, used primarily to replace data at a given position.
     *
     * @param doctor The doctor object to write to the CSV file.
     * @param index The index at which to write the doctor's data.
     */
    public static void writeDoctor(Doctor doctor, int index){
        List<Doctor> doctors = readDoctorsFromCSV();
        int size = doctors.size();
        if (index < 0 || index >= size){
            System.out.println("index out of range");
            return;
        }
        doctors.set(index, doctor);
        writeDoctorsToCSV(doctors);
    }

    /**
     * Replaces an administrator's data in the CSV file. This function finds the existing record by hospital ID,
     * then updates it with the provided administrator data if found.
     *
     * @param admin The administrator object containing updated data.
     */
    public static void replaceAdmin(Administrator admin){
        int index = findAdmin(admin.getHospitalID());
        writeAdmin(admin, index);
    }

    /**
     * Searches for an administrator in the CSV file by their hospital ID and returns the index position in the list.
     * Outputs a message if the administrator is not found.
     *
     * @param adminID The hospital ID of the administrator to find.
     * @return The index of the administrator if found, otherwise -1 indicating not found.
     */
    public static int findAdmin(String adminID){
        int i;
        List<Administrator> admins = readAdminsFromCSV();
        for (i = 0; i < admins.size(); i++){
            if (admins.get(i).getHospitalID().equals(adminID)){
                return i;
            }
        }
        System.out.println("admin not found");
        return -1;
    }

    /**
     * Writes the administrator's data to a specific index in the CSV file. If the index is out of range, an error message is displayed.
     * This method is used primarily to update existing records with new data.
     *
     * @param admin The administrator whose data is to be written to the CSV.
     * @param index The index in the list where the data should be updated.
     */
    public static void writeAdmin(Administrator admin, int index){
        List<Administrator> admins = readAdminsFromCSV();
        int size = admins.size();
        if (index < 0 || index >= size){
            System.out.println("index out of range");
            return;
        }
        admins.set(index, admin);
        writeAdminsToCSV(admins);
    }

    /**
     * Removes a pharmacist's record from the CSV file by replacing it with the last record and then deleting the last record.
     * This method ensures that the CSV file is compacted after removal to avoid any gaps in the data.
     *
     * @param pharmacist The pharmacist object to be removed from the CSV file.
     */
    public static void removePharmacist(Pharmacist pharmacist){
        int index = findDoctor(pharmacist.getHospitalID());
        Pharmacist lastPharm = getLastPharmacist();
        writePharmacist(lastPharm, index);
        removeLastPharmacist();
        Pharmacist.updatePharmacists();
    }

    /**
     * Retrieves the last pharmacist from the CSV file.
     *
     * @return The last pharmacist in the list, or null if the list is empty.
     */
    public static Pharmacist getLastPharmacist(){
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();
        return (pharmacists.get(pharmacists.size() - 1));
    }

    /**
     * Removes the last pharmacist from the CSV file and updates the file accordingly.
     */
    public static void removeLastPharmacist(){
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();
        pharmacists.remove(pharmacists.size() - 1);
        writePharmacistsToCSV(pharmacists);
    }

    /**
     * Replaces a pharmacist's information in the CSV file. Finds the pharmacist's index by hospital ID
     * and writes the updated data to that position in the CSV.
     *
     * @param pharmacist The pharmacist object containing updated information.
     */
    public static void replacePharmacist(Pharmacist pharmacist){
        int index = findPharmacist(pharmacist.getHospitalID());
        writePharmacist(pharmacist, index);
    }

    /**
     * Searches for a pharmacist by their hospital ID in the CSV file and returns their index.
     *
     * @param pharmacistID The hospital ID of the pharmacist to find.
     * @return The index of the pharmacist if found, otherwise -1.
     */
    public static int findPharmacist(String pharmacistID){
        int i;
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();
        for (i = 0; i < pharmacists.size(); i++){
            if (pharmacists.get(i).getHospitalID().equals(pharmacistID)){
                return i;
            }
        }
        System.out.println("pharmacist not found");
        return -1;
    }

    /**
     * Writes a pharmacist's updated information to a specific index in the CSV file.
     * If the index is out of range, an error message is displayed and the operation is aborted.
     *
     * @param pharmacist The pharmacist whose information is to be updated.
     * @param index The index at which to update the information.
     */
    public static void writePharmacist(Pharmacist pharmacist, int index){
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();
        int size = pharmacists.size();
        if (index < 0 || index >= size){
            System.out.println("index out of range");
            return;
        }
        pharmacists.set(index, pharmacist);
        writePharmacistsToCSV(pharmacists);
    }

    /**
     * Changes the password hash for a specific user identified by their hospital ID in the credentials CSV file.
     * If the hospital ID is found, it updates the hash value and writes the changes back to the CSV file.
     *
     * @param hospitalID The hospital ID of the user whose password needs to be changed.
     * @param newHash The new hash value for the user's password.
     */
    public void changePassword(String hospitalID, int newHash){
        List<List<String>> credentials = read(CREDENTIAL_CSV_PATH);
        for (int i = 1; i < credentials.size(); i++) {
            if (hospitalID.equals(credentials.get(i).get(0))) { //hospitalId match
                credentials.get(i).set(1, Integer.toString(newHash));
                write(CREDENTIAL_CSV_PATH, credentials);
                System.out.println("Password sucessfully reset");
                return;
            }
        }
        System.out.println("Unable to reset password");
    }

    /**
     * Adds a new patient record to the existing list of patients in the CSV file.
     *
     * @param patient The Patient object to be added to the patient CSV file.
     */
    public static void addPatient(Patient patient){
        List<Patient> patients = readPatientsFromCSV();
        patients.add(patient);
        writePatientsToCSV(patients);
    }

    /**
     * Adds a new doctor record to the existing list of doctors in the CSV file and updates the internal list of doctors.
     *
     * @param doctor The Doctor object to be added to the doctor CSV file.
     */
    public static void addDoctor(Doctor doctor){
        List<Doctor> doctors = readDoctorsFromCSV();
        doctors.add(doctor);
        writeDoctorsToCSV(doctors);
        Doctor.updateDoctors();
    }

    /**
     * Adds a new administrator record to the existing list of administrators in the CSV file.
     *
     * @param admin The Administrator object to be added to the administrator CSV file.
     */
    public static void addAdmin(Administrator admin){
        List<Administrator> admins = readAdminsFromCSV();
        admins.add(admin);
        writeAdminsToCSV(admins);
    }

    /**
     * Adds a new pharmacist record to the existing list of pharmacists in the CSV file and updates the internal list of pharmacists.
     *
     * @param pharmacist The Pharmacist object to be added to the pharmacist CSV file.
     */
    public static void addPharmacist(Pharmacist pharmacist){
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();
        pharmacists.add(pharmacist);
        writePharmacistsToCSV(pharmacists);
        Pharmacist.updatePharmacists();
    }

    /**
     * Adds a new credential record to the credentials CSV file. This record includes the user's ID,
     * hashed password, and a cryptographic salt.
     *
     * @param id The unique identifier for the user whose credentials are being added.
     * @param hashValue The hashed value of the user's password.
     * @param salt The cryptographic salt used in the hashing process.
     */
    public void addCredential(String id, int hashValue, String salt){
        //  add the credential of a role being created
        List<List<String>> credentials = read(CREDENTIAL_CSV_PATH);
        credentials.add(List.of(id, Integer.toString(hashValue), salt));
        write(CREDENTIAL_CSV_PATH, credentials);
    }

    /**
     * Searches for and retrieves the hashed password and salt for a specific user's credentials based on their ID.
     * Returns an array containing the hash and salt, or null values if the credentials are not found.
     *
     * @param id The user's ID for which to find the credentials.
     * @return An array where the first element is the hashed password and the second element is the salt.
     */
    public String[] findCredential(String id){
        String[] hashSalt = new String[2];
        List<List<String>> credentials = read(CREDENTIAL_CSV_PATH);
        for (int i = 0; i<credentials.size(); i++){
            if (credentials.get(i).get(0).equals(id)){
                credentials.get(i).get(1);
                credentials.get(i).get(2);
                hashSalt[0] = credentials.get(i).get(1);
                hashSalt[1] = credentials.get(i).get(2);
            }
        }
        return hashSalt;
    }

    /**
     * Removes a user's credential record from the credentials CSV file based on their ID.
     * This method finds the credential by ID and removes it from the list before updating the CSV file.
     *
     * @param id The ID of the user whose credential needs to be removed.
     */
    public void removeCredention(String id){
        List<List<String>> credentials = read(CREDENTIAL_CSV_PATH);
        for (int i = 0; i<credentials.size(); i++){
            if (credentials.get(i).get(0).equals(id)){
                credentials.remove(i);
            }
        }
        write(CREDENTIAL_CSV_PATH, credentials);
    }

    /**
     * Main method to initialize the application with default user credentials.
     * Reads patient and staff information from CSV files, sets default passwords,
     * and writes the credentials to a separate CSV file.
     */
    public static void main(String[] args) {
        String patientPath = "csv/Patient_List.csv";
        String staffPath = "csv/Staff_List.csv";
        String credentialPath = "csv/credentials.csv";

        CSVService csvService = new CSVService();

        List<List<String>> credentials = new ArrayList<>();
        credentials.add(List.of("hospitalId", "password"));

        List<List<String>> data = csvService.read(patientPath);
        for (List<String> row : data.subList(1, data.size())) {
            credentials.add(List.of(row.get(0), "defaultPatientPassword"));
        }

        data = csvService.read(staffPath);
        for (List<String> row : data.subList(1, data.size())){
            credentials.add(List.of(row.get(0), "defaultStaffPassword"));
        }

        // Writing to credentials.csv
        csvService.write(credentialPath, credentials);
    }
}
