import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class StaffService {

    public static void displayStaffList(int byRole){
        if (byRole == 1){
            System.out.println("----------Doctors--------------------------");
            System.out.format("ID     Name                 Gender Age\n");
            System.out.println("-------------------------------------------");
            displayDoctorList(byRole);

            System.out.println("----------Pharmacists----------------------");
            System.out.format("ID     Name                 Gender Age\n");
            System.out.println("-------------------------------------------");
            displayPharmacistList(byRole);

            System.out.println("----------Admins---------------------------");
            System.out.format("ID     Name                 Gender Age\n");
            System.out.println("-------------------------------------------");
            displayAdminList(byRole);

        } else{
            System.out.format("ID     Role          Name                 Gender Age\n");
            System.out.println("-----------------------------------------------------------");
            Collections.sort(User.users, Comparator.comparing(User::getName));
            User e;
            for (int i = 0; i<User.users.size(); i++){
                e = User.users.get(i);
                if (!e.getRole().equals("patient")){
                    System.out.format("%-6s %-13s %-20s %-6s %-2d\n", e.getHospitalID(), e.getRole(), e.getName(), e.getGender(), e.getAge());
                    //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                }
            }
            System.out.println("-----------------------------------------------------------");
        }
    }

    public static void displayAdminList(int role){
        Administrator admin;
        if (role == 1){
            for (int i = 0; i<Administrator.administrators.size(); i++){
                admin = Administrator.administrators.get(i);
                System.out.format("%-6s %-20s %-6s %-2d\n", admin.getHospitalID(), admin.getName(), admin.getGender(), admin.getAge());
                //System.out.println(admin.getHospitalID() + '\t' + '\t' + admin.getName() + '\t' + admin.getGender() + '\t' + admin.getAge());
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender Age\n");
            for (int i = 0; i<Administrator.administrators.size(); i++){
                admin = Administrator.administrators.get(i);
                System.out.format("%-6s %-13s %-20s %-6s %-2d\n", admin.getHospitalID(), admin.getRole(), admin.getName(), admin.getGender(), admin.getAge());
             }
        }
        System.out.println("\n");
        
    }

    public static void displayDoctorList(int role){
        Doctor doctor;
        if (role == 1){
            for (int i = 0; i<Doctor.doctors.size(); i++){
                doctor = Doctor.doctors.get(i);
                System.out.format("%-6s %-20s %-6s %-2d\n", doctor.getHospitalID(), doctor.getName(), doctor.getGender(), doctor.getAge());
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender Age\n");
            for (int i = 0; i<Doctor.doctors.size(); i++){
                doctor = Doctor.doctors.get(i);
                System.out.format("%-6s %-13s %-20s %-6s %-2d\n", doctor.getHospitalID(), doctor.getRole(), doctor.getName(), doctor.getGender(), doctor.getAge());
             }
        }
        System.out.println("\n");
    }

    public static void displayPharmacistList(int role){
        Pharmacist pharma;
        if (role == 1){
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                pharma = Pharmacist.pharmacists.get(i);
                System.out.format("%-6s %-20s %-6s %-2d\n", pharma.getHospitalID(), pharma.getName(), pharma.getGender(), pharma.getAge());
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender Age\n");
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                pharma = Pharmacist.pharmacists.get(i);
                System.out.format("%-6s %-13s %-20s %-6s %-2d\n", pharma.getHospitalID(), pharma.getRole(), pharma.getName(), pharma.getGender(), pharma.getAge());
             }
        }
        System.out.println("\n");
    }

    public static void updateStaff(){

    }
    
    public static void addStaff(String name, int r, int g, int age, String defaultPass){
        Scanner scanner = new Scanner(System.in);
        String gender;
        String staffID;
        staffID = createID(r);
        if (g == 1){
            gender = "Male";
        } else{
            gender = "Female";
        }

        if (r == 1){
            System.out.println("New Doctor: \nID: " + staffID + "\nName: " + name + "\n Gender: " + gender + "\n Age: " + age);
            int c = 0;
            do { 
                System.out.println("Confirm this new Staff: \n1 Yes \n2 Re-enter Details \n3 Cancel");
                c = scanner.nextInt();
                switch (c){
                    case 1:
                        break;
                    case 2:
                        AdministratorMenu.addStaff();
                        break;
                    case 3: 
                        return;
                    default:
                        return;
                } 
            }   while (c<4 && c>1);
            Doctor doctor = new Doctor(staffID, defaultPass, name, gender, age);
            System.out.println("Created with Password" + defaultPass);
        } else{
            System.out.println("New Pharmacist: \nID: " + staffID + "\nName: " + name + "\n Gender: " + gender + "\n Age: " + age);
            int c = 0;
            do { 
                System.out.println("Confirm this new Staff: \n1 Yes \n2 Re-enter Details \n3 Cancel");
                c = scanner.nextInt();
                switch (c){
                    case 1:
                        break;
                    case 2:
                        AdministratorMenu.addStaff();
                        break;
                    case 3: 
                        return;
                    default:
                        return;
                } 
            }   while (c<4 && c>1);
            Pharmacist pharmacist = new Pharmacist(staffID, defaultPass, name, gender, age);
            System.out.println("Created with Password" + defaultPass);
        } 
        return;
    }

    public static void removeStaff(){

    }

    public static String createID(int r){
        char[] charID;
        String staffID;
        int intID = 0;
        int previous = 0;
        if (r == 1){ // doctor  D001
            Collections.sort(Doctor.doctors, Comparator.comparing(Doctor::getHospitalID));
            for (int i = 0; i<Doctor.doctors.size(); i++){
                charID = Doctor.doctors.get(i).hospitalID.substring(1).toCharArray();
                intID = (charID[0]*100)+(charID[1]*10)+charID[2];
                if (i == 0){
                    previous = intID; 
                } else{
                    if (intID > previous + 1){
                        staffID = "D" + String.valueOf(previous+1);
                        return staffID;
                    }
                    previous = intID;
                }
            }
            staffID = "D" + String.valueOf(intID+1);
            return staffID;
        
        } else{ // pharmacist P001 
            Collections.sort(Pharmacist.pharmacists, Comparator.comparing(Pharmacist::getHospitalID));
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                charID = Pharmacist.pharmacists.get(i).hospitalID.substring(1).toCharArray();
                intID = (charID[0]*100)+(charID[1]*10)+charID[2];
                if (i == 0){
                    previous = intID; 
                } else{
                    if (intID > previous + 1){
                        staffID = "D" + String.valueOf(previous+1);
                        return staffID;
                    }
                    previous = intID;
                }
            }
            staffID = "D" + String.valueOf(intID+1);
            return staffID;
        } 
    }
}
