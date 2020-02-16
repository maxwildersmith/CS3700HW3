package Matrix;

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 *      QUESTIONS
 *      Different method setup/call?
 *      ms instead of s
 */
public class MatMult {
    private float[][] a,b,c;
    private int m, n, p;

    public MatMult(float[][] a, float[][] b, float[][] c, int m, int n, int p) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.m = m;
        this.n = n;
        this.p = p;
    }

    public void matmult(int numOfThreads){
        c = new float[c.length][c[0].length];
        CountDownLatch latch = new CountDownLatch(m*p);
        ExecutorService pool = Executors.newFixedThreadPool(numOfThreads);
        long startTime = System.currentTimeMillis();
        for(int x=0;x<m;x++) {
            for (int y = 0; y < p; y++) {
                int row = x, col = y;
                pool.submit(() -> {
                    for (int i = 0; i < a[0].length; i++) {
                        c[row][col] += a[row][i] * b[i][col];
                    }
                    latch.countDown();
                });
            }
        }

        pool.shutdown();
        try {
            latch.await();
            //System.out.printf("%d, %d\n",numOfThreads,(System.currentTimeMillis()-startTime));
            System.out.printf("Time with %d thread: %d ms\n",numOfThreads,(System.currentTimeMillis()-startTime));
        } catch (InterruptedException e) {
            System.out.println("Timed out");
            e.printStackTrace();
        }
    }

    public void printAB(){
        System.out.println("A: ");
        printMat(a);
        System.out.println("B: ");
        printMat(b);
    }
    public void printC(){
        System.out.println("C: ");
        printMat(c);
    }

    public static void main(String[] args) {
        System.out.println("Starting multithreaded matrix multiplication...\n\n");
        System.out.println("Enter the max number of threads to run with: ");
        Scanner in = new Scanner(System.in);
        int threads = in.nextInt();
        if(threads > Runtime.getRuntime().availableProcessors())
            System.out.println("\tNote: There are only "+Runtime.getRuntime().availableProcessors()+" physical processors in this machine");

        System.out.println("Enter m for A is of dimensions mxn: ");
        int m = in.nextInt();
        System.out.println("Enter n for A is of dimensions mxn: ");
        int n = in.nextInt();
        System.out.println("Enter p for B is of dimensions nxp: ");
        int p = in.nextInt();
        float[][] A = new float[m][n];
        float[][] B = new float[n][p];
        float[][] C = new float[m][p];

        System.out.println("Print Matrices? (y/n): ");
        boolean print = in.next().trim().toLowerCase().charAt(0)=='y';
        System.out.println("Enter your own data? (y/n): ");
        if(in.next().trim().toLowerCase().charAt(0)=='y') {
            in = new Scanner(System.in);
            System.out.println("Enter the first row of values for Matrix A, with each value separated by a space, ex. 1 2 3: ");
            A[0] = parseRow(in.nextLine());
            for (int i = 1; i < m; i++) {
                System.out.println("Enter the next row of values for Matrix A, with each value separated by a space: ");
                A[i] = parseRow(in.nextLine());
            }

            System.out.println("Enter the first row of values for Matrix B, with each value separated by a space, ex. 1 2 3: ");
            B[0] = parseRow(in.nextLine());
            for (int i = 1; i < n; i++) {
                System.out.println("Enter the next row of values for Matrix B, with each value separated by a space: ");
                B[i] = parseRow(in.nextLine());
            }
        } else {
            for(int i=0;i<A.length;i++)
                for(int j=0;j<A[0].length;j++)
                    A[i][j] = (int) (Math.random()*100);
            for(int i=0;i<B.length;i++)
                for(int j=0;j<B[0].length;j++)
                    B[i][j] = (int) (Math.random()*100);
        }
        MatMult matMult = new MatMult(A,B,C,m,n,p);
        if(print)
            matMult.printAB();
        for(int i=1;i<=threads;i++)
            matMult.matmult(i);
        if(print)
            matMult.printC();
    }

    public static void printMat(float[][] mat){
        for (float[] row: mat) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
    public static float[] parseRow(String row){
        String[] strings = row.trim().split(" ");
        float[] vals = new float[strings.length];
        for(int i=0;i<strings.length;i++)
            vals[i] = Float.parseFloat(strings[i]);
        return vals;
    }
}
