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

    }
}
