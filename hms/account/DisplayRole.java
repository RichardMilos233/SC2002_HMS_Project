package hms.account;

import java.util.Collections;
import java.util.Comparator;

import hms.users.Administrator;
import hms.users.Doctor;
import hms.users.Pharmacist;
import hms.users.User;

public class DisplayRole implements IDisplay{
    /**
     * Displays a list of all staff members filtered by specific categories such as role
     * 
     */
    @Override
    public void displayByRole(){
        IDisplayHeader display = getDisplayHeader();
        System.out.println("--------------Doctors----------------------");
        display.displayHeaderNoRole();
        displayDoctorList();
        System.out.println("--------------Pharmacists------------------");
        display.displayHeaderNoRole();
        displayPharmacistList();
        System.out.println("--------------Admins-----------------------");
        display.displayHeaderNoRole();
        displayAdminList();
    }

    /**
     * Displays a list of all staff members filtered by specific categories such as role, name, ID, age, or gender.
     * 
     * @param comparator An integer representing the filter type: 
     *                   1 - by role, 2 - by name, 3 - by ID, 4 - by age, 5 - by gender.
     */
    @Override
    public void displayByAttribute(Comparator<User> comparator) {
        IDisplayHeader display = new DisplayFormat();
        display.displayHeader();
        Collections.sort(User.getUsers(), comparator);
        for (User user : User.getUsers()) {
            if (!user.getRole().equals("patient")) {
                DisplayFormat.displayUserFormatRole(user);
            }
        }
        System.out.println("-----------------------------------------------------------");
    }
    
    /**
     * Displays a formatted list of all administrators in the system.
     * 
     * @param role Specifies the context in which this method is called, affecting output formatting.
     * @param display allows the interface method from DisplayFormat to be called 
     */
    public void displayAdminList(){
        Administrator admin;
        getDisplayHeader().displayHeader();
        for (int i = 0; i<Administrator.getAdministrators().size(); i++){
            admin = Administrator.getAdministrators().get(i);
            DisplayFormat.displayUserFormat((User) admin);
            //System.out.println(admin.getHospitalID() + '\t' + '\t' + admin.getName() + '\t' + admin.getGender() + '\t' + admin.getAge());
        }
        System.out.println("-------------------------------------------");
        System.out.println("\n");
        
    }

    /**
     * Displays a formatted list of all doctors in the system.
     * 
     * @param role Specifies the context in which this method is called, affecting output formatting.
     */
    public static void displayDoctorList(){
        Doctor doctor;
        getDisplayHeader().displayHeader();
        for (int i = 0; i<Doctor.getDoctors().size(); i++){
            doctor = Doctor.getDoctors().get(i);
            DisplayFormat.displayUserFormat((User) doctor);
        }
        System.out.println("-------------------------------------------");
        System.out.println("\n");
    }

    /**
     * Displays a formatted list of all pharmacists in the system.
     * 
     * @param role Specifies the context in which this method is called, affecting output formatting.
     */
    public static void displayPharmacistList(){
        Pharmacist pharma;
        getDisplayHeader().displayHeader();
        for (int i = 0; i<Pharmacist.getPharmacists().size(); i++){
            pharma = Pharmacist.getPharmacists().get(i);
            DisplayFormat.displayUserFormat((User) pharma);
        }
        System.out.println("-------------------------------------------");
        System.out.println("\n");
    }

    /**
     * Displays a formatted list of all administrators in the system.
     * 
     * @return IDisplayHeader in order for classes to call Header  
     */
    public static IDisplayHeader getDisplayHeader(){
        IDisplayHeader display = new DisplayFormat();
        return display;
    }
}
