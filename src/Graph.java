
import java.io.*;
import java.util.*;

public class Graph {

	private class Edge {
		public int lbl;
		public int wght;
		public Edge nxt;
	}

	public static enum State {
		White, Grey, Black
	}

	private class Vertex {
		public int prnt;
		public State state;

		public Vertex() {
			state = State.White;
			prnt = -1;
		}
	}

	public Graph(boolean directed) {
		this.directed = directed;
	}

	private boolean directed;

	private Edge[] adjList;

	private Vertex[] vertexList;

	private int vertexCount;

	public void initializeGraph(String filename) throws IOException {

		BufferedReader r = new BufferedReader(new FileReader(filename));
		String line = null;
		line = r.readLine();
		vertexCount = Integer.parseInt(line);
		vertexList = new Vertex[vertexCount];
		for (int i = 0; i < vertexCount; ++i) {
			vertexList[i] = new Vertex();
		}
		adjList = new Edge[vertexCount];
		while ((line = r.readLine()) != null) {
			String[] parts = line.split("\\s");

			if (parts.length == 3) {
				int from = Integer.parseInt(parts[0]);
				int to = Integer.parseInt(parts[1]);
				int weight = Integer.parseInt(parts[2]);

				addEdge(from, to, weight);
			}
		}

	}

	public void addEdge(int source, int dest, int weight) {
		addEdge(source, dest, weight, directed);
	}

	// Add an edge to the graph.
	public void addEdge(int src, int dest, int weight, boolean flag) {

		// Add an edge from the source to the destination.
		Edge edge = new Edge();
		edge.lbl = dest;
		edge.wght = weight;
		edge.nxt = adjList[src];
		adjList[src] = edge;

		// If this is an undirected graph, add the reverse edge.
		if (!flag) {
			addEdge(dest, src, weight, true);
		}
	}

	public void breadthFirstSearch(int sNode) {

		for (Vertex v : vertexList) {
			v.prnt = -1;
			v.state = State.White;
		}

		Queue queue = new LinkedList<>();
		vertexList[sNode].state = State.Grey;
		queue.add(sNode);

		while (!queue.isEmpty()) {

			int index = (int) queue.poll();
			Edge edge = adjList[index];

			while (edge != null) {

				int dest = edge.lbl;
				if (vertexList[dest].state == State.White) {
					queue.add(dest);
					vertexList[dest].prnt = index;
					vertexList[dest].state = State.Grey;
				}

				edge = edge.nxt;
			}

			vertexList[index].state = State.Black;
		}
	}

	public void realizePath(int dest) {
		if (dest == -1)
			return;
		Vertex vertex = vertexList[dest];
		realizePath(vertex.prnt);
		System.out.print(dest + ">");
	}

	public void printPath(int source, int dest) {
		int i = dest;
		realizePath(i);
		System.out.println();

	}
}