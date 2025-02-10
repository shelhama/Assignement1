/*
 * Class: CMSC204 
 * Instructor: Samuella Helha
 * Description: This class serves as the primary password checker utility, containing methods 
 * that validate passwords and enforce security policies. It throws exceptions if a requirement isn't met.
 * Due: 02/09/2025
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently. 
 * I have not copied the code from a student or any source. I have not given my code to any student.
 */

/** 
 * Password checker utility class that provides methods to validate passwords based on 
 * security rules such as length, uppercase and lowercase letters, digits, special characters, 
 * and sequence constraints.
 * 
 * @author Samuella Helha
 */
package Assignement1;
import java.util.ArrayList;

/**
 * Utility class for password validation.
 */
public class PasswordCheckerUtility {
    
    /**
     * Validates whether a password meets all security requirements.
     *
     * @param password The password to check.
     * @return true if the password meets all requirements.
     * @throws LengthException if the password is too short (less than 6 characters).
     * @throws NoUpperAlphaException if the password lacks an uppercase letter.
     * @throws NoLowerAlphaException if the password lacks a lowercase letter.
     * @throws NoDigitException if the password lacks a numeric digit.
     * @throws InvalidSequenceException if the password contains more than two repeating characters in sequence.
     * @throws NoSpecialCharacterException if the password lacks a special character.
     */
    public static boolean isValidPassword(String password)
            throws LengthException, NoUpperAlphaException, NoLowerAlphaException,
                   NoDigitException, InvalidSequenceException, NoSpecialCharacterException {
        
        if (password.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long.");
        }
        if (!containsUpperCase(password)) {
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character.");
        }
        if (!containsLowerCase(password)) {
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character.");
        }
        if (!containsDigit(password)) {
            throw new NoDigitException("The password must contain at least one digit.");
        }
        // Check for invalid sequences before checking special characters
        if (containsInvalidSequence(password)) {
            throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
        }
        if (!containsSpecialCharacter(password)) {
            throw new NoSpecialCharacterException("The password must contain at least one special character.");
        }
        return true;
    }

    /**
     * Checks whether a password is considered weak (between 6 and 9 characters).
     *
     * @param password The password to check.
     * @return false if the password is strong.
     * @throws WeakPasswordException if the password is between 6 and 9 characters long.
     */
    public static boolean isWeakPassword(String password) throws WeakPasswordException {
        if (password.length() >= 6 && password.length() <= 9) {
            throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
        }
        return false;
    }
    
    /**
     * Compares two passwords and returns whether they are identical.
     *
     * @param password The original password.
     * @param passwordConfirm The password to compare with.
     * @return true if the passwords match, false otherwise.
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    /**
     * Retrieves a list of invalid passwords from an input list.
     *
     * @param passwords A list of passwords to validate.
     * @return An ArrayList containing invalid passwords with their respective error messages.
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();
        
        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (Exception e) {
                invalidPasswords.add(password + " " + e.getMessage());
            }
        }
        return invalidPasswords;
    }

    /**
     * Checks if a password contains at least one uppercase letter.
     *
     * @param password The password to check.
     * @return true if the password contains an uppercase letter, false otherwise.
     */
    private static boolean containsUpperCase(String password) {
        return password.matches(".*[A-Z].*");
    }

    /**
     * Checks if a password contains at least one lowercase letter.
     *
     * @param password The password to check.
     * @return true if the password contains a lowercase letter, false otherwise.
     */
    private static boolean containsLowerCase(String password) {
        return password.matches(".*[a-z].*");
    }

    /**
     * Checks if a password contains at least one digit.
     *
     * @param password The password to check.
     * @return true if the password contains a digit, false otherwise.
     */
    private static boolean containsDigit(String password) {
        return password.matches(".*[0-9].*");
    }

    /**
     * Checks if a password contains at least one special character.
     *
     * @param password The password to check.
     * @return true if the password contains a special character, false otherwise.
     */
    private static boolean containsSpecialCharacter(String password) {
        return !password.matches("[a-zA-Z0-9]*");
    }

    /**
     * Checks if a password contains three or more repeated characters in sequence.
     *
     * @param password The password to check.
     * @return true if the password contains an invalid character sequence, false otherwise.
     */
    private static boolean containsInvalidSequence(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }
}
