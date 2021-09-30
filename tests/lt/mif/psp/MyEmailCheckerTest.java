package lt.mif.psp;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyEmailCheckerTest {

    private static final EmailChecker emailChecker = new EmailChecker();

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