package mamarantearaujo_hw2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class Insertion extends AbsSortAlgorithm {

	Insertion() {
		super();
	}
	
	
	/**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
	public void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                this.n_comparisons++;
            	this.n_exchanges++;
            	exch(a, j, j-1);
            }
			this.n_comparisons++;//when the test statement of the for loop is false
        }
    }
}