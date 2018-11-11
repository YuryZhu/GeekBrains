package ru.yzhiharevich.network_lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

class Client {
    DataInputStream in;
    DataOutputStream out;

    public void clientStart() {

        Socket socket = null;
        final String IP_ADRESS = "localhost";
        final int PORT = 3128;
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String strIn = null;
                        try {
                            strIn = in.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(strIn);
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String str;
                            try {
                                System.out.print("Input your message: ");
                                Scanner sb = new Scanner(System.in);
                                str = sb.nextLine();
                                out.writeUTF(str);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

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

    public void readStream() {
        String strIn = null;
        try {
            strIn = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(strIn);

    }

    public void outStream() {
        while (true) {
            String str;
            try {
                System.out.print("Input your message: ");
                Scanner sb = new Scanner(System.in);
                str = sb.nextLine();
                out.writeUTF(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

