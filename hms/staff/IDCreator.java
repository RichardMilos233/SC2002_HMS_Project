package hms.staff;

import java.util.Collections;
import java.util.Comparator;

import hms.users.Doctor;
import hms.users.Pharmacist;

/**
 * Generates unique IDs for new staff members based on the role character.
 */
public class IDCreator {
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
