package hms.account;

import hms.users.Administrator;
import hms.users.Doctor;
import hms.users.Pharmacist;
import hms.users.User;

public class StaffFinder {
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

}
