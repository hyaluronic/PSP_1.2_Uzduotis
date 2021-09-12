public class PhoneChecker {

    public boolean isNotEmpty(String number) {
        return number != "";
    }

    public boolean onlyNumbers(String number) {
        return true;
    }

    public String changeBeginning(String number,String country) {
        if (number.startsWith("8") && country == "Lithuania"){
            number.replaceFirst("8","+370");
        }
        return number;
    }

    public boolean numberLength(String number, String country) {
        return true;
    }

    public boolean checkCountryCode(String number, String country) {
        return true;
    }
}
