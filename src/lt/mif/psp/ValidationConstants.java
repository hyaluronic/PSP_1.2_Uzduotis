package lt.mif.psp;

import java.util.List;

public class ValidationConstants {
    public static final List<Character> NUMBERS = List.of('1', '2' ,'3', '4', '5', '6', '7', '8', '9', '0');
    public static final ValidationRule DEFAULT_VALIDATION_RULE = new ValidationRule(11, "8", "+370");
    public static final List<Character> SPECIAL_SYMBOLS = List.of('!', '#', '$', '%', '^', '&', '*', '(',
            ')', '/', '~', '[', ']', '@');
}
