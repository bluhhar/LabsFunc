package com.integral;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                Лабораторная работа №3: Вычисление интеграла
                Функция: (2 * x + 0.5) / sin(x)
                a = 0.5, b = 1.2
                """);
        System.out.println("Введите номер теста:");
        int number = in.nextInt();

        switch (number) {
            case 1 -> {
                System.out.println("Тест 1: Свободный выбор количества элементарных отрезков: ");
                int n = in.nextInt();
                Test0(n);
            }
            case 2 -> {
                System.out.println("Тест 2: n = 10");
                Test0(10);
            }
            case 3 -> {
                System.out.println("Тест 3: n = 100");
                Test0(100);
            }
            default ->
                    System.out.println("Error: Введен номер несуществующего теста!");
        }
        in.close();
    }

    public static void Test0(int n){
        SearchIntegral search = new SearchIntegral();

        long start = System.nanoTime();
        System.out.println("\nМетод левых прямоугольников: \n" + "Интеграл = " + search.LeftRectangle(0.5, 1.2, n));
        long finish = System.nanoTime();
        System.out.println("Время выполнения алгоритма " + (finish - start) / 1000 + " (мс)");

        start = System.nanoTime();
        System.out.println("\nМетод правых прямоугольников: \n" + "Интеграл = " + search.RightRectangle(0.5, 1.2, n));
        finish = System.nanoTime();
        System.out.println("Время выполнения алгоритма " + (finish - start) / 1000 + " (мс)");

        start = System.nanoTime();
        System.out.println("\nМетод центральных прямоугольников: \n" + "Интеграл = " + search.CenterRectangle(0.5, 1.2, n));
        finish = System.nanoTime();
        System.out.println("Время выполнения алгоритма " + (finish - start) / 1000 + " (мс)");

        start = System.nanoTime();
        System.out.println("\nМетод трапеции: \n" + "Интеграл = " + search.Trapezoid(0.5, 1.2, n));
        finish = System.nanoTime();
        System.out.println("Время выполнения алгоритма " + (finish - start) / 1000 + " (мс)");

        start = System.nanoTime();
        System.out.println("\nПравило Симпсона: \n" + "Интеграл = " + search.Simpson(0.5, 1.2, n));
        finish = System.nanoTime();
        System.out.println("Время выполнения алгоритма " + (finish - start) / 1000 + " (мс)");
    }
}