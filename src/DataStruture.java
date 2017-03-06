
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class DataStruture {
    private HashMap<Integer, ArrayList<DirectedEdge>> adjList = new HashMap(); // adjacency-list
    private int numNodes;
    public DataStruture() {}


    public DataStruture(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String line = null;
        
        line = reader.readLine();
        
        
        
        numNodes = Integer.parseInt(line);
        
        while (( line = reader.readLine() ) != null) {
            
            String[] parts = line.split("\\s");

            if (parts.length == 3) {
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                double weight = Double.parseDouble(parts[2]);

                combineEdge(new DirectedEdge(from, to, weight));
            }
        }
    }

    public ArrayList<DirectedEdge> edg(int vertex) {
        return adjList.get(vertex);
    }

   
    public ArrayList<DirectedEdge> edges() {
        ArrayList l = new ArrayList<DirectedEdge>();

        for (int from : adjList.keySet()) {
            ArrayList<DirectedEdge> CEdges = adjList.get(from);
            for (DirectedEdge e : CEdges) {
                l.add(e);
            }
        }
        return l;
    }

   
    public Iterable<Integer> vertices() {
        HashSet hSet = new HashSet();
        for (DirectedEdge edge : edges()) {
            hSet.add(edge.from());
            hSet.add(edge.to());
        }

        return hSet;
    }

  
    public int size() { return adjList.size(); }

   
    public String toString() {
        String out = "";
        for (int from : adjList.keySet()) {
            ArrayList<DirectedEdge> list = adjList.get(from);
            out += from + " -> ";

            if (list.size() == 0)
                out += "-,";

            for (DirectedEdge edge : list)
                out += edge.to() + " @ " + edge.weight() + ", ";

            out += "\n";
        }

        return out;
    }

    
    public void combineEdge(DirectedEdge newEdge) {

        if (!adjList.containsKey(newEdge.from()))
            adjList.put(newEdge.from(), new ArrayList<DirectedEdge>());

        ArrayList<DirectedEdge> cEdges = adjList.get(newEdge.from());

        
        boolean isEdge = false;
        for (int i = 0; i < cEdges.size(); i++) {
            if (cEdges.get(i).to() == newEdge.to()) {
                cEdges.set(i, newEdge);
                isEdge = true;
                break;
            }
        }

        if (!isEdge)
            cEdges.add(newEdge);

        adjList.put(newEdge.from(), cEdges);
    }

	public int getNumNodes() {
		return numNodes;
	}

	public void setNumNodes(int nNodes) {
		this.numNodes = nNodes;
	}
}
