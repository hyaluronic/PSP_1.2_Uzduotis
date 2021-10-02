package lt.mif.psp;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class EmailChecker {

    private Set<Character> specialSymbols = new HashSet<>();

    public EmailChecker() {
        specialSymbols.addAll(ValidationConstants.SPECIAL_SYMBOLS);
    }

    /**
     * @param specialSymbols
     * sets specialSymbols if 'specialSymbols' is not null.
     */
    public void setSpecialSymbols(Set<Character> specialSymbols) {
        if (specialSymbols != null) {
            this.specialSymbols = specialSymbols;
        }
    }

    /**
     * @param specialSymbol
     * adds 'specialSymbol' to specialSymbols set if it is not there.
     */
    public void addSpecialSymbol(char specialSymbol) {
        this.specialSymbols.add(specialSymbol);
    }

    /**
     * @param specialSymbol
     * removes 'specialSymbol' from specialSymbols set if it is there.
     */
    public void removeSpecialSymbol(char specialSymbol) {
        this.specialSymbols.remove(specialSymbol);
    }

    /**
     * @param email
     * @return true if 'email' is not null and contains only one '@' sign.
     */
    public boolean hasAtSign(String email) {
        return email != null && email.chars().filter(p -> p == '@').count() == 1;
    }

    /**
     * @param email
     * @return true if 'email' has '@' sign and no specialSymbols.
     */
    public boolean checkBadSymbols(String email) {
        String prefix = getEmailPrefix(email);
        String domain = getEmailDomain(email);
        return prefix != null &&
                domain != null &&
                specialSymbols.stream().noneMatch(s -> prefix.contains(s.toString())) &&
                specialSymbols.stream().noneMatch(s -> domain.contains(s.toString()));
    }

    /**
     * @param email
     * @return true if 'email' is not null, 'email' has TLD, TLD is not null, length from 2 to 253,
     * and has no specialCharacters and first and last character is not '-'.
     */
    public boolean correctTLDCheck(String email) {
        if (email == null) {
            return false;
        }
        String domain = getEmailDomain(email);
        if (domain == null) {
            return false;
        }
        String[] domains = domain.split("\\.");
        String tld = domains[domains.length - 1];
        return domain.contains(".") &&
                validateEmailDomainLength(tld) &&
                specialSymbols.stream().noneMatch(s -> tld.contains(s.toString())) &&
                validateSubAndTopDomain(tld);
    }

    /**
     * @param email
     * @return true if 'email' is not null, 'email' has domain, domain is not null, length from 2 to 253,
     * and has no specialCharacters and first and last character is not '-'.
     */
    public boolean correctDomainCheck(String email) {
        if (email == null) {
            return false;
        }
        String domain = getEmailDomain(email);
        return validateEmailDomainLength(domain) &&
                domain.contains(".") &&
                validateSubAndTopDomain(domain);
    }

    /**
     * @param email
     * @return true if 'email' is not null and is not empty,
     */
    public boolean notEmpty(String email) {
        return !email.isEmpty();
    }

    private String getEmailDomain(String email) {
        return hasAtSign(email) && email.length() >= email.indexOf("@") ? email.substring(email.indexOf('@') + 1).toLowerCase(Locale.ROOT) : null;
    }

    private String getEmailPrefix(String email) {
        return hasAtSign(email) ? email.substring(0, email.indexOf('@')).toLowerCase(Locale.ROOT) : null;
    }

    private boolean validateEmailDomainLength(String domain) {
        return domain != null && domain.length() >= 2 && domain.length() <= 253;
    }

    private boolean validateSubAndTopDomain(String domain) {
        if (domain == null) {
            return false;
        }
        String subDomain = domain.contains(".") ? domain.substring(0, domain.indexOf('.')) : domain;
        if(subDomain.length() <=1){
            return false;
        }
        if (specialSymbols.stream().anyMatch(s -> subDomain.contains(s.toString()))) {
            return false;
        }
        if (subDomain.charAt(0) == '-' || subDomain.charAt(subDomain.length() - 1) == '-') {
            return false;
        }
        if (domain.contains(".")) {
            return validateSubAndTopDomain(domain.substring(domain.indexOf(".") + 1));
        }
        return true;
    }

}
