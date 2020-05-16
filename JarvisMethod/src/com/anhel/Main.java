package com.anhel;

public class Main {

    public static void main(String[] args) {
        JarvisMethodWithDivision jarvis = new JarvisMethodWithDivision();
        //double [] X = {1, 2, 3, 7, 5, 6 , 8, 8, 9 , 10, 11,  12};
        //double [] Y = {3, -2, -3, -5, 5, 5, 5, 1, 1, -1, 4, 3};
        double [] X = {1, 2, 5, 6, 6, 7, 10, 5, 3, 6, 5, 8};
        double [] Y = {1, 5, 8, 3, 7, 2, 0, -5, 3, -5, 5, 0};
        jarvis.jarvisMethod(X, Y);
    }
}
