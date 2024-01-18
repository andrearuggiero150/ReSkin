package com.exemple.externalSoftware;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class externalSoftware {
    public static void main(String[] args) throws Exception {
        System.out.println("Server");
        ServerSocket serverSocket = new ServerSocket(7878);
        while(true) {
            Random random = new Random();
            String totale = "";
            for (int i = 0; i <= 7; i++) {
                totale = totale.concat(Integer.toString(random.nextInt( 10)));
            }
            System.out.println(totale);

            Socket socket = serverSocket.accept();
            System.out.println("Connessione avviata");

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            ObjectInputStream ois = new ObjectInputStream(is);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            String scritto = (String) ois.readObject();
            if (scritto.equals(totale))
                oos.writeObject(true);
            else
                oos.writeObject(false);

            System.out.println("Connessione conclusa");
            socket.close();
        }
    }
}