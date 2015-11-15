package mamarantearaujo_hw3;

import java.util.Iterator;

// place the "10sonnets.txt" file within your top-level project so it can be found.

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * You are to modify this class to process all of the words in the given 10sonnets.txt file; you can
 * copy this file from the Git repository and place it directly in your project folder (make sure it
 * is a "sibling" file to the "src" folder in your project otherwise it won't be found.
 * 
 * You know you have this class correctly implemented when the output looks like this:
 * 
 * thou (37)
 * thy (35)
 * the (31)
 * to (27)
 * and (23)
 * in (20)
 * that (19)
 * ...
 * 
 */
public class ReportDuplicates {
	
	public static void main(String[] args) {
		// Make sure that the file "10sonnets.txt" is found as a file in your top-level project. Don't 
		// place it within your package folder where this code resides. See the level where I have placed 
		// it in the GitRepository and you should do the same.
		In in = new In ("10sonnets.txt");
		
		// In solving this problem, you should take advantage of the following symbol table whose
		// implementation is provided for you. There is also a Max Priority Queue into which you 
		// can insert WordPair objects; please review that class to see how these WordPair objects
		// are to be compared against each other.
		SequentialSearchST<String,Integer> words = new SequentialSearchST<String,Integer>();
		MaxPQ<WordPair> pq = new MaxPQ<WordPair>(100);
		
		// This demonstrates how you read words from a file
		while (!in.isEmpty()) {
			String word = in.readString();
			
			Integer freq = words.get(word);
			
			if(freq == null) //it does not contain 'word'
				words.put(word, 1);
			else
				words.put(word, freq+1);
		}
		
		Iterator<String> it = words.iterator();
		
		while(it.hasNext()) {
			String word = it.next();
			Integer value = words.get(word);
			words.delete(word);
			pq.insert(new WordPair(word, value));
		}
		
		int pq_size = pq.size();
		
		for(int i = 0; i < pq_size; i++) {
			WordPair pair = pq.delMax();
			if(pair.count.compareTo(1) <= 0)
				break;
			StdOut.printf("%s (%d)\n", pair.word, pair.count);
		}
	}
}
