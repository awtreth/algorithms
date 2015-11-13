package mamarantearaujo_hw2;

import edu.princeton.cs.algs4.StdOut;

abstract class AbsSortAlgorithm implements ISortAlgorithm{
	 
	int n_exchanges;
	int n_comparisons;
	
	AbsSortAlgorithm()
	{
		this.n_exchanges = 0;
		this.n_comparisons = 0;
	}
	
//	public int nComparisons() { return this.n_comparisons; }
//	public int nExchanges() { return this.n_exchanges; }
	
	// is v < w ?
    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    protected void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    // print array to standard output
    protected void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
    
}
