package com.anhel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Chain {

    public void ChainMethod() throws FileNotFoundException {
        ArrayList<Point> vertex = readInputData("src/com/anhel/resource/vertex.txt");
        ArrayList<Point> edges = readInputData("src/com/anhel/resource/edges.txt");

        ArrayList<ArrayList<Point>> chains = buildChains(vertex, edges);

        while (true) {
            Scanner sc = new Scanner(System.in);

            Point point = new Point(0, 0);
            System.out.println("Введіть точку: ");
            point.x = sc.nextInt();
            point.y = sc.nextInt();

            if(!isVertex(vertex, point)){
                searchSector(chains, point, vertex);
            }

        }
    }

    public boolean isVertex(ArrayList<Point> vertex, Point point) {
        for (int i = 0; i < vertex.size(); i++) {
            if (point.equals(vertex.get(i))) {
                System.out.println("Point is vertex " + i);
                return true;
            }
        }
        return false;
    }

    public static ArrayList<ArrayList<Point>> buildChains(ArrayList<Point> V, ArrayList<Point> E) {
        ArrayList<ArrayList<Point>> arrayLines = new ArrayList<>();

        int[] Weight = new int[E.size()];

        for (int i = 0; i < E.size(); i++) {
            Weight[i] = 1;
        }
        for (int i = 1; i < V.size() - 1; i++) {
            int win = weightIn(Weight, E, i);
            int d1 = edgeOut1(Weight, E, i);
            int vOut = edgesOut(E, i);
            if (win > vOut) {
                Weight[d1] = win - vOut + 1;
            }
        }
        for (int i = V.size() - 2; i > 0; i--) {
            int wout = weightOut(Weight, E, i);
            int d2 = edgeIn1(Weight, E, i);
            int win = weightIn(Weight, E, i);
            if (wout > win) {
                Weight[d2] = wout - win + Weight[d2];
            }
        }
        boolean flag = true;
        while (flag) {
            ArrayList<Point> line = new ArrayList<>();
            int nextV = 0;
            while (nextV != V.size() - 1) {
                int nextE = edgeOut1(Weight, E, nextV);
                Weight[nextE]--;
                nextV = E.get(nextE).y;
                line.add(E.get(nextE));
            }
            arrayLines.add(line);
            flag = edgeOut1(Weight, E, 0) != -1;
        }

        int n = arrayLines.size();
        for (int i = 0; i < n; i++) {
            int k = arrayLines.get(i).size();
            System.out.println( i + ") ____________");
            for (int j = 0; j < k; j++) {
                System.out.println("(" + arrayLines.get(i).get(j).x + ";  " + arrayLines.get(i).get(j).y + ")");
            }
        }

        return arrayLines;
    }

    public static int isRightFromEdge(Point a, Point b, Point middle) {
        int D = (middle.x - a.x) * (b.y - a.y) - (middle.y - a.y) * (b.x - a.x);

        if (D == 0) {
            if (b.y == a.y) { //горизонтальна
                if (a.x < middle.x) return 1;
                if (b.x > middle.x) return -1;
            }
        }
        return Long.signum(D);
    }


    public static int isRightFromChain(Point point, ArrayList<Point> chain, ArrayList<Point> figure) {
        int n = chain.size();
        int mid = n / 2;
        int r = n - 1;
        int l = 0;
        boolean wasChange = true;

        while (wasChange) {
            if (figure.get(chain.get(mid).x).y > point.y) {
                if (r == mid) {
                    return isRightFromEdge(figure.get(chain.get(mid).x), figure.get(chain.get(mid).y), point);
                }
                r = mid;
                mid = l + (r - l) / 2;
                wasChange = true;
            } else if (figure.get(chain.get(mid).y).y < point.y) {
                if (l == mid && r != mid) {
                    mid = r;
                    wasChange = true;
                } else {
                    l = mid;
                    mid = l + (r - l) / 2;
                    wasChange = true;
                }
            } else {
                return isRightFromEdge(figure.get(chain.get(mid).x), figure.get(chain.get(mid).y), point);
            }
        }

        return isRightFromEdge(figure.get(chain.get(mid).x), figure.get(chain.get(mid).y), point);
    }

    public static ArrayList<Point> readInputData(String filename) throws FileNotFoundException {
        ArrayList<Point> arr = new ArrayList<>();

        Scanner scFile = new Scanner(new File(filename));
        while (scFile.hasNextInt()) {
            Point tmp = new Point();
            tmp.x = scFile.nextInt();
            tmp.y = scFile.nextInt();
            arr.add(tmp);
        }
        return arr;
    }



    public static int searchSector(ArrayList<ArrayList<Point>> chains, Point point, ArrayList<Point> figure) {
        int n = chains.size();
        int mid = n / 2;
        int r = n - 1;
        int l = 0;

        //над
        if (figure.get(chains.get(0).get(chains.get(0).size() - 1).y).y < point.y) {
            System.out.println("Point is out");
            return 0;
        }
        //під
        if (figure.get(chains.get(0).get(0).x).y > point.y) {
            System.out.println("Point is out");
            return 0;
        }
        //верш
        if (point.equals(figure.get(chains.get(0).get(chains.get(0).size() - 1).y))) {
            System.out.println("Point is on line " + mid);
            return 0;
        }
        //поза
        if (point.y == figure.get(chains.get(0).get(chains.get(0).size() - 1).y).y) {
            System.out.println("Point is out");
            return 0;
        }


        //лівіше/правіше
        if (isRightFromChain(point, chains.get(l), figure) == -1
                || (isRightFromChain(point, chains.get(r), figure) == 1)) {
            System.out.println("Point is out");
            return 0;

        }


        int tempComp = isRightFromChain(point, chains.get(mid), figure);
        while (tempComp != 0 && r - l > 1) {
            if (tempComp == 1) l = mid;
            else r = mid;
            mid = l + (r - l) / 2;
            tempComp = isRightFromChain(point, chains.get(mid), figure);
        }

        if (tempComp == 0){
            System.out.println("Point is on line " + mid);
            return 0;
        }
        if (tempComp > 0) {
            System.out.println("Point is in sector " + mid);
            return 0;
        }
        else {
            System.out.println("Point is in sector " + (mid - 1));
            return 0;
        }
    }//******************

    public static int weightIn(int[] Weight, ArrayList<Point> E, int index) {
        int counter = 0;
        for (int i = 0; i < E.size(); i++) {
            if (E.get(i).y == index) counter += Weight[i];
        }
        return counter;
    }

    public static int edgesOut(ArrayList<Point> E, int index) {
        int counter = 0;
        for (Point point : E) {
            if (point.x == index) counter++;
        }
        return counter;
    }

    public static int weightOut(int[] Weight, ArrayList<Point> E, int index) {
        int counter = 0;
        for (int i = 0; i < E.size(); i++) {
            if (E.get(i).x == index) counter += Weight[i];
        }
        return counter;
    }

    public static int edgeOut1(int[] Weight, ArrayList<Point> E, int index) {
        for (int i = 0; i < E.size(); i++) {
            if (E.get(i).x == index && Weight[i] > 0) return i;
        }
        return -1;
    }

    public static int edgeIn1(int[] Weight, ArrayList<Point> E, int index) {
        for (int i = 0; i < E.size(); i++) {
            if (E.get(i).y == index && Weight[i] > 0) return i;
        }
        return -1;
    }


}

