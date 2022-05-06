package com.differential;

public class Main {

    public static void main(String[] args) {
	    Test1();
    }

    public static void Test1(){
        SearchDifferential search = new SearchDifferential();
        long start = System.nanoTime();
        double[] points = search.methodNelderMead();
        long finish = System.nanoTime();
        System.out.println("(" + points[0] + ", " + points[1] + ", " + points[2] + ")");
        System.out.print("Число итераций = " + search.getIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) / 1000 + " (мс)");

        //double[] u = new double[]{0.392837100659193, 0.392837100659193, -4.556910367646638};//, 0.15713484026367722};
        double[] u = new double[]{0.4, 0.4, -5};
        start = System.nanoTime();
        points  = search.methodGradientDescent(u, 0.1);
        finish = System.nanoTime();
        System.out.println("\n(" + points[0] + ", " + points[1] + ", " + points[2] + ")");
        System.out.print("Число итераций = " + search.getIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) / 1000 + " (мс)");
    }
}
