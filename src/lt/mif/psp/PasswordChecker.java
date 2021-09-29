package lt.mif.psp;

import java.util.List;
import java.util.Set;

public class PasswordChecker {

    private Set<Character> specialSymbols;

    public PasswordChecker() {
        this.specialSymbols = ValidationConstants.SPECIAL_SYMBOLS;
    }

    /***
     *
     * @param specialSymbols
     * sets specialSymbols if 'specialSymbols' is not null.
     */
    public void setSpecialSymbols(Set<Character> specialSymbols) {
        if(specialSymbols != null){
            this.specialSymbols = specialSymbols;
        }
    }

    public void addSpecialSymbol(char specialSymbol) {
        this.specialSymbols.add(specialSymbol);
    }

    public void removeSpecialSymbol(char specialSymbol) {
        this.specialSymbols.remove(specialSymbol);
    }

    public boolean passwordLength(String password, int length) {
        return password != null && password.length() >= length;
    }

    public boolean hasUpperCase(String password) {
        if(password == null){
            return false;
        }
        char ch;
        boolean uppercaseFlag = false;
        for(int i = 0; i < password.length(); i++){
            ch = password.charAt(i);
            if(Character.isUpperCase(ch)){
                uppercaseFlag = true;
                break;
            }
        }
        return uppercaseFlag;
    }

    public boolean hasSpecialCharacter(String password) {
        return password != null && specialSymbols.stream().anyMatch(s -> password.contains(s.toString()));
    }

}
