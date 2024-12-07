package com.project.infoman_pro.controller;

import com.project.infoman_pro.Main;
import com.project.infoman_pro.utility.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void initialize() {
        loginMessageLabel.setVisible(false);
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        usernameField.getStyleClass().remove("error");
        passwordField.getStyleClass().remove("error");
        loginMessageLabel.setVisible(false);

        // Validating
        if (username.isEmpty() || password.isEmpty()) {
            loginMessageLabel.setVisible(true);
            if( username.isEmpty() && password.isEmpty()){ // If both fields are blank
                usernameField.getStyleClass().add("error");
                passwordField.getStyleClass().add("error");
                loginMessageLabel.setText("Username & password cannot be empty.");
            } else { //If either one is blank
                if (username.isEmpty()) {
                    usernameField.getStyleClass().add("error");
                    loginMessageLabel.setText("Username cannot be empty.");
                }
                if (password.isEmpty()) {
                    passwordField.getStyleClass().add("error");
                    loginMessageLabel.setText("Password cannot be empty.");
                }
            }
        } else if (!authenticateUser(username, password)) {
            loginMessageLabel.setVisible(true);
            loginMessageLabel.setText("Invalid username or password.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        Database db = new Database();

        String query = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement statement = db.connection().prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return BCrypt.checkpw(password, resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return false;
    }

}