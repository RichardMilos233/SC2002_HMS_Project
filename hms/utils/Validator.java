package hms.utils;

import java.util.Scanner;
/**
 * Provides methods to validate various types of user inputs using a Scanner.
 */
public class Validator {
    /**
     * Validates that the input from the scanner is an integer.
     *
     * @param scanner The scanner to read the input from.
     * @return The validated integer.
     */
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

    /**
     * Clears any extra input from the scanner.
     *
     * @param scanner The scanner to clear.
     */
    private static void handleExtraInput(Scanner scanner){
        if (scanner.hasNextLine()){
            scanner.nextLine();
        }
    }

    /**
     * Validates that the input is a proper name string. Ensures the name does not have multiple spaces,
     * not just spaces, and has a minimum length of 4 characters.
     *
     * @param scanner The scanner to read the input from.
     * @return The validated and formatted name string.
     */
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

    /**
     * Formats the input name string to have each word start with an uppercase letter followed by lowercase letters.
     *
     * @param name The name string to format.
     * @return The formatted name string.
     */
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

    /**
     * Validates that the input string contains no spaces.
     *
     * @param scanner The scanner to read the input from.
     * @return The validated string.
     */
    public static String validateStringNoSpace(Scanner scanner) { // ensures string has no space in it 
        String input; 
        while (true) { 
            if (scanner.hasNextLine()) { 
                input = scanner.nextLine(); 
                if (input.contains(" ") || input.isBlank()){
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

    /**
     * Validates that the input string is a valid email address. Assumes an email contains '@'.
     *
     * @param scanner The scanner to read the input from.
     * @return The validated email string.
     */
    public static String validateEmail(Scanner scanner) { // ensures string has no space in it 
        String input; 
        while (true) { 
            if (scanner.hasNextLine()) { 
                input = scanner.nextLine(); 
                if (!input.contains("@") || input.isBlank()){
                    System.out.println("Invalid. Please enter a valid email: "); 
                } else{
                    break;
                }
                // Input is valid, exit the loop 
            } else { 
                System.out.println("Invalid. Please enter an email: "); 
                scanner.next();  
                // Clear the invalid input 
            }
        } 
        return input;
    }

    /**
     * Validates that the input from the scanner is a non-blank string. 
     * Ensures that the string is not empty and contains characters other than just spaces.
     *
     * @param scanner The scanner to read the input from.
     * @return The validated non-blank string.
     */
    public static String validateLine(Scanner scanner) { // ensures string has no space in it 
        String input; 
        while (true) { 
            if (scanner.hasNextLine()) { 
                input = scanner.nextLine(); 
                if (input.isBlank()){
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

    /**
     * Validates that the input from the scanner is a single character. 
     * Ensures that the input consists of exactly one character.
     *
     * @param scanner The scanner to read the input from.
     * @return The validated character.
     */
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

    /**
     * Validates a single character input and converts it to uppercase.
     *
     * @param scanner The scanner to read the input from.
     * @return The validated and uppercase character.
     */
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
