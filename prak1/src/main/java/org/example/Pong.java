package org.example;

import static org.example.PingPong.isPingTurn;
import static org.example.PingPong.lock;

class Pong implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                while (isPingTurn) {
                    try {lock.wait();}
                    catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.print("Pong ");
                isPingTurn = true;
                lock.notify();
            }
        }
    }
}
