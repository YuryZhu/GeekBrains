package ru.yzhiharevich.lesson7.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.io.IOException;

public class PersonalWindowController {
    @FXML
    Button btn1;

    @FXML
    TextField textFieldPersonal;

    public void closeAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void sendMsg(ActionEvent actionEvent) throws IOException {
        DataOutputStream out = ((PersonalStart)btn1.getScene().getWindow()).out;
        String nickTo = ((PersonalStart)btn1.getScene().getWindow()).nickTo;

        try {
            out.writeUTF("/w " + nickTo + " " + textFieldPersonal.getText());
            textFieldPersonal.clear();
            textFieldPersonal.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
