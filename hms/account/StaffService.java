package hms.account;

import hms.users.*;
import hms.staff.*;
import hms.storage.CSVService;
import hms.utils.cryptography.Hasher;
import hms.utils.cryptography.Salter;
import hms.utils.cryptography.SimpleAdditiveHash;
import java.util.*;
/**
 * Provides services for managing and displaying staff information within a healthcare system. 
 * This class includes methods for listing staff by various categories, updating staff information,
 * and handling unique identification for new staff members.
 */
public class StaffService {
     /**
     * Displays a list of all staff members filtered by specific categories such as role, name, ID, age, or gender.
     * 
     * @param byCategory An integer representing the filter type: 
     *                   1 - by role, 2 - by name, 3 - by ID, 4 - by age, 5 - by gender.
     */
    public static void displayStaffList(int byCategory){
        // role = 1, name = 2, ID= 3, age=4, gender=5
        //Display a list of staff filtered by role, gender, age, etc
        switch (byCategory) {
            case 1:
                System.out.println("--------------Doctors----------------------");
                System.out.format("ID     Name                 Gender     Age\n");
                System.out.println("-------------------------------------------");
                displayDoctorList(byCategory);
                System.out.println("--------------Pharmacists------------------");
                System.out.format("ID     Name                 Gender     Age\n");
                System.out.println("-------------------------------------------");
                displayPharmacistList(byCategory);
                System.out.println("--------------Admins-----------------------");
                System.out.format("ID     Name                 Gender     Age\n");
                System.out.println("-------------------------------------------");
                displayAdminList(byCategory);
                break;
            case 2:
                {
                    System.out.format("ID     Role          Name                 Gender     Age\n");
                    System.out.println("-----------------------------------------------------------");
                    Collections.sort(User.getUsers(), Comparator.comparing(User::getName));
                    User e;
                    for (int i = 0; i<User.getUsers().size(); i++){
                        e = User.getUsers().get(i);
                        if (!e.getRole().equals("patient")){
                            e.displayTableFormatRole();
                            //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                        }
                    }       System.out.println("-----------------------------------------------------------");
                    break;
                }
            case 3:
                {
                    System.out.format("ID     Role          Name                 Gender     Age\n");
                    System.out.println("-----------------------------------------------------------");
                    Collections.sort(User.getUsers(), Comparator.comparing(User::getHospitalID));
                    User e;
                    for (int i = 0; i<User.getUsers().size(); i++){
                        e = User.getUsers().get(i);
                        if (!e.getRole().equals("patient")){
                            e.displayTableFormatRole();
                            //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                        }
                    }       System.out.println("-----------------------------------------------------------");
                    break;
                }
            case 4:
                {
                    System.out.format("ID     Role          Name                 Gender     Age\n");
                    System.out.println("-----------------------------------------------------------");
                    Collections.sort(User.getUsers(), Comparator.comparing(User::getAge));
                    User e;
                    for (int i = 0; i<User.getUsers().size(); i++){
                        e = User.getUsers().get(i);
                        if (!e.getRole().equals("patient")){
                            e.displayTableFormatRole();
                            //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                        }
                    }       System.out.println("-----------------------------------------------------------");
                    break;
                }
            case 5:
                {
                    System.out.format("ID     Role          Name                 Gender     Age\n");
                    System.out.println("-----------------------------------------------------------");
                    Collections.sort(User.getUsers(), Comparator.comparing(User::getGender));
                    User e;
                    for (int i = 0; i<User.getUsers().size(); i++){
                        e = User.getUsers().get(i);
                        if (!e.getRole().equals("patient")){
                            e.displayTableFormatRole();
                            //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                        }
                    }       System.out.println("-----------------------------------------------------------");
                    break;
                }
            default:
                break;
        }
    }
    /**
     * Displays a formatted list of all administrators in the system.
     * 
     * @param role Specifies the context in which this method is called, affecting output formatting.
     */
    public static void displayAdminList(int role){
        Administrator admin;
        if (role == 1){
            for (int i = 0; i<Administrator.getAdministrators().size(); i++){
                admin = Administrator.getAdministrators().get(i);
                admin.displayTableFormat();
                //System.out.println(admin.getHospitalID() + '\t' + '\t' + admin.getName() + '\t' + admin.getGender() + '\t' + admin.getAge());
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender     Age\n");
            for (int i = 0; i<Administrator.getAdministrators().size(); i++){
                admin = Administrator.getAdministrators().get(i);
                admin.displayTableFormatRole();
             }
        }
        System.out.println("\n");
        
    }
    /**
     * Displays a formatted list of all doctors in the system.
     * 
     * @param role Specifies the context in which this method is called, affecting output formatting.
     */
    public static void displayDoctorList(int role){
        Doctor doctor;
        if (role == 1){
            for (int i = 0; i<Doctor.getDoctors().size(); i++){
                doctor = Doctor.getDoctors().get(i);
                doctor.displayTableFormat();
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender     Age\n");
            for (int i = 0; i<Doctor.getDoctors().size(); i++){
                doctor = Doctor.getDoctors().get(i);
                doctor.displayTableFormatRole();
             }
        }
        System.out.println("\n");
    }
    /**
     * Displays a formatted list of all pharmacists in the system.
     * 
     * @param role Specifies the context in which this method is called, affecting output formatting.
     */
    public static void displayPharmacistList(int role){
        Pharmacist pharma;
        if (role == 1){
            for (int i = 0; i<Pharmacist.getPharmacists().size(); i++){
                pharma = Pharmacist.getPharmacists().get(i);
                pharma.displayTableFormat();
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender     Age\n");
            for (int i = 0; i<Pharmacist.getPharmacists().size(); i++){
                pharma = Pharmacist.getPharmacists().get(i);
                pharma.displayTableFormatRole();
             }
        }
        System.out.println("\n");
    }
    /**
     * Updates the information of an existing staff member, possibly changing their role and reallocating a new ID.
     * 
     * @param u The user to update.
     * @param name The new name for the user.
     * @param role The new role for the user if applicable.
     * @param gender The new gender for the user.
     * @param age The new age for the user.
     * @return The updated user object, potentially as a new instance if the role was changed.
     */
    public static User updateStaff(User u, String name, String role, String gender, int age){
        CSVService csvService = new CSVService();
        if (!name.equals(u.getName())){
            u.setName(name);
        } if (gender!=null){
            u.setGender(gender);
        } 
        if (age != u.getAge() && age !=0){
            u.setAge(age);
        }
        if (role.equals(u.getRole())){
            CSVService.replaceUser(u);
        } else { 
            // if changing role, delete the user, instantiate a new one 
            gender = u.getGender();
            String[] hashSalt = csvService.findCredential(u.getHospitalID());
            removeStaff(u);
            int hash = Integer.parseInt(hashSalt[0]);
            String salt = hashSalt[1];
            String staffID = addStaff(name, role.toUpperCase().charAt(0), gender.toUpperCase().charAt(0), age, "");
            csvService.addCredential(staffID, hash, salt);
            User.updateUsers();
            System.out.println("Successfully updated to " + role.toUpperCase().charAt(0) + role.substring(1) + " with new ID " + staffID +"\n");
            return findStaffDetails(staffID);
            // change the stored hash and the stored salt for this user

            // OR set role of user to change, create a new ID 
            /* u.setRole(role);
            // change csv to reflect this, then update Doctor.getDoctors e.g.
            if (role.equals("doctor")){;
                CSVService.removePharmacist((Pharmacist) u);
                u.setHospitalID(String.valueOf(createID(role.toUpperCase().charAt(0))));
                CSVService.replaceUser(u);
            } else{
                CSVService.removeDoctor((Doctor) u);
                u.setHospitalID(String.valueOf(createID(role.toUpperCase().charAt(0))));
                CSVService.replaceUser(u);
            } */
        
        }
        return u;
        
        
        // if (role.contains("doctor")){
        //     Doctor.updateDoctors();
        // } else{
        //     Pharmacist.updatePharmacists();
        // }
    }

    /**
     * Adds a new staff member to the system and generates a unique ID for them.
     * 
     * @param name The name of the new staff member.
     * @param roleChar The initial character indicating the staff's role ('D' for Doctor, 'P' for Pharmacist).
     * @param genderChar The initial character indicating the staff's gender ('M' for Male, 'F' for Female).
     * @param age The age of the new staff member.
     * @param defaultPass The default password for the new staff member.
     * @return The unique hospital ID assigned to the new staff member.
     */
    public static String addStaff(String name, char roleChar, char genderChar, int age, String defaultPass){
        CSVService csvService = new CSVService();
        Hasher hasher = new SimpleAdditiveHash();
        String gender;
        String staffID;
        String role;
        staffID = createID(roleChar);
        if (genderChar == 'M'){
            gender = "Male";
        } else{
            gender = "Female";
        }

        if (roleChar == 'D'){
            role = "doctor";
        } else{
            role = "pharmacist";
        }

        if (role.equals("doctor")){
            Doctor doctor = new Doctor(staffID, name, gender, age);
            CSVService.addDoctor(doctor);
            //CSVService.writeDoctor(doctor, Character.getNumericValue(staffID.charAt(3)-1));
        } else{
            Pharmacist pharmacist = new Pharmacist(staffID, name, gender, age);
            CSVService.addPharmacist(pharmacist);
        } 
        if (!defaultPass.isBlank()){
            String salt = Salter.createSaltString();
            csvService.addCredential(staffID, hasher.hash(defaultPass, salt), salt);
            System.out.println(roleChar + role.substring(1) + " with ID " + staffID + " created with password " + defaultPass);
            User.updateUsers();
        }
        return staffID;
    }

    /**
     * Finds and returns details of a staff member based on their ID.
     * 
     * @param ID The unique hospital ID of the staff member.
     * @return The User object representing the staff member, or null if not found.
     */
    public static User findStaffDetails(String ID){
        if (ID.startsWith("D") && ID.length()==4){
            for (int i = 0; i<Doctor.getDoctors().size(); i++){
                if (Doctor.getDoctors().get(i).getHospitalID().matches(ID)){
                    return Doctor.getDoctors().get(i);
                }
            }
            System.out.println("Error - Doctor with ID " + ID + " not found");
        } else if (ID.startsWith("P") && ID.length()==4){
            for (int i = 0; i<Pharmacist.getPharmacists().size(); i++){
                if (Pharmacist.getPharmacists().get(i).getHospitalID().matches(ID)){
                    return Pharmacist.getPharmacists().get(i);
                }
            }
            System.out.println("Error - Pharmacist with ID " + ID + " not found");
        } else if (ID.startsWith("A") && ID.length()==4){
            for (int i = 0; i<Administrator.getAdministrators().size(); i++){
                if (Administrator.getAdministrators().get(i).getHospitalID().matches(ID)){
                    return Administrator.getAdministrators().get(i);
                }
            }
            System.out.println("Error - Administrator with ID " + ID + " not found");
        } else{
            System.out.println("Error - ID format for staff is incorrect \nPlease follow DXXX, PXXX, or AXXX");
        }
        return null;
    }

     /**
     * Removes a staff member from the system based on their User object.
     * 
     * @param u The User object representing the staff member to be removed.
     */
    public static void removeStaff(User u){
        CSVService csvService = new CSVService();
        String role = u.getRole();
        if (u instanceof Doctor){
            CSVService.removeDoctor((Doctor)u);
            Doctor.getDoctors().remove((Doctor)u);
        } else if (u instanceof Pharmacist){
            CSVService.removePharmacist((Pharmacist)u);
            Pharmacist.getPharmacists().remove((Pharmacist)u);
        } else if (u instanceof Administrator) {
            System.out.println("Admin cannot be deleted");
            return;
        } else{
            System.out.println("Error - ID format for staff is incorrect \nPlease follow DXXX, or PXXX");
        }
        csvService.removeCredention(u.getHospitalID());
        User.getUsers().remove(u);
        User.updateUsers();
        
        System.out.println("Staff removed");
    }
    
    /**
     * Creates a unique ID for a new staff member based on the role character.
     * 
     * @param r The character representing the role of the staff ('D' for Doctor, 'P' for Pharmacist).
     * @return A unique ID string for the new staff member.
     */

    public static String createID(char r){
        char[] charID;
        String staffID;
        int intID = 0;
        int previous = 0;
        if (r == 'D'){ // doctor  D001
            Collections.sort(Doctor.getDoctors(), Comparator.comparing(Doctor::getHospitalID));
            for (int i = 0; i<Doctor.getDoctors().size(); i++){
                charID = Doctor.getDoctors().get(i).hospitalID.substring(1).toCharArray();
                intID = (Character.getNumericValue(charID[0])*100)+(Character.getNumericValue(charID[1])*10)+Character.getNumericValue(charID[2]);
                if (i == 0){
                    previous = intID; 
                } else{
                    if (intID > previous + 1){
                        if ((previous+1)/100 > 0){
                            staffID = "P" + String.valueOf(previous+1);
                        } else if ((previous+1)/10 > 0){
                            staffID = "P" + "0" + String.valueOf(previous+1);
                        } else {
                            staffID = "P" + "0" + "0" + String.valueOf(previous+1);
                        }
                        return staffID;
                    }
                    previous = intID;
                }
            }
            if ((intID+1)/100 > 0){
                staffID = "D" + String.valueOf(intID+1);
            } else if ((intID+1)/10 > 0){
                staffID = "D" + "0" + String.valueOf(intID+1);
            } else {
                staffID = "D" + "0" + "0" + String.valueOf(intID+1);
            }
            return staffID;
        
        } else{ // pharmacist P001 
            Collections.sort(Pharmacist.getPharmacists(), Comparator.comparing(Pharmacist::getHospitalID));
            for (int i = 0; i<Pharmacist.getPharmacists().size(); i++){
                charID = Pharmacist.getPharmacists().get(i).hospitalID.substring(1).toCharArray();
                intID = (Character.getNumericValue(charID[0])*100)+(Character.getNumericValue(charID[1])*10)+Character.getNumericValue(charID[2]);
                if (i == 0){
                    previous = intID; 
                } else{
                    if (intID > previous + 1){
                        if ((previous+1)/100 > 0){
                            staffID = "P" + String.valueOf(previous+1);
                        } else if ((previous+1)/10 > 0){
                            staffID = "P" + "0" + String.valueOf(previous+1);
                        } else {
                            staffID = "P" + "0" + "0" + String.valueOf(previous+1);
                        }
                        return staffID;
                    }
                    previous = intID;
                }
            }
            if ((intID+1)/100 > 0){
                staffID = "P" + String.valueOf(intID+1);
            } else if ((intID+1)/10 > 0){
                staffID = "P" + "0" + String.valueOf(intID+1);
            } else {
                staffID = "P" + "0" + "0" + String.valueOf(intID+1);
            }
            return staffID;
        } 
    }
}


