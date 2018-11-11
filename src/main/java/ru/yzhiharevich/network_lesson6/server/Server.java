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
                        Thread tin = new Thread(new Runnable() {
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
                        });
                        Thread tout = new Thread(new Runnable() {
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
                        });
                        tin.start();
                        tout.start();
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