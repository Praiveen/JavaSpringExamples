package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server {
    public static void main(String[] args) {
        try {
            EquationImpl obj = new EquationImpl();
            Equation stub = (Equation) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(1090);
            registry.bind("Obj", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server err " + e.toString());
            e.printStackTrace();
        }
    }
}

//C:\Program Files\Java\jdk-21\bin
//C:\MIREA\3kurs5sem\java5sem\prak2\src\main\java
//java org.example.Client
//java org.example.Server
//Javac *.java