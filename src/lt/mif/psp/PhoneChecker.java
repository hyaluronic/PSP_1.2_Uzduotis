package lt.mif.psp;

import java.util.HashMap;
import java.util.Map;

public class PhoneChecker {
    private Map<String, ValidationRule> validationRules = new HashMap<>();

    public PhoneChecker() {
        this.validationRules.put("DEFAULT_VALIDATION_RULE", ValidationConstants.DEFAULT_VALIDATION_RULE);
    }

    public void setValidationRules(Map<String, ValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void addValidationRule(String instruction, ValidationRule validationRule) {
        this.validationRules.put(instruction, validationRule);
    }

    public void removeValidationRule(String instruction) {
        this.validationRules.remove(instruction);
    }

    public boolean isNotEmpty(String number) {
        return !number.isBlank();
    }

    public boolean onlyNumbers(String number) {
        if(number == null){
            return false;
        }
        char ch;
        boolean valid = true;
        for(int i = 0; i < number.length(); i++){
            ch = number.charAt(i);
            if(!ValidationConstants.NUMBERS.contains(ch)){
                valid = false;
            }
        }
        return valid;
    }

    public String changeBeginning(String number,String country) {
        if(number != null){
            ValidationRule validationRule = getValidationRule(country);
            String numberWithoutPrefix = number.substring(validationRule.getPrefixShort().length()+1);
            number = validationRule.getPrefixLong() + numberWithoutPrefix;
        }
        return number;
    }

    public boolean numberLength(String number, String country) {
        if(number == null){
            return false;
        }
        if(number.contains("+")){
            number = number.substring(number.indexOf("+")+1);
        }
        return number.length() == getValidationRule(country).getLength();
    }

    public boolean checkCountryCode(String number, String country) {
        if(number == null){
            return false;
        }
        ValidationRule validationRule = getValidationRule(country);
        String numberPrefix = number.substring(0, validationRule.getPrefixLong().length());
        return numberPrefix.equals(validationRule.getPrefixLong());
    }

    private ValidationRule getValidationRule(String instruction){
        ValidationRule validationRule = null;
        if(instruction != null) {
            validationRule = validationRules.get(instruction);
        }
        if(validationRule == null){
            validationRule = validationRules.get("DEFAULT_VALIDATION_RULE");
        }
        return validationRule;
    }
}
