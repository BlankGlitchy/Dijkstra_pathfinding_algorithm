import java.util.*;

import javafx.util.Pair;

public class Graph {
    private ArrayList<Vertex> Vertices = new ArrayList<>();

    public Vertex addVertex(String id) {
        Vertex newvertex = new Vertex(id);
        Vertices.add(newvertex);
        return newvertex;
    }

    public void addVertex(Vertex v) {
        Vertices.add(v);
    }

    public Vertex getVertex(String s) {
        for (Vertex v : Vertices) {
            if (v.Name == s)
                return v;
        }
        return null;
    }

    public void newEdge(Vertex from, Vertex to, int dist, int tim) {
        Edge newedge = new Edge(from, to);
        newedge.distance = dist;
        newedge.time = tim;
    }
    public void newEdgeBig(Vertex from, Vertex to, int dist) {
        Edge newEdgeBig = new Edge(from, to);
        newEdgeBig.distance = dist;
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestDistance(Vertex source, Vertex end) {
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> DistanceMap = new HashMap<>();
        Map<Vertex, Integer> t = new HashMap<>();


        // initialize arrays
        for (Vertex v : Vertices) {
            DistanceMap.put(v, 1000);
            PredecessorMap.put(v, null);
            t.put(v, 1000);
        }
        // Edges
        DistanceMap.put(source, 0);
        // Vertices - Functions as handled
        t.put(source, 0);


        // Dijkstra's algorithm for shortest distance
        for (int i = 0; i < Vertices.size(); i++) {
            Vertex current = getMin(t);
            for (int j = 0; j < current.getOutEdges().size(); j++) {
                // is the distance smaller than the next distance update the maps
                if (DistanceMap.get(current) + current.getOutEdges().get(j).distance < DistanceMap.get(current.getOutEdges().get(j).getTovertex())) {

                    // Updates the distance map
                    DistanceMap.put(current.getOutEdges().get(j).getTovertex(), DistanceMap.get(current) + current.getOutEdges().get(j).distance);
                    // Update the t map
                    t.put(current.getOutEdges().get(j).getTovertex(), DistanceMap.get(current) + current.getOutEdges().get(j).distance);
                    // update the Predecessor map
                    PredecessorMap.put(current.getOutEdges().get(j).getTovertex(), current);
                }
            }
            t.remove(current);
        }
        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(end), PredecessorMap));
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestTime(Vertex source, Vertex end) {
        Map<Vertex, Vertex> PredecessorMapTime = new HashMap<>();
        Map<Vertex, Integer> timeMap = new HashMap<>();
        Map<Vertex, Integer> t = new HashMap<>();


        // initialize arrays
        for (Vertex v : Vertices) {
            timeMap.put(v, 1000);
            PredecessorMapTime.put(v, null);
            t.put(v, 1000);
        }
        // Edges
        timeMap.put(source, 0);
        // Vertices - Functions as handled
        t.put(source, 0);

        // Dijkstra's algorithm for shortest distance
        for (int i = 0; i < timeMap.size(); i++) {
            Vertex current = getMin(t);
            for (int j = 0; j < current.getOutEdges().size(); j++) {
                // is the distance smaller than the next distance update the maps
                if (timeMap.get(current) + current.getOutEdges().get(j).time < timeMap.get(current.getOutEdges().get(j).getTovertex())) {

                    // Updates the distance map
                    timeMap.put(current.getOutEdges().get(j).getTovertex(), timeMap.get(current) + current.getOutEdges().get(j).time);
                    // Update the t map
                    t.put(current.getOutEdges().get(j).getTovertex(), timeMap.get(current) + current.getOutEdges().get(j).time);
                    // update the Predecessor map
                    PredecessorMapTime.put(current.getOutEdges().get(j).getTovertex(), current);
                }
            }
            t.remove(current);
        }
        return (new Pair<Integer, Map<Vertex, Vertex>>(timeMap.get(end), PredecessorMapTime));
    }


    public Vertex getMin(Map<Vertex, Integer> tMinMap) {
        //Create a map entry
        Map.Entry<Vertex, Integer> min = null;


        for (Map.Entry<Vertex, Integer> entry : tMinMap.entrySet()
        ) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min.getKey();
    }
}


class Vertex {
    public String Name;
    public ArrayList<Edge> OutEdges = new ArrayList<>();

    public Vertex(String id) {
        Name = id;
    }

    public void addOutEdge(Edge outedge) {
        OutEdges.add(outedge);
    }

    public ArrayList<Edge> getOutEdges() {
        return OutEdges;
    }
}

class Edge {
    private Vertex fromvertex;
    private Vertex tovertex;
    public int distance = 0;
    public int time = 0;

    public Vertex getTovertex() {
        return tovertex;
    }

    public Edge(Vertex from, Vertex to) {
        fromvertex = from;
        tovertex = to;
        fromvertex.addOutEdge(this);
        //If not directional
        tovertex.addOutEdge(this);
    }
}