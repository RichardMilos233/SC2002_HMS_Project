import java.time.LocalDate;
import java.time.Period;

public class DateConverter {
    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null && birthDate.isBefore(currentDate)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            throw new IllegalArgumentException("Birthdate cannot be in the future");
        }
    }
    
    public static LocalDate converDate(String date){
        return LocalDate.parse(date);
    }
}
