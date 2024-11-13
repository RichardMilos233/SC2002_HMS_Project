import java.util.*;

public class Administrator extends User { //ignore Staff first
	public static List<Administrator> administrators = new ArrayList<>();

	public Administrator() {
		super();
		this.role = "administrator";
		administrators.add(this);
	}

	public Administrator(String staffID, String password, String name, String gender, int age){
		super(staffID, password, name, gender, age);
		this.role = "administrator";
		administrators.add(this);
	}

	public static Administrator fromCSV(String data) {	// create a new admin then return
		String[] fields = data.split(",");
        String hospitalID = fields[0];
        String password = fields[1];
        String name = fields[2];
        String gender = fields[3];
        int age = Integer.parseInt(fields[4]);
        String role = fields[5];
        Administrator admin = new Administrator(hospitalID, password, name, gender, age);
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

}