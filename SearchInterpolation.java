package com.company;

public class SearchInterpolation {

    public double f(double x) { return 2 * Math.pow(x,2) - Math.pow(Math.E, x); } // Заданная функция z = 2*x^2-e^x

    public double Interpolation(double A, double H, double e){
        double[] x = new double[4];
        double[] f_min = new double[4];
        x[0] = A;
        x[1] = A + H;
        f_min[0] = f(x[0]);
        f_min[1] = f(x[1]);
        double high_tier, low_tier;
        double min;
            do {
                if (f(A) < f(A + H)) {
                    x[2] = A - H;
                    f_min[2] = f(x[2]);
                } else {
                    x[2] = A + 2 * H;
                    f_min[2] = f(x[2]);
                }
                //high_tier = (Math.pow(x[1], 2) - Math.pow(x[2], 2)) * f_min[0] + (Math.pow(x[2], 2) - Math.pow(x[0], 2)) * x[1] + (Math.pow(x[0], 2) - Math.pow(x[1], 2)) * f_min[2];
                //low_tier = (x[1] - x[2]) * f_min[0] + (x[2] - x[0]) * f_min[1] + (x[0] - x[1]) * f_min[2];
                //x[3] = 0.5 * (high_tier / low_tier);

                high_tier = 0.5 * (f_min[0] - f_min[1]) * (x[1] - x[2]) * (x[2] - x[0]);
                low_tier = (x[1] - x[2]) * f_min[0] + (x[2] - x[0]) * f_min[1] + (x[0] - x[1]) * f_min[2];
                x[3] = 0.5 * (x[0] + x[1]) + (high_tier/low_tier);
                f_min[3] = f(x[3]);

                for(int i = 0; i < 4; i++){
                    System.out.println(x[i]);
                    System.out.println(f_min[i]);
                }
                min = x[0];
                for(int i = 0; i < x.length; i++) {
                    if (x[i] < min) {
                        min = x[i];
                    }
                }
            } while (Math.abs(x[3] - A) < e);
        return min;
    }
}
