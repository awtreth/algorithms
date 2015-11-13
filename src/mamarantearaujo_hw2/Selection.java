package mamarantearaujo_hw2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Sedgewick, 4ed
public class Selection extends AbsSortAlgorithm{

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int min = i;

            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) {
                	min = j;
                }
            }
            exch(a, i, min);
        }
    }

}