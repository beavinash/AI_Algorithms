	
	public class DirectedEdge {
	    private int src, dest;
	    private double cost;
	
	    public DirectedEdge(int from, int to, double weight) {
	        this.src = from;
	        this.dest = to;
	        this.cost = weight;
	    }
	
	    
	    public int from() { return src; }
	
	   
	    public int to() { return dest; }
	
	   
	    public double weight() { return cost; }
	}
	
