package com.example.prak3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CapitalizeClient {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Передайте IP-адрес сервера в качестве единственного аргумента командной строки");
            return;
        }

        try (var socket = new Socket(args[0], 59898)) {
            System.out.println("Подключено к серверу. Введите строки текста, затем Ctrl + D или Ctrl + C, чтобы выйти");

            var scanner = new Scanner(System.in);
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);

            Thread listenerThread = new Thread(() -> {
                while (in.hasNextLine()) {
                    System.out.println("Сообщение от сервера: " + in.nextLine());
                }
            });
            listenerThread.start();

            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
            }

            listenerThread.join();
        } catch (IOException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
    }
}
