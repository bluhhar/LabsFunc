package com.company;

public class SearchAlgorithms {

    private int _iteration = 0; // Число итераций
    private final double PHI = (1 + Math.sqrt(5)) / 2; // Число ФИ

    public double f(double x) { return Math.pow(64, x) - Math.pow(8, x) - 56; } // Заданная функция (64 ^ x - 8 ^ x - 56)

    public double df(double x) { return Math.pow(64, x) * Math.log(64) - Math.pow(8, x) * Math.log(8); } // Производная функции (64 ^ x * ln(64)- 8 ^ x * ln(8))

    public int GetIteration() { return _iteration; } // Метод возвращающий число итераций

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
        _iteration = 0;
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
            _iteration++;
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
        _iteration = 0;
        while (Math.abs(b - a) < e) {
            x_1 = b - (b - a) / PHI;
            x_2 = a + (b - a) / PHI;
            if (f(x_1) >= f(x_2))
                a = x_1;
            else
                b = x_2;
            _iteration++;
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
        _iteration = 0;
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
            _iteration++;
        }
        return f(x_1);
    }
    /*
    Метод Ньютона
    //double x_1 = a - (df(x_0)*e)/(df(x_0+e)-df(x_0));
        //x_1 = a - (df(a) - df(a - e)) / e;
        //x_1 = x_0 - (df(x_0)-df(x_0-e))/e;
        //x_1 = a - (f(a) * (b - a)) / (f(b) - f(a));
                    //x_1 = a - (f(a)  / (f(b) - f(a))) * (b - a); //ДОБАВИТЬ ФУНКЦИЮ ПРОИЗВОДНОЙ

            //x_1 = x_0 - (df(x_1) * ((x_0 - x_1)) / df(x_0) - df(x_1));
            //x_1 = x_0 - (f(x_1) * ((x_0 - x_1)) / f(x_0) - f(x_1));
            //x_1 = x_0 - (f(x_0) * ((x_1 - x_0)) / f(x_1) - f(x_0));
            //x_1 = a - (f(a) * ((b - a)) / f(b) - f(a));
     */
    public double SearchSecant(double x_0, double e){
        _iteration = 0;
        double x_1 = x_0 - (f(x_0)*e)/(f(x_0+e)-f(x_0));
        double x_2 = (f(x_1)*x_0-f(x_0)*x_1)/(f(x_1)-f(x_0));
        x_0 = x_1;
        x_1 = x_2;
        while(Math.abs(x_1-x_0) > e){
            x_2 = (f(x_1)*x_0-f(x_0)*x_1)/(f(x_1)-f(x_0));
            x_0 = x_1;
            x_1 = x_2;
            _iteration++;
        }
        return x_2;
    }
}
