package com.company;

public class Main {

    public static void main(String[] args) {
        Test1();
        //Test2();
    }
    public static void Test1(){
        System.out.println("Лабораторная работа №1: Поиск минимального экстремума функции, различными методами поиска" +
                "\n Функция: y = 64 ^ x - 8 ^ x - 56\n");
        SearchAlgorithms search = new SearchAlgorithms();

        long start = System.currentTimeMillis();
        System.out.println("Метод дихотомии: \n" + "Экстремум y = " + search.DichotomySearch(0.01, 0.0000001, -1, 0));
        long finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод золотого сечения: \n" + "Экстремум y = " + search.GoldenSearch(-1, 0, 0.01));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод хорд: \n" + "Экстремум y = " + search.SecantSearch(-1, 0, 0.01));//+ search.SearchSecant(-1, 0.000001));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");
    }

    public static void Test2(){
        System.out.println("Лабораторная работа №1: Поиск минимального экстремума функции, различными методами поиска" +
                "\n Функция: y = 64 ^ x - 8 ^ x - 56\n");
        SearchAlgorithms search = new SearchAlgorithms();

        long start = System.currentTimeMillis();
        System.out.println("Метод дихотомии: \n" + "Экстремум y = " + search.DichotomySearch(0.01, 0.0000001, -1, 1));
        long finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод золотого сечения: \n" + "Экстремум y = " + search.GoldenSearch(-1, 1, 0.01));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод хорд: \n" + "Экстремум y = " + search.SecantSearch(-1, 1, 0.01));//+ search.SearchSecant(-1, 0.000001));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");
    }
}
