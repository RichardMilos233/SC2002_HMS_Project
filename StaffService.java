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

    public static void displayAdminList(int role){
        Administrator admin;
        if (role == 1){
            for (int i = 0; i<Administrator.administrators.size(); i++){
                admin = Administrator.administrators.get(i);
                System.out.println(admin.getHospitalID() + '\t' + admin.getName() + '\t' + admin.getGender() + '\t' + admin.getAge());
             }
        } else{
            for (int i = 0; i<Administrator.administrators.size(); i++){
                admin = Administrator.administrators.get(i);
                System.out.println(admin.getHospitalID() + '\t' + admin.getRole() + '\t' + admin.getName() + '\t' + admin.getGender() + '\t' + admin.getAge());
             }
        }
        
    }

    public static void displayDoctorList(int role){
        Doctor doctor;
        if (role == 1){
            for (int i = 0; i<Doctor.doctors.size(); i++){
                doctor = Doctor.doctors.get(i);
                System.out.println(doctor.getHospitalID() + '\t' + doctor.getName() + '\t' + doctor.getGender() + '\t' + doctor.getAge());
             }
        } else{
            for (int i = 0; i<Doctor.doctors.size(); i++){
                doctor = Doctor.doctors.get(i);
                System.out.println(doctor.getHospitalID()  + '\t' + doctor.getRole() + '\t' + doctor.getName() + '\t' + doctor.getGender() + '\t' + doctor.getAge());
             }
        }
    }

    public static void displayPharmacistList(int role){
        Pharmacist pharma;
        if (role == 1){
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                pharma = Pharmacist.pharmacists.get(i);
                System.out.println(pharma.getHospitalID() + '\t' + pharma.getName() + '\t' + pharma.getGender() + '\t' + pharma.getAge());
             }
        } else{
            for (int i = 0; i<Pharmacist.pharmacists.size(); i++){
                pharma = Pharmacist.pharmacists.get(i);
                System.out.println(pharma.getHospitalID() + '\t' + pharma.getRole() + '\t' + pharma.getName() + '\t' + pharma.getGender() + '\t' + pharma.getAge());
             }
        }
    }

    public static void updateStaff(){

    }
    
    public static void addStaff(){

    }

    public static void removeStaff(){

    }
}
