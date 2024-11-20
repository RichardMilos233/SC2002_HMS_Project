package hms.account;

import hms.account.users.Administrator;
import hms.account.users.Doctor;
import hms.account.users.Pharmacist;
import hms.account.users.User;
import hms.storage.CSVService;

public class StaffRemover {
     /**
     * Removes a staff member from the system based on their User object.
     * 
     * @param u The User object representing the staff member to be removed.
     */
    public static void removeStaff(User u){
        CSVService csvService = new CSVService();
        String role = u.getRole();
        if (u instanceof Doctor doctor){
            CSVService.removeDoctor(doctor);
            Doctor.getDoctors().remove(doctor);
        } else if (u instanceof Pharmacist pharmacist){
            CSVService.removePharmacist(pharmacist);
            Pharmacist.getPharmacists().remove(pharmacist);
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
