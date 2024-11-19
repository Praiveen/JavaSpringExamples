package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Equation extends Remote {
    OutObject solve(double a, double b, double c) throws RemoteException;
}
