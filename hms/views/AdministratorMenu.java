package hms.views;

import hms.appointment.AdministratorAppointmentViewer;
import hms.inventory.AdministratorInventoryManagement;
import hms.inventory.Inventory;
import hms.inventory.Medication;
import hms.staff.DisplayRole;
import hms.staff.DisplayFormat;
import hms.staff.IDisplay;
import hms.staff.StaffAdder;
import hms.staff.StaffFinder;
import hms.staff.StaffRemover;
import hms.staff.StaffUpdater;
import hms.users.Administrator;
import hms.users.User;
import hms.utils.Validator;
import java.util.*;
/**
 * Manages the administrator menu interface, providing various functionalities
 * such as viewing and managing hospital staff, appointments, and medication inventory.
 */
public class AdministratorMenu {
    /**
     * Displays the main menu for an administrator and processes the user's choices.
     * The menu allows access to functions like managing staff, viewing appointment details,
     * managing medication inventory, and changing the administrator's password.
     *
     * @param administrator The administrator currently logged in and using the menu.
     */
    public static void displayAdminMenu(Administrator administrator){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("\n-----------Administrator Menu------------\n" +
                                "1 View and Manage Hospital Staff \n" + 
                                "2 View Appointments details \n" + 
                                "3 View and Manage Medication Inventory \n" + 
                                "4 View Replenishment Requests \n" + 
                                "5 Change Password \n" + 
                                "6 Logout\n" + 
                                "Enter your choice:");
            choice = Validator.validateInt(scanner);

            switch (choice) {
                case 1:
                    displayStaffList();
                    break;
                case 2:
                    viewAppointmentDetails();
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    AdministratorInventoryManagement.manageInventory();
                    // inventoryService.approveReplenishRequest(replenishRequests.get(Integer.parseInt(scanner.nextLine())-1));
                    break;
                case 5:
                    administrator.changePassword();
                    break;
                case 6:
                    administrator.logout();
                    break;

                default:
                    System.out.println("Invalid input."); 
            }
        }while (choice != 6);
    }
    /**
     * Manages the inventory options in a sub-menu for an administrator.
     */
    private static void displayInventory() { // this moved to somewhere else
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        Inventory inventoryService = new Inventory();

        do {
            System.out.println("Inventory Management: \n" + //
                                "1 Display Medications \n" + //
                                "2 Add New Medication \n" + //
                                "3 Remove Medication \n" + //
                                "4 Update Stock Levels \n" + //
                                "5 Exit ");
                                
            List<Medication> medications = inventoryService.retrieveMedications();
            choice = Validator.validateInt(scanner);
            switch (choice) {
                case 1:
                    inventoryService.viewInventory();
                    break;
                case 2:
                    Medication newMedication = new Medication();
                    System.out.println("Enter Medication Name: ");
                    newMedication.setMedicationName(Validator.validateLine(scanner));
                    System.out.println("Enter Stock Level: ");
                    newMedication.setStock(Validator.validateInt(scanner));
                    System.out.println("Enter Stock Alert Level: ");
                    newMedication.setStockAlert(Validator.validateInt(scanner));
                    inventoryService.addMedication(newMedication); // can wrap this into another method?
                    break;
                case 3:

                    for (int i = 0; i < medications.size(); i++) {
                        System.out.println( (i+1) + " - " + medications.get(i).getMedicationName());
                    }

                    int medChoice;
                    do { 
                        System.out.println("Choose Medication to Remove OR 0 to Return: "); 
                        medChoice = Validator.validateInt(scanner);
                        if (medChoice == 0){
                            return;
                        }
                    }
                    while (medChoice<1 || medChoice>medications.size());
                    
                    
                    inventoryService.removeMedication(medications.get(medChoice-1));
                    break;
                case 4:
                    inventoryService.viewInventory();
                    int medToUpdate;
                    do {
                        System.out.println("Select Medication to Replenish OR 0 to Return: ");
                        medToUpdate = Validator.validateInt(scanner);
                        if (medToUpdate == 0){
                            return;
                        }
                    } while (medToUpdate<0 || medToUpdate>medications.size());
                    
                    int stockLevelToSet;
                    do { 
                        System.out.println("Set Level of Stock OR 0 to Return: ");
                        stockLevelToSet = Validator.validateInt(scanner);
                        if (stockLevelToSet == 0){
                            return;
                        }
                    } while (stockLevelToSet<0);
                    inventoryService.updateStockLevel(medications.get(medToUpdate-1), stockLevelToSet);
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }
  
    /**
     * Displays a menu to select different staff display options, handling the selection to show staff
     * based on various criteria like role, name, ID, age, or gender, or to manage staff directly.
     * Continues to display the menu until the user chooses to return to the main menu.
     * @throws UnsupportedOperationException if an unsupported operation is performed.
     */
    public static void displayStaffList() {
        Scanner scanner = new Scanner(System.in);
        int c;
        IDisplay displayRole = new DisplayRole();
        do {  // inconsistent print method what is the preference
            // Display a list of staff filtered by role, gender, age, etc
            System.out.println("Would you like to: \n" + 
                                "1 Display All Staff by Role \n" + 
                                "2 Display All Staff by Name \n" + 
                                "3 Display All Staff by ID \n" + 
                                "4 Display All Staff by Age \n" + 
                                "5 Display All Staff by Gender \n" + 
                                "6 Display Doctors \n" + 
                                "7 Display Pharmacists \n" + 
                                "8 Manage Staff \n" + 
                                "9 Return to Menu");
            c = Validator.validateInt(scanner);
            switch (c){
                case 1:
                    displayRole.displayByRole();
                    break;
                case 2:
                    displayRole.displayByAttribute(Comparator.comparing(User::getName));
                    break;
                case 3:
                    displayRole.displayByAttribute(Comparator.comparing(User::getHospitalID));
                    break;
                case 4:
                    displayRole.displayByAttribute(Comparator.comparing(User::getAge));
                    break;
                case 5:
                    displayRole.displayByAttribute(Comparator.comparing(User::getGender));
                    break; 
                case 6:
                    DisplayRole.displayDoctorList();
                    break;
                case 7:
                    DisplayRole.displayPharmacistList();
                    break;
                case 8:
                    manageStaff();
                    break;
                case 9:
                    return;
                default:
                    break;
            }
        } while (c!=9);
        
    throw new UnsupportedOperationException();
    }
     /**
     * Provides a management interface for staff, allowing addition, updating, or removal of staff records.
     * Continues to display the management options until the user chooses to return to the main menu.
     * @throws UnsupportedOperationException if an unsupported operation is performed.
     */
    public static void manageStaff() {
        Scanner scanner = new Scanner(System.in);
        int c;
        do { System.out.println("Would you like to: \n" + //
                                "1 Add Staff \n" + //
                                "2 Update Staff \n" + //
                                "3 Remove Staff \n" + //
                                "4 Return to Menu");
            c = Validator.validateInt(scanner);
            switch (c){
                case 1:
                    // name, role, id automatically allocated i think, gender, age, salary?
                    addStaff();
                    break;
                case 2:
                    // which one 
                    updateStaff();
                    break; 
                case 3:
                    // which one 
                    removeStaff();
                    break;
                case 4:
                    return;
                default:
                    break;
            } 
        } while (c!= 4);
        throw new UnsupportedOperationException();
    }
    /**
     * Interactively collects information to add a new staff member to the system, excluding administrators.
     * Prompts for staff details such as name, role, gender, age, and a default password. Provides a confirmation
     * step before finalizing the addition of a new staff member.
     */
    public static void addStaff() { // cannot add admin
        Scanner scanner = new Scanner(System.in);
        String name, defaultPass;
        int age;
        char role, gender;

        do { // NAME
            System.out.println("Staff's Name: ");
            name = Validator.validateName(scanner);
        } while(name.isBlank());
        System.out.println("Name entered: " + name);

        do { // ROLE
            System.out.println("\nStaff's Role: \nD Doctor\nP Pharmacist");
            role = Validator.validateCharToUpper(scanner);
        } while (role!='D'&& role!='P');
        
        do { // GENDER
            System.out.println("\nStaff's Gender: \nM Male\nF Female");
            gender = Validator.validateCharToUpper(scanner);
        } while (gender!='M'&& gender!='F');
        
        do { // Age
            System.out.println("\nStaff's Age: ");
            age =  Validator.validateInt(scanner);
        } while (age <18 || age>100);

       
        do { // NAME
            System.out.println("\nMake a default password for the new Staff member (at least 8 characters): ");
            defaultPass = Validator.validateStringNoSpace(scanner);
        } while(defaultPass.isBlank() || defaultPass.isEmpty() || (defaultPass.length()<8));
        
        int c;
        if (role=='D'){
            if (gender=='M'){
                System.out.println("\nNew Doctor: \nName: " + name + "\nGender: Male\nAge: " + age);
            } else {
                System.out.println("\nNew Doctor: \nName: " + name + "\nGender: Female\nAge: " + age);
            }
            do { 
                System.out.println("\nConfirm this new Staff? \nY Yes \nN No, re-enter Details \nC Cancel");
                c =  Validator.validateCharToUpper(scanner);
                switch (c){
                    case 'Y':
                        StaffAdder.addStaff(name, role, gender, age, defaultPass);
                        break;
                    case 'N':
                        AdministratorMenu.addStaff();
                        break;
                    case 'C': 
                        return;
                    default:
                        break;
                } 
            }   while (c!='Y' && c!='N' && c!='C');
        } else {
            if (gender=='M'){
                System.out.println("\nNew Pharmacist: \nName: " + name + "\nGender: Male\nAge: " + age);
            } else {
                System.out.println("\nNew Pharmacist: \nName: " + name + "\nGender: Female\nAge: " + age);
            }
            do { 
                System.out.println("\nConfirm this new Staff? \nY Yes \nN No, re-enter Details \nC Cancel");
                c =  Validator.validateCharToUpper(scanner);
                switch (c){
                    case 'Y':
                        StaffAdder.addStaff(name, role, gender, age, defaultPass);
                        break;
                    case 'N':
                        AdministratorMenu.addStaff();
                        break;
                    case 'C': 
                        return;
                    default:
                        break;
                } 
            }   while (c!='Y' && c!='N' && c!='C');
        }
    }
    /**
     * Provides an interface for updating existing staff details. Allows updating of name, role, gender,
     * age, or password. The staff ID must be provided by the user to fetch the correct staff details for updating.
     * Changes are applied once all desired updates are confirmed.
     */
    public static void updateStaff() { // update admin, doctor, or pharmacist
        Scanner scanner = new Scanner(System.in);
        String ID;
        do { 
            System.out.println("Enter the ID of the Staff member you want to update: ");
            ID = Validator.validateStringNoSpace(scanner);
        } while (StaffFinder.findStaffDetails(ID) == null);

        User u = StaffFinder.findStaffDetails(ID);
            int c;
            String name = u.getName();
            int age = u.getAge();
            String gender = u.getGender();
            String role = u.getRole();
            String opposite = null;
            String otherRole;
            do { 
                System.out.println("What detail would you like to update: \n" + 
                                    "1 Name: " + name + "\n" +
                                    "2 Role: " + role + "\n" + 
                                    "3 Gender: " + gender + "\n" + 
                                    "4 Age: " + age + "\n" +
                                    "5 Finish Updating" + "\n" +
                                    "6 Change Password");
                c =  Validator.validateInt(scanner);
                switch (c){
                    case 1:
                        // update name
                        do { 
                            System.out.println("Enter new Name: ");
                            name = Validator.validateName(scanner); 
                        } while (name.isBlank());
                        break;
                    case 2:
                        // update role
                        if (!u.getHospitalID().startsWith("A")){
                                if (role.startsWith("d")){
                                    otherRole = "pharmacist";
                                } else {
                                    otherRole = "doctor";
                                }
                                System.out.println("Switching to " + otherRole);
                                role = otherRole;
                        } else {
                            System.out.println("Admin's role cannot be changed");
                        }
                        break;
                    case 3:
                        // update gender
                        if (gender.startsWith("M")){
                            opposite = "Female";
                        } else{
                            opposite = "Male";
                        }
                        System.out.println("Switching gender to " + opposite);
                        gender = opposite;
                        break;
                    case 4:
                        do { 
                            System.out.println("Type the new age: ");
                            age =  Validator.validateInt(scanner);
                        } while (age<18);
                        System.out.println("Updating age to " + age);
                        break;
                    case 5:
                        u = StaffUpdater.updateStaff(u, name, role, opposite, age);
                        u.display();
                        break;
                    case 6:
                        u.changePassword();
                    default:
                        break;
                }
            } while (c!=5); 
        
        //throw new UnsupportedOperationException();
    }

     /**
     * Allows the removal of a staff member from the system. Requires confirmation from the user
     * before removal is finalized. The staff ID must be entered to identify the staff member for removal.
     * Note: Administrators cannot be removed using this method.
     */
    public static void removeStaff() { // cannot remove admin
        Scanner scanner = new Scanner(System.in);
        String ID;
        User u;
        do { 
            System.out.println("Enter the ID of the Staff member you want to remove or 0 to return");
            ID = Validator.validateStringNoSpace(scanner);
            if (ID.equals("0")){
                return;
            }
            u = StaffFinder.findStaffDetails(ID);
        } while (u==null);
       
        char c;
        do { 
            System.out.println("Are you sure you want to remove? \n");
            u.display(); 
            System.out.println("Y Yes \tN No");
            c =  Validator.validateCharToUpper(scanner);
            switch (c){
                case 'Y':
                    StaffRemover.removeStaff(u);
                    break;
                case 'N':
                    return;
                default:
                    break;
            }
        } while (c!='Y' && c!='N');

        //throw new UnsupportedOperationException();
    }
    /**
     * Displays a list of all appointments using a method from {@link AdministratorAppointmentViewer}.
     */ 
    public static void viewAppointmentDetails() {
        AdministratorAppointmentViewer.viewAllAppointment();
    }
}