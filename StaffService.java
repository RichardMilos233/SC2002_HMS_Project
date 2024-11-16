import java.util.*;

public class StaffService {

    public static void displayStaffList(int byRole){
        if (byRole == 1){
            System.out.println("--------------Doctors----------------------");
            System.out.format("ID     Name                 Gender     Age\n");
            System.out.println("-------------------------------------------");
            displayDoctorList(byRole);

            System.out.println("--------------Pharmacists------------------");
            System.out.format("ID     Name                 Gender     Age\n");
            System.out.println("-------------------------------------------");
            displayPharmacistList(byRole);

            System.out.println("--------------Admins-----------------------");
            System.out.format("ID     Name                 Gender     Age\n");
            System.out.println("-------------------------------------------");
            displayAdminList(byRole);

        } else{
            System.out.format("ID     Role          Name                 Gender     Age\n");
            System.out.println("-----------------------------------------------------------");
            Collections.sort(User.getUsers(), Comparator.comparing(User::getHospitalID));
            User e;
            for (int i = 0; i<User.getUsers().size(); i++){
                e = User.getUsers().get(i);
                if (!e.getRole().equals("patient")){
                    System.out.format("%-6s %-13s %-20s %-10s %-2d\n", e.getHospitalID(), e.getRole(), e.getName(), e.getGender(), e.getAge());
                    //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                }
            }
            System.out.println("-----------------------------------------------------------");
        }
    }

    public static void displayAdminList(int role){
        Administrator admin;
        if (role == 1){
            for (int i = 0; i<Administrator.getAdministrators().size(); i++){
                admin = Administrator.getAdministrators().get(i);
                System.out.format("%-6s %-20s %-10s %-2d\n", admin.getHospitalID(), admin.getName(), admin.getGender(), admin.getAge());
                //System.out.println(admin.getHospitalID() + '\t' + '\t' + admin.getName() + '\t' + admin.getGender() + '\t' + admin.getAge());
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender     Age\n");
            for (int i = 0; i<Administrator.getAdministrators().size(); i++){
                admin = Administrator.getAdministrators().get(i);
                System.out.format("%-6s %-13s %-20s %-10s %-2d\n", admin.getHospitalID(), admin.getRole(), admin.getName(), admin.getGender(), admin.getAge());
             }
        }
        System.out.println("\n");
        
    }

    public static void displayDoctorList(int role){
        Doctor doctor;
        if (role == 1){
            for (int i = 0; i<Doctor.getDoctors().size(); i++){
                doctor = Doctor.getDoctors().get(i);
                System.out.format("%-6s %-20s %-10s %-2d\n", doctor.getHospitalID(), doctor.getName(), doctor.getGender(), doctor.getAge());
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender     Age\n");
            for (int i = 0; i<Doctor.getDoctors().size(); i++){
                doctor = Doctor.getDoctors().get(i);
                System.out.format("%-6s %-13s %-20s %-10s %-2d\n", doctor.getHospitalID(), doctor.getRole(), doctor.getName(), doctor.getGender(), doctor.getAge());
             }
        }
        System.out.println("\n");
    }

    public static void displayPharmacistList(int role){
        Pharmacist pharma;
        if (role == 1){
            for (int i = 0; i<Pharmacist.getPharmacists().size(); i++){
                pharma = Pharmacist.getPharmacists().get(i);
                System.out.format("%-6s %-20s %-10s %-2d\n", pharma.getHospitalID(), pharma.getName(), pharma.getGender(), pharma.getAge());
             }
             System.out.println("-------------------------------------------");

        } else{
            System.out.format("ID     Role          Name                 Gender     Age\n");
            for (int i = 0; i<Pharmacist.getPharmacists().size(); i++){
                pharma = Pharmacist.getPharmacists().get(i);
                System.out.format("%-6s %-13s %-20s %-10s %-2d\n", pharma.getHospitalID(), pharma.getRole(), pharma.getName(), pharma.getGender(), pharma.getAge());
             }
        }
        System.out.println("\n");
    }

    public static void updateStaff(User u, String name, String role, String gender, int age){
        if (!name.isBlank() && !name.isEmpty()){
            u.setName(name);
        } if (!role.isBlank() && !role.isEmpty()){
            u.setRole(role);
        } if (!gender.isBlank() && !gender.isEmpty()){
            u.setGender(gender);
        } if (age != 0){
            u.setAge(age);
        }
        CSVService.replaceUser(u);
    }
    
    public static void addStaff(String name, char roleChar, char genderChar, int age, String defaultPass){
        String gender;
        String staffID;
        staffID = createID(roleChar);
        if (genderChar == 'M'){
            gender = "Male";
        } else{
            gender = "Female";
        }

        if (roleChar == 'D'){
            Doctor doctor = new Doctor(staffID, name, gender, age);
            CSVService.addDoctor(doctor);
            //CSVService.writeDoctor(doctor, Character.getNumericValue(staffID.charAt(3)-1));
            System.out.println("Doctor with ID " + staffID + " created with password " + defaultPass);
        } else{
            Pharmacist pharmacist = new Pharmacist(staffID, name, gender, age);
            CSVService.addPharmacist(pharmacist);
            System.out.println("Pharmacist with ID " + staffID + " created with password " + defaultPass);
        } 
    }

    public static User findStaffDetails(String ID){
        if (ID.startsWith("D") && ID.length()==4){
            for (int i = 0; i<Doctor.getDoctors().size(); i++){
                if (Doctor.getDoctors().get(i).getHospitalID().matches(ID)){
                    return Doctor.getDoctors().get(i);
                }
            }
            System.out.println("Error - Doctor with ID" + ID + "not found");
        } else if (ID.startsWith("P") && ID.length()==4){
            for (int i = 0; i<Pharmacist.getPharmacists().size(); i++){
                if (Pharmacist.getPharmacists().get(i).getHospitalID().matches(ID)){
                    return Pharmacist.getPharmacists().get(i);
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
            CSVService.removeDoctor((Doctor)u);
            Doctor.getDoctors().remove((Doctor)u);
        } else {
            CSVService.replacePharmacist((Pharmacist)u);
            Pharmacist.getPharmacists().remove((Pharmacist)u);
        }
        User.getUsers().remove(u);
    }

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
