package com.anhel;

public class QuickHull {
    int [] Result = new int[100];
    int number = 0;
    int number2 = 0;

    public void quickHull(double [] X, double [] Y){
        int left = returnLeft(X);
        int right = returnRight(X);
        findMaxDistancePoint(X, Y, 0, left, right  );
        sortUpPoints(left, right, X);
        findMaxDistancePointDown(X, Y, 1, left, right  );
        sortDownPoints(X);
        printShell(X, Y);
    }

    public void printShell(double [] X, double [] Y){
        System.out.println("begin");
        for (int i = 0; i < number; i++){
            System.out.println("(" + X[Result[i]] + "; " + Y[Result[i]] + ")");
        }
        System.out.println("(" + X[Result[0]] + "; " + Y[Result[0]] + ")");
        System.out.println("end");
    }

    public void sortUpPoints(int left, int right, double [] X){
        Result[number] = left;
        Result[number + 1] = right;
        int n = number + 2;
        int temp;
        for (int  i = 0; i < n; i++){
            for (int  j = 0; j < n; j++){
                if(X[Result[i]] < X[Result[j]]){
                    temp = Result[i];
                    Result[i] = Result[j];
                    Result[j] = temp;
                }
            }
        }
        number = n;
        number2 = number;
    }

    public void sortDownPoints(double [] X){
        int temp;
        for (int  i = number2; i < number; i++){
            for (int  j = number2; j < number; j++){
                if(X[Result[i]] > X[Result[j]]){
                    temp = Result[i];
                    Result[i] = Result[j];
                    Result[j] = temp;
                }
            }
        }
    }

    public void findMaxDistancePoint(double [] X, double [] Y, int upOrDown, int left, int right) {
        //System.out.println("---------");
        double maxDistance = 0;
        double distance;
        int k = 0;
        if (upOrDown == 0) {
            for (int i = 0; i < X.length; i++) {
                distance = findDistance(X[i], Y[i], X[left], Y[left], X[right], Y[right]);
                if (maxDistance < distance && isItUp(X[i], Y[i], X[left], Y[left], X[right], Y[right]) == 0) {
                    maxDistance = distance;
                    k = i;
                }
            }
            //System.out.println("*");
            if (maxDistance > 0) {
                //System.out.println(k);
                Result[number] = k;
                number ++;
                findMaxDistancePoint(X, Y, upOrDown, left, k);
                findMaxDistancePoint(X, Y, upOrDown, k, right);
            }
        }
    }

    public void findMaxDistancePointDown(double [] X, double [] Y, int upOrDown, int left, int right) {
        //System.out.println("---");
        double maxDistance = 0;
        double distance;
        int k = 0;
        if (upOrDown != 0) {
            for (int i = 0; i < X.length; i++) {
                distance = findDistance(X[i], Y[i], X[left], Y[left], X[right], Y[right]);
                if (maxDistance < distance && isItUp(X[i], Y[i], X[left], Y[left], X[right], Y[right]) != 0) {
                    maxDistance = distance;
                    k = i;
                }
            }
           // System.out.println("*");
            if (maxDistance > 0) {
                //System.out.println(k);
                Result[number] = k;
                number ++;
                findMaxDistancePointDown(X, Y, upOrDown, left, k);
                findMaxDistancePointDown(X, Y, upOrDown, k, right);
            }
        }
    }

    public int isItUp(double x, double y, double x1, double y1, double x2, double y2) { //???
        double arrowTime = (x2 - x1) * (y1 - y) - (x1 - x) * (y2 - y1);
        if (arrowTime < 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public double findDistance(double x, double y, double x1, double y1, double x2, double y2) {
        double distance;
        double A = equationOfLineA(y1, y2);
        double B = equationOfLineB(x1, x2);
        double C = equationOfLineC(x1, y1, x2, y2);
        distance = Math.abs(A * x + B * y + C) / Math.sqrt(A * A + B * B);
        return distance;
    }

    public double equationOfLineA( double y1, double y2) {
        return y1 - y2;
    }

    public double equationOfLineB(double x1, double x2) {
        return x2 - x1;
    }

    public double equationOfLineC(double x1, double y1, double x2, double y2) {
        return x1 * (y2 - y1) - y1 * (x2 - x1);
    }

    public int returnLeft(double [] X) {
        int k = 0;
        double min = X[0];
        for (int i = 1; i < X.length; i++) {
            if (min > X[i]) {
                min = X[i];
                k = i;
            }
        }
        return k;
    }

    public int returnRight(double [] X) {
        int k = 0;
        double max = X[0];
        for (int i = 1; i < X.length; i++) {
            if (max < X[i]) {
                max = X[i];
                k = i;
            }
        }
        return k;
    }
}
