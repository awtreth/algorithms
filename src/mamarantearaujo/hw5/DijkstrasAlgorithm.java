package mamarantearaujo.hw5;

import edu.princeton.cs.algs4.StdOut;

// modify this for your solution.

public class DijkstrasAlgorithm {
	// TODO: The result of your computation is stored in these arrays.
	static double dist[];
	static int pred[];     // this is equivalent to edgeTo as discussed for DFS/BFS
	static boolean visited[];     // this is equivalent to edgeTo as discussed for DFS/BFS

	// conduct from this designated source vertex.
	public static void singleSourceShortestPath(DiGraphMatrix graph, int s) {
		setup(graph.V, s);

		while(true) {
			int u = getU();

			if(dist[u] == Double.MAX_VALUE || visited[u]) return;

			visited[u] = true;

			Queue<DirectedEdge> adj = (Queue<DirectedEdge>) graph.adj(u);

			for(DirectedEdge edge : adj) {
				int w = (int)edge.weight();
				double newLen = dist[u] + w;

				if(newLen < dist[edge.to()]) {
					dist[edge.to()] = newLen;
					pred[edge.to()] = u;
				}
			}
		}
	}

	public static void printDist() {
		for(double value : dist) {
			StdOut.println(value);
		}
	}

	private static void initDist(int V, int s) {
		dist = new double[V];
		for(int i = 0; i < V; i++) {
			dist[i] = Double.MAX_VALUE;
		}
		dist[s] = 0;
	}

	private static void initPred(int V) {
		pred = new int[V];
		for(int i = 0; i < V; i++) {
			pred[i] = -1;
		}
	}

	private static void setup(int V, int s) {
		initDist(V,s);
		initPred(V);
		visited = new boolean[V];//start with false
	}

	private static int getU() {
		double minDist = Double.MAX_VALUE;
		int minIdx=0;

		for(int i = 0; i < dist.length; i++) {
			if(!visited[i]) {
				if(dist[i] < minDist) {
					minIdx = i;
					minDist = dist[i];
				}
			}
		}

		return minIdx;
	}

	public static void main(String[] args) {
		// TODO: Replace
		// Manually construct the DiGraphMatrix sample graph from Question 4 and 
		// demonstrate that it produces the results as shown.
		DiGraphMatrix graph = new DiGraphMatrix(5);
		graph.addEdge(0, 1, 2);
		graph.addEdge(0, 4, 4);
		graph.addEdge(1, 2, 3);
		graph.addEdge(2, 4, 1);
		graph.addEdge(4, 3, 7);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 0, 8);

		StdOut.println(graph.toString());

		singleSourceShortestPath(graph, 0);

		printDist();

	}
}
