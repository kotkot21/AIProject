import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    List <Vertex> Vertices = new ArrayList<Vertex>();

    public Graph() {
        this.Vertices = new LinkedList<Vertex>();
    }
    public Vertex addVertex(String v) {
        Vertex newvertex = new Vertex(v);
        this.Vertices.add(newvertex);
        return newvertex;
    }

    public void addEdge(Vertex v1, Vertex v2, double weight) {
        v1.addEdge(v2,weight);
        v2.addEdge(v1,weight);
    }

    public Vertex getVertex(String s) {
        for (Vertex v : Vertices){
            if (v.name == s){
                return v;
            }
        }
        return null;
    }
    public int getIndex(Vertex v) {
        return Vertices.indexOf(v);
    }


}
