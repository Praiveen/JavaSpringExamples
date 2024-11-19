package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Client() {}

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1090);
            Equation stub = (Equation) registry.lookup("Obj");
            OutObject solution = stub.solve(8, -14, 5);
            System.out.println("Ответ: " + solution);
        } catch (Exception e) {
            System.out.println("Client err " + e.toString());
            e.printStackTrace();
        }
    }
}
