package mamarantearaujo.hw5;


import java.util.LinkedList;

import edu.princeton.cs.algs4.In;

// skeletal structure for your HW5

public class Graph {

	final int V;
	int E;
	Bag<Integer>[] adj;

	/**
	 * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
	 * param V the number of vertices
	 *
	 * @param  V number of vertices
	 * @throws IllegalArgumentException if <tt>V</tt> < 0
	 */
	public Graph(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	/** Added this method for day20 to load graph from file. */
	public Graph (In in) {
		this (in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge (v,w);
		}
	}

	public int V() { return V; }
	public int E() { return E; }


	/** Adds the undirected edge v-w to this graph. */
	public void addEdge(int v, int w) {
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}


	/** Returns the vertices adjacent to vertex <tt>v</tt>. */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/** Returns the degree of vertex <tt>v</tt>. */
	public int degree(int v) {
		return adj[v].size();
	}

	/** Implement as part of HW5, Question 1. */
	public Graph complement() { 
		Graph fullGraph = Graph.completeGraph(this.V);

		for (int number1 = 0; number1 < V; number1++) {
			for (Integer number2 : adj[number1]) {
				if(number2>number1)
					fullGraph.removeEdge(number1,number2);
			}
		}

		return fullGraph;
	}

	private void removeEdge(int v, int w) {
		int initialSize = adj[v].size();
		this.adj[v].remove(w);
		this.adj[w].remove(v);
		if(adj[v].size()!=initialSize)
			this.E--;
		//TODO: exception
	}


	//Helper function that returns the all connected graph with V vertices
	static public Graph completeGraph(int nVertices) {
		Graph graph = new Graph(nVertices);

		for (int i = 0; i < nVertices; i++) {
			for (int j = i+1; j < nVertices; j++) {
				graph.addEdge(i,j);
			}
		}
		return graph;
	}


	/** Implement as part of HW5, Question 1. */
	//it assumes that it is always a simple valid graph
	public boolean connected() { 
		
		DepthFirstSearch dfs = new DepthFirstSearch(this, 0);
		
		if(dfs.count()<V) return false;
		return true;
	}	

	/** Implement as part of HW5, Question 2. */
	public int status(int v) { 
		BreadthFirstSearch bfs = new BreadthFirstSearch (this,v);
		return bfs.status();
	}
	
	/** Implement as part of HW5, Question 2. */
	public boolean statusInjective() {
		LinkedList<Integer> computedStatus = new LinkedList<Integer>();
		
		for(int i = 0; i < V; i++) {
			int status = this.status(i);
			if(computedStatus.contains(status))
				return false;
			computedStatus.add(status);
		}
		
		return true;
	}

	/** Implement as part of HW5, Question 3. */
	public int findSafeVertex() { 
		int minEdges = Integer.MAX_VALUE;
		int minV = 0;
		int count = 0;
		
		for	(Bag bag : adj) {
			int nEdges = bag.size();
			if(nEdges < minEdges) {
				minEdges = nEdges;
				minV = count;
			}
			count++;
		}
		
		return minV;
	}

	/** Implement as part of HW5, Question 3. */
	public int diameter() {
		if(!this.connected())
			return -1;
		//else

		int maxEccentricity = 0;
		
		for(int i = 0; i < V; i++) {
			int ec = eccentricity(i);
			if(ec > maxEccentricity)
				maxEccentricity= ec;
		}
		return maxEccentricity;
	}
	
	private int eccentricity(int v) {
		
		BreadthFirstSearch bfs = new BreadthFirstSearch (this,v);
		int maxDist = 0;
		
		for(int i = 0; i < V; i++) {
			if(bfs.distTo(i) > maxDist)
				maxDist = bfs.distTo(i);
		}
		
		return maxDist;
	}

	/**
	 * Returns a string representation of this graph.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
	 *         followed by the <em>V</em> adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + "\n");
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

}
