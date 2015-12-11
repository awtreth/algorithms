package mamarantearaujo.hw5;

public class BreadthFirstSearch {
	boolean marked[];	// which vertices have been seen already
	//int count;			// how many connected
	int[] edgeTo;
	int[] distTo;
	int status = 0;
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
	public int status() {return status;}
	
	/** Continue BFS search over graph by visiting vertex v. */  
	private void bfs (int s) {

		while(!q.isEmpty()) {
			int v = q.dequeue();
			for(int w : g.adj(v)) {
				if(!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v]+1;
					status+=distTo[w];
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}
}
