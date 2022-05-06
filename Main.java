package com.integral;

public class Main {

    public static void SetFunction(String method){
        SearchIntegral search = new SearchIntegral();
        long start = System.currentTimeMillis();
        //System.out.println("\nМетод левых прямоугольников: \n" + "Интеграл = " + search.method(0.5, 1.2, 52));
        long finish = System.currentTimeMillis();
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");
    }

    public static void main(String[] args) {
        Test1();
        //Test2();
    }

    public static void Test0(){

    }

    public static void Test1(){ //с 10 до 100 в отчет
        System.out.println("Лабораторная работа №3: Вычисление интеграла" +
                "\n Функция: \n");

        SearchIntegral search = new SearchIntegral();

        long start = System.currentTimeMillis();
        System.out.println("\nМетод левых прямоугольников: \n" + "Интеграл = " + search.LeftRectangle(0.5, 1.2, 10));
        long finish = System.currentTimeMillis();
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод правых прямоугольников: \n" + "Интеграл = " + search.RightRectangle(0.5, 1.2, 10));
        finish = System.currentTimeMillis();
        System.out.println("\nВремя выполнения алгоритма " + (finish - start)+ " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод центральных прямоугольников: \n" + "Интеграл = " + search.CenterRectangle(0.5, 1.2, 10));
        finish = System.currentTimeMillis();
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("Метод трапеции: \n" + "Интеграл = " + search.Trapezoid(0.5, 1.2, 10));
        finish = System.currentTimeMillis();
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nПравило Симпсона: \n" + "Интеграл = " + search.Simpson(0.5, 1.2, 10));
        finish = System.currentTimeMillis();
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");
    }

    public static void Test2(){

    }
}
