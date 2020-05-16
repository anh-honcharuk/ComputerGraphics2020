package com.anhel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Approximation approximation = new Approximation();

        Scanner scan = new Scanner(System.in);
        System.out.println("Введіть 0, щоб вивести готовий приклад, або інше число для того, щоб ввести точки самостійно:\n");
        int choose = scan.nextInt();

        if (choose == 0){
           /* QuickHull quickHull = new QuickHull();
        double [] X = {1, 2, 5, 6, 6, 7, 10, 5, 3, 6, 5, 8};
        double [] Y = {1, 5, 8, 3, 7, 2, 0, -5, 3, -5, 5, 0};
        quickHull.quickHull(X, Y);
*/
/*
            JarvisMethodWithDivision jarvis = new JarvisMethodWithDivision();
            //double [] X = {1, 2, 3, 7, 5, 6 , 8, 8, 9 , 10, 11,  12};
            //double [] Y = {3, -2, -3, -5, 5, 5, 5, 1, 1, -1, 4, 3};
            double [] X = {1, 2, 5, 6, 6, 7, 10, 5, 3, 6, 5, 8};
            double [] Y = {1, 5, 8, 3, 7, 2, 0, -5, 3, -5, 5, 0};
            jarvis.jarvisMethod(X, Y);*/

            double [] X = {1, 2, 4, 6, 6, 7, 10, 5, 3, 6, 5, 8, 2, 10};
            double [] Y = {1, 5, 8, 3, 7, 2, 0, -5, 3, -5, 5, 0, -1, -1};
            approximation.approxHull(X, Y, 3);

        } else {
            //QuickHull quickHull = new QuickHull();
            //JarvisMethodWithDivision jarvis = new JarvisMethodWithDivision();
            System.out.println("Введіть к-ть точок:");
            int n = scan.nextInt();
            System.out.println("Введіть координати точок (x, y):");
            double [] X = new double[n];
            double [] Y = new double[n];
            scanPoints(n, X, Y);
            approximation.approxHull(X, Y, 10);
            //quickHull.quickHull(X, Y);
            //jarvis.jarvisMethod(X, Y);

        }
    }

    public static void scanPoints(int n, double[] X, double[] Y) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            X[i] = scan.nextDouble();
            Y[i] = scan.nextDouble();
        }
    }
}
