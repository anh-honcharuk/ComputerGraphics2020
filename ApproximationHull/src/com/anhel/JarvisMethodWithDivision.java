package com.anhel;

import static java.lang.Math.acos;

public class JarvisMethodWithDivision {

    int[] rightPoints = new int[100];
    int[] leftPoints = new int[100];
    int rightIndex = 0;
    int leftIndex = 0;
    //int[] result = new int[100];

    public void jarvisMethod(double[] X, double[] Y) {
        int[] edgePoints = findEdgePoints(Y);
        int topY = edgePoints[0];
        int bottomY = edgePoints[1];
        division(X, Y, topY, bottomY);

        System.out.println("begin");
        System.out.println("(" + X[bottomY] + "; " + Y[bottomY] + ")");
        findMinAngle(X, Y, -1, bottomY, topY,1);
        findMinAngle(X, Y, -1, topY, bottomY,-1);
        System.out.println("end");
    }

    public void findMinAngle(double[] X, double[] Y, int beginPoit, int endPoint, int stopPoint, int n){
        double X1;
        double Y1;
        double X2 = X[endPoint];
        double Y2 = Y[endPoint];

        if (beginPoit == -1){
            X1 = X2 - n;
            Y1 = Y2;
        } else {
            X1 = X[beginPoit];
            Y1 = Y[beginPoit];
        }
        double angle;
        double min;
        int minIndex = 0;
        if (n == 1) {
            min = 10;
            for (int i = 0; i < rightIndex; i++){
                angle = findAngle(X[rightPoints[i]], Y[rightPoints[i]], X1, Y1, X2, Y2);
                if (angle < min && (X[rightPoints[i]] != X2 || Y[rightPoints[i]] != Y2)){
                    min = angle;
                    minIndex = rightPoints[i];
                }
            }
            System.out.println("(" + X[minIndex] + "; " + Y[minIndex] + ")");
        }
        if (n == -1) {
            min = 3;
            for (int i = 0; i < leftIndex; i++){
                angle = findAngle(X[leftPoints[i]], Y[leftPoints[i]], X1, Y1, X2, Y2);
                if (angle < min && (X[leftPoints[i]] != X2 || Y[leftPoints[i]] != Y2)){
                    min = angle;
                    minIndex = leftPoints[i];
                }
            }
            System.out.println("(" + X[minIndex] + "; " + Y[minIndex] + ")");
        }

        if (minIndex != stopPoint) {
            findMinAngle(X, Y, endPoint, minIndex, stopPoint,n);
        }

    }

    public double findAngle ( double x, double y, double x1, double y1, double x2, double y2){
        double ABx = x2 - x1;
        double ABy = y2 - y1;
        double BCx = x - x2;
        double BCy = y - y2;
        double mod = (ABx * BCx + ABy * BCy) / (Math.sqrt(ABx * ABx + ABy * ABy) * Math.sqrt(BCx * BCx + BCy * BCy));
        double angle = acos(mod);

        return angle;
    }

    public double whereIsPoint(double[] X, double[] Y, int topY, int bottomY, int point) {
        double x = (Y[point] - Y[bottomY]) * (X[topY] - X[bottomY]) / (Y[topY] - Y[bottomY]) - X[bottomY];
        return x;
    }

    public void division(double[] X, double[] Y, int topY, int bottomY) {
        int n;
        for (int i = 0; i < X.length; i++) {
            n = isItUp (X[i], X[i], X[bottomY], Y[bottomY], X[topY], Y[topY]);
            if (n == 0) {
               // System.out.println(i + "___");
                rightPoints[rightIndex] = i;
                rightIndex++;
            }
            if (n == 1) {
               // System.out.println(i);
                leftPoints[leftIndex] = i;
                leftIndex++;
            }

        }
       rightPoints[rightIndex] = topY;
        rightIndex++;
        leftPoints[leftIndex] = bottomY;
        leftIndex++;
        rightPoints[rightIndex] = bottomY;
        rightIndex++;
        leftPoints[leftIndex] = topY;
        leftIndex++;
    }

    public int isItUp ( double x, double y, double x1, double y1, double x2, double y2){ //???
        double arrowTime = (x2 - x1) * (y1 - y) - (x1 - x) * (y2 - y1);
        if (arrowTime > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public int[] findEdgePoints(double[] Y) {
        int topY = 0;
        int bottomY = 0;
        for (int i = 1; i < Y.length; i++) {
            if (Y[topY] < Y[i]) {
                topY = i;
            }
            if (Y[bottomY] > Y[i]) {
                bottomY = i;
            }
        }
        int[] mainPoints = {topY, bottomY};
        return mainPoints;
    }
}
