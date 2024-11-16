import java.util.*;

public class Administrator extends User { //ignore Staff first
	public static List<Administrator> administrators = new ArrayList<>();

	public Administrator(String staffID, String name, String gender, int age){
		super(staffID, name, gender, age);
		this.role = "administrator";
	}

	public static Administrator fromCSV(String data) {	// create a new admin then return
		String[] fields = data.split(",");
        String hospitalID = fields[0];
        String name = fields[1];
        String gender = fields[2];
        int age = Integer.parseInt(fields[3]);
        String role = fields[4];
        Administrator admin = new Administrator(hospitalID, name, gender, age);
        return admin;
    }

	public static Administrator getAdministrator(){	//list out all admins, then select an admin
		Scanner scanner = new Scanner(System.in);
		Administrator administrator;
        int i;
        int choice = -1;

		System.out.println("Choose the administrator you want:");
		for (i = 0; i < Administrator.administrators.size(); i++){
			administrator = Administrator.administrators.get(i);
			System.out.println(i+1 + ": " + administrator.getName());
		}

		choice = scanner.nextInt();
		if (choice <1 || choice > Administrator.administrators.size()){
			return null;
		}
		
        administrator = Administrator.administrators.get(choice-1);
		return administrator;
	}

	public static List<Administrator> getAdministrators(){
		if (administrators.size() == 0){
			administrators = CSVService.readAdminsFromCSV();
		}
		return administrators;
	}
}