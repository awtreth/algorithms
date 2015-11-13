package mamarantearaujo_hw2;

import edu.princeton.cs.algs4.*;

// Q3 on Homeework 2
public class HeapExercise {

	static Double[] generateData(int n) {
		Double[] vals = new Double[n];
		for (int i = 0; i < n; i++) {
			vals[i] = StdRandom.uniform();
		}
		return vals;
	}

	// You are responsible for updating these values.
	//
	// [n][0] = fewest number of comparisons for delMax on heap of size n
	// [n][1] = most number of comparisons for delMax on heap of size n
	// [n][2] = fewest number of comparisons for insert on heap of size n
	// [n][3] = most number of comparisons for insert on heap of size n
		
	static int[][] results = new int[12][4];

	public static void generateReport() {
		StdOut.println("Heap Trials");
		StdOut.println("N\tDelMax\tInsert");
		for (int n = 4, idx = 0; n <= 8192; n*= 2, idx++) {
			//StdOut.println(n + "\t" + results[idx][0] + "-" + results[idx][1] + "\t" +
				//	 results[idx][2] + "-" + results[idx][3]);
			StdOut.println(n + "\t" + results[idx][0] + "\t" + results[idx][1] + "\t" +
				 results[idx][2] + "\t" + results[idx][3]);
		}
	}

	// Update results, given information for the given trial, data size N, number of comparisons
	// during the delete operation and number of comparisons during the insert operation.
	private static void updateEntry(int trial, int n, int delComparisons, int insertComparisons) {
		if(trial == 0)
		{
			results[n][0] = delComparisons;//min
			results[n][1] = delComparisons;//max
			results[n][2] = insertComparisons;//min
			results[n][3] = insertComparisons;//max
		}else {
			if(delComparisons < results[n][0])//min
				results[n][0] = delComparisons;
			else if(delComparisons > results[n][1])//max
				results[n][1] = delComparisons;

			if(insertComparisons < results[n][2])//min
				results[n][2] = insertComparisons ;
			else if(insertComparisons > results[n][3])//max
				results[n][3] = insertComparisons ;
		}
	}

	public static void main(String[] args) {

		int T = 10;
		
		for (int t = 0; t < T; t++) {
			for (int n = 4, idx = 0; n <= 8192; n*= 2, idx++) {
				MaxPQ<Double> pq = new MaxPQ<Double>(n);
				pq.insert(generateData(n));
				
				int delComparisons = 0, insertComparisons = 0;
				
				for(int i = 0; i < 1000; i++) {
					pq.delMax();//nComparisons is reset to zero inside the function
					delComparisons += pq.nComparisons;
					pq.insert(StdRandom.uniform());//nComparisons is reset to zero inside the function
					insertComparisons += pq.nComparisons;
				}
				updateEntry(t, idx, delComparisons, insertComparisons);
			}
		}

		generateReport();
	}

}
