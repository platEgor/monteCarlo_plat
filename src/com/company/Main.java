package com.company;

import java.util.Date;

import static java.lang.Math.*;

public class Main {

    static double a = 1;

    public static void main(String[] args) {
        long t = new Date().getTime();

        long n = (long) pow(10, 4);
        double e = pow(10, -4);

        MonteCarlo mc1 = new MonteCarlo(0, 0, a/2, a/2){
            @Override
            public boolean belong(double xp, double yp){return Main.belong1(xp, yp);}
        };

        MonteCarlo mc2 = new MonteCarlo(0, 0, a/2, a*(1-sqrt(3)/2)){
            @Override
            public boolean belong(double xp, double yp){return Main.belong2(xp, yp);}
        };

        MonteCarlo mc3 = new MonteCarlo(a*(1-sqrt(3)/2), a*(1-sqrt(3)/2), a/2, a/2){
            @Override
            public boolean belong(double xp, double yp){return Main.belong3(xp, yp);}
        };

        double[] a2 = mc2.areaMC(n, e), a1 = mc1.areaMC(n, e), a3 = mc3.areaMC(n, e);
        System.out.println("Area1: " + a1[0] + "\nArea2: " + 2*a2[0] + "\nArea3: " + 4*a3[0] + "\nerror: " + abs(a*a-4*(a1[0]+2*a2[0])-4*a3[0])/(a*a) + "\ntime: " + (double)(new Date().getTime() - t)/1000 + " s" + "\nn: " + a1[3] + " " + a2[3] + " " + a3[3]);
    }

    public static boolean belong1(double xp, double yp) {
        return (Math.sqrt( (xp-a)*(xp-a) + (yp-a)*(yp-a) ) > a
                && Math.sqrt( xp*xp + (yp-a)*(yp-a) ) < a
                && Math.sqrt( (xp-a)*(xp-a) + yp*yp) < a);
    }
    public static boolean belong2(double xp, double yp) {
        return (Math.sqrt(xp*xp + (yp-a)*(yp-a)) > a);
    }
    public static boolean belong3(double xp, double yp) {
        return (Math.sqrt( (xp-a)*(xp-a) + (yp-a)*(yp-a) ) < a);
    }
}