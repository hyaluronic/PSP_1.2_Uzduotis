package lt.mif.psp;

public class ValidationRule {
    private final int length;
    private final String prefixShort;
    private final String prefixLong;

    public ValidationRule(int length, String prefixShort, String prefixLong) {
        this.length = length;
        this.prefixShort = prefixShort;
        this.prefixLong = prefixLong;
    }

    public int getLength() {
        return length;
    }

    public String getPrefixShort() {
        return prefixShort;
    }

    public String getPrefixLong() {
        return prefixLong;
    }
}
