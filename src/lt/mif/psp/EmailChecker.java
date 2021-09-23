package lt.mif.psp;

import java.util.Locale;

public class EmailChecker {

    public boolean hasAtSign(String email) {
         return email != null && email.contains("@");
    }

    public boolean checkBadSymbols(String email) {

        String prefix = getEmailPrefix(email);
        String domain = getEmailDomain(email);
        return prefix != null &&
                domain != null &&
                ValidationConstants.SPECIAL_SYMBOLS.stream().noneMatch(s -> prefix.contains(s.toString())) &&
                ValidationConstants.SPECIAL_SYMBOLS.stream().noneMatch(s -> domain.contains(s.toString()));
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
        return validateSubAndTopDomain(domains[domains.length-1].toLowerCase(Locale.ROOT));
    }

    public boolean correctDomainCheck(String email) {
        if(email == null){
            return false;
        }
        String domain = getEmailDomain(email);
        return validateEmailDomainLength(domain) && validateSubAndTopDomain(domain.toLowerCase(Locale.ROOT));
    }

    public boolean notEmpty(String email) {
        return !email.isBlank();
    }

    private String getEmailDomain(String email){
        return hasAtSign(email) && email.length() >= email.indexOf("@") ? email.substring(email.indexOf('@') + 1) : null;
    }

    private String getEmailPrefix(String email){
        return hasAtSign(email) ? email.substring(0, email.indexOf('@')-1) : null;
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
        if(ValidationConstants.SPECIAL_SYMBOLS.stream().anyMatch(s -> subDomain.contains(s.toString()))){
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
