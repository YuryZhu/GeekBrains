package ru.yzhiharevich.network_lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

class Client {
    public static void main(String args[]) {
        DataInputStream in;
        DataOutputStream out;
        Socket socket = null;
        final String IP_ADRESS = "localhost";
        final int PORT = 3128;
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
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
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

