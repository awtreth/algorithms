package mamarantearaujo.hw5;

import edu.princeton.cs.algs4.StdOut;

public class GraphTest {

	public static void main(String[] args) {
		
		//1-B)
		Graph graph = new Graph(5);
				
		System.out.println("Empty Graph\n" + graph.toString());
		System.out.println("Complement:\n" + graph.complement().toString());
	
		//1-D)
		Graph graph2 = new Graph(5);
		
		graph2.addEdge(0, 1);
		graph2.addEdge(1, 2);
		graph2.addEdge(2, 3);
		System.out.println(graph2.connected());
		graph2.addEdge(3, 4);
		System.out.println(graph2.connected());
		
		System.out.println(graph2.complement().connected());
		
		//2-A)
		Graph graph3 = new Graph(5);
		
		graph3.addEdge(0,1);
		graph3.addEdge(1,2);
		graph3.addEdge(1,3);
		graph3.addEdge(3,4);
		
		StdOut.println(graph3.status(0));
		StdOut.println(graph3.status(1));
		StdOut.println(graph3.status(2));
		StdOut.println(graph3.status(3));
		StdOut.println(graph3.status(4));
		
		//2-B)
		Graph graph4 = new Graph(7);
		graph4.addEdge(0,1);
		graph4.addEdge(1,2);
		graph4.addEdge(1,3);
		graph4.addEdge(3,4);
		graph4.addEdge(2, 3);
		graph4.addEdge(4, 5);
		graph4.addEdge(5, 6);
		
		StdOut.println(graph2.statusInjective());
		StdOut.println(graph4.statusInjective());
		
		//2-C) Bonus
		//Graph graph5 = new Graph(6);
		
		//3-C)
		StdOut.println(graph3.diameter());
		StdOut.println(graph4.diameter());
		
		//3-B)
		graph3.addEdge(0, 4);
		graph4.addEdge(0, 4);
		StdOut.println(graph2.findSafeVertex());
		StdOut.println(graph3.findSafeVertex());
		StdOut.println(graph4.findSafeVertex());
		
	}

}
