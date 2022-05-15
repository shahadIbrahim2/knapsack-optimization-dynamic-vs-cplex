package com.company;
import java.util.Collections;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

    // Returns the maximum value that can
    // be put in a knapsack of capacity W
    static int knapSack(int W, int wt[],
                        int val[], int n)
    {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        //2 array list one for dynamic and one for ceplex
        ArrayList<Double> d = new ArrayList<>();
        ArrayList<Double> e = new ArrayList<>();

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;

                else if (wt[i - 1] <= w) {
                    K[i][w]
                            = max(val[i - 1]
                                    + K[i - 1][w - wt[i - 1]],
                            K[i - 1][w]);
                } else
                    K[i][w] = K[i - 1][w];

            }

        }
        while (n != 0) {
            // if it is taken
            if (K[n][W] != K[n - 1][W]) {
                d.add(1.0);
                e.add(1.0);
                W -= wt[n - 1];
            }
            // if it is not taken
            else {
                d.add(0.0);
                e.add(0.0);
            }
            n--;
        }
        //print reverse !!
        Collections.reverse(d);
        System.out.println(d);
        //compare between Ceplex and dynamic algorithm as an Evaluation criteria + print accuracy
        int size = d.size(), size2 = e.size();
        int prec = size / size2, m = 100;
        int r = prec * m;
        System.out.println("Precentage is " + r + "%");

        //print out the last item **max val
        int[] lastNum = K[K.length - 1];
        System.out.println("The maximum value is = "+ lastNum[lastNum.length-1]);


        return K[n][W];
    }
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(Paths.get("C:\\Users\\shdgh\\Desktop\\inst2.txt"));


        //read the file , weight and values
        int n = input.nextInt();
        int w = input.nextInt();
        int[] wt = new int[n];
        int[] val = new int[n];
        int count = 0;
        while (count < n) {
            val[count] = input.nextInt();
            wt[count] = input.nextInt();
            count++;
        }

        int result =   knapSack(w, wt, val, n);
    }
}
