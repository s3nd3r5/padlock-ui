package com.senders.padlock.ui.main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private @FXML Node root ;
    private @FXML PasswordField passwordField;
    private @FXML PasswordField secretField;
    private @FXML TextField fileField;

    @FXML
    private void closeApp(){
        Stage stage  = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void changeFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your padlock file");
        File f = fileChooser.showOpenDialog(root.getScene().getWindow());
        if(f != null && f.exists() && f.isFile()){
            fileField.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void login(){
        try{
            String errorText = "An unexpected error occurred! :(";
            if(StringUtils.isBlank(passwordField.getText())) {
                errorText = "Unable to Login: Password cannot be blank";
            }else if(StringUtils.isBlank(secretField.getText())) {
                errorText = "Unable to Login: Secret cannot be blank";
            }else if(StringUtils.isBlank(fileField.getText())){
                errorText = "Unable to Login: You must choose a file";
            }else{
                //Do Login
                throw new RuntimeException("Butts happened");
            }

        }catch(Exception e) {
            logger.error("Error during login", e);
            new org.controlsfx.dialog.ExceptionDialog(e).show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passwordField.setText(System.getenv("PADLOCK_PASSWORD"));
        secretField.setText(System.getenv("PADLOCK_SECRET"));
        fileField.setText(System.getenv("PADLOCK_FILE"));
    }
}
