package lt.mif.psp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyPhoneCheckerTest {

    private static final PhoneChecker phoneChecker = new PhoneChecker();
    private final static ValidationRule RULE_1 = new ValidationRule(5, "0", "+371");

    @BeforeEach
    public void setUp() {
        phoneChecker.addValidationRule("RULE_1", RULE_1);
    }

    //rewrote his test that could not work, as I was unable to do a PR
    @Test
    void phoneValidator_isNotLongerThanX() {
        phoneChecker.addValidationRule("Lithuania", new ValidationRule(11, "8", "+370"));
        assertTrue(phoneChecker.numberLength("+37061234567", "Lithuania"));
    }

    //rewrote his test that could not work, as I was unable to do a PR
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
    void validAreaCode() {
        assertTrue(phoneChecker.isPrefixValid("8"));
    }

    @Test
    void validCountryCode() {
        assertTrue(phoneChecker.isPrefixValid("+370"));
    }

    @Test
    void validCountryCodeWithValidationRule() {
        assertTrue(phoneChecker.isPrefixValid("+371", "RULE_1"));
        assertFalse(phoneChecker.isPrefixValid("+370", "RULE_1"));
    }

    @Test
    void validLengthWithValidationRule() {
        assertTrue(phoneChecker.numberLength("+37112", "RULE_1"));
        assertTrue(phoneChecker.numberLength("12345", "RULE_1"));
        assertFalse(phoneChecker.numberLength("+37061234567", "RULE_1"));
        assertFalse(phoneChecker.numberLength("1234567", "RULE_1"));
    }

    @Test
    void changeBeginningWithValidationRule() {
        assertEquals("+371123456", phoneChecker.changeBeginning("0123456", "RULE_1"));
        assertNotEquals("+371123456", phoneChecker.changeBeginning("1123456", "RULE_1"));
        assertEquals("1123456", phoneChecker.changeBeginning("1123456", "RULE_1"));
    }

}
