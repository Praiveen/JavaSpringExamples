package org.example;

public class PingPong {
    public static final Object lock = new Object();
    public static boolean isPingTurn = true;

    public static void main(String[] args) {
        Thread pingThread = new Thread(new Ping());
        Thread pongThread = new Thread(new Pong());

        pingThread.start();
        pongThread.start();
    }
}
