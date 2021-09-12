public class PasswordChecker {

    public boolean passwordLength(String password, int length) {
        if(password.length() >= length) {
            return true;
        }
        return false;
    }

    public boolean hasUpperCase(String password) {
        return !password.equals(password.toLowerCase());
    }

    public boolean hasSpecialCharacter(String password) {
        return true;
    }

}
