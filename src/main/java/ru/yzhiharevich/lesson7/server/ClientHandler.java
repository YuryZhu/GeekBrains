package ru.yzhiharevich.lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

// класс для работы к клиентами
public class ClientHandler {

    private MainServer server;

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    ArrayList<String> blackList;

    public ClientHandler(final MainServer server, final Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // цикл для авторизации
                        while (true) {
                            String str = in.readUTF();
                            // если приходит сообщение начинающееся с /auth значит пользователь хочет авторизоваться
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (newNick != null) {
                                    if (!server.isNickBusy(newNick)) {
                                        sendMsg("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Учетная запись уже используется!");
                                    }
                                } else {
                                    sendMsg("Неверный логин/пароль!");
                                }
                            }
                        }
                        // цикл для работы
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/")) {
                                if (str.equals("/end")) {
                                    out.writeUTF("/serverClosed");
                                }
                                if (str.startsWith("/w ")) {
                                    String[] tokens = str.split(" ", 3);
                                    server.sentPrivateMsg(ClientHandler.this, tokens[1], tokens[2]);
                                }
                                if (str.startsWith("/blacklist ")) {
                                    String[] tokens = str.split(" ");
                                    String answer = AuthService.setBlackList(ClientHandler.this.getNick(), tokens[1]);
                                    blackList.add(tokens[1]);
                                    if (answer.equals(tokens[1])){
                                        sendMsg("Пользователь " + tokens[1] + " уже есть в черном списке");
                                    }
                                    sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                                }
                            } else {
                                server.broadcastMsg(ClientHandler.this, nick + ": " + str);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

    // метод для оправки сообщения 1 клиенту
    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlackList(String nick) {
        boolean answer =AuthService.getIfNickInBlackList(ClientHandler.this.getNick(), nick);
        return  answer;
    }
}

