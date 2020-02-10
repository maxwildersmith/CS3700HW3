package Matrix;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting multithreaded matrix multiplication...\n\n");
        System.out.println("Enter the max number of threads to run with: ");
        Scanner in = new Scanner(System.in);
        int threads = in.nextInt();
        System.out.println("Enter m for A is of dimensions mxn: ");
        int m = in.nextInt();
        System.out.println("Enter n for A is of dimensions mxn: ");
        int n = in.nextInt();
        System.out.println("Enter p for B is of dimensions nxp: ");
        int p = in.nextInt();
    }

    /*
    a b c  *  j k l  =  aj+ak+al
    d e f     m n o
    g h i     p q r
     */
    private static double[][] matmult(float A, float B, float C, int m, int n, int p){

        return new double[0][0];
    }
}
