package lt.mif.psp;

public class ValidationRule {
    private final int length;
    private final String areaCode;
    private final String countryCode;

    public ValidationRule(int length, String areaCode, String prefixLong) {
        this.length = length;
        this.areaCode = areaCode;
        this.countryCode = prefixLong;
    }

    public int getLength() {
        return length;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
