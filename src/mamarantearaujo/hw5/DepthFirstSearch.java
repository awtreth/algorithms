package mamarantearaujo.hw5;

public class DepthFirstSearch {
	
	boolean marked[];	// which vertices have been seen already
	int count;			// how many connected
	int[] edgeTo;
	Graph g;			// graph being searched
	public DepthFirstSearch (Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.g = g;
		dfs(s);
	}
	
	public int count() { return count; }    			 // number of vertices connected to s
	public boolean marked(int v) { return marked[v]; }
	
	/** Continue DFS search over graph by visiting vertex v. */  
	private void dfs (int v) {
		marked[v] = true;    // we have now seen this vertex 
		count++;
		
		// look into neighbors
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v; //Record that we go to w from v
				dfs (w);
			}
		}
	}
}
