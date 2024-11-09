import java.util.Collections;
import java.util.Comparator;

public class StaffService {

    public static void displayStaffList(int byRole){
        if (byRole == 1){
            System.out.println("--------------Doctors---------------");
            System.out.println("Hospital ID\tName\t\tGender\tAge");
            System.out.println("-----------------------------------");
            displayDoctorList(byRole);

            System.out.println("------------Pharmacists------------");
            System.out.println("Hospital ID\tName\t\tGender\tAge");
            System.out.println("-----------------------------------");
            displayPharmacistList(byRole);

            System.out.println("---------------Admins---------------");
            System.out.println("Hospital ID\tName\t\tGender\tAge");
            System.out.println("-----------------------------------");
            displayAdminList(byRole);

        } else{
            System.out.println("Hospital ID\tRole\t\tName\t\tGender\tAge");
            System.out.println("-----------------------------------------------------------");
            Collections.sort(User.users, Comparator.comparing(User::getName));
            User e;
            for (int i = 0; i<User.users.size(); i++){
                e = User.users.get(i);
                if (!e.getRole().equals("patient")){
                    System.out.format("%-6s%-6s%-20s%-6s%-2d", e.getHospitalID(), e.getRole(), e.getName(), e.getGender(), e.getAge());
                    //System.out.println(e.getHospitalID() + '\t' + '\t' + e.getRole() + '\t' + e.getName() + '\t' + e.getGender() + '\t' + e.getAge());
                }
            }
        }
    }

    public static void displayAdminList(int role){
        Administrator admin;
        if (role == 1){
            for (int i = 0; i<Administrator.administrators.size(); i++){
                admin = Administrator.administrators.get(i);
                System.out.format("%-6s%-20s%-6s%-2d", admin.getHospitalID(), admin.getName(), admin.getGender(), admin.getAge());
                //System.out.println(admin.getHospitalID() + '\t' + '\t' + admin.getName() + '\t' + admin.getGender() + '\t' + admin.getAge());
             }

        } else{
            System.out.println("Hospital ID\tRole\tName\tGender\tAge");
            for (int i = 0; i<Administrator.administrators.size(); i++){
                admin = Administrator.administrators.get(i);
                System.out.println(admin.getHospitalID() + '\t' + '\t' + admin.getRole() + '\t' + admin.getName() + '\t' + '\t' + admin.getGender() + '\t' + admin.getAge());
             }
        }
        System.out.println("\n");
        
    }

    public static void displayDoctorList(int role){
        Doctor doctor;
        if (role == 1){
            for (int i = 0; i<Doctor.doctors.size(); i++){
                doctor = Doctor.doctors.get(i);
                System.out.println(doctor.getHospitalID() + '\t' + '\t' + doctor.getName() + '\t' + doctor.getGender() + '\t' + doctor.getAge());
             }

        } else{
            System.out.println("Hospital ID\tRole\tName\tGender\tAge");
            for (int i = 0; i<Doctor.doctors.size(); i++){
                doctor = Doctor.doctors.get(i);
                System.out.println(doctor.getHospitalID()  + '\t' + '\t' + doctor.getRole() + '\t' + doctor.getName() + '\t' + '\t' + doctor.getGender() + '\t' + doctor.getAge());
             }
        }
        System.out.println("\n");
    }

    public static void displayPharmacistList(int role){
        Pharmacist pharma;
        if (role == 1){
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                pharma = Pharmacist.pharmacists.get(i);
                System.out.println(pharma.getHospitalID() + '\t' + '\t' + pharma.getName() + '\t' + pharma.getGender() + '\t' + pharma.getAge());
             }

        } else{
            System.out.println("Hospital ID\tRole\tName\tGender\tAge");
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                pharma = Pharmacist.pharmacists.get(i);
                System.out.println(pharma.getHospitalID() + '\t' + '\t' + pharma.getRole() + '\t' + pharma.getName() + '\t' + '\t' + pharma.getGender() + '\t' + pharma.getAge());
             }
        }
        System.out.println("\n");
    }

    public static void updateStaff(){

    }
    
    public static void addStaff(){

    }

    public static void removeStaff(){

    }
}
