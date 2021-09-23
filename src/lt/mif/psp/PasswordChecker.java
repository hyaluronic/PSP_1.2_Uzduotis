package lt.mif.psp;

import java.util.List;

public class PasswordChecker {

    private List<Character> specialSymbols;

    public PasswordChecker() {
        this.specialSymbols = ValidationConstants.SPECIAL_SYMBOLS;
    }

    public void setSpecialSymbols(List<Character> specialSymbols) {
        this.specialSymbols = specialSymbols;
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
