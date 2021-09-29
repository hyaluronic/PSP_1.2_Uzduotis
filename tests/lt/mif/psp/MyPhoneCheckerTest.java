package lt.mif.psp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyPhoneCheckerTest {

    private static final PhoneChecker phoneChecker = new PhoneChecker();

    @Test
    void phoneValidator_isNotLongerThanX() {
        phoneChecker.addValidationRule("Lithuania", new ValidationRule(11, "8", "+370"));
        assertTrue(phoneChecker.numberLength("+37061234567", "Lithuania"));
    }

    @Test
    void phoneValidator_validCountryCode() {
        phoneChecker.addValidationRule("Lithuania", new ValidationRule(11, "8", "+370"));
        assertTrue(phoneChecker.checkCountryCode("+37061234567", "Lithuania"));
    }

    @Test
    void changeShortToLongPrefix() {
        String phoneNumber = "865555546";
        assertEquals("+37065555546", phoneChecker.changeBeginning(phoneNumber));
    }

    @Test
    void validShortPrefix() {
        assertTrue(phoneChecker.isPrefixValid("8"));
    }

    @Test
    void validLongPrefix() {
        assertTrue(phoneChecker.isPrefixValid("+370"));
    }

}
