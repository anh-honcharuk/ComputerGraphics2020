package com.anhel;

public class Main {

    public static void main(String[] args) {
        QuickHull quickHull = new QuickHull();
        double [] X = {1, 2, 5, 6, 6, 7, 10, 5, 3, 6, 5, 8};
        double [] Y = {1, 5, 8, 3, 7, 2, 0, -5, 3, -5, 5, 0};
        quickHull.quickHull(X, Y);
    }
}
