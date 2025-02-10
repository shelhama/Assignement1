/*
 * Class: CMSC204 
 * Instructor: Samuella helha
 * Description: Used as the primary password checker utility, has methods that throw exceptions if requirement isn't met
 * Due: 02/09/2025
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. I have not given my code to any student.
*/

/**
 * @author Samuella Helha
 */
package Assignement1;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class PasswordCheckerTest_STUDENT {

	/**Test a valid password 
	 * (should return true)
	 * @throws Exception if an error occurs during setup.
	 */
    @Test
    public void testValidPassword() throws Exception {
        String validPassword = "Hello@123";
        assertTrue(PasswordCheckerUtility.isValidPassword(validPassword));
    }
    

/**Test that a password shorter than 6 characters 
 * throws LengthException
 * @throws Exception  if the password is less than the required length.
 */
    @Test(expected = LengthException.class)
    public void testInvalidPasswordLength() throws Exception {
        String shortPassword = "H@1a"; // fewer than 6 characters
        PasswordCheckerUtility.isValidPassword(shortPassword);
    }
    
    /**Test that a password with no uppercase letters
     *  throws NoUpperAlphaException
     * @throws Exception if the password does not contain at least one uppercase letter.
     */
    @Test(expected = NoUpperAlphaException.class)
    public void testNoUpperCase() throws Exception {
        String noUpper = "hello@123";
        PasswordCheckerUtility.isValidPassword(noUpper);
    }
    
    /**Test that a password with no lowercase letters 
     * throws NoLowerAlphaException
     * @throws Exception if the password does not contain at least one lowercase letter.
     */
    @Test(expected = NoLowerAlphaException.class)
    public void testNoLowerCase() throws Exception {
        String noLower = "HELLO@123";
        PasswordCheckerUtility.isValidPassword(noLower);
    }
    
    /** Test that a password with no digit 
     * throws NoDigitException
     * @throws Exception  if the password does not contain at least one numeric character.
     */
    @Test(expected = NoDigitException.class)
    public void testNoDigit() throws Exception {
        String noDigit = "Hello@abc";
        PasswordCheckerUtility.isValidPassword(noDigit);
    }
    
    /** Test that a password with no special character
     *  throws NoSpecialCharacterException
     * @throws Exception if password doesnt contain special character.
     */
    @Test(expected = NoSpecialCharacterException.class)
    public void testNoSpecialCharacter() throws Exception {
        String noSpecial = "Hello1234";
        PasswordCheckerUtility.isValidPassword(noSpecial);
    }
    
    /**Test that a password with three identical characters in sequence
     *  throws InvalidSequenceException
     * @throws Exception if the password contains consecutive repeating characters.
     */
    @Test(expected = InvalidSequenceException.class)
    public void testInvalidSequence() throws Exception {
        // "Heee@123" contains three 'e' characters in a row.
        String invalidSequence = "Heee@123";
        PasswordCheckerUtility.isValidPassword(invalidSequence);
    }
    
    /**Test that a valid password which is weak (length between 6 and 9) 
     * causes a WeakPasswordException
     * @throws Exception if password is weak
     */
    @Test(expected = WeakPasswordException.class)
    public void testWeakPassword() throws Exception {
        // 7-character password is valid but weak.
        String weakPassword = "Hello@1";
        PasswordCheckerUtility.isWeakPassword(weakPassword);
    }
    
    /** Test that a strong password (10 or more characters) 
     * does not throw WeakPasswordException
     * @throws Exception if password is strong.
     */
    @Test
    public void testStrongPassword() throws Exception {
        String strongPassword = "Hello@12345";
        // If the password is strong, isWeakPassword should return false.
        assertFalse(PasswordCheckerUtility.isWeakPassword(strongPassword));
    }
    
    /** 
     * Test the getInvalidPasswords method by supplying a list of passwords.
     * It should return a list containing only the invalid passwords.
     * The test verifies that three out of four passwords are identified as invalid.
     */
    @Test
    public void testGetInvalidPasswords() {
        ArrayList<String> testPasswords = new ArrayList<>();
        testPasswords.add("Hello@123");    // valid
        testPasswords.add("hello@123");     // invalid (no uppercase)
        testPasswords.add("Hello1234");     // invalid (no special character)
        testPasswords.add("Heee@123");      // invalid (invalid sequence)
        
        ArrayList<String> invalids = PasswordCheckerUtility.getInvalidPasswords(testPasswords);
        // We expect three invalid passwords.
        assertEquals(3, invalids.size());
    }
    
    /** 
     * Test the comparePasswordsWithReturn method.
     * This method checks whether two passwords are the same.
     * It should return true for matching passwords and false otherwise.
     */
    @Test
    public void testComparePasswordsWithReturn() {
        // Assuming comparePasswordsWithReturn is implemented in PasswordCheckerUtility.
        assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn("Hello@123", "Hello@123"));
        assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn("Hello@123", "hello@123"));
    }
}
