package ru.yzhiharevich.lesson7.client;

import com.sun.javafx.scene.control.skin.LabeledText;
import com.sun.jmx.snmp.tasks.Task;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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

    @FXML
    ListView<String> clientList;

    @FXML
    ListView<String> blackList;

    Socket socket;

    DataInputStream in;
    DataOutputStream out;

    boolean isAuthorized;
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
            clientList.setVisible(false);
            clientList.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // цикл для авторизации
                        while (true) {
                            final String str = in.readUTF();

                            // если получаем ответ /authok то значит мы авторизовались корректно
                            if (str.startsWith("/authok")) {
//                                myTimerTask(true);
                                setAthorized(true);
                                Main.timer.cancel();
                                personalMessages();
                                break;
                            } else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        publicMessages(str);
                                        System.out.println(str);
                                    }
                                });
                            }
                        }
                        // цикл для работы
                        while (true) {
                            final String str = in.readUTF();
                            if (str.startsWith("/")) {
                                if (str.equals("/serverClosed")) break;
                                if (str.startsWith("/clientlist")) {
                                    final String[] tokens = str.split(" ");
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            clientList.getItems().clear();
                                            for (int i = 1; i < tokens.length; i++) {
                                                clientList.getItems().add(tokens[i]);
                                            }
                                        }
                                    });
                                }
                                if (str.startsWith("/blacklist")) {
                                    final String[] tokens = str.split(" ");
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            blackList.getItems().clear();
                                            for (int i = 1; i < tokens.length; i++) {
                                                blackList.getItems().add(tokens[i]);
                                            }
                                        }
                                    });
                                }
                            } else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        publicMessages(str);
                                        System.out.println(str);
                                    }
                                });
                            }
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
            }).start();
        } catch (
                IOException e) {
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
        String[] allData = str.split(" ", 2);
        String nick = allData[0] + ", ";
        String msg = allData[1];
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM HH:mm:ss ");
        Text text = new Text(nick + formatForDateNow.format(dateNow) + "\n" + msg);
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

    public void myTimerTask(Timer timer) {

        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Закрываю");
                Platform.exit();
            }
        };
        timer.schedule(task, 1000 * 120);
        System.out.println("TimerTask начал выполнение");
    }

    public void closeAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void personalMessages() {
        clientList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    clientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    String nickTo = clientList.getSelectionModel().getSelectedItem();

                    try {
                        PersonalStart privateChat = new PersonalStart(nickTo, out);
                        privateChat.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
