import java.util.Scanner;

public class Validator {
    
    public static int validateInt(Scanner scanner) { // ensure int is entered
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
        } 
        handleExtraInput(scanner);
        return input;
    }

    private static void handleExtraInput(Scanner scanner){
        if (scanner.hasNextLine()){
            scanner.nextLine();
        }
    }

    public static String validateName(Scanner scanner) {  // ensures string was entered, not too many spaces, no double space, formats name
        String name;
        while (true) { 
            if (scanner.hasNextLine()) { 
                name = scanner.nextLine(); 
                if (name.contains("  ") || (name.length()-name.trim().length()>4) || name.isBlank() || name.isEmpty() || name.length()<4){
                    System.out.println("Invalid. Please enter a Name: "); 
                } else{
                    break;
                }
                // Input is valid, exit the loop 
            } else { 
                System.out.println("Invalid. Please enter a Name: "); 
                scanner.next();  
                // Clear the invalid input 
            }
        } 
        name = formatName(name);
        return name;
    }

    private static String formatName(String name) {  // makes name have upper case initials and lower case for all others 
        // Name formatting
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        for (int i = 0; i<name.length()-1; i++){
            if (name.charAt(i) == ' ' && name.charAt(i+1)!=' '){
                name = name.substring(0,i+1) + name.substring(i+1, i+2).toUpperCase() + name.substring(i+2).toLowerCase();
            }
        }
        return name;
    }

    public static String validateStringNoSpace(Scanner scanner) { // ensures string has no space in it 
        String input; 
        while (true) { 
            if (scanner.hasNextLine()) { 
                input = scanner.nextLine(); 
                if (input.isBlank()){
                    continue;
                }
                else if (input.contains(" ") || input.isBlank()){
                    System.out.println("Invalid. Please enter a string without using space: "); 
                } else{
                    break;
                }
                // Input is valid, exit the loop 
            } else { 
                System.out.println("Invalid. Please enter a string without using space: "); 
                scanner.next();  
                // Clear the invalid input 
            }
        } 
        return input;
    }
    public static String validateNotes(Scanner scanner) { // ensures string has no space in it 
        String input; 
        while (true) { 
            if (scanner.hasNextLine()) { 
                input = scanner.nextLine(); 
                if (input.isBlank()){
                    continue;
                }
                else if (input.isBlank()){
                    System.out.println("Invalid. Please enter a string: "); 
                } else{
                    break;
                }
                // Input is valid, exit the loop 
            } else { 
                System.out.println("Invalid. Please enter a string: "); 
                scanner.next();  
                // Clear the invalid input 
            }
        } 
        return input;
    }

    public static char validateChar(Scanner scanner) {  // ensures string with only 1 char is entered
        String input; 
        while (true) { 
            if (scanner.hasNext()) { 
                input = scanner.next(); 
                if (input.length()==1){
                    break;
                } else{
                    System.out.println("Invalid. Please enter a string without using space: "); 
                }
                // Input is valid, exit the loop 
            } else { 
                System.out.println("Invalid. Please enter a string without using space: "); 
                scanner.next();  
                // Clear the invalid input 
            }
        } 
        handleExtraInput(scanner);
        return input.charAt(0);
    }

    public static char validateCharToUpper(Scanner scanner) {  // ensures string with only 1 char is entered and converts to uppercase
        String input; 
        while (true) { 
            if (scanner.hasNext()) { 
                input = scanner.next().toUpperCase(); 
                if (input.length()==1){
                    break;
                } else{
                    System.out.println("Invalid. Please enter a string without using space: "); 
                }
                // Input is valid, exit the loop 
            } else { 
                System.out.println("Invalid. Please enter a string without using space: "); 
                scanner.next();  
                // Clear the invalid input 
            }
        } 
        return input.charAt(0);
    }
}
