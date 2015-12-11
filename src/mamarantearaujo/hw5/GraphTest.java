package mamarantearaujo.hw5;

import edu.princeton.cs.algs4.StdOut;

public class GraphTest {

	public static void main(String[] args) {

		complementQ1bTest();
		connectedQ1dTest();
		bonusTest();
//		statusExample();
//		findSafeVertexExample();
//		diameterExample();
	
	}

	public static void printDashes() {
		StdOut.println("--------------------");
	}

	public static void printStars() {
		StdOut.println("********************");
	}

	//Question 1-B) Complement demonstration
	public static void complementQ1bTest() {
		Graph graph = new Graph(5);

		StdOut.println("QUESTION 1 - b)");
		printDashes();
		StdOut.println("Empty Graph:\n" + graph.toString());
		StdOut.println("Complement:\n" + graph.complement().toString());
		printStars();
	}


	//Question 1-B) Complement demonstration
	public static void connectedQ1dTest() {
		//1-D)
		Graph graph = new Graph(5);

		StdOut.println("QUESTION 1 - d)");
		printDashes();
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		StdOut.println("G\n" + graph.toString());
		StdOut.print("Is connected? "); StdOut.println(graph.connected());
		printDashes();
		StdOut.println("G'(complement of G):\n" + graph.complement().toString());
		StdOut.print("Is connected? "); StdOut.println(graph.connected());
		printStars();
	}

	//status() and statusInjective() methods test
	public static void statusExample() {
		StdOut.println("QUESTION 2 - a) and b)");
		printDashes();
		Graph graph3 = new Graph(5);

		graph3.addEdge(0,1);
		graph3.addEdge(1,2);
		graph3.addEdge(1,3);
		graph3.addEdge(3,4);

		StdOut.println(graph3.toString());

		for(int i = 0; i < graph3.V(); i++)
			StdOut.printf("Status of vertex %d = %d\n", i, graph3.status(i));

		StdOut.printf("StatusInjective: %b\n", graph3.statusInjective());

		printDashes();
		Graph graph4 = new Graph(7);

		graph4.addEdge(0,1);
		graph4.addEdge(1,2);
		graph4.addEdge(1,3);
		graph4.addEdge(3,4);
		graph4.addEdge(2, 3);
		graph4.addEdge(4, 5);
		graph4.addEdge(5, 6);

		StdOut.println(graph4.toString());

		for(int i = 0; i < graph4.V(); i++)
			StdOut.printf("Status of vertex %d = %d\n", i, graph4.status(i));

		StdOut.printf("StatusInjective: %b\n", graph4.statusInjective());

		printStars();
	}

	public static void findSafeVertexExample() {
		//3-B)
		StdOut.println("QUESTION 3 - b)");
		printDashes();

		Graph graph = new Graph(5);

		//unconnected graph
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);

		StdOut.println("Unconnected Graph:");
		StdOut.println(graph.toString());

		StdOut.printf("Safe Vertex (unconnected): %d\n", graph.findSafeVertex());

		printDashes();
		//connected graph
		graph.addEdge(4, 2);
		graph.addEdge(1, 2);

		StdOut.println("Connected Graph:");
		StdOut.println(graph.toString());
		StdOut.printf("Safe Vertex (connected): %d\n", graph.findSafeVertex());

		printStars();
	}

	public static void diameterExample() {
		//3-B)
		StdOut.println("QUESTION 3 - c) diameter");
		printDashes();

		Graph graph = new Graph(5);
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(4, 2);
		graph.addEdge(1, 2);
		graph.addEdge(0, 3);
		
		StdOut.println(graph.toString());
		StdOut.println(graph.diameter());
		
		printStars();
	}
	
	//Look for one statusInjective possible graph with V vertices
	//returns null if it doesn't find
	//usually not feasable for V >= 22 (tested in i7)
	public static Graph findOneInjective(int V) {
		int[][] edgesSet = allEdges(V); //All possible edges in a graph with V vertices
		int maxEdges = V*(V-1)/2;
		int allCombinations = (int) Math.pow(2, maxEdges);//2^15 for V==6
		
		for (int i = 0; i < allCombinations; i++) {//test all combinations
			Graph graph = new Graph(V);//new graph to be checked
			
			//create the graph based on the bits of i
			for(int pos = 0; pos < maxEdges; pos++) {
				if(bit(i,pos)==1)//check the bit 'pos' of 'i'
					graph.addEdge(edgesSet[pos][0], edgesSet[pos][1]);//add the edge 'pos'
			}
			if(graph.statusInjective())//check
				return graph;
		}
		
		return null;//no graph was found
	}

	//get the bit value in position 'pos' of the number 'num'
	public static int bit(int num, int pos) {
		return (num >> pos) & 1;
	}
	
	//Return all possible edges in a graph with V vertices
	//array of {v,w} edges
	public static int[][] allEdges(int V) {
		int[][] edges = new int[V*(V-1)/2][2];
		
		int count = 0;
		
		for (int i = 0; i < V; i++) {//N
			for (int j = i+1; j < V; j++) {//N(N-1)/2
				edges[count][0] = i;
				edges[count++][1] = j;
			}
		}
		
		return edges;
	}
	
	//Question 2-c) test
	public static void bonusTest() {
		
		StdOut.println("Bonus Question Test");
		StdOut.println("Try to find a possible graph with 3->8 vertices that is statusInjective");
		printDashes();
		for(int i = 3; i <= 8; i++) {
			Graph graph = findOneInjective(i);
			if(graph!=null)
				StdOut.println(graph.toString());
			else
				StdOut.printf("not possible to test with %d\n", i);
		}
	}
	
}


