package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                Лабораторная работа №1: Поиск минимального экстремума функции, различными методами поиска
                Функция: y = 64 ^ x - 8 ^ x - 56
                """);
        System.out.println("Введите номер теста:");
        int number = in.nextInt();
        double e, delta, a, b;
        switch (number) {
            case 1 -> {
                System.out.println("Тест 1: Свободный выбор параметров (порядок e >> delta >> a >> b, с запятой, вводи через enter по одному параметру): ");
                e = in.nextDouble();
                delta = in.nextDouble();
                a = in.nextDouble();
                b = in.nextDouble();
                Test1(a, b, e, delta);
            }
            case 2 -> {
                System.out.println("Тест 2: left = -1, right = 0, epsilon = 0.0000001, delta = 0.01");
                Test2(-1, 0, 0.0000001, 0.01);
            }
            case 3 -> {
                System.out.println("Тест 3: left = -1, right = 1, epsilon = 0.0000001, delta = 0.01");
                Test3(-1, 1, 0.0000001, 0.01);
            }
            default ->
                System.out.println("Error: Введен номер несуществующего теста!");
        }
        in.close();
    }
    public static void Test1(double a, double b, double e, double delta) {
        SearchAlgorithms search = new SearchAlgorithms();

        long start = System.currentTimeMillis();
        System.out.println("Метод дихотомии: \n" + "Экстремум y = " + search.DichotomySearch(delta, e, a, b));
        long finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод золотого сечения: \n" + "Экстремум y = " + search.GoldenSearch(a, b, delta));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод хорд: \n" + "Экстремум y = " + search.SecantSearch(a, b, delta));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");
    }

    public static void Test2(double a, double b, double e, double delta) {
        SearchAlgorithms search = new SearchAlgorithms();

        long start = System.currentTimeMillis();
        System.out.println("Метод дихотомии: \n" + "Экстремум y = " + search.DichotomySearch(delta, e, a, b));
        long finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод золотого сечения: \n" + "Экстремум y = " + search.GoldenSearch(a, b, delta));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод хорд: \n" + "Экстремум y = " + search.SecantSearch(a, b, delta));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");
    }

    public static void Test3(double a, double b, double e, double delta) {
        SearchAlgorithms search = new SearchAlgorithms();

        long start = System.currentTimeMillis();
        System.out.println("Метод дихотомии: \n" + "Экстремум y = " + search.DichotomySearch(delta, e, a, b));
        long finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод золотого сечения: \n" + "Экстремум y = " + search.GoldenSearch(a, b, delta));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");

        start = System.currentTimeMillis();
        System.out.println("\nМетод хорд: \n" + "Экстремум y = " + search.SecantSearch(a, b, delta));
        finish = System.currentTimeMillis();
        System.out.print("Число итераций = " + search.GetIteration());
        System.out.println("\nВремя выполнения алгоритма " + (finish - start) + " (мс)");
    }
}
