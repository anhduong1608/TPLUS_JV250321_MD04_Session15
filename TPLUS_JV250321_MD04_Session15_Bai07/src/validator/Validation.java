package validator;

import java.math.BigDecimal;

public class Validation {
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isIntegerInRange(String input, int min, int max) {
        if (!isNotEmpty(input)) return false;
        try {
            int number = Integer.parseInt(input);
            return number >= min && number <= max;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean isPositiveInteger(String input) {
        if (!isNotEmpty(input)) return false;
        try {
            int number = Integer.parseInt(input);
            return number > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidLength(String input, int length) {
        return isNotEmpty(input) && input.length() <= length;
    }

    public static boolean isValidDecimal(String input, int precision, int scale) {
        try {
            BigDecimal number = new BigDecimal(input);

            int actualScale = number.scale();
            int actualPrecision = number.precision();

            return actualPrecision <= precision && actualScale <= scale;
        } catch (Exception e) {
            return false;
        }
    }
}
