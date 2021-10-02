package lt.mif.psp;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MyEmailCheckerTest {

    private static final EmailChecker emailChecker = new EmailChecker();

    @Test
    void validateEmail() {
        final String email = "emailas@mailas.com";
        assertAll(
                () -> assertTrue(emailChecker.hasAtSign(email)),
                () -> assertTrue(emailChecker.checkBadSymbols(email)),
                () -> assertTrue(emailChecker.correctDomainCheck(email))
        );
        final String emailTwo = "emailas@ma$las.com";
        assertAll(
                () -> assertTrue(emailChecker.hasAtSign(emailTwo)),
                () -> assertFalse(emailChecker.checkBadSymbols(emailTwo)),
                () -> assertFalse(emailChecker.correctDomainCheck(emailTwo))
        );
    }

    @Test
    void setSpecialSymbols() {
        emailChecker.setSpecialSymbols(Set.of('!', '#'));
        assertTrue(emailChecker.checkBadSymbols("asd2$&@gmail.com"));
        assertFalse(emailChecker.checkBadSymbols("!#@gmail.com"));
    }

    @Test
    void addSpecialSymbol() {
        String email = "asde2@gmail.com";
        assertTrue(emailChecker.checkBadSymbols(email));
        emailChecker.addSpecialSymbol('2');
        assertFalse(emailChecker.checkBadSymbols(email));
    }

    @Test
    void removeSpecialSymbol() {
        String email = "asd!!@gmail.com";
        assertFalse(emailChecker.checkBadSymbols(email));
        emailChecker.removeSpecialSymbol('!');
        assertTrue(emailChecker.checkBadSymbols(email));
    }
}