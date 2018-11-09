package ru.yzhiharevich.network_lesson6.server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class Server {
    Socket socket = null;
    DataInputStream in;
    DataOutputStream out;

    public void serverStart() {
        try {
            ServerSocket server = new ServerSocket(3128);

            System.out.println("server is started");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                try {
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    try {
                        while (true) {
                            System.out.print("Input your message: ");
                            Scanner sb = new Scanner(System.in);
                            String str = sb.nextLine();
                            out.writeUTF(str);
                            String strIn = in.readUTF();
                            System.out.println(strIn);
                            if(str.equals("/end")) break;
                            if(strIn.equals("/end")) break;
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
                    }

                } catch (Exception e) {
                    System.out.println("init error: " + e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}