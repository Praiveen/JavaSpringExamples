package com.example.prak3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executors;


public class CapitalizeServer {
    private static final List<PrintWriter> clientWriters = new ArrayList<>();
    private static final List<String> messages = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(59898)) {
            System.out.println("Сервер запущен...");
            var pool = Executors.newFixedThreadPool(20);

            Timer broadcastTimer = new Timer(true);
            broadcastTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    broadcastMessages();
                }
            }, 0, 5000);

            while (true) {
                pool.execute(new Capitalizer(listener.accept()));
            }
        }
    }

    private static void broadcastMessages() {
        if (!messages.isEmpty()) {
            String messageToSend = String.join("\n", messages);
            System.out.println(messages);
            for (PrintWriter writer : clientWriters) {
                writer.println(messageToSend);
            }
            messages.clear();
        }
    }

    private static class Capitalizer implements Runnable {
        private Socket socket;
        Capitalizer(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            System.out.println("Подключение: " + socket);
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(out);
                while (in.hasNextLine()) {
                    String test = in.nextLine();
                    messages.add(test);
                }
            } catch (Exception e) {
                System.out.println("Ошибка:" + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}


//C:\MIREA\3kurs5sem\java5sem\prak3\src\main\java>
// java com.example.prak3.CapitalizeServer
//
//C:\MIREA\3kurs5sem\java5sem\prak3\src\main\java>
// java com.example.prak3.CapitalizeClient 127.0.0.1