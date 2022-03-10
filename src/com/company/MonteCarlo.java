package com.company;

import java.util.SplittableRandom;

public abstract class MonteCarlo {
    private final double xmin, ymin, xmax, ymax;

    MonteCarlo(double Xmin, double Ymin, double Xmax, double Ymax){
        xmin = Xmin;
        ymin = Ymin;
        xmax = Xmax;
        ymax = Ymax;
    }

    public abstract boolean belong(double xp, double yp);

    public double[] areaMC(long n, double eg){
        SplittableRandom rnd = new SplittableRandom();
        double e, cx = 0, cy = 0, k = 0, s, s0 = 0, xp, yp;
        long i = 1;
        do{
            for (; i < n-1; i++) {
                xp = rnd.nextDouble(xmin, xmax);
                yp = rnd.nextDouble(ymin, ymax);
                if (belong(xp, yp)) {
                    cx += xp;
                    cy += yp;
                    k++;
                }
            }
            s = k/n;
            e = 2*Math.abs(s0 - s)/(s0 + s);
            s0 = s;
            i = n+1;
            n *= 1.2;
        } while (e > eg);
        return new double[]{s*(xmax - xmin)*(ymax - ymin), cx/k, cy/k, i};
    }
}