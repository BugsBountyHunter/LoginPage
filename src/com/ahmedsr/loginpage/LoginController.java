package com.ahmedsr.loginpage;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML
    private TextField usernameTextFiled;
    @FXML
    private PasswordField passwordTextFiled;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private CheckBox rememberCheckBox;
    @FXML
    private Label statusLbl;

    @FXML
    public void initialize(){
        disableButtons();
    }
    @FXML
    public void onBtnClicked(ActionEvent event){
      if(event.getSource().equals(loginBtn)){
          //Update background task in Ui thread.
          Runnable task = new Runnable() {
              @Override
              public void run() {
                  try {
                      //do task in background
                      Thread.sleep(1000);
                      Platform.runLater(new Runnable() {
                          @Override
                          public void run() {
                              //update ui thread
                              statusLbl.setText( "Login Successfully.. " + " Welcome " + usernameTextFiled.getText());
                              statusLbl.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                          }
                      });
                  } catch (InterruptedException interruptedException) {
                      interruptedException.printStackTrace();
                  }

              }
          };
          new Thread(task).start();

      }else if(event.getSource().equals(registerBtn)){
          //TODO:- Update later.
          statusLbl.setText( " Sorry " + "|" + usernameTextFiled.getText() + "|" + " We Will Coming Soon.");
          statusLbl.setStyle("-fx-background-color: red; -fx-text-fill: white;");
      }



      if(rememberCheckBox.isSelected()){
          usernameTextFiled.clear();
          passwordTextFiled.clear();
          disableButtons();
      }
    }

    @FXML
    public void handleKeyReleased(){
        String usernameText = usernameTextFiled.getText();
        String passwordText = passwordTextFiled.getText();

        boolean disableButtons = usernameText.isEmpty() || passwordText.isEmpty() || usernameText.trim().isEmpty();

        loginBtn.setDisable(disableButtons);
        registerBtn.setDisable(disableButtons);
    }

    private void disableButtons(){
        loginBtn.setDisable(true);
        registerBtn.setDisable(true);
    }
}
