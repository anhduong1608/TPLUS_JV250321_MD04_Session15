package validator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validation {
    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isPositiveInteger(String input) {
        if (isEmpty(input)) return false;
        try {
            int number = Integer.parseInt(input);
            return number > 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isIntegerInRange(String input, int min, int max) {
        if (isEmpty(input)) return false;
        try {
            int number = Integer.parseInt(input);
            return number >= min && number <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String input, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        if (isEmpty(input)) return false;
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
