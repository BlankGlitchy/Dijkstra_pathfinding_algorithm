import java.util.*;

import javafx.util.Pair;

/***
 * @Author Morten Jensen
 * Portfolio 3, SD exam
 */

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

    // Creating a new edge with two parameters "dist" + "time"
    public void newEdge(Vertex from, Vertex to, int dist, int time) {
        Edge newedge = new Edge(from, to);
        newedge.distance = dist;
        newedge.time = time;
    }

    // Creating a new edge, but only with one parameter "dist"
    public void newEdgeBig(Vertex from, Vertex to, int dist) {
        Edge newEdgeBig = new Edge(from, to);
        newEdgeBig.distance = dist;
    }

    // Creating a pair witch takes a Integer and a map witch takes two vertices
    public Pair<Integer, Map<Vertex, Vertex>> ShortestDistance(Vertex source, Vertex end) {
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> distanceMap = new HashMap<>();
        Map<Vertex, Integer> t = new HashMap<>();


        // initialize arrays
        for (Vertex v : Vertices) {
            distanceMap.put(v, 1000);
            PredecessorMap.put(v, null);
            t.put(v, 1000);
        }
        // sets the source to zero - is the edges
        distanceMap.put(source, 0);
        // same in t. t functions as a handled funvtion of the verticies
        t.put(source, 0);


        // Dijkstra's algorithm for shortest distance
        for (int i = 0; i < Vertices.size(); i++) {
            // Use the function getMin on t map
            Vertex current = getMin(t);
            for (int j = 0; j < current.getOutEdges().size(); j++) {
                // takes the current and the current outdeges and multiply if that is smaller than the outer edge to next vertex run statement
                if (distanceMap.get(current) + current.getOutEdges().get(j).distance < distanceMap.get(current.getOutEdges().get(j).getTovertex())) {

                    // Updates the distance map - with current vertex and current out edge distance
                    distanceMap.put(current.getOutEdges().get(j).getTovertex(), distanceMap.get(current) + current.getOutEdges().get(j).distance);

                    // Update the t map - with current vertex and current out edge distance
                    t.put(current.getOutEdges().get(j).getTovertex(), distanceMap.get(current) + current.getOutEdges().get(j).distance);

                    // update the Predecessor map - with the last vertex we were at
                    PredecessorMap.put(current.getOutEdges().get(j).getTovertex(), current);
                }
            }
            // remove current from t
            t.remove(current);
        }
        // Return the pair
        return (new Pair<Integer, Map<Vertex, Vertex>>(distanceMap.get(end), PredecessorMap));
    }

    // Creating a pair witch takes a Integer and a map witch takes two vertices
    public Pair<Integer, Map<Vertex, Vertex>> ShortestTime(Vertex source, Vertex end) {
        // create objects of the maps
        Map<Vertex, Vertex> PredecessorMapTime = new HashMap<>();
        Map<Vertex, Integer> timeMap = new HashMap<>();
        Map<Vertex, Integer> t = new HashMap<>();


        // initialize arrays
        for (Vertex v : Vertices) {
            timeMap.put(v, 1000);
            PredecessorMapTime.put(v, null);
            t.put(v, 1000);
        }
        // sets the source to zero - is the edges
        timeMap.put(source, 0);

        // same in t. t functions as a handled funvtion of the verticies
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

        // foreach map entry in tMinMap entryset run the if statement
        for (Map.Entry<Vertex, Integer> entry : tMinMap.entrySet()
        ) {
            // is min == null or min value is bigger than entry value sets min equals to entry and return min key
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