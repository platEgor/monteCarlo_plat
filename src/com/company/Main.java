package com.company;

import java.util.Date;

import static java.lang.Math.*;

public class Main {

    static double a = 1;

    public static void main(String[] args) {
        long t = new Date().getTime();

        long n = (long) pow(10, 4);
        double e = pow(10, -4);

        MonteCarlo mc1 = new MonteCarlo(0, 0, a, a){
            @Override
            public boolean belong(double xp, double yp){return Main.belong1(xp, yp);}
        };

        MonteCarlo mc2 = new MonteCarlo(0, 0, a, a){
            @Override
            public boolean belong(double xp, double yp){return Main.belong2(xp, yp);}
        };

        MonteCarlo mc3 = new MonteCarlo(0, 0, a, a){
            @Override
            public boolean belong(double xp, double yp){return Main.belong3(xp, yp);}
        };

        double a1 = mc1.areaMC(n, e)[0], a2 = mc2.areaMC(n, e)[0], a3 = mc3.areaMC(n, e)[0];
        System.out.println("Area1: " + a1 + "\nArea2: " + a2 + "\nArea3: " + a3 + "\nerror: " + abs(a*a-4*(a1+a2)-a3)/(a*a) + "\ntime: " + (double)(new Date().getTime() - t)/1000 + " s");
    }

    public static boolean belong1(double xp, double yp) {
        return (Math.sqrt( xp*xp + yp*yp ) > a
                && Math.sqrt( xp*xp + (yp-a)*(yp-a) ) < a
                && Math.sqrt( (xp-a)*(xp-a) + yp*yp) < a);
    }
    public static boolean belong2(double xp, double yp) {
        return (Math.sqrt( xp*xp + yp*yp ) > a
                && Math.sqrt((xp-a)*(xp-a) + yp*yp) > a);
    }
    public static boolean belong3(double xp, double yp) {
        return (Math.sqrt( xp*xp + yp*yp ) < a
                && Math.sqrt( (xp-a)*(xp-a) + (yp-a)*(yp-a) ) < a
                && Math.sqrt( xp*xp + (yp-a)*(yp-a) ) < a
                && Math.sqrt( (xp-a)*(xp-a) + yp*yp ) < a);
    }
}