package lt.mif.psp;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class EmailChecker {

    private Set<Character> specialSymbols;

    public EmailChecker() {
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

    public boolean hasAtSign(String email) {
         return email != null && email.contains("@");
    }

    public boolean checkBadSymbols(String email) {

        String prefix = getEmailPrefix(email);
        String domain = getEmailDomain(email);
        return prefix != null &&
                domain != null &&
                specialSymbols.stream().noneMatch(s -> prefix.contains(s.toString())) &&
                specialSymbols.stream().noneMatch(s -> domain.contains(s.toString()));
    }

    public boolean correctTLDCheck(String email) {
        if(email == null){
            return false;
        }
        String domain = getEmailDomain(email);
        if(domain == null){
            return false;
        }
        String[] domains = domain.split("\\.");
        return validateEmailDomainLength(domains[domains.length-1]) && validateSubAndTopDomain(domains[domains.length-1]);
    }

    public boolean correctDomainCheck(String email) {
        if(email == null){
            return false;
        }
        String domain = getEmailDomain(email);
        return validateEmailDomainLength(domain) && validateSubAndTopDomain(domain);
    }

    public boolean notEmpty(String email) {
        return !email.isBlank();
    }

    private String getEmailDomain(String email){
        return hasAtSign(email) && email.length() >= email.indexOf("@") ? email.substring(email.indexOf('@') + 1).toLowerCase(Locale.ROOT) : null;
    }

    private String getEmailPrefix(String email){
        return hasAtSign(email) ? email.substring(0, email.indexOf('@')).toLowerCase(Locale.ROOT) : null;
    }

    private boolean validateEmailDomainLength(String domain){
        return domain != null && domain.length() >= 2 && domain.length() <= 253;
    }

    private boolean validateSubAndTopDomain(String domain) {
        if(domain == null){
            return false;
        }
        if(domain.length() <= 1){
            return false;
        }
        String subDomain = domain.contains(".") ? domain.substring(0, domain.indexOf('.')) : domain;
        if(specialSymbols.stream().anyMatch(s -> subDomain.contains(s.toString()))){
            return false;
        }
        if (subDomain.charAt(0) == '-' || subDomain.charAt(subDomain.length() - 1) == '-') {
            return false;
        }
        if(domain.contains(".")) {
            return validateSubAndTopDomain(domain.substring(domain.indexOf(".") + 1));
        }
        return true;
    }

}
