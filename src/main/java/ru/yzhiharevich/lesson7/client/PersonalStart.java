package ru.yzhiharevich.lesson7.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class PersonalStart extends Stage {
     String nickTo;
     DataOutputStream out;
    public PersonalStart(String nickTo, DataOutputStream out) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("personalWindow.fxml"));
        Scene scene = new Scene(root);
        setScene(scene);
        this.nickTo = nickTo;
        this.out = out;
    }

}
