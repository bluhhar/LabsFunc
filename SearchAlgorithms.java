package com.company;

public class SearchAlgorithms {

    private int iteration = 0; // Число итераций
    private final double PHI = (1 + Math.sqrt(5)) / 2; // Число ФИ

    public double f(double x) { return Math.pow(64, x) - Math.pow(8, x) - 56; } // Заданная функция (64 ^ x - 8 ^ x - 56)

    public double df(double x) { return Math.pow(64, x) * Math.log(64) - Math.pow(8, x) * Math.log(8); } // Производная функции (64 ^ x * ln(64)- 8 ^ x * ln(8))

    public int GetIteration() { return iteration; } // Метод возвращающий число итераций

    /**
     * Метод Дихотомии
     * @param delta - кратчайшие расстояние
     * @param e - эпсилон
     * @param a - левая граница интервала
     * @param b - правая граница интервала
     * @return Значение минимального экстремума заданной функции
     */
    public double DichotomySearch(double delta, double e, double a, double b) {
        if (f(a) == 0)
            return a;
        if (f(b) == 0)
            return b;
        double x_1 = (a + b)/ 2 - e;
        double x_2 = (a + b)/ 2 + e;
        double _delta = b-a;
        iteration = 0;
        while(delta < _delta) {
            if(f(x_1) <= f(x_2)) {
                b = x_2;
            }
            else {
                a = x_1;
            }
            x_1 = (a + b) / 2 - e;
            x_2 = (a + b) / 2 + e;
            _delta = b - a;
            iteration++;
        }
        return f((a + b) / 2);
    }

    /**
     * Метод золотого сечения
     * @param a - левая граница интервала
     * @param b - правая граница интервала
     * @param e - эпсилон
     * @return Значение минимального экстремума заданной функции
     */
    public double GoldenSearch(double a, double b, double e) {
        double x_1, x_2;
        iteration = 0;
        while (true) {
        //while (Math.abs(b - a) < e) {
            x_1 = b - (b - a) / PHI;
            x_2 = a + (b - a) / PHI;
            if (f(x_1) >= f(x_2))
                a = x_1;
            else
                b = x_2;
            if (Math.abs(b - a) < e)
                break;
            iteration++;
        }
        return f((a + b) / 2);
    }

    /**
     * Метод Хорд
     * @param a - левая граница интервала
     * @param b - правая граница интервала
     * @param e - эпсилон
     * @return Значение минимального экстремума заданной функции
     */
    public double SecantSearch(double a, double b, double e) {
        double x_0 = a;
        double x_1 = b;
        double fx_1;
        iteration = 0;
        while (Math.abs(x_1 - x_0) > e) {
            x_0 = x_1;
            x_1 = a - (df(a)  / (df(b) - df(a))) * (b - a);
            fx_1 = f(x_1);
            if (fx_1 == 0) {
                break;
            }
            if(fx_1 * df(b) > 0) {
                b = x_1;
            }
            else {
                a = x_1;
            }
            iteration++;
        }
        return f(x_1);
    }
}
