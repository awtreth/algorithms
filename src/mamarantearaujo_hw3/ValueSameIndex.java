package mamarantearaujo_hw3;

import edu.princeton.cs.algs4.StdOut;

public class ValueSameIndex {
	/** 
	 * Given index values, lo and hi, which are inclusive to the array, a,
	 * return index such that a[idx] = idx.
	 * 
	 * If no such index exists, then return -1.
	 */
	public static int index (int[] a, int lo, int hi) {

		int count = 0;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			count++;
			if (mid < a[mid]) hi = mid - 1;
			else {
				count++;
				if (mid > a[mid]) lo = mid + 1;
				else {
					StdOut.printf("idx = %d\n", mid);
					StdOut.printf("nInspections = %d\n\n", count);
					return mid;
				}
			}
		}
		StdOut.println("idx not found");
		StdOut.printf("nInspections = %d\n\n", count);
		return -1;
	}

	public static void main(String[] args) {
		//2 example of worst case
		int[] ex1 = {-1, 0, 1, 2, 3, 4, 5, 7}; //it finds
		int[] ex2 = {-1, 0, 1, 2, 3, 4, 5, 6}; //it does not find
		
		//Example of when there is a match at the beginning of the array
		int[] ex3 = {0, 2, 3, 4, 5, 6, 7, 8};
		
		
		index(ex1, 0, ex1.length-1);
		index(ex2, 0, ex2.length-1);
		index(ex3, 0, ex2.length-1);
	}


}
