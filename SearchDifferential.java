package com.differential;

public class SearchDifferential {
    private int _iteration = 0;

    private final double _alpha = 1.0;
    private final double _beta = 0.5;
    private final double _gamma = 2.0;
    private final double _epsilon = 0.01;

    private final int _T = 1;
    private final int _NDIM = 3;

    private int _H = 0;
    private int _L = 0;

    public double f(double[] x) { return Math.pow((x[0] - 1),4) + Math.pow((x[1] - 3), 2) + 4 * Math.pow((x[2] + 5), 4); } // Заданная функция (x1-1)^4+(x2-3)^2+4*(x3+5)^4

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

    public double[] getFunctionValues(double[][] points){
        double[] values = new double[_NDIM+1];
        for(int i = 0; i <= _NDIM; i++){
            values[i] = f(points[i]);
        }
        return values;
    }

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

    public double checkForCompletion(double[] values, double[] C){
        double sum = 0;
        for(int i = 0; i <= _NDIM; i++){
            sum += Math.pow(values[i] - f(C), 2);
        }
        return Math.sqrt((1.0/(_NDIM+1))*sum);
    }

    public boolean checkForStretching(double[] values, double[] R){
        for (int j = 0; j < _NDIM + 1; j++) {
            if (j != _H) {
                if (f(R) < values[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public double[] functionReflecting(double[][] points, double[] C){
        double[] R = new double[_NDIM];
        for(int i = 0; i < _NDIM; i++){
            R[i]=C[i]+_alpha*(C[i]-points[_H][i]);
        }
        return R;
    }

    public double[] functionStretching(double[] R, double[] C){
        double[] S = new double[_NDIM];
        for(int i = 0; i < _NDIM; i++){
            S[i]=C[i]+_gamma*(R[i]-C[i]);
        }
        return S;
    }

    public double[] functionZipping(double[] C, double[][] points){
        double[] Z = new double[_NDIM];
        for(int i = 0; i < _NDIM; i++) {
            Z[i]=C[i]+_beta*(points[_H][i]-C[i]);
        }
        return Z;
    }

    public void functionReducting(double[][] points){
        for(int j=0;j<_NDIM+1;j++) {
            for(int k=0;k<=_NDIM-1;k++) {
                if (j!=_L){
                    points[j][k]=points[_L][k]+0.5*(points[j][k]-points[_L][k]);
                }
            }
        }
    }

    public double[][] functionReAssignment(double[][] points, double[] imp){
        for (int k = 0; k < _NDIM; k++) {
            points[_H][k] = imp[k];
        }
        return points;
    }

    public double[] methodNelderMead(){
        double[][] points = getRegularSimplex();
        double[] values = getFunctionValues(points);
        getMinMaxIndexsOfPoints(values);
        double[] C = getCenterOfGravity(points);
        double[] R, S, Z;
        while(checkForCompletion(values, C) > _epsilon){
            R = functionReflecting(points, C);
            if(f(R) < values[_L]) {
                S = functionStretching(R, C);
                if (f(S) < f(R)) {
                    functionReAssignment(points, S);
                } else {
                    functionReAssignment(points, R);
                }
            } else {
                if (checkForStretching(values, R)) {
                    if (f(R)< values[_H]) {
                        functionReAssignment(points, R);
                    }
                    Z = functionZipping(C, points);
                    if (f(Z) < values[_H]) {
                        functionReAssignment(points, Z);
                    } else {
                        functionReducting(points);
                    }
                } else {
                    functionReAssignment(points, R);
                }
            }
            values = getFunctionValues(points);
            getMinMaxIndexsOfPoints(values);
            C = getCenterOfGravity(points);
        }
        return C;
    }
    /*
    ____________________________________________________________________________________________________________________
    ____________________________________________________________________________________________________________________
    ____________________________________________________________________________________________________________________
    ____________________________________________________________________________________________________________________
    ____________________________________________________________________________________________________________________
     */

    private double[] getGradient(double[] vector){
        double step = 1e-1;
        double[] grad = new double[_NDIM], temp = new double[_NDIM];

        for (byte i = 0; i < _NDIM; i++) {
            for(byte j = 0; j < _NDIM; j++){
                if(i == j){
                    temp[j] = vector[j] + step;
                    continue;
                }
                temp[j] = vector[j];
            }
            grad[i] = (f(temp) - f(vector)) / step;
        }
        return grad;
    }

    private double getNorm(double[] vector) {
        double result = 0;
        for (double aVector : vector) {
            result += aVector * aVector;
        }
        return Math.sqrt(result);

    }

    /*public double DichotomySearch(double delta, double e, double a, double b, double[] u) {
        double x_1 = (a + b)/ 2 - e;
        double x_2 = (a + b)/ 2 + e;
        double _delta = b-a;
        while(delta < _delta) {
            if(fun(x_1, u) <= fun(x_2, u)) {
                b = x_2;
            }
            else {
                a = x_1;
            }
            x_1 = (a + b) / 2 - e;
            x_2 = (a + b) / 2 + e;
            _delta = b - a;
        }
        return (a + b) / 2;
    }*/

    private double DichotomySearch(double a, double b, double[] u, double e) {
        double del = e / 10;
        double x1, x2;
        do {
            x1 = (b + a - del) / 2;
            x2 = (b + a + del) / 2;
            double i1 = getNextPoint(x1, u);
            double i2 = getNextPoint(x2, u);
            if (i1 < i2) {
                b = x2;
            } else if (i1 > i2) {
                a = x1;
            } else {
                a = x1;
                b = x2;
            }
        } while (Math.abs(b - a) >= e);

        return (b + a) / 2;
    }

    // Вспомогательная функция для выбора направления спуска методом дихотомии
    private double getNextPoint(double alpha, double[] u0) {
        double[] gradI0 = getGradient(u0);

        double[] u = new double[u0.length];
        for (int i = 0; i < u.length; i++) {
            u[i] = u0[i] - alpha * gradI0[0];
        }
        return f(u);
    }



    public double[] findBySteepestDescent(double[] u, double eps) {
        double[] gradI0 = getGradient(u);
        while (Math.abs(getNorm(gradI0)) > eps) {
            double b = 1;
            double alpha = DichotomySearch(0, b, u, eps);
            for (int i = 0; i < u.length; i++) {
                u[i] -= alpha * gradI0[i];
            }
            gradI0 = getGradient(u);
        }
        return u;
    }
}
