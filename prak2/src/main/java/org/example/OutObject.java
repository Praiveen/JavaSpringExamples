package org.example;

import java.io.Serializable;

public class OutObject implements Serializable {
//    private static final long serialVersionUID = 1L;

    private double out1;
    private double out2;
    private boolean hasOut;

    public OutObject(double out1, double out2, boolean hasOut) {
        this.out1 = out1;
        this.out2 = out2;
        this.hasOut = hasOut;
    }

    @Override
    public String toString() {
        if (hasOut) {
            return "корень 1: " + out1 + " корень 2: " + out2;
        } else {
            return "нет корней";
        }
    }
}
