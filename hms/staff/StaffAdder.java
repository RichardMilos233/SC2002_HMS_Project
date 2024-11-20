package hms.staff;

import hms.storage.*;
import hms.storage.CSVService;
import hms.users.Doctor;
import hms.users.Pharmacist;
import hms.users.User;
import hms.utils.cryptography.Salter;
import hms.utils.cryptography.SimpleAdditiveHash;
import hms.utils.cryptography.Hasher;
import java.util.*;

/**
 * Provides functionality to add staff members to the system.
 */
public class StaffAdder {
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
        staffID = IDCreator.createID(roleChar);
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

}
