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

	/** Implement as part of HW5, Question 1. 
	 * g' is the complement of g if:
	 * - they have the same number of vertices
	 * - an edge exists in g' if it doesn't exist in g
	 * - an edge doesn't exist in g' if it exists in g
	 * */
	public Graph complement() {
		//Completely connected graph of V vertices
		Graph fullGraph = Graph.completeGraph(this.V);

		for (int v1 = 0; v1 < V; v1++) {
			//for each vertice v2 conected to v1
			for (Integer v2 : adj[v1]) {
				//I've already looked for v2 that are smaller than v1 when I checked the v1's edges
				if(v2>v1)
					fullGraph.removeEdge(v1,v2);
			}
		}

		return fullGraph;//not full anymore
	}

	//Helper function (as private, because it's just used in complement() method
	private void removeEdge(int v, int w) {
		int initialSize = adj[v].size();
		this.adj[v].remove(w);//void method, does nothing when it was not already in the bag
		this.adj[w].remove(v);

		if(adj[v].size()!=initialSize) //to check if it was actually removed
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
		//compute a complete DepthFirstSearch from vertice 0
		DepthFirstSearch dfs = new DepthFirstSearch(this, 0);
		//It is connected if it could reach all the nodes

		//count() return the number of marked (visited) vertices 
		if(dfs.count()<V) return false; //Didn't mark all the vertices
		return true;//All the vertices were visited
	}	

	/** Implement as part of HW5, Question 2. 
	 * 
	 * Returns the sum of the shortest distances to all other nodes
	 * valid only for undirected connected graphs
	 * */
	public int status(int v) { 
		if(this.connected()) {
			//bfs record the shortest distance to each node
			BreadthFirstSearch bfs = new BreadthFirstSearch (this,v);
			return bfs.distToSum();
		}
		return -1;
	}

	/** Implement as part of HW5, Question 2. 
	 * 
	 * It's true when status of all vertices are different from each other
	 * 
	 * */
	public boolean statusInjective() {
		LinkedList<Integer> computedStatus = new LinkedList<Integer>();

		for(int i = 0; i < V; i++) {
			int status = this.status(i);
			if(computedStatus.contains(status)) //identify repeated status
				return false;
			computedStatus.add(status);
		}

		return true;
	}

	/** Implement as part of HW5, Question 3. 
	 * 
	 * Perform BreadthFirstSearch until it visits a vertex (the 1st time you see it) whose all connected
	 * vertices were already marked as visited. This vertex is safe, so its removal will not disconnect the graph.
	 * 
	 * */
	public int findSafeVertex() { 
		
		if(!this.connected()) return -1;
		
		boolean[] marked = new boolean[this.V()];//mark visited points
		Queue<Integer> q = new Queue<Integer>();
		marked[0] = true;
		q.enqueue(0);

		while(!q.isEmpty()) {
			int v = q.dequeue();
			int qsize_before = q.size();
			for(int w : this.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					q.enqueue(w);//we increase the size of the queue for each new unmarked neighboor
				}
			}
			if(q.size() == qsize_before)//all adjacent vertices were already marked
				return v;
		}
		
		return -1;//it can't reach this point
	}

	/** Implement as part of HW5, Question 3.
	 * 
	 *  maximum eccentric of any of its vertices
	 * */
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

	//length  of  the  shortest  path  from  that  vertex  to  the furthest  vertex x from v in  the  graph
	private int eccentricity(int v) {

		BreadthFirstSearch bfs = new BreadthFirstSearch (this,v);
		//return bfs.maxDist();
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
