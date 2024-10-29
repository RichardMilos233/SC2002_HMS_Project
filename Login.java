import java.util.*;
import java.time.*;;

public class Login {
    public static User login(User user){
        List<String> credentials = getLoginCredentials();
        if (checkCredentials(credentials)){
            System.out.println("login success");
            user = allocateAccount(credentials);
            return user;
        }
        else{
            System.out.println("the account does not exist");
            return null;
        }
    }
    
    public static boolean checkCredentials (List<String> credentials){
        //initialize credentials.csv to store the data for user login: first column for hospitalId, second column for password
        // need to specify the format of the credentials.csv, i.e. what value is placed at which column
        String hospitalId = credentials.get(0);
        String password = credentials.get(1);

        String filePath = "credentials.csv";
        List<List<String>> accountList = ExcelService.readCsv(filePath);
        
        for (int i = 1; i < accountList.size(); i++) {
            if (hospitalId.equals(accountList.get(i).get(0))) { //hospitalId match
                if (password.equals(accountList.get(i).get(1))){    //password match
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> getLoginCredentials(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID");
        String hospitalIdInput = scanner.nextLine();
        System.out.println("Enter your password");
        String passwordInput = scanner.nextLine();

        List<String> credentials = new ArrayList<>();
        credentials.add(hospitalIdInput);
        credentials.add(passwordInput);

        scanner.close();
        return credentials;
    }

    public static User allocateAccount(List<String> credentials){   //allocate acc info from existing files
        String hospitalId = credentials.get(0);
        String password = credentials.get(1);

        if (hospitalId.length() == 5){//patient
            String filePath = "Patient_List.csv";
            List<List<String>> data = ExcelService.readCsv(filePath);
        
            for (int i = 1; i < data.size(); i++) {
                List<String> patientInfo = data.get(i);
                if (hospitalId.equals(patientInfo.get(0))) {    //hospitalId match
                    String name = patientInfo.get(1);
                    String gender = patientInfo.get(3);
                    LocalDate birth = LocalDate.parse(patientInfo.get(2));
                    int age = DateConverter.calculateAge(birth);
                    int contactNumber = Integer.parseInt(patientInfo.get(6));
                    String email = patientInfo.get(5);
                    String bloodType = patientInfo.get(4);

                    Patient patient = new Patient(hospitalId, password, name, gender, age, birth, contactNumber, email, bloodType);
                    return patient;
                }
            }
        }
        else if (hospitalId.length() == 4){//staff
            String filePath = "Staff_List.csv";
            List<List<String>> data = ExcelService.readCsv(filePath);

            for (int i = 1; i < data.size(); i++) {
                List<String> patientInfo = data.get(i);
                if (hospitalId.equals(patientInfo.get(0))) {    //hospitalId match
                    String name = patientInfo.get(1);
                    String gender = patientInfo.get(3);
                    int age = Integer.parseInt(patientInfo.get(4));

                    switch (hospitalId.charAt(0)) {
                        case 'D':
                            Doctor doctor = new Doctor(hospitalId, password, name, gender, age);
                            return doctor;
                            // break;
                        case 'P':
                            Pharmacist pharmacist = new Pharmacist(hospitalId, password, name, gender, age);
                            return pharmacist;
                        case 'A':
                            Administrator administrator = new Administrator(hospitalId, password, name, gender, age);
                            return administrator;
                    
                        default:
                            break;
                    }
                }
            }
        }
        return null;    //just to fulfill the return type requirement
    }
}
