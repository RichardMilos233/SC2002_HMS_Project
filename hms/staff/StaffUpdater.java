package hms.staff;

import hms.storage.CSVService;
import hms.users.User;

/**
 * Provides methods to update existing staff member information.
 */
public class StaffUpdater {
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
            StaffRemover.removeStaff(u);
            int hash = Integer.parseInt(hashSalt[0]);
            String salt = hashSalt[1];
            String staffID = StaffAdder.addStaff(name, role.toUpperCase().charAt(0), gender.toUpperCase().charAt(0), age, "");
            csvService.addCredential(staffID, hash, salt);
            User.updateUsers();
            System.out.println("Successfully updated to " + role.toUpperCase().charAt(0) + role.substring(1) + " with new ID " + staffID +"\n");
            return StaffFinder.findStaffDetails(staffID);
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
    }

}
