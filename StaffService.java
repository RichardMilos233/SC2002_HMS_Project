import java.util.*;

public class StaffService {

    public static void displayStaffList(int byRole){
        if (byRole == 1){
            System.out.println("--------------Doctors----------------------");
            System.out.format("ID     Name                 Gender Age\n");
            System.out.println("-------------------------------------------");
            displayDoctorList(byRole);

            System.out.println("--------------Pharmacists------------------");
            System.out.format("ID     Name                 Gender Age\n");
            System.out.println("-------------------------------------------");
            displayPharmacistList(byRole);

            System.out.println("--------------Admins-----------------------");
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

    public static void updateStaff(User u, String name, String role, String gender, int age){
        if (!name.isBlank() && !name.isEmpty()){
            u.setName(name);
        } else if (!role.isBlank() && !role.isEmpty()){
            u.setRole(role);
        } else if (!gender.isBlank() && !gender.isEmpty()){
            u.setGender(gender);
        } else if (age != 0){
            u.setAge(age);
        }
    }
    
    public static void addStaff(String name, int r, int g, int age, String defaultPass){
        String gender;
        String staffID;
        staffID = createID(r);
        if (g == 1){
            gender = "Male";
        } else{
            gender = "Female";
        }

        if (r == 1){
            Doctor doctor = new Doctor(staffID, defaultPass, name, gender, age);
            System.out.println("Doctor with ID " + staffID + " created with password " + defaultPass);
        } else{
            Pharmacist pharmacist = new Pharmacist(staffID, defaultPass, name, gender, age);
            System.out.println("Pharmacist with ID " + staffID + " created with password " + defaultPass);
        } 
    }

    public static User findStaffDetails(String ID){
        if (ID.startsWith("D") && ID.length()==4){
            for (int i = 0; i<Doctor.doctors.size(); i++){
                if (Doctor.doctors.get(i).getHospitalID().matches(ID)){
                    return Doctor.doctors.get(i);
                }
            }
            System.out.println("Error - Doctor with ID" + ID + "not found");
        } else if (ID.startsWith("P") && ID.length()==4){
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                if (Pharmacist.pharmacists.get(i).getHospitalID().matches(ID)){
                    return Pharmacist.pharmacists.get(i);
                }
            }
            System.out.println("Error - Pharmacist with ID" + ID + "not found");
        } else{
            System.out.println("Error - ID format for staff is incorrect \nPlease follow DXXX or PXXX");
        }
        return null;
    }

    public static void removeStaff(User u){
        String role = u.getRole();
        if (role.startsWith("d")){
            Doctor.doctors.remove(u);
        } else {
            Pharmacist.pharmacists.remove(u);
        }
        User.users.remove(u);
        u = null;
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
            Collections.sort(Pharmacist.pharmacists, Comparator.comparing(Pharmacist::getHospitalID));
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                charID = Pharmacist.pharmacists.get(i).hospitalID.substring(1).toCharArray();
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
