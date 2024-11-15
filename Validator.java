import java.util.Scanner;

public class Validator {
    public static int validateInt(Scanner scanner) { 
        int input; 
        while (true) { 
            if (scanner.hasNextInt()) { 
                input = scanner.nextInt(); 
                break; 
                // Input is valid, exit the loop 
            } else { 
                System.out.println("Invalid. Please enter a number: "); 
                scanner.next();  
                // Clear the invalid input 
            }
        } return input;
    }
}
