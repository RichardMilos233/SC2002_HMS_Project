public class StaffService {

    public static void displayStaffList(){
        System.out.println("Hospital ID \t Role \t Name \t Gender \t Age");
    }

    public static void displayAdminList(int staff){
        
    }

    public static void displayDoctorList(int staff){
        Doctor d;
        if (staff == 0){
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
