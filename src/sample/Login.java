package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.KeyEvent;


public class Login {
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPw;
    @FXML
    Button btnLogin;

    public static Stage homeStage = new Stage();


    public void loginClick() throws Exception {

        String username = txtUsername.getText();
        String password = txtPw.getText();
        if (!username.equals("") && !password.equals("")) {
            if (username.equals("sa") && password.equals("12")) {

                Main.PrimaryStage.close();
                txtUsername.clear();
                txtPw.clear();
                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                homeStage.initStyle(StageStyle.UNDECORATED);
                homeStage.setScene(new Scene(root, 550, 400));
                homeStage.show();


            } else {
                Alert invalidError = new Alert(Alert.AlertType.NONE);
                invalidError.setAlertType(Alert.AlertType.ERROR);
                invalidError.setContentText("Invalid Username or Password");
                invalidError.showAndWait();
            }

        } else {
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("Username or password field is empty");
            nullError.showAndWait();
        }
    }
}





