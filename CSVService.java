import java.io.*;
import java.util.*;

public class CSVService implements IReadable, IWritable{
    private static final String DOCTOR_CSV_PATH = "./csv/doctors.csv";
    private static final String PATIENT_CSV_PATH = "./csv/patients.csv";
    private static final String ADMIN_CSV_PATH = "./csv/administrators.csv";
    private static final String PHARMACIST_CSV_PATH = "./csv/pharmacists.csv";
    private static final String CREDENTIAL_CSV_PATH = "./csv/credentials.csv";

    private static final String ROLE_HEADER = "hospitalID,name,gender,age,role";

    @Override
    // Method to read data from a CSV file
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

    protected String getSalt(String ID){
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

    public static void replacePatient(Patient patient){
        int index = findPatient(patient.getHospitalID());
        writePatient(patient, index);
    }

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

    public static void replaceDoctor(Doctor doctor){
        int index = findDoctor(doctor.getHospitalID());
        writeDoctor(doctor, index);
    }

    public static void removeDoctor(Doctor doctor){
        int index = findDoctor(doctor.getHospitalID());
        Doctor lastDoc = getLastDoctor();
        writeDoctor(lastDoc, index);
        removeLastDoctor();
        Doctor.updateDoctors();
    }

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

    public static Doctor getLastDoctor(){
        List<Doctor> doctors = readDoctorsFromCSV();
        return doctors.get(doctors.size() - 1);
    }

    public static void removeLastDoctor(){
        List<Doctor> doctors = readDoctorsFromCSV();
        doctors.remove(doctors.size() - 1);
        writeDoctorsToCSV(doctors);
    }

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

    public static void replaceAdmin(Administrator admin){
        int index = findAdmin(admin.getHospitalID());
        writeAdmin(admin, index);
    }

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

    public static void removePharmacist(Pharmacist pharmacist){
        int index = findDoctor(pharmacist.getHospitalID());
        Pharmacist lastPharm = getLastPharmacist();
        writePharmacist(lastPharm, index);
        removeLastPharmacist();
        Pharmacist.updatePharmacists();
    }

    public static Pharmacist getLastPharmacist(){
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();
        return (pharmacists.get(pharmacists.size() - 1));
    }

    public static void removeLastPharmacist(){
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();
        pharmacists.remove(pharmacists.size() - 1);
        writePharmacistsToCSV(pharmacists);
    }

    public static void replacePharmacist(Pharmacist pharmacist){
        int index = findPharmacist(pharmacist.getHospitalID());
        writePharmacist(pharmacist, index);
    }

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

    public void changePassword(String hospitalID, int newHash){
        List<List<String>> credentials = read(CREDENTIAL_CSV_PATH);
        for (int i = 1; i < credentials.size(); i++) {
            if (hospitalID.equals(credentials.get(i).get(0))) { //hospitalId match
                credentials.get(i).set(1, Integer.toString(newHash));
                write(CREDENTIAL_CSV_PATH, credentials);
                System.out.println("password sucessfully reset");
                return;
            }
        }
        System.out.println("unable to reset password");
    }

    public static void addPatient(Patient patient){
        List<Patient> patients = readPatientsFromCSV();
        patients.add(patient);
        writePatientsToCSV(patients);
    }

    public static void addDoctor(Doctor doctor){
        List<Doctor> doctors = readDoctorsFromCSV();
        doctors.add(doctor);
        writeDoctorsToCSV(doctors);
        Doctor.updateDoctors();
    }

    public static void addAdmin(Administrator admin){
        List<Administrator> admins = readAdminsFromCSV();
        admins.add(admin);
        writeAdminsToCSV(admins);
    }

    public static void addPharmacist(Pharmacist pharmacist){
        List<Pharmacist> pharmacists = readPharmacistsFromCSV();
        pharmacists.add(pharmacist);
        writePharmacistsToCSV(pharmacists);
        Pharmacist.updatePharmacists();
    }

    public void addCredential(String id, int hashValue, String salt){
        //  add the credential of a role being created
        List<List<String>> credentials = read(CREDENTIAL_CSV_PATH);
        credentials.add(List.of(id, Integer.toString(hashValue), salt));
        write(CREDENTIAL_CSV_PATH, credentials);
    }
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

    public void removeCredention(String id){
        List<List<String>> credentials = read(CREDENTIAL_CSV_PATH);
        for (int i = 0; i<credentials.size(); i++){
            if (credentials.get(i).get(0).equals(id)){
                credentials.remove(i);
            }
        }
        write(CREDENTIAL_CSV_PATH, credentials);
    }

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
