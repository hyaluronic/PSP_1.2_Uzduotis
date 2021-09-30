package lt.mif.psp;

import java.util.Set;

public class ValidationConstants {
    public static final ValidationRule DEFAULT_VALIDATION_RULE = new ValidationRule(11, "8", "+370");
    public static final Set<Character> SPECIAL_SYMBOLS = Set.of('!', '#', '$', '%', '^', '&', '*', '(',
            ')', '/', '~', '[', ']', '@');
}
