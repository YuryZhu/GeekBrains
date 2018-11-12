package ru.yzhiharevich.lesson7.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {
    int count = 0;

    @FXML
    private VBox mainPanel;

    @FXML
    private TextField textField;

    @FXML
    private VBox chatBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox bottomPanel;

    @FXML
    private HBox upperPanel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    private Socket socket;

    private DataInputStream in;
    private DataOutputStream out;

    private boolean isAuthorized;

    private final String IP_ADRESS = "localhost";
    private final int PORT = 3129;

    // метод который в зависимости от переменной isAuthorized
    // показывает или скрывает панель авторизации и панель для работы с чатом
    public void setAthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;

        if (!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        // цикл для авторизации
                        while (true) {
                            // если получаем ответ /authok то значит мы авторизовались корректно
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                setAthorized(true);
                                break;
                            } else {
                                publicMessages(str);
//                                textArea.appendText(str + "\n");
                            }
                        }
                        // цикл для работы
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverClosed")) break;
                            publicMessages(str);
//                            textArea.appendText(str + "\n");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // отправка сообщений
    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // метод для авторизации
    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void publicMessages(String str) {
        str = textField.getText();

        Text text = new Text(str);
        HBox hbox = new HBox(12);
        TextFlow flow = new TextFlow(text);

        flow.getStyleClass().add("textFlowFlipped");
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(flow);
        hbox.getStyleClass().add("hbox");
        chatBox.getChildren().addAll(hbox);
        this.count++;
        textField.clear();
        textField.requestFocus();
        scrollPane.vvalueProperty().bind(chatBox.heightProperty());
    }

    public void closeAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}
