package com.integral;

public class SearchIntegral {

    public double f(double x) { return (2 * x + 0.5) / Math.sin(x); } // Заданная функция (2*x+0.5)/sin(x)

    /**
     * Метод прямоугольников
     * @param a Левая граница
     * @param b Правая граница
     * @param frac Отклонения для левой, правой и центральной формулы
     * @param n Кол-во элементарных отрезков
     * @return Сумма площади криволинейной трапеции (значение интеграла)
     */
    public double Rectangle(double a, double b, double frac, int n) {
        double result = 0;
        double h = (b - a) / n;
        for(int i = 0; i < n; i++)
            result += f(a + h * (i + frac));
        return h * result;
    }

    /**
     * Метод левых прямоугольников (первая формула прямоугольников)
     */
    public double LeftRectangle(double a, double b, int n){
        return Rectangle(a, b, 0, n);
    }

    /**
     * Метод правых прямоугольников (вторая формула прямоугольников)
     */
    public double RightRectangle(double a, double b, int n){
        return Rectangle(a, b, 1, n);
    }

    /**
     * Метод центральных прямоугольников (усложненная формула прямоугольников)
     */
    public double CenterRectangle(double a, double b, int n){
        return Rectangle(a, b, 0.5, n);
    }

    /**
     * Метод трапеции
     * @param a Левая граница
     * @param b Правая граница
     * @param n Кол-во элементарных отрезков
     * @return Сумма площади криволинейной трапеции (значение интеграла)
     */
    public double Trapezoid(double a,double b, int n){
        double result = (f(a) + f(b)) / 2;
        double h = (b - a) / n;
        for(int i = 1; i < n; i++)
            result += f(a + h * i);
        return h * result;
    }

    /**
     * Правило Симпсона для нахождения интеграла
     * @param a Левая граница
     * @param b Правая граница
     * @param n Кол-во элементарных отрезков
     * @return Сумма площади криволинейной трапеции (значение интеграла)
     */
    public double Simpson(double a, double b, int n){
        double result = 0;
        double h = (b - a) / n;
        for(int i = 0; i < n; i++){
            if(i % 2 == 0)
                result += 2 * f(a + h * i);
            else
                result += 4 * f(a + h * i);
        }
        return (h / 3) * result;
    }
}

