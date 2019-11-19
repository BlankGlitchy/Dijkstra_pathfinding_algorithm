import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class GraphTests {

    public static void main(String[] args) {
        // Create graph
        GraphTests TestGraph = new GraphTests();
        Graph g = TestGraph.MakeSmallGraph();
        Graph G = TestGraph.makeBigGraph();

        Vertex source = g.getVertex("A");
        Vertex end = g.getVertex("F");
        Vertex start = G.getVertex("10");
        Vertex destination = G.getVertex("6");

        Pair<Integer, Map<Vertex, Vertex>> resultsDist = g.ShortestDistance(source, end);
        Pair<Integer, Map<Vertex, Vertex>> resultsTime = g.ShortestTime(source, end);
        Pair<Integer, Map<Vertex, Vertex>> resultBig = G.ShortestDistance(start, destination);


        Vertex currentDist = end;
        Vertex currentTime = end;
        Vertex currentBig = destination;

        ArrayList<Vertex> PathDist = new ArrayList<>();
        ArrayList<Vertex> pathTime = new ArrayList<>();
        ArrayList<Vertex> pathBig = new ArrayList<>();

        PathDist.add(end);
        pathTime.add(end);
        pathBig.add(destination);

        while ((currentDist != source) && (resultsDist.getValue().get(currentDist) != null)) {
            currentDist = resultsDist.getValue().get(currentDist);
            PathDist.add(0, currentDist);


        }
        System.out.println("This is the shortest distance:");
        for (Vertex v : PathDist) {
            System.out.print(v.Name);
            if (v != end) {
                System.out.print("->");
            }
        }

        System.out.println("");
        System.out.println("");
        while ((currentTime != source) && (resultsTime.getValue().get(currentTime) != null)) {
            currentTime = resultsTime.getValue().get(currentTime);
            pathTime.add(0, currentTime);
        }
        System.out.println("This is smallest time:");
        for (Vertex t : pathTime) {
            System.out.print(t.Name);
            if (t != end) {
                System.out.print("->");
            }
        }
        while ((currentBig != start) && (resultBig.getValue().get(currentBig) != null)) {
            currentBig = resultBig.getValue().get(currentBig);
            pathBig.add(0, currentBig);
        }
        System.out.println("");
        System.out.println("");

        System.out.println("This is shortest distance for the big graph:");
        for (Vertex pG : pathBig) {
            System.out.print(pG.Name);
            if (pG != destination) {
                System.out.print("->");
            }
        }
    }

    public Graph MakeSmallGraph() {
        Graph myGraph = new Graph();
        final Vertex A = myGraph.addVertex("A");
        final Vertex B = myGraph.addVertex("B");
        final Vertex C = myGraph.addVertex("C");
        final Vertex D = myGraph.addVertex("D");
        final Vertex E = myGraph.addVertex("E");
        final Vertex F = myGraph.addVertex("F");

        myGraph.newEdge(A, B, 1, 2);
        myGraph.newEdge(A, C, 5, 1);
        myGraph.newEdge(A, D, 4, 6);
        myGraph.newEdge(B, C, 3, 2);
        myGraph.newEdge(B, D, 2, 3);
        myGraph.newEdge(B, E, 2, 4);
        myGraph.newEdge(C, F, 1, 8);
        myGraph.newEdge(C, E, 2, 2);
        myGraph.newEdge(D, F, 2, 7);
        myGraph.newEdge(E, F, 3, 6);


        return myGraph;

    }

    public Graph makeBigGraph() {
        Graph bigGraph = new Graph();

        final Vertex v1 = bigGraph.addVertex("1");
        final Vertex v2 = bigGraph.addVertex("2");
        final Vertex v3 = bigGraph.addVertex("3");
        final Vertex v4 = bigGraph.addVertex("4");
        final Vertex v5 = bigGraph.addVertex("5");
        final Vertex v6 = bigGraph.addVertex("6");
        final Vertex v7 = bigGraph.addVertex("7");
        final Vertex v8 = bigGraph.addVertex("8");
        final Vertex v9 = bigGraph.addVertex("9");
        final Vertex v10 = bigGraph.addVertex("10");

        bigGraph.newEdgeBig(v1, v2, 10);
        bigGraph.newEdgeBig(v1, v4, 20);
        bigGraph.newEdgeBig(v1, v5, 20);
        bigGraph.newEdgeBig(v1, v6, 5);
        bigGraph.newEdgeBig(v1, v7, 15);
        bigGraph.newEdgeBig(v2, v3, 5);
        bigGraph.newEdgeBig(v2, v4, 10);
        bigGraph.newEdgeBig(v3, v4, 5);
        bigGraph.newEdgeBig(v3, v2, 15);
        bigGraph.newEdgeBig(v4, v5, 10);
        bigGraph.newEdgeBig(v5, v6, 5);
        bigGraph.newEdgeBig(v7, v6, 10);
        bigGraph.newEdgeBig(v8, v7, 5);
        bigGraph.newEdgeBig(v8, v2, 20);
        bigGraph.newEdgeBig(v8, v1, 5);
        bigGraph.newEdgeBig(v9, v8, 20);
        bigGraph.newEdgeBig(v9, v2, 15);
        bigGraph.newEdgeBig(v9, v10, 10);
        bigGraph.newEdgeBig(v10, v3, 15);
        bigGraph.newEdgeBig(v10, v2, 5);

        return bigGraph;
    }
}
