import java.util.*;

public class AdministratorMenu {
    
    public static void displayAdminMenu(Administrator administrator){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("Administrator Menu: \n" + 
                                "1 View and Manage Hospital Staff \n" + 
                                "2 View Appointments details \n" + 
                                "3 View and Manage Medication Inventory \n" + 
                                "4 View Replenishment Requests \n" + 
                                "5 Change Password \n" + 
                                "6 Logout");
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
  
    private static void displayInventory() { // this moved to somewhere else
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        Inventory inventoryService = new Inventory();

        do {
            System.out.println("Inventory Management: \n" + //
                                "1 Display Medications \n" + //
                                "2 Add New Medication \n" + //
                                "3 Remove Medication \n" + //
                                "4 Update Stock Levels \n" + //
                                "5 Exit ");
                                
            List<Medication> medications = inventoryService.retrieveMedications();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inventoryService.viewInventory();
                    break;
                case 2:
                    Medication newMedication = new Medication();
                    System.out.println("Enter Medication Name: ");
                    newMedication.setMedicationName(scanner.nextLine());
                    System.out.println("Enter Stock Level: ");
                    newMedication.setStock(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Enter Stock Alert Level: ");
                    newMedication.setStockAlert(Integer.parseInt(scanner.nextLine()));

                    inventoryService.addMedication(newMedication); // can wrap this into another method?
                    break;
                case 3:

                    for (int i = 0; i < medications.size(); i++) {
                        System.out.println( (i+1) + " - " + medications.get(i).getMedicationName());
                    }

                    System.out.println("Choose Medication to Remove: ");
                    int medChoice = Integer.parseInt(scanner.nextLine());
                    
                    inventoryService.removeMedication(medications.get(medChoice-1));
                    break;
                case 4:
                    inventoryService.viewInventory();
                    System.out.println("Enter Medication Name to Update Stock Levels: ");
                    String medToRemove = scanner.nextLine();
                    System.out.println("Enter Stock Level to Set to: ");
                    int stockLevelToSet = Integer.parseInt(scanner.nextLine());
                    inventoryService.updateStockLevel(medToRemove, stockLevelToSet);
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }
  
  
    public static void displayStaffList() {
        Scanner scanner = new Scanner(System.in);
        int c = 0;
        do {  // inconsistent print method what is the preference
            System.out.println("Would you like to: \n" + 
                                "1 Display All Staff by Role \n" + 
                                "2 Display All Staff by ID \n" + 
                                "3 Display Doctors \n" + 
                                "4 Display Pharmacists \n" + 
                                "5 Manage Staff \n" + 
                                "6 Return to Menu");
            c = scanner.nextInt();
            switch (c){
                case 1:
                    StaffService.displayStaffList(1);
                    // TO DO - add option for alphabetical
                    break;
                case 2:
                    StaffService.displayStaffList(0);
                    break; 
                case 3:
                    System.out.format("ID     Name                 Gender Age\n");
                    System.out.println("-------------------------------------------");
                    StaffService.displayDoctorList(1);
                    break;
                case 4:
                    System.out.format("ID     Name                 Gender Age\n");
                    System.out.println("-------------------------------------------");
                    StaffService.displayPharmacistList(1);
                    break;
                case 5:
                    manageStaff();
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        } while (c!=6);
        
    throw new UnsupportedOperationException();
    }

    public static void manageStaff() {
        Scanner scanner = new Scanner(System.in);
        int c = 0;
        do { System.out.println("Would you like to: \n" + //
                                "1 Add Staff \n" + //
                                "2 Update Staff \n" + //
                                "3 Remove Staff \n" + //
                                "4 Return to Menu");
            c = scanner.nextInt();
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

    public static String verifyName(String name){
        if(name.isBlank() || name.isEmpty() && (name.length()-name.trim().length() > 4 && name.contains("  "))){
            return "";
        }
        // Name formatting
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        for (int i = 0; i<name.length()-1; i++){
            if (name.charAt(i) == ' ' && name.charAt(i+1)!=' '){
                name = name.substring(0,i+1) + name.substring(i+1, i+2).toUpperCase() + name.substring(i+2).toLowerCase();
            }
        }
        return name;
    }

	public static void addStaff() { // cannot add admin
        Scanner scanner = new Scanner(System.in);
        String name, defaultPass;
        int role, gender, age;

        do { // NAME
            System.out.println("Staff's Name: ");
            name = scanner.nextLine();
        } while(verifyName(name).isBlank());
        name = verifyName(name);
        System.out.println("Name entered: " + name);

        do { // ROLE
            System.out.println("\nStaff's Role: \n1 Doctor\n2 Pharmacist");
            role = scanner.nextInt();
        } while (role >2 || role<1);
        
        do { // GENDER
            System.out.println("\nStaff's Gender: \n1 Male\n2 Female");
            gender = scanner.nextInt();
        } while (gender >2 || gender<1);
        
        do { // Age
            System.out.println("\nStaff's Age: ");
            age = scanner.nextInt();
            scanner.nextLine();
        } while (age <18 || age>100);

       
        do { // NAME
            System.out.println("\nMake a default password for the new Staff member (at least 8 characters): ");
            defaultPass = scanner.nextLine();
        } while(defaultPass.isBlank() || defaultPass.isEmpty() || (defaultPass.length()<8));
        
        int c;
        if (role==1){
            if (gender==1){
                System.out.println("\nNew Doctor: \nName: " + name + "\nGender: Male\nAge: " + age);
            } else {
                System.out.println("\nNew Doctor: \nName: " + name + "\nGender: Female\nAge: " + age);
            }
            c = 0;
            do { 
                System.out.println("\nConfirm this new Staff: \n1 Yes \n2 Re-enter Details \n3 Cancel");
                c = scanner.nextInt();
                switch (c){
                    case 1:
                        StaffService.addStaff(name, role, gender, age, defaultPass);
                        break;
                    case 2:
                        AdministratorMenu.addStaff();
                        break;
                    case 3: 
                        return;
                    default:
                        break;
                } 
            }   while (c>3 || c<1);
        } else {
            if (gender==1){
                System.out.println("\nNew Pharmacist: \nName: " + name + "\nGender: Male\nAge: " + age);
            } else {
                System.out.println("\nNew Pharmacist: \nName: " + name + "\nGender: Female\nAge: " + age);
            }
            c = 0;
            do { 
                System.out.println("Confirm this new Staff: \n1 Yes \n2 Re-enter Details \n3 Cancel");
                c = scanner.nextInt();
                switch (c){
                    case 1:
                        StaffService.addStaff(name, role, gender, age, defaultPass);
                        break;
                    case 2:
                        AdministratorMenu.addStaff();
                        break;
                    case 3: 
                        return;
                    default:
                        break;
                } 
            }   while (c>3 || c<1);
        }
        /* System.out.println("Confirm creating staff: \n" +
        "Name: " + name + "\n" + "ID: " + id + "\n" + "Role: " + role + "\n" + ); */
        //throw new UnsupportedOperationException();
	}

	public static void updateStaff() { // update admin, doctor, or pharmacist
        Scanner scanner = new Scanner(System.in);
        String ID;
        do { 
            System.out.println("Enter the ID of the Staff member you want to update: ");
            ID = scanner.next();
        } while (StaffService.findStaffDetails(ID) == null);

        User u = StaffService.findStaffDetails(ID);
            int c = 0;
            int d;
            String name;
            do { 
                System.out.println("What detail would you like to update: \n" + 
                                    "1 Name: " + u.getName() + "\n" +
                                    "2 Role: " + u.getRole() + "\n" + 
                                    "3 Gender: " + u.getGender() + "\n" + 
                                    "4 Age: " + u.getAge() + "\n" +
                                    "5 Finish Updating");
                c = scanner.nextInt();
                scanner.nextLine();
                switch (c){
                    case 1:
                        // update name
                        do { 
                            System.out.println("Enter new Name: ");
                            name = scanner.nextLine(); 
                        } while (verifyName(name).isBlank());
                        name = verifyName(name);
                        StaffService.updateStaff(u, name, "", "", 0);
                        break;
                    case 2:
                        // update role
                        String role;
                        do { 
                            System.out.println("Select the new Role: \n1 Doctor \n2 Pharmacist");
                            d = scanner.nextInt();
                        } while (d>2 || (d==1 && u.getRole().matches("doctor")) || (d==2 && u.getRole().matches("pharmacist")));
                        if (d == 1){
                            role = "doctor";
                        } else{
                            role = "pharmacist";
                        }
                        StaffService.updateStaff(u, "", role, "", 0);
                        break;
                    case 3:
                        // update gender
                        String gender;
                        String opposite;
                        if (u.getGender().startsWith("M")){
                            opposite = "Female";
                        } else{
                            opposite = "Male";
                        }
                        System.out.println("Switching gender to " + opposite);
                        StaffService.updateStaff(u, "", "", opposite, 0);
                        break;
                    case 4:
                        int age;
                        do { 
                            System.out.println("Type the new age: ");
                            age = scanner.nextInt();
                        } while (age<18 || age==u.getAge());
                        StaffService.updateStaff(u, "", "", "", age);
                        break;
                    case 5:
                        System.out.println("New details for ID: " + ID + "\nName: " + u.getName() + "\nRole: " + u.getRole() + 
                                    "\nGender: " + u.getGender() + "\nAge: " + u.getAge());
                        break;
                    default:
                        break;
                }
            } while (c!=5); 
        
        //throw new UnsupportedOperationException();
	}

	public static void removeStaff() { // cannot remove admin
        Scanner scanner = new Scanner(System.in);
        String ID;
        System.out.println("Enter the ID of the Staff member you want to remove");
        ID = scanner.next();
        User u = StaffService.findStaffDetails(ID);
        int c = 0;
        do { 
            System.out.println("Are you sure you want to remove " + u.getRole().substring(0, 1).toUpperCase() 
                                + u.getRole().substring(1) +" " + u.getName() + "\n1 Yes \n2 No");
            c = scanner.nextInt();
            switch (c){
                case 1:
                    StaffService.removeStaff(u);
                    System.out.println("Staff removed");
                    break;
                case 2:
                    return;
                default:
                    break;
            }
        } while (c>2 || c<1);

		//throw new UnsupportedOperationException();
	}

	public static void viewAppointmentDetails() {
        AdministratorAppointmentViewer.viewAllAppointment();
	}

	public void addStock() {
		// TODO - implement Administrator.addStock
		throw new UnsupportedOperationException();
	}

	public void removeStock() {
		// TODO - implement Administrator.removeStock
		throw new UnsupportedOperationException();
	}

	public void updateStock() {
		// TODO - implement Administrator.updateStock
		throw new UnsupportedOperationException();
	}

	public void updateStockAlert() {
		// TODO - implement Administrator.updateStockAlert
		throw new UnsupportedOperationException();
	}

	public static void approveReplenishmentRequest() {
		// TODO - implement Administrator.approveReplenishmentRequest
		throw new UnsupportedOperationException();
	}
}