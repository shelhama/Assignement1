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

public class NoLowerAlphaException extends Exception
{
	public NoLowerAlphaException()
	{
		super("The password must contain at least one lowercase alphabetic character");
	}
	
	public NoLowerAlphaException(String message)
	{
		super(message);
	}
}
