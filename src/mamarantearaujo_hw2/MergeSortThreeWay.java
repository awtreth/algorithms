package mamarantearaujo_hw2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Proper Merge Sort from Sedgewick, 4ed
public class MergeSortThreeWay extends AbsSortAlgorithm{
    
	Comparable aux[];
	
    public void sort(Comparable[] a) {
    	aux = new Comparable[a.length];
    	sort (a, 0, a.length-1);
    }
    
    // recursive helper function    
    private void sort (Comparable[] a, int lo, int hi) {
    	if (hi <= lo+1) return;
    	
    	int left = lo + (hi-lo)/3;
    	int right = hi - left;
    	
    	sort(a, lo, left);
    	sort(a, left+1, right);
    	sort(a, right+1, hi);
    	merge(a, lo, left, right, hi);
    }
    
    // merge sorted results a[lo..mid] with a[mid+1..hi] back into a
    private void merge (Comparable[] a, int lo, int left, int right, int hi) {
    	int i = lo;     // starting index into left sorted sub-array
    	int j = left+1;  // starting index into middle sorted sub-array
    	int k = right+1; // starting index into right sorted sub-array
    	
    	// copy a[lo..hi] into aux[lo..hi]
    	for (int m = lo; m <= hi; m++) {
    		aux[m] = a[m];
    	}
    	
    	boolean left_exausted = false;
    	boolean middle_exausted = false;
    	boolean right_exausted = false;
    	
    	// now comes the merge. Something you might simulate with flashcards
    	// drawn from two stack piles. This is the heart of mergesort. 
    	for (int m = lo; m <= hi; m++) {
    		if(i > left) left_exausted = true;
    		if(j > right) middle_exausted = true;
    		if(k > hi) right_exausted = true;
    		
    		if(left_exausted && middle_exausted) 			{a[m] = aux[k++];}
    		else if (left_exausted && right_exausted)	{a[m] = aux[j++];}
    		else if (middle_exausted && right_exausted) {a[m] = aux[i++];}
    		else if(left_exausted) {
    			if  (less(aux[k], aux[j]))  { a[m] = aux[k++]; } 
    			else                        { a[m] = aux[j++]; }
    		}else if(middle_exausted) {
    			if  (less(aux[k], aux[i]))  { a[m] = aux[k++]; } 
    			else                        { a[m] = aux[i++]; }
    		}else if(right_exausted) {
    			if  (less(aux[j], aux[i]))  { a[m] = aux[j++]; } 
    			else                        { a[m] = aux[i++]; }
    		}else {
    			if (less(aux[k], aux[j])) {
    				if(less(aux[k], aux[i])) 	{a[m] = aux[k++];}
    				else					 	{a[m] = aux[i++];}
    			}else if(less(aux[j], aux[i]))  {a[m] = aux[j++];}
    			else					 		{a[m] = aux[i++];}
    		}
    	}
    }
}