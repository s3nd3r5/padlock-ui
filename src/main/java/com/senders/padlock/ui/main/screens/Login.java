package com.senders.padlock.ui.main.screens;

import com.senders.padlock.ui.main.Main;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Login {
    private static final Dimension PREFFERED_DIMENSIONS = new Dimension(250,225),
            FILLER_DIMENSIONS = new Dimension(5,5),
            TEXTBOX_MIN_SIZE = new Dimension(200,20),
            TEXTBOX_MAX_SIZE = new Dimension(1000,25);
    private static final EmptyBorder EMPTY_BORDER = new EmptyBorder(10,10,5,10);
    private final JFrame loginFrame;
    private final JPanel contentPanel,verticalPanel, horizontalPanel;
    private final JLabel passwordLabel, secretLabel;
    private final JTextField defaultFileTextField;
    private final JPasswordField passwordField, secretField;
    private final JButton loginButton, cancelButton, changeFileButton;

    private static Box.Filler FILLER(){
        return new Box.Filler(FILLER_DIMENSIONS,FILLER_DIMENSIONS,FILLER_DIMENSIONS);
    }

    public Login(final Main main){
        passwordLabel = new JLabel("Password");
        secretLabel = new JLabel("Secret");

        passwordField = new JPasswordField();
        configurePasswordField();

        secretField = new JPasswordField();
        configureSecretField();

        defaultFileTextField = new JTextField();
        configureDefaultFileTextField();

        loginButton = new JButton("Login");
        configureLoginButton(main);

        cancelButton = new JButton("Exit");
        configureCancelButton();

        changeFileButton = new JButton("Change file");
        configureChangeFileButton();

        loginFrame = new JFrame("Padlock - Login");
        configureLoginFrame();

        contentPanel = new JPanel(new BorderLayout());
        configureContentPanel();

        verticalPanel = new JPanel();
        configureVerticalPanel();

        horizontalPanel = new JPanel();
        configureHorizontalPanel();

        addToVerticalPanel();
        addToHorizontalPanel();
        addToContentPanel();
        addToFrame();
    }

    private void addToVerticalPanel() {
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        secretLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        secretField.setAlignmentX(Component.CENTER_ALIGNMENT);
        defaultFileTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeFileButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        verticalPanel.add(passwordLabel);
        verticalPanel.add(FILLER());
        verticalPanel.add(passwordField);
        verticalPanel.add(FILLER());
        verticalPanel.add(secretLabel);
        verticalPanel.add(FILLER());
        verticalPanel.add(secretField);
        verticalPanel.add(FILLER());
        verticalPanel.add(defaultFileTextField);
        verticalPanel.add(FILLER());
        verticalPanel.add(changeFileButton);
    }

    private void addToHorizontalPanel() {
        horizontalPanel.add(loginButton);
        horizontalPanel.add(cancelButton);
    }

    private void addToContentPanel(){
        contentPanel.add(verticalPanel, BorderLayout.CENTER);
        contentPanel.add(horizontalPanel, BorderLayout.SOUTH);
    }

    private void addToFrame(){
        loginFrame.setContentPane(contentPanel);
    }

    public void start(){
        loginFrame.setVisible(true);
    }

    private void configurePasswordField(){
        String password = System.getenv("PADLOCK_PASSWORD");
        if(StringUtils.isNotBlank(password)){
            passwordField.setText(password);
        }
        passwordField.setMaximumSize(TEXTBOX_MAX_SIZE);
        passwordField.setMinimumSize(TEXTBOX_MIN_SIZE);
    }

    private void configureSecretField(){
        String secret = System.getenv("PADLOCK_SECRET");
        if(StringUtils.isNotBlank(secret)){
            secretField.setText(secret);
        }
        secretField.setMaximumSize(TEXTBOX_MAX_SIZE);
        secretField.setMinimumSize(TEXTBOX_MIN_SIZE);
    }

    private void configureDefaultFileTextField(){
        String defaultFile = System.getenv("PADLOCK_FILE");
        if(StringUtils.isNotBlank(defaultFile)){
            defaultFileTextField.setText(defaultFile);
        }
        defaultFileTextField.setEditable(false);
        defaultFileTextField.setMinimumSize(TEXTBOX_MIN_SIZE);
        defaultFileTextField.setMaximumSize(TEXTBOX_MAX_SIZE);
    }

    private void configureLoginButton(final Main main){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin(main);
            }
        });
    }

    private void configureCancelButton() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(false);
                loginFrame.dispose();
            }
        });
    }

    private void configureChangeFileButton() {
        changeFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
                int returnVal = fileChooser.showOpenDialog(loginFrame);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    if(file != null){
                        defaultFileTextField.setText(file.getAbsolutePath());
                        defaultFileTextField.setCaretPosition(file.getAbsolutePath().length());

                    }
                }
            }
        });
    }

    private void configureLoginFrame(){
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setPreferredSize(PREFFERED_DIMENSIONS);
        loginFrame.setMinimumSize(PREFFERED_DIMENSIONS);
        loginFrame.setMaximumSize(PREFFERED_DIMENSIONS);
        loginFrame.setResizable(false);
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {

                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e){
                    throw new RuntimeException("Unable to set look and feel");
                }
                break;
            }
        }
    }

    private void configureContentPanel(){
        contentPanel.setPreferredSize(PREFFERED_DIMENSIONS);
        contentPanel.setMaximumSize(PREFFERED_DIMENSIONS);
        contentPanel.setMinimumSize(PREFFERED_DIMENSIONS);
        contentPanel.setBorder(EMPTY_BORDER);
    }

    private void configureVerticalPanel(){
        BoxLayout layout = new BoxLayout(verticalPanel,BoxLayout.Y_AXIS);
        verticalPanel.setLayout(layout);

    }

    private void configureHorizontalPanel(){
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        horizontalPanel.setLayout(layout);
    }

    private void handleLogin(final Main main){
        char[] password = passwordField.getPassword();
        char[] secret = secretField.getPassword();
        String file = defaultFileTextField.getText();

//        main.doPadlock(password,secret,file);
    }

}
