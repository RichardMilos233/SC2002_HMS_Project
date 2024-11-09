import java.util.Collections;
import java.util.Comparator;

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
    
    public static void addStaff(){

    }

    public static void removeStaff(){

    }
}
