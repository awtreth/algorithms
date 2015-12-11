package mamarantearaujo.hw5;

import edu.princeton.cs.algs4.StdOut;

// modify this for your solution.

public class DijkstrasAlgorithm {
	static double dist[];
	static int pred[];     // this is equivalent to edgeTo as discussed for DFS/BFS
	static boolean visited[];     // this is equivalent to edgeTo as discussed for DFS/BFS

	// conduct from this designated source vertex.
	public static void singleSourceShortestPath(DiGraphMatrix graph, int s) {
		setup(graph.V, s);//initialize dist, pred and visited arrays

		while(true) {
			int u = getU();

			//I needed to add the second condition to work
			//For me, the first one is just valid for not connected graphs
			//getU() returns a visited u (in this case: 0) when all were already visited
			//read visited[u] as visitedAllVertices (all values of visited[] are true at this point)
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

	//print the distances
	public static void printDist() {
		for(double value : dist) {
			StdOut.println(value);
		}
	}

	//initialize the dist array with MAX_VALUE for all the vertices
	//except for the start vertex s
	private static void initDist(int V, int s) {
		dist = new double[V];
		for(int i = 0; i < V; i++) {
			dist[i] = Double.MAX_VALUE;
		}
		dist[s] = 0;
	}

	//initialize the pred array with -1
	private static void initPred(int V) {
		pred = new int[V];
		for(int i = 0; i < V; i++) {
			pred[i] = -1;
		}
	}

	//initialize dist, pred and visited arrays
	private static void setup(int V, int s) {
		initDist(V,s);
		initPred(V);
		visited = new boolean[V];//start with false
	}

	//returns the vertex whose dist is smallest of unvisited vertices
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

		singleSourceShortestPath(graph, 3);

		printDist();

	}
}
