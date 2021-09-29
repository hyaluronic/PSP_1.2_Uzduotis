package lt.mif.psp;

import java.util.HashMap;
import java.util.Map;

public class PhoneChecker {
    private Map<String, ValidationRule> validationRules = new HashMap<>();

    public PhoneChecker() {
        this.validationRules.put("DEFAULT_VALIDATION_RULE", ValidationConstants.DEFAULT_VALIDATION_RULE);
    }

    public void addValidationRule(String instruction, ValidationRule validationRule) {
        this.validationRules.put(instruction, validationRule);
    }

    /***
     *
     * @param instruction
     * Removes ValidationRule form validationRules with key 'instruction'.
     * cannot remove DEFAULT_VALIDATION_RULE.
     */
    public void removeValidationRule(String instruction) {
        if(!instruction.equals("DEFAULT_VALIDATION_RULE")){
            this.validationRules.remove(instruction);
        }
    }

    /***
     *
     * @param number
     * @return true if 'number' is not null or has non whitespace characters,
     * false otherwise.
     */
    public boolean isNotEmpty(String number) {
        return !number.isBlank();
    }

    /***
     *
     * @param number
     * @return true if there is only numbers in 'number',
     * false otherwise.
     */
    public boolean onlyNumbers(String number) {
        if (number == null) {
            return false;
        }
        char ch;
        boolean valid = true;
        for (int i = 0; i < number.length(); i++) {
            ch = number.charAt(i);
            if (!Character.isDigit(ch)) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    /***
     *
     * @param number
     * @return if number is not null changes its prefixShort to prefixLong
     * by 'DEFAULT_VALIDATION_RULE' (length: 11, prefixShort:"8", prefixLong"+370").
     * if 'number' prefix does not match ValidationRules prefixShort, returns 'number' unchanged.
     */
    public String changeBeginning(String number) {
        return changeBeginning(number, null);
    }

    /***
     *
     * @param number
     * @param country
     * @return if 'number' is not null changes its beginning from prefixShort to prefixLong from ValidationRule by 'country'.
     * if 'ValidationRule' is not found in the map or 'country' is null changes prefix from '8' to '+370'
     * if 'number' prefix does not match ValidationRules prefixShort, same 'number' is returned.
     */
    public String changeBeginning(String number, String country) {
        if (number != null) {
            ValidationRule validationRule = getValidationRule(country);
            if (number.startsWith(validationRule.getPrefixShort())) {
                String numberWithoutPrefix = number.substring(validationRule.getPrefixShort().length());
                number = validationRule.getPrefixLong() + numberWithoutPrefix;
            }
        }
        return number;
    }

    /***
     *
     * @param number
     * @return true if 'number' prefix starts with prefixShort or prefixLong
     * from ValidationRule found 'DEFAULT_VALIDATION_RULE' (length: 11, prefixShort:"8", prefixLong"+370").
     * returns false otherwise.
     */
    public boolean isPrefixValid(String number) {
        return isPrefixValid(number, null);
    }

    /***
     *
     * @param number
     * @param instruction
     * @return true if 'number' prefix starts with prefixShort or prefixLong
     * from ValidationRUle found by 'instruction', or 'DEFAULT_VALIDATION_RULE'
     * (length: 11, prefixShort:"8", prefixLong"+370") if rule is null no rule found.
     * returns false otherwise.
     */
    public boolean isPrefixValid(String number, String instruction) {
        ValidationRule validationRule = getValidationRule(instruction);
        return number.startsWith(validationRule.getPrefixShort()) || number.startsWith(validationRule.getPrefixLong());
    }

    /***
     *
     * @param number
     * @return true if 'number' length is equal or greater than length
     * from 'DEFAULT_VALIDATION_RULE' (length: 11, prefixShort:"8", prefixLong"+370").
     * returns false otherwise.
     */
    public boolean numberLength(String number) {
        return numberLength(number, null);
    }

    /***
     *
     * @param number
     * @param country
     * @return true if 'number' length is equal or greater than length
     * from ValidationRule by 'country', if ValidationRule not found, 'DEFAULT_VALIDATION_RULE'
     * is used (length: 11, prefixShort:"8", prefixLong"+370").
     * returns false otherwise
     */
    public boolean numberLength(String number, String country) {
        if (number == null) {
            return false;
        }
        if (number.contains("+")) {
            number = number.substring(number.indexOf("+") + 1);
        }
        return number.length() == getValidationRule(country).getLength();
    }

    /***
     *
     * @param number
     * @return true if 'number' starts with prefixLong from
     * 'DEFAULT_VALIDATION_RULE' (length: 11, prefixShort:"8", prefixLong"+370").
     * returns false otherwise.
     */
    public boolean checkCountryCode(String number) {
        return checkCountryCode(number, null);
    }

    /***
     *
     * @param number
     * @param country
     * @return true if 'number' starts with prefixLong from ValidationRule by 'country',
     * if no rule is found 'DEFAULT_VALIDATION_RULE' (length: 11, prefixShort:"8", prefixLong"+370") is used.
     * returns false otherwise.
     */
    public boolean checkCountryCode(String number, String country) {
        if (number == null) {
            return false;
        }
        ValidationRule validationRule = getValidationRule(country);
        String numberPrefix = number.substring(0, validationRule.getPrefixLong().length());
        return numberPrefix.equals(validationRule.getPrefixLong());
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
