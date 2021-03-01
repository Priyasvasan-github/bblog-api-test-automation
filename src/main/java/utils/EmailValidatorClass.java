package utils;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Email validator to verify email is valid
 */
public class EmailValidatorClass {
   public static boolean getValidity(String email) {

       // Get an EmailValidator
       EmailValidator validator = EmailValidator.getInstance();

       // Validate specified String containing an email address
       if (!validator.isValid(email)) {
           return false;
       }
       return true;
   }
}