package lt.mif.psp;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    PasswordChecker passwordChecker = new PasswordChecker();

    @Test
    void setSpecialSymbols() {
        passwordChecker.setSpecialSymbols(Set.of('a','b'));
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