public class StaffService {

    public static void displayStaffList(int byRole){
        System.out.println("Hospital ID \t Role \t Name \t Gender \t Age");
        if (byRole == 1){
            System.out.println("------------Doctors------------ \n");
            displayDoctorList(byRole);
            System.out.println("------------Pharmacists------------ \n");
            displayPharmacistList(byRole);
            System.out.println("------------Admins------------ \n");
            displayAdminList(byRole);

        }
    }

    public static void displayAdminList(int staff){
        
    }

    public static void displayDoctorList(int role){
        Doctor d;
        if (role == 1){
            for (int i = 0; i<Doctor.doctors.size(); i++){
                d = Doctor.doctors.get(i);
                System.out.println(d.getHospitalID() + '\t' + d.getName() + '\t' + d.getGender() + '\t' + d.getAge());
             }
        } else{
            for (int i = 0; i<Doctor.doctors.size(); i++){
                d = Doctor.doctors.get(i);
                System.out.println(d.getHospitalID()  + '\t' + d.getRole() + '\t' + d.getName() + '\t' + d.getGender() + '\t' + d.getAge());
             }
        }
    }

    public static void displayPharmacistList(int staff){
        
    }

    public static void updateStaff(){

    }
    
    public static void addStaff(){

    }

    public static void removeStaff(){

    }
}
