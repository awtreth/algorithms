package hw2;

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
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Quick extends AbsSortAlgorithm{

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
    // and return the index j.
    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) { 

            // find item on lo to swap
            while (less(a[++i], v))
            {
            	this.n_comparisons++;
                if (i == hi) {
                	this.n_comparisons--;
                	break;
                }
            }
            this.n_comparisons++; //when the while loop is false
            
            // find item on hi to swap
            while (less(v, a[--j]))
            {
            	this.n_comparisons++;
                if (j == lo) {
                	this.n_comparisons--;
                	break;      // redundant since a[lo] acts as sentinel
                }
            }
            this.n_comparisons++; //when the while loop is false
            
            // check if pointers cross
            if (i >= j) break;
            
            exch(a, i, j);
            this.n_exchanges++;
        }
        	
        // put partitioning item v at a[j]
        exch(a, lo, j);
        this.n_exchanges++;
        
        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
}

