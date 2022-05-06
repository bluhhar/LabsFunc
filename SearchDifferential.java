package com.differential;

public class SearchDifferential {
    private int _iteration = 0;

    // Константы для метода Нелдера-Мида
    private final double _alpha = 1.0;
    private final double _beta = 0.5;
    private final double _gamma = 2.0;
    private final double _epsilon = 0.01;

    private final int _T = 1;
    private final int _NDIM = 3;

    private int _H = 0;
    private int _L = 0;

    public double f(double[] x) { return Math.pow((x[0] - 1),4) + Math.pow((x[1] - 3), 2) + 4 * Math.pow((x[2] + 5), 4); } // Заданная функция (x1-1)^4+(x2-3)^2+4*(x3+5)^4

    public int getIteration() { return _iteration; } // Метод возвращающий число итераций

    /**
     * Метод, который инициализирует вершины симплекса
     * @return Массив вершин симплекса
     */
    public double[][] getRegularSimplex() {
        double[][] points = new double[_NDIM + 1][_NDIM];
        for (int j = 0; j < _NDIM + 1; j++) {
            for (int k = 0; k < _NDIM; k++) {
                if (j == 0) {
                    points[j][k] = 0;
                } else {
                    if (j == k + 1) {
                        points[j][k] = _T / (_NDIM * Math.sqrt(2)) * (Math.sqrt(_NDIM + 1) - 1 + _NDIM);
                    } else {
                        points[j][k] = _T / (_NDIM * Math.sqrt(2)) * (Math.sqrt(_NDIM + 1) - 1);
                    }
                }
            }
        }
        return points;
    }

    /**
     * Метод, который находит значения функции в точках
     * @param points - массив вершин симплекса
     * @return Массив значений фукнции в точках
     */
    public double[] getFunctionValues(double[][] points){
        double[] values = new double[_NDIM+1];
        for(int i = 0; i <= _NDIM; i++){
            values[i] = f(points[i]);
        }
        return values;
    }

    /**
     * Процедура, которая находит индексы минимального и максимального значения
     * в точках (значениях функции)
     * @param values - массив значений функции в точках
     * Присваивает значения минимального и максимального индекса для глобальных
     *               переменных _L и _H
     */
    public void getMinMaxIndexsOfPoints(double[] values) {
        for (int j = 0; j < _NDIM + 1; j++){//поиск H и L - индексов вершин с макс. и мин. значениями функции{
            if (values[j] > values[_H]) {
                _H = j;
            }
            if (values[j] < values[_L]) {
                _L = j;
            }
        }
    }

    /**
     * Метод, который ищет центр тяжести всех вершин за исключением points[_H]
     * @param points - массив вершин симплекса
     * @return Массив центра тяжести симплекса
     */
    public double[] getCenterOfGravity(double[][] points){
        double sum;
        double[] C = new double[_NDIM];
        for(int i = 0; i < _NDIM; i++){
            sum = 0;
            for(int j = 0; j <= _NDIM; j++){
                sum += points[j][i];
            }
            C[i] = (1.0 / _NDIM) * (sum - points[_H][i]);
        }
        return C;
    }

    /**
     * Проверка на завершенность
     * @param values - массив значений функции в точках
     * @param valuesCenter - массив центра тяжести симплекса
     * @return Значение суммы
     */
    public double checkForCompletion(double[] values, double[] valuesCenter){
        double sum = 0;
        for(int i = 0; i <= _NDIM; i++){
            sum += Math.pow(values[i] - f(valuesCenter), 2);
        }
        return Math.sqrt((1.0/(_NDIM+1))*sum);
    }

    /**
     * Проверка на сжатие
     * @param values - массив значений функции в точках
     * @param valuesReflect - массив значений сжатия вершин симплекса
     * @return true - сжатие эффективно > редукция вершин, false - если сжатие неэффективно < редукция
     */
    public boolean checkForStretching(double[] values, double[] valuesReflect){
        for (int j = 0; j < _NDIM + 1; j++) {
            if (j != _H) {
                if (f(valuesReflect) < values[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Функция отражения вершин симплекса
     * @param points - массив вершин симплекса
     * @param valuesCenter - массив центра тяжести симплекса
     * @return Массив точек отражения вершин симплекса
     */
    public double[] functionReflecting(double[][] points, double[] valuesCenter){
        double[] valuesReflect = new double[_NDIM];
        for(int i = 0; i < _NDIM; i++){
            valuesReflect[i] = valuesCenter[i] + _alpha * (valuesCenter[i] - points[_H][i]);
        }
        return valuesReflect;
    }

    /**
     * Функция растяжения вершин симплекса
     * @param valuesReflect - массив значений сжатия вершин симплекса
     * @param valuesCenter - массив центра тяжести симплекса
     * @return Массив точек растяжения вершин симплекса
     */
    public double[] functionStretching(double[] valuesReflect, double[] valuesCenter){
        double[] valuesStretch = new double[_NDIM];
        for(int i = 0; i < _NDIM; i++){
            valuesStretch[i]=valuesCenter[i]+_gamma*(valuesReflect[i]-valuesCenter[i]);
        }
        return valuesStretch;
    }

    /**
     * Функция сжатия вершин симплекса
     * @param valuesCenter - массив центра тяжести симплекса
     * @param points - массив вершин симплекса
     * @return Массив точек сжатия вершин симплекса
     */
    public double[] functionZipping(double[] valuesCenter, double[][] points){
        double[] valuesZip = new double[_NDIM];
        for(int i = 0; i < _NDIM; i++) {
            valuesZip[i]=valuesCenter[i]+_beta*(points[_H][i]-valuesCenter[i]);
        }
        return valuesZip;
    }

    /**
     * Функция редукции вершин симплекса
     * @param points - массив вершин симплекса
     *               Пересчитывает вершины симплекса
     */
    public void functionReducting(double[][] points){
        for(int j=0;j<_NDIM+1;j++) {
            for(int k=0;k<=_NDIM-1;k++) {
                if (j!=_L){
                    points[j][k]=points[_L][k]+0.5*(points[j][k]-points[_L][k]);
                }
            }
        }
    }

    /**
     * Функция переприсваивания вершин симплекса
     * (убирает переизбыток циклов в коде)
     * @param points - массив вершин симплекса
     * @param imp - имплекант переприсваивания
     * @return Массив переприсвоиных вершин
     */
    public double[][] functionReAssignment(double[][] points, double[] imp){
        for (int k = 0; k < _NDIM; k++) {
            points[_H][k] = imp[k];
        }
        return points;
    }

    /**
     * Метод Нелдера-Мида
     * @return Массив точек в которых находится минимум функции
     */
    public double[] methodNelderMead(){
        double[][] points = getRegularSimplex();
        double[] values = getFunctionValues(points);
        getMinMaxIndexsOfPoints(values);
        double[] valuesCenter = getCenterOfGravity(points);
        double[] valuesReflect, valuesStretch, valuesZip;
        _iteration = 0;
        while(checkForCompletion(values, valuesCenter) > _epsilon){
            valuesReflect = functionReflecting(points, valuesCenter);
            if(f(valuesReflect) < values[_L]) {
                valuesStretch = functionStretching(valuesReflect, valuesCenter);
                if (f(valuesStretch) < f(valuesReflect)) {
                    functionReAssignment(points, valuesStretch);
                } else {
                    functionReAssignment(points, valuesReflect);
                }
            } else {
                if (checkForStretching(values, valuesReflect)) {
                    if (f(valuesReflect)< values[_H]) {
                        functionReAssignment(points, valuesReflect);
                    }
                    valuesZip = functionZipping(valuesCenter, points);
                    if (f(valuesZip) < values[_H]) {
                        functionReAssignment(points, valuesZip);
                    } else {
                        functionReducting(points);
                    }
                } else {
                    functionReAssignment(points, valuesReflect);
                }
            }
            values = getFunctionValues(points);
            getMinMaxIndexsOfPoints(values);
            valuesCenter = getCenterOfGravity(points);
            _iteration++;
        }
        return valuesCenter;
    }

    /**
     * Метод получения градиента вектора
     * @param vector - массив координат вектора
     * @return Массив значений градиента вектора
     */
    private double[] getGradient(double[] vector){
        double step = 1e-1;
        double[] gradient = new double[_NDIM],
                 temp = new double[_NDIM];
        for (byte i = 0; i < _NDIM; i++) {
            for(byte j = 0; j < _NDIM; j++){
                if(i == j){
                    temp[j] = vector[j] + step;
                    continue;
                }
                temp[j] = vector[j];
            }
            gradient[i] = (f(temp) - f(vector)) / step;
        }
        return gradient;
    }

    /**
     * Метод длины результирующего вектора
     * @param vector - массив координат вектора
     * @return Сумму длины результирующего вектора
     */
    private double getNorm(double[] vector) {
        double result = 0;
        for (double v : vector) {
            result += v * v;
        }
        return Math.sqrt(result);
    }

    /**
     * Метод выбора направления спуска, вспомогательный для метода дихотомии
     * @param vector - массив координат вектора
     * @param a - альфа
     * @return Значение функции
     */
    private double getNextPoint(double[] vector, double a) {
        double[] gradient = getGradient(vector);
        double[] u = new double[vector.length];
        for (int i = 0; i < u.length; i++) {
            u[i] = vector[i] - a * gradient[0];
        }
        return f(u);
    }

    /**
     * Метод Дихотомии (для поиска минимума функции)
     * @param a - левая граница интервала
     * @param b - правая граница интервала
     * @param vector - массив координат вектора
     * @param e - эпсилон
     * @param delta - кратчайшие расстояние
     * @return Значение минимального экстремума заданной функции
     */
    private double searchDichotomy(double a, double b, double[] vector, double e, double delta) {
        double x1, x2;
        do {
            x1 = (b + a - delta) / 2;
            x2 = (b + a + delta) / 2;
            double fx_1 = getNextPoint(vector, x1);
            double fx_2 = getNextPoint(vector, x2);
            if (fx_1 < fx_2) {
                b = x2;
            } else if (fx_1 > fx_2) {
                a = x1;
            } else {
                a = x1;
                b = x2;
            }
        } while (Math.abs(b - a) >= e);

        return (b + a) / 2;
    }

    /**
     *Метод наискорейшего спуска
     * @param startVector - начальный вектор приближения
     * @param eps - эпсилон
     * @return Массив точек в которых находится минимум функции
     */
    public double[] methodGradientDescent(double[] startVector, double eps) {
        double[] gradient = getGradient(startVector);
        _iteration = 0;
        while (Math.abs(getNorm(gradient)) > eps) {
            double b = 1;
            double alpha = searchDichotomy(0, b, startVector, eps, eps / 10);
            for (int i = 0; i < startVector.length; i++) {
                startVector[i] -= alpha * gradient[i];
            }
            gradient = getGradient(startVector);
            _iteration++;
        }
        return startVector;
    }
}
