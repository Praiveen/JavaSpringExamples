package org.example;

import java.rmi.RemoteException;

public class EquationImpl implements Equation {

    @Override
    public OutObject solve(double a, double b, double c) throws RemoteException {
        double discriminant = b * b - 4 * a * c;

        if (discriminant >= 0) {
            double out1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double out2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new OutObject(out1, out2, true);
        } else {
            return new OutObject(0, 0, false);
        }
    }
}
