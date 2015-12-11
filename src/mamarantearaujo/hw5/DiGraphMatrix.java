package mamarantearaujo.hw5;

// Complete this implementation which represents a Directed Graph using
// an Adjacency Matrix.

public class DiGraphMatrix {
	final int V;
	int E;
	double[][] weights;
	
	public DiGraphMatrix (int V) {
		this.V = V;
		this.E = 0;
		weights = new double[V][V];
		initMatrix();//set the weights
	}
	
	//set the weights
	//0 for the diagonals, -1 otherwise
	private void initMatrix() {
		for	(int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++)
				weights[i][j] = -1;//invalid
			weights[i][i] = 0;
		}
	}
	
	//add an edge form source to target (directed)
	public void addEdge (int source, int target, double weight) {
		E++;
		weights[source][target] = weight;
	}
	
	
	/** Returns information about given directed edge, or null if doesn't exist. */
	public DirectedEdge getEdge (int source, int target) {
		double weight = weights[source][target];
		if(weight < 0)//invalid
			return null;
		else
			return new DirectedEdge(source, target, weight);
	}
	
	//returns a queue of the vertices that are adjacent to v
	public Iterable<DirectedEdge> adj(int v) {
		// Hint: You could create a Queue of DirectedEdges, populating it from the
		// specific row of the matrix 'weights' and then return that. 
		
		Queue<DirectedEdge> edges = new Queue<DirectedEdge>();
		
		for(int i = 0; i < V; i++) {
			DirectedEdge edge = this.getEdge(v, i);//v to i
			if(edge!=null && v != i)//doesn't count to itself
				edges.enqueue(edge);
		}
		
		return edges;
	}
	
	// Don't Bother to implement reverse() as shown on p. 569
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (DirectedEdge e : adj(v)) {
                s.append(e.toString());
            }
            s.append("\n");
        }
        return s.toString();
    }
	
}
