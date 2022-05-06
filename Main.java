package com.company;

public class Main {
    public static void main(String[] args) {
	    Test();
    }
    public static void Test(){
        SearchInterpolation search = new SearchInterpolation();
        System.out.println("\nМинимум функции = " + search.Interpolation(1, 0.5, 0.001));
    }
}
