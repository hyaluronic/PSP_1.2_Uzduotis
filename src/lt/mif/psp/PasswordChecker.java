package lt.mif.psp;

import java.util.Set;

public class PasswordChecker {

    private Set<Character> specialSymbols;

    public PasswordChecker() {
        this.specialSymbols = ValidationConstants.SPECIAL_SYMBOLS;
    }

    /**
     *
     * @param specialSymbols
     * sets specialSymbols if 'specialSymbols' is not null.
     */
    public void setSpecialSymbols(Set<Character> specialSymbols) {
        if (specialSymbols != null) {
            this.specialSymbols = specialSymbols;
        }
    }

    /**
     *
     * @param specialSymbol
     * adds 'specialSymbol' to specialSymbols set if it is not there.
     */
    public void addSpecialSymbol(char specialSymbol) {
        this.specialSymbols.add(specialSymbol);
    }

    /**
     *
     * @param specialSymbol
     * removes 'specialSymbol' from specialSymbols set if it is there.
     */
    public void removeSpecialSymbol(char specialSymbol) {
        this.specialSymbols.remove(specialSymbol);
    }

    /**
     *
     * @param password
     * @return true if 'password' is not blank and
     * length is greater or equal to given 'length'.
     * Returns false otherwise.
     */
    public boolean passwordLength(String password, int length) {
        return !password.isBlank() && password.length() >= length;
    }

    /**
     *
     * @param password
     * @return true if 'password' is not blank and
     * has at least one uppercase character.
     * Returns false otherwise.
     */
    public boolean hasUpperCase(String password) {
        return !password.isBlank() && password.chars().anyMatch(Character::isUpperCase);
    }

    /**
     *
     * @param password
     * @return true if 'password' is not blank and
     * has at least one special character.
     * Returns false otherwise.
     */
    public boolean hasSpecialCharacter(String password) {
        return !password.isBlank() && specialSymbols.stream().anyMatch(s -> password.contains(s.toString()));
    }

}
