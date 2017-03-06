/**
 * Main class for running the application
 */

import java.io.IOException;
import java.util.*;

public class Algorithm {
	
    private int size;
    private HashMap<Integer, Double> wght; 
    private HashMap<Integer, Integer> pNode; 
    private PriorityQueue<Integer> pQueue; 
    private DataStruture plot; 

    
    public Algorithm(DataStruture graph) {
        this.plot = graph;
        size = graph.size();
    }

    
    public ArrayList<Integer> shortestPathAStarHeuristics(int vertexA, int vertexB) {
        pNode = new HashMap<Integer, Integer>();
        wght = new HashMap<Integer, Double>();
        pQueue = new PriorityQueue<Integer>(size, PQComparator);

        for (int vertex : plot.vertices())
            wght.put(vertex, Double.POSITIVE_INFINITY);

        pNode.put(vertexA, -1);
        wght.put(vertexA, 0.0);
        pQueue.add(vertexA); 

        while (pQueue.size() > 0) {
            int currentNode = pQueue.poll();
            ArrayList<DirectedEdge> neighbors = plot.edg(currentNode);

            if (neighbors == null) continue;

            for (DirectedEdge neighbor : neighbors) {
                int nextVertex = neighbor.to();

                double newDistance = wght.get(currentNode) + neighbor.weight();
                if (wght.get(nextVertex) == Double.POSITIVE_INFINITY) {
                    pNode.put(nextVertex, currentNode);
                    wght.put(nextVertex, newDistance);
                    pQueue.add(nextVertex);
                } else {
                    if (wght.get(nextVertex) > newDistance) {
                        pNode.put(nextVertex, currentNode);
                        wght.put(nextVertex, newDistance);
                    }
                }
            }
        }

        ArrayList<Integer> nodePath = new ArrayList<Integer>();


        Stack<Integer> nodePathTemp = new Stack<Integer>();
        nodePathTemp.push(vertexB);

        int v = vertexB;
        while (pNode.containsKey(v) && pNode.get(v) >= 0 && v > 0) {
            v = pNode.get(v);
            nodePathTemp.push(v);
        }

        while (nodePathTemp.size() > 0)
            nodePath.add(nodePathTemp.pop());

        return nodePath;
    }
    
    public ArrayList<Integer> shortestPathGreedyHeuristics(int vertexA, int vertexB) {
        pNode = new HashMap<Integer, Integer>();
        wght = new HashMap<Integer, Double>();
        pQueue = new PriorityQueue<Integer>(size, PQComparator);

        for (int vertex : plot.vertices())
            wght.put(vertex, Double.POSITIVE_INFINITY);

        pNode.put(vertexA, -1); 
        wght.put(vertexA, 0.0);
        pQueue.add(vertexA); 

        while (pQueue.size() > 0) {
            int currentNode = pQueue.poll();
            ArrayList<DirectedEdge> neighbors = plot.edg(currentNode);

            if (neighbors == null) continue;

            for (DirectedEdge neighbor : neighbors) {
                int nextVertex = neighbor.to();

                double newDistance = wght.get(currentNode) + neighbor.weight();
                if (wght.get(nextVertex) == Double.POSITIVE_INFINITY) {
                    pNode.put(nextVertex, currentNode);
                    wght.put(nextVertex, newDistance);
                    pQueue.add(nextVertex);
                } else {
                    if (wght.get(nextVertex) > newDistance) {
                        pNode.put(nextVertex, currentNode);
                        wght.put(nextVertex, newDistance);
                    }
                }
            }
        }

        ArrayList<Integer> nodePath = new ArrayList<Integer>();

        Stack<Integer> nodePathTemp = new Stack<Integer>();
        nodePathTemp.push(vertexB);

        int v = vertexB;
        while (pNode.containsKey(v) && pNode.get(v) >= 0 && v > 0) {
            v = pNode.get(v);
            nodePathTemp.push(v);
        }

        while (nodePathTemp.size() > 0)
            nodePath.add(nodePathTemp.pop());

        return nodePath;
    }
    public Comparator<Integer> PQComparator = new Comparator<Integer>() {

        public int compare(Integer a, Integer b) {
            if (wght.get(a) > wght.get(b)) {
                return 1;
            } else if (wght.get(a) < wght.get(b)) {
                return -1;
            }
            return 0;
        }
    };

    public static void main(String args[]) {
        DataStruture graph;

        try {
        	Graph g = new Graph(true);
            g.initializeGraph(args[0]);
            g.breadthFirstSearch(0);
            g.printPath(0,5);
            graph = new DataStruture(args[0]);
            Algorithm finder = new Algorithm(graph);
            System.out.print(finder.shortestPathAStarHeuristics(0, graph.getNumNodes()-1).get(0));
            for(int i = 1; i < finder.shortestPathAStarHeuristics(0, graph.getNumNodes()-1).size(); i++)
            {
            	System.out.print(">" + finder.shortestPathAStarHeuristics(0, graph.getNumNodes()-1).get(i));
            }

            
        } catch (IOException e) {}
    }
}
