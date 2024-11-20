package hms.staff;

import hms.storage.CSVService;
import hms.users.Administrator;
import hms.users.Doctor;
import hms.users.Pharmacist;
import hms.users.User;

/**
 * Provides functionality to remove a staff member from the system.
 */
public class StaffRemover {
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

}
