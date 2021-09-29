package lt.mif.psp;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Validator {

    PasswordChecker pwd = new PasswordChecker();
    PhoneChecker phone = new PhoneChecker();
    EmailChecker email = new EmailChecker();


    //Validate password
    //Check if password is longer than X
    @Test
    void passwordValidator_isLongerThanX() {
        assertTrue(pwd.passwordLength("password",8));
    }

    //Check if password has uppercase letters
    @Test
    void passwordValidator_hasUppercase() {
        assertTrue(pwd.hasUpperCase("Password"));
    }

    //Check if password has special symbols
    @Test
    void passwordValidator_hasSpecialCharacters() {
        assertTrue(pwd.hasSpecialCharacter("Password@"));
    }

    //Validate phone

    @Test
    void phoneValidator_isNotEmpty() {
        assertTrue(phone.isNotEmpty("+37061234567"));
    }

    @Test
    void phoneValidator_hasOnlyNumbers() {
        assertTrue(phone.onlyNumbers("861234567"));
    }

    @Ignore("No way PhoneChecker to get instruction for 'Lithuania'")
    @Test
    void phoneValidator_isNotLongerThanX() {
        assertTrue(phone.numberLength("+37061234567","Lithuania"));
    }

    @Ignore("No way PhoneChecker to get instruction for 'Lithuania'")
    @Test
    void phoneValidator_validCountryCode() {
        assertTrue(phone.checkCountryCode("+37061234567","Lithuania"));
    }

    //Validate email

    @Test
    void emailValidator_isNotEmpty() {
        assertTrue(email.notEmpty("email@email.com"));
    }

    @Test
    void emailValidator_hasAtSign() {
        assertTrue(email.hasAtSign("email@email.com"));
    }

    @Test
    void emailValidator_hasNoBadSymbols() {
        assertTrue(email.checkBadSymbols("email@email.com"));
    }

    @Test
    void emailValidator_hasCorrectTLD() {
        assertTrue(email.correctTLDCheck("email@email.com"));
    }

    @Test
    void emailValidator_hasCorrectDomain() {
        assertTrue(email.correctDomainCheck("email@email.com"));
    }

}
