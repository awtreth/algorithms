package mamarantearaujo.hw5;

public class BreadthFirstSearch {
	boolean marked[];	// which vertices have been seen already
	//int count;			// how many connected
	int[] edgeTo;
	int[] distTo;
	int distToSum = 0;
	Queue<Integer> q = new Queue<Integer>();
	Graph g;			// graph being searched
	
	public BreadthFirstSearch (Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		distTo = new int[g.V()];
		
		this.g = g;
		
		for (int v = 0; v < g.V(); v++)
			distTo[v] = Integer.MAX_VALUE;
		distTo[s] = 0;
		marked[s] = true;
		q.enqueue(s);
		
		
		bfs(s);
	}
	
	//public int count() { return count; }    			 // number of vertices connected to s
	public boolean marked(int v) { return marked[v]; }
	public int distTo(int v) {return distTo[v];}
	public int distToSum() {return distToSum;}
	
	/** Continue BFS search over graph by visiting vertex v. */  
	private void bfs (int s) {
		int v = 0;
		while(!q.isEmpty()) {
			v = q.dequeue();
			for(int w : g.adj(v)) {
				if(!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v]+1;
					distToSum+=distTo[w];
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}
}
