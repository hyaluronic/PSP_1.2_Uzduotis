package lt.mif.psp;

import java.util.HashMap;
import java.util.Map;

public class PhoneChecker {

    /**
     * DEFAULT_VALIDATION_RULE: (length: 11, areaCode:"8", countryCode:"+370")
     */
    private final Map<String, ValidationRule> validationRules = new HashMap<>();

    public PhoneChecker() {
        this.validationRules.put("DEFAULT_VALIDATION_RULE", ValidationConstants.DEFAULT_VALIDATION_RULE);
    }

    /**
     * @param instruction key to get ValidationRule from validationRules map
     * @param validationRule
     * puts 'instruction' as key and 'validationRule' as value to validationRules map
     *                       if 'instruction' and 'validationRule' is not null.
     */
    public void addValidationRule(String instruction, ValidationRule validationRule) {
        if (instruction != null && validationRule != null) {
            this.validationRules.put(instruction, validationRule);
        }
    }

    /**
     * @param instruction
     * Removes ValidationRule form validationRules with key 'instruction'.
     *                    Cannot remove DEFAULT_VALIDATION_RULE.
     */
    public void removeValidationRule(String instruction) {
        if (instruction != null && !instruction.equals("DEFAULT_VALIDATION_RULE")) {
            this.validationRules.remove(instruction);
        }
    }

    /**
     * @param number
     * @return true if 'number' is not null and is not empty,
     */
    public boolean isNotEmpty(String number) {
        return number != null && !number.isEmpty();
    }

    /**
     * @param number if first character is '+', skips it.
     * @return true if 'number' is not blank and there is only numbers in 'number',
     * false otherwise.
     */
    public boolean onlyNumbers(String number) {
        if (number == null) {
            return false;
        }
        if (number.startsWith("+")) {
            number = number.substring(1);
        }
        return !number.isBlank() && number.chars().allMatch(Character::isDigit);
    }

    /**
     * @param number
     * @return if number is not null changes its areaCode to countryCode
     * by 'DEFAULT_VALIDATION_RULE' (length: 11, areaCode:"8", countryCode:"+370").
     * if 'number' areaCode does not match ValidationRules areaCode, returns 'number' unchanged.
     */
    public String changeBeginning(String number) {
        return changeBeginning(number, null);
    }

    /**
     * @param number
     * @param country
     * @return if 'number' is not null changes its beginning from areaCode to countryCode from ValidationRule by 'country'.
     * if 'ValidationRule' is not found in the map or 'country' is null changes areaCode from '8' to '+370'
     * if 'number' areaCode does not match ValidationRules areaCode, same 'number' is returned.
     */
    public String changeBeginning(String number, String country) {
        if (number != null) {
            ValidationRule validationRule = getValidationRule(country);
            if (number.startsWith(validationRule.getAreaCode())) {
                String numberWithoutPrefix = number.substring(validationRule.getAreaCode().length());
                number = validationRule.getCountryCode() + numberWithoutPrefix;
            }
        }
        return number;
    }

    /**
     * @param number
     * @return true if 'number' prefix starts with areaCode or countryCode
     * from ValidationRule found 'DEFAULT_VALIDATION_RULE' (length: 11, areaCode:"8", countryCode:"+370").
     * returns false otherwise.
     */
    public boolean isPrefixValid(String number) {
        return isPrefixValid(number, null);
    }

    /**
     * @param number
     * @param instruction key to get ValidationRule from validationRules map
     * @return true if 'number' prefix starts with areaCode or countryCode
     * from ValidationRUle found by 'instruction', or 'DEFAULT_VALIDATION_RULE'
     * (length: 11, areaCode:"8", countryCode:"+370") if rule is null no rule found.
     * returns false otherwise.
     */
    public boolean isPrefixValid(String number, String instruction) {
        ValidationRule validationRule = getValidationRule(instruction);
        return number.startsWith(validationRule.getAreaCode()) || number.startsWith(validationRule.getCountryCode());
    }

    /**
     * @param number
     * @return true if 'number' length is equal or greater than length
     * from 'DEFAULT_VALIDATION_RULE' (length: 11, areaCode:"8", countryCode:"+370").
     * If number starts with areaCode it is changed to countryCode before length check.
     * returns false otherwise.
     */
    public boolean numberLength(String number) {
        return numberLength(number, null);
    }

    /**
     * @param number
     * @param country
     * @return true if 'number' length is equal or greater than length
     * from ValidationRule by 'country', if ValidationRule not found, 'DEFAULT_VALIDATION_RULE'
     * is used (length: 11, areaCode:"8", countryCode:"+370").
     * If number starts with areaCode it is changed to countryCode before length check.
     * returns false otherwise
     */
    public boolean numberLength(String number, String country) {
        if (number == null) {
            return false;
        }
        ValidationRule validationRule = getValidationRule(country);
        String changedNumber = changeBeginning(number);
        if (changedNumber.contains("+")) {
            changedNumber = changedNumber.substring(changedNumber.indexOf("+") + 1);
        }
        return changedNumber.length() == validationRule.getLength();
    }

    /**
     * @param number
     * @return true if 'number' starts with countryCode from
     * 'DEFAULT_VALIDATION_RULE' (length: 11, areaCode:"8", countryCode:"+370").
     * returns false otherwise.
     */
    public boolean checkCountryCode(String number) {
        return checkCountryCode(number, null);
    }

    /**
     * @param number
     * @param country
     * @return true if 'number' starts with countryCode from ValidationRule by 'country',
     * if no rule is found 'DEFAULT_VALIDATION_RULE' (length: 11, areaCode:"8", countryCode:"+370") is used.
     * returns false otherwise.
     */
    public boolean checkCountryCode(String number, String country) {
        if (number == null) {
            return false;
        }
        ValidationRule validationRule = getValidationRule(country);
        String numberPrefix = number.substring(0, validationRule.getCountryCode().length());
        return numberPrefix.equals(validationRule.getCountryCode());
    }

    private ValidationRule getValidationRule(String instruction) {
        ValidationRule validationRule = null;
        if (instruction != null) {
            validationRule = validationRules.get(instruction);
        }
        if (validationRule == null) {
            validationRule = validationRules.get("DEFAULT_VALIDATION_RULE");
        }
        return validationRule;
    }
}
