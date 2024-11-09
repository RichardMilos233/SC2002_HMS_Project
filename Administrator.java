import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	public static Administrator getAdministrator(){	//list out all admins, then select an admin
		Scanner scanner = new Scanner(System.in);
		Administrator administrator;
        int i;
        int choice = -1;

		System.out.println("Choose the doctor you want:");
		for (i = 0; i < Administrator.administrators.size(); i++){
			administrator = Administrator.administrators.get(i);
			System.out.println(i+1 + ": " + administrator.getName());
		}

		choice = scanner.nextInt();
		if (choice <1 || choice > Doctor.doctors.size()){
			return null;
		}
		
        administrator = Administrator.administrators.get(choice-1);
		return administrator;
	}

	/* public void displayStaffList() {
		// TODO - implement Administrator.displayStaffList
		throw new UnsupportedOperationException();
	}

	public void addStaff() {
		// TODO - implement Administrator.addStaff
		throw new UnsupportedOperationException();
	}

	public void updateStaff() {
		// TODO - implement Administrator.updateStaff
		throw new UnsupportedOperationException();
	}

	public void removeStaff() {
		// TODO - implement Administrator.removeStaff
		throw new UnsupportedOperationException();
	}

	public void viewAppointmentDetails() {
		// TODO - implement Administrator.viewAppointmentDetails
		throw new UnsupportedOperationException();
	}

	public void viewInventory() {
		// TODO - implement Administrator.viewInventory
		throw new UnsupportedOperationException();
	}

	public void addStock() {
		// TODO - implement Administrator.addStock
		throw new UnsupportedOperationException();
	}

	public void removeStock() {
		// TODO - implement Administrator.removeStock
		throw new UnsupportedOperationException();
	}

	public void updateStock() {
		// TODO - implement Administrator.updateStock
		throw new UnsupportedOperationException();
	}

	public void updateStockAlert() {
		// TODO - implement Administrator.updateStockAlert
		throw new UnsupportedOperationException();
	}

	public void approveReplenishmentRequest() {
		// TODO - implement Administrator.approveReplenishmentRequest
		throw new UnsupportedOperationException();
	} */

}