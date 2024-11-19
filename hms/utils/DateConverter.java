package hms.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
/**
 * Provides utility methods for handling date conversions and age calculations based on dates.
 */
public class DateConverter {
    /**
     * Calculates the age in years based on a given birth date up to the current date.
     * If the birth date provided is after the current date, it throws an IllegalArgumentException.
     *
     * @param birthDate The birth date to calculate the age from.
     * @return The calculated age in years.
     * @throws IllegalArgumentException If the birth date is in the future.
     */
    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null && birthDate.isBefore(currentDate)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            throw new IllegalArgumentException("Birthdate cannot be in the future");
        }
    }
    
    /**
     * Converts a string representing a date in ISO-8601 format (e.g., "yyyy-MM-dd") into a LocalDate object.
     *
     * @param date The string representation of the date to be converted.
     * @return The LocalDate object corresponding to the input string.
     * @throws DateTimeParseException If the text cannot be parsed or the format is not valid.
     */
    public static LocalDate convertDate(String date){
        return LocalDate.parse(date);
    }
}
