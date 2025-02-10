/*
 * Class: CMSC204 
 * Instructor: Samuella helha
 * Description: Used as the primary password checker utility, has methods that throw exceptions if requirement isn't met
 * Due: 02/09/2025
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. I have not given my code to any student.
*/
package Assignement1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class PasswordMain extends BorderPane {

    private Label passwordLabel, passwordALabel;
    private Label instruction1Label, instruction2Label, instruction3Label, instruction4Label;
    private Label instruction5Label, instruction6Label;
    private TextField passwordText, passwordAText;
    private Button checkPwdButton, exitButton, checkPwdsInFileButton;
    private DecimalFormat format = new DecimalFormat("#0.000");
    private Alert alert = new Alert(AlertType.INFORMATION);

    public PasswordMain() {
        // Create the instructions panel (top region)
        VBox instructionPanel = new VBox();
        instructionPanel.setAlignment(Pos.CENTER_LEFT);
        
        instruction1Label = new Label("Use the following rules when creating your passwords:");
        instruction2Label = new Label("\t1. Length must be greater than 6; a strong password will contain at least 10 characters");
        instruction3Label = new Label("\t2. Must contain at least one upper case alpha character");
        instruction4Label = new Label("\t3. Must contain at least one lower case alpha character");
        instruction5Label = new Label("\t4. Must contain at least one numeric character");
        instruction6Label = new Label("\t5. May not have more than 2 of the same character in sequence");
        
        VBox.setMargin(instruction1Label, new Insets(2, 10, 2, 10));
        VBox.setMargin(instruction2Label, new Insets(2, 10, 2, 10));
        VBox.setMargin(instruction3Label, new Insets(2, 10, 2, 10));
        VBox.setMargin(instruction4Label, new Insets(2, 10, 2, 10));
        VBox.setMargin(instruction5Label, new Insets(2, 10, 2, 10));
        VBox.setMargin(instruction6Label, new Insets(2, 10, 2, 10));
        
        instructionPanel.getChildren().addAll(instruction1Label, instruction2Label, instruction3Label,
                                              instruction4Label, instruction5Label, instruction6Label);

        // Create the password entry panel (center region)
        HBox passwordInputBox1 = new HBox();
        passwordLabel = new Label("Password:");
        passwordText = new TextField();
        HBox.setMargin(passwordLabel, new Insets(10, 10, 10, 10));
        HBox.setMargin(passwordText, new Insets(10, 10, 10, 10));
        passwordInputBox1.setAlignment(Pos.CENTER);
        passwordInputBox1.getChildren().addAll(passwordLabel, passwordText);
        
        HBox passwordInputBox2 = new HBox();
        passwordALabel = new Label("Re-type\nPassword:");
        passwordAText = new TextField();
        HBox.setMargin(passwordALabel, new Insets(10, 10, 10, 10));
        HBox.setMargin(passwordAText, new Insets(10, 10, 10, 10));
        passwordInputBox2.setAlignment(Pos.CENTER);
        passwordInputBox2.getChildren().addAll(passwordALabel, passwordAText);
        
        VBox passwordPanel = new VBox();
        VBox.setMargin(passwordInputBox1, new Insets(10, 10, 10, 10));
        VBox.setMargin(passwordInputBox2, new Insets(10, 10, 10, 10));
        passwordPanel.setAlignment(Pos.CENTER);
        passwordPanel.getChildren().addAll(passwordInputBox1, passwordInputBox2);
        
        // Create the buttons (bottom region)
        checkPwdButton = new Button("Check Password");
        checkPwdButton.setMnemonicParsing(true);
        checkPwdButton.setTooltip(new Tooltip("Select to check a password."));
        checkPwdButton.setOnAction(event -> checkPassword());
        
        checkPwdsInFileButton = new Button("Check Passwords in File");
        checkPwdsInFileButton.setMnemonicParsing(true);
        checkPwdsInFileButton.setTooltip(new Tooltip("Select to read passwords from a file"));
        checkPwdsInFileButton.setOnAction(event -> {
            try {
                readFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        exitButton = new Button("Exit");
        exitButton.setMnemonicParsing(true);
        exitButton.setTooltip(new Tooltip("Select to close the application"));
        exitButton.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
        
        HBox buttonPanel = new HBox();
        buttonPanel.setAlignment(Pos.CENTER);
        HBox.setMargin(checkPwdButton, new Insets(10, 10, 10, 10));
        HBox.setMargin(checkPwdsInFileButton, new Insets(10, 10, 10, 10));
        HBox.setMargin(exitButton, new Insets(10, 10, 10, 10));
        buttonPanel.getChildren().addAll(checkPwdButton, checkPwdsInFileButton, exitButton);
        
        // Place the panels in the BorderPane regions
        setTop(instructionPanel);
        setCenter(passwordPanel);
        setBottom(buttonPanel);
    }

    // Method to check a single password entered by the user
    public void checkPassword() {
		//Get information
		String passwordString = passwordText.getText();
		String passwordAString = passwordAText.getText();
		try
		{
			if (!PasswordCheckerUtility.comparePasswordsWithReturn(passwordString, passwordAString)) {
				throw new UnmatchedException();
			}
			
			if (PasswordCheckerUtility.isValidPassword(passwordString)) {
				if (PasswordCheckerUtility.isWeakPassword(passwordString)) {
					alert.setContentText("Password is OK but weak");
					alert.showAndWait();
				}
				else {
					alert.setContentText("Password is valid");
					alert.showAndWait();
				}	
			} else {
				alert.setContentText("Password is Not valid");
				alert.showAndWait();
			}
		}
		catch (UnmatchedException ex)
		{
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
		catch (Exception ex)		{
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}			
	}


    // Method to read a file of passwords and validate them
    public void readFile() {
        FileChooser fileChooser = new FileChooser();
        // Restrict to text files
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        // Open the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            ArrayList<String> passwords = new ArrayList<>();
            try (Scanner scanner = new Scanner(selectedFile)) {
                while (scanner.hasNextLine()) {
                    passwords.add(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "File Error", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            
            // Validate the list of passwords
            ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwords);
            StringBuilder results = new StringBuilder();
            if (invalidPasswords.isEmpty()) {
                results.append("All passwords are valid!");
            } else {
                results.append("Invalid Passwords:\n");
                for (String s : invalidPasswords) {
                    results.append(s).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, results.toString(), "Password Check Results", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
