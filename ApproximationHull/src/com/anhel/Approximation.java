package com.anhel;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class Approximation {
    ArrayList<Integer> list_ = new ArrayList<Integer>();

    public void approxHull(double[] X, double[] Y, int n) {
        int begin = returnLeft(X);
        int end = returnRight(X);
        int k = 0;
        int[] max = new int[n];
        int[] min = new int[n];
        int[][] list = new int[n][X.length];
        list = division(n, X);
        findLeft(X,Y,begin);

        for (int i = 0; i < n; i++) {
            max[i] = findMax(list, Y, i);
            list_.add(max[i]);
            System.out.println("(" + X[max[i]] + "; " + Y[max[i]] + ")");
        }

        findRight(X,Y,end);

        for (int i = n - 1; i >= 0; i--) {
            min[i] = findMin(list, Y, i);
            list_.add(min[i]);
            System.out.println("(" + X[min[i]] + "; " + Y[min[i]] + ")");
        }

    }

    private int [][] division(int n, double[] X) {
        int begin = returnLeft(X);
        int end = returnRight(X);
        double piece  = (X[end] - X[begin]) / n;
        int [][] list = new int[n][X.length];
        int k = 0;
        int p = 0;
        for (double i = X[begin]; i < X[end]; i += piece) {
            p = 0;
            for (int j = 0; j < X.length; j++) {
                if (X[j] >= i && X[j] < i + piece && X[j] != X[begin]) {
                    list[k][p] = j;
                    p++;
                }
            }
            if (k < n) k++;
        }
        return list;
    }

    private int findMax(int[][] list, double[] Y, int num){
        int max = list[num][0];
        for (int i = 0; i < list[num].length; i++) {
            if (Y[list[num][i]] > Y[max]) {
                max = list[num][i];
            }
        }
        return max;
    }
    /*
    private int findMin(List<Integer> list, double[] Y){
        int min = 0;
        for (int i = 0; i < list.size(); i++) {
            if (Y[list.get(i)] < Y[min]) {
                min = list.get(i);
            }
        }
        return min;
    }*/
    private int findMin(int[][] list, double[] Y, int num){
        int max = list[num][0];
        for (int i = 0; i < list[num].length; i++) {
            if (Y[list[num][i]] < Y[max]) {
                max = list[num][i];
            }
        }
        return max;
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

    private void findLeft(double [] X, double [] Y, int left) {
        int minLeft = left;
        int maxLeft = left;
        for (int i = 0; i < X.length; i++) {
            if (X[left] == X[i]) {
                if (Y[maxLeft] < Y[i]) {
                    maxLeft = i;
                }
                if (Y[minLeft] > Y[i]) {
                    minLeft = i;
                }
            }
        }
        System.out.println("(" + X[minLeft] + "; " + Y[minLeft] + ")");
        list_.add(minLeft);
        if (maxLeft != minLeft) {
            System.out.println("(" + X[maxLeft] + "; " + Y[maxLeft] + ")");
            list_.add(maxLeft);
        }
    }

    private void findRight(double [] X, double [] Y, int left) {
        int minRight = left;
        int maxRight = left;
        for (int i = 0; i < X.length; i++) {
            if (X[left] == X[i]) {
                if (Y[minRight] < Y[i]) {
                    minRight = i;
                }
                if (Y[minRight] > Y[i]) {
                    minRight = i;
                }
            }
        }
        System.out.println("(" + X[maxRight] + "; " + Y[maxRight] + ")");
        list_.add(maxRight);
        if (maxRight != minRight) {
            System.out.println("(" + X[minRight] + "; " + Y[minRight] + ")");
            list_.add(minRight);
        }
    }
}
