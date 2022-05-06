package com.differential;

public class Main {

    public static void main(String[] args) {
	    Test1();
    }

    public static void Test1(){
        SearchDifferential search =new SearchDifferential();
        double[] points = search.methodNelderMead();
        for(int i = 0; i < points.length; i++){
            System.out.println(points[i]);
        }
        //double[] u = new double[]{0.392837100659193, 0.392837100659193, -4.556910367646638};//, 0.15713484026367722};
        double[] u = new double[]{0.4, 0.4, -5};
        double[] x = search.findBySteepestDescent(u, 0.1);
        for(int i = 0; i < x.length; i++){
            System.out.println(x[i]);
        }
    }
}
