package mamarantearaujo_hw2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 *  The <tt>Quick</tt> class provides static methods for sorting an
 *  array and selecting the ith smallest element in an array using quicksort.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  Provides an alternate partition method to use. 
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class QuickAlternate extends AbsSortAlgorithm{

	// This class should not be instantiated.
	//private QuickAlternate() { } //Why?

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 * @param a the array to be sorted
	 */
	public void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	// quicksort the subarray from a[lo] to a[hi]
	private void sort(Comparable[] a, int lo, int hi) { 
		if (hi <= lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}

	// partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
	// and return the index j. Alternate partitioning method.
	private int partition(Comparable[] a, int lo, int hi) {
		// choose right-most element as the 'pivot'

		// all values <= pivot are moved to the front of array and pivot inserted
		// just after them.
		int store = lo;
		for (int i = lo; i <= hi; i++) {
			if (less(a[i], a[hi])) {
				exch(a, store, i);
				store++;
			}
		}
		
		exch(a, store, hi);
		
		return store;
	}
}

