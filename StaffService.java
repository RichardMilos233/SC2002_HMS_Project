import java.util.*;

public class StaffService {

    public static void displayStaffList(int byCategory){
        // role = 1, name = 2, ID= 3, age=4, gender=5
        //Display a list of staff filtered by role, gender, age, etc
        if (byCategory == 1){
            System.out.println("--------------Doctors----------------------");
            System.out.format("ID     Name                 Gender     Age\n");
            System.out.println("-------------------------------------------");
            displayDoctorList(byCategory);

            System.out.println("--------------Pharmacists------------------");
            System.out.format("ID     Name                 Gender     Age\n");
            System.out.println("-------------------------------------------");
            displayPharmacistList(byCategory);

            System.out.println("--------------Admins-----------------------");
            System.out.format("ID     Name                 Gender     Age\n");
            System.out.println("-------------------------------------------");
            displayAdminList(byCategory);

        } else if (byCategory==2){
            System.out.format("ID     Role          Name                 Gender     Age\n");
            System.out.println("-----------------------------------------------------------");
            Collections.sort(User.getUsers(), Comparator.comparing(User::getName));
            User e;
            for (int i = 0; i<User.getUsers().size(); i++){
                e = User.getUsers().get(i);
                if (!e.getRole().equals("patient")){
                    System.out.format("%-6s %-13s %-20s %-10s %-2d\n", e.getHospitalID(), e.getRole(), e.getName(), e.getGender(), e.getAge());
                    //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                }
            }
            System.out.println("-----------------------------------------------------------");
        } else if (byCategory==3){
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
        } else if (byCategory==4){
            System.out.format("ID     Role          Name                 Gender     Age\n");
            System.out.println("-----------------------------------------------------------");
            Collections.sort(User.getUsers(), Comparator.comparing(User::getAge));
            User e;
            for (int i = 0; i<User.getUsers().size(); i++){
                e = User.getUsers().get(i);
                if (!e.getRole().equals("patient")){
                    System.out.format("%-6s %-13s %-20s %-10s %-2d\n", e.getHospitalID(), e.getRole(), e.getName(), e.getGender(), e.getAge());
                    //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                }
            }
            System.out.println("-----------------------------------------------------------");
        } else if (byCategory==5){
            System.out.format("ID     Role          Name                 Gender     Age\n");
            System.out.println("-----------------------------------------------------------");
            Collections.sort(User.getUsers(), Comparator.comparing(User::getGender));
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

    public static User updateStaff(User u, String name, String role, String gender, int age){
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
            String[] hashSalt = CSVService.findCredential(u.getHospitalID());
            removeStaff(u);
            int hash = Integer.parseInt(hashSalt[0]);
            String salt = hashSalt[1];
            String staffID = addStaff(name, role.toUpperCase().charAt(0), gender.toUpperCase().charAt(0), age, "");
            CSVService.addCredential(staffID, hash, salt);
            User.updateUsers();
            System.out.println("Successfully updated to " + role.toUpperCase().charAt(0) + role.substring(1) + " with new ID " + staffID +"\n");
            return findStaffDetails(staffID);
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
        
        
        // if (role.contains("doctor")){
        //     Doctor.updateDoctors();
        // } else{
        //     Pharmacist.updatePharmacists();
        // }
    }
    
    public static String addStaff(String name, char roleChar, char genderChar, int age, String defaultPass){
        Hasher hasher = new SimpleAdditiveHash();
        String gender;
        String staffID;
        String role;
        staffID = createID(roleChar);
        if (genderChar == 'M'){
            gender = "Male";
        } else{
            gender = "Female";
        }

        if (roleChar == 'D'){
            role = "doctor";
        } else{
            role = "pharmacist";
        }

        if (role.equals("doctor")){
            Doctor doctor = new Doctor(staffID, name, gender, age);
            CSVService.addDoctor(doctor);
            //CSVService.writeDoctor(doctor, Character.getNumericValue(staffID.charAt(3)-1));
        } else{
            Pharmacist pharmacist = new Pharmacist(staffID, name, gender, age);
            CSVService.addPharmacist(pharmacist);
        } 
        if (!defaultPass.isBlank()){
            String salt = Salter.createSaltString();
            CSVService.addCredential(staffID, hasher.hash(defaultPass, salt), salt);
            System.out.println(roleChar + role.substring(1) + " with ID " + staffID + " created with password " + defaultPass);
            User.updateUsers();
        }
        return staffID;
    }

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

    public static void removeStaff(User u){
        String role = u.getRole();
        if (role.startsWith("d")){
            CSVService.removeDoctor((Doctor)u);
            Doctor.getDoctors().remove((Doctor)u);
        } else {
            CSVService.removePharmacist((Pharmacist)u);
            Pharmacist.getPharmacists().remove((Pharmacist)u);
        }
        CSVService.removeCredention(u.getHospitalID());
        User.getUsers().remove(u);
        User.updateUsers();
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
