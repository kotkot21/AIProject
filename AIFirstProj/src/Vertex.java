import java.util.ArrayList;
import java.util.List;

public class Vertex {

    public String name;
    public List<Edge> neighbours;

    public Vertex(String name) {
        this.name = name;
        this.neighbours =  new ArrayList<>();
    }

    public void addEdge(Vertex end, double weight){
        this.neighbours.add(new Edge(this, end, weight));
    }
}
