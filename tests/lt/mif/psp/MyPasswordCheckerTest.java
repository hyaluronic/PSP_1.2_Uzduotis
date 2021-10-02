package lt.mif.psp;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MyPasswordCheckerTest {

    PasswordChecker passwordChecker = new PasswordChecker();

    @Test
    void validatePassword() {
        final String password = "123456ASDFGH@#$%";
        assertAll(
                () -> assertTrue(passwordChecker.passwordLength(password, 10)),
                () -> assertTrue(passwordChecker.hasSpecialCharacter(password)),
                () -> assertTrue(passwordChecker.hasUpperCase(password))
        );
        final String passwordTwo = "123456asd";
        assertAll(
                () -> assertFalse(passwordChecker.passwordLength(passwordTwo, 10)),
                () -> assertFalse(passwordChecker.hasSpecialCharacter(passwordTwo)),
                () -> assertFalse(passwordChecker.hasUpperCase(passwordTwo))
        );
    }

    @Test
    void setSpecialSymbols() {
        passwordChecker.setSpecialSymbols(Set.of('a', 'b'));
        assertTrue(passwordChecker.hasSpecialCharacter("a"));
        assertFalse(passwordChecker.hasSpecialCharacter("c!@#$12"));
    }

    @Test
    void addSpecialSymbol() {
        String password = "12345";
        assertFalse(passwordChecker.hasSpecialCharacter(password));
        passwordChecker.addSpecialSymbol('1');
        assertTrue(passwordChecker.hasSpecialCharacter(password));
    }

    @Test
    void removeSpecialSymbol() {
        String password = "a!";
        assertTrue(passwordChecker.hasSpecialCharacter(password));
        passwordChecker.removeSpecialSymbol('!');
        assertFalse(passwordChecker.hasSpecialCharacter(password));
    }
}