import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {

        double[][] data = {
                {0, 68, 55, 30, 82, 48, 33, 10, 76, 43},
                {68, 0, 90, 11, 76, 20, 55, 17, 62, 99},
                {55, 90, 0, 70, 63, 96, 51, 90, 88, 64},
                {30, 11, 70, 0, 91, 86, 78, 99, 53, 92},
                {82, 76, 63, 91, 0, 43, 88, 53, 42, 75},
                {48, 20, 96, 86, 43, 0, 63, 97, 37, 26},
                {33, 55, 51, 78, 88, 63, 0, 92, 87, 81},
                {10, 17, 90, 99, 53, 97, 92, 0, 81, 78},
                {76, 62, 88, 53, 42, 37, 87, 81, 0, 45},
                {43, 99, 64, 92, 75, 26, 81, 78, 45, 0}
        };

        int x = data.length;
        String[] names = {"Ahmed", "Salem", "Ayman", "Hani", "Kamal", "Samir", "Hakam", "Fuad", "Ibrahim", "Khalid"};

        Graph g = new Graph();

        for (String name : names) {
            g.addVertex(name);
        }
        DislikeMatrix dis = new DislikeMatrix(data);

        for (int i = 0; i < x; i++) {
            for (int j = i + 1; j < x; j++) {
                Vertex v1 = g.getVertex(names[i]);
                Vertex v2 = g.getVertex(names[j]);
                g.addEdge(v1, v2, dis.getDislike(names[i], names[j]));
            }
        }

        bestFirstSearch(g);
        UCS(g);
        Astar(g);
    }



    public static double hn(double x){
        return (x/100)*(x/100);
    }
    public static double f(double x){
        return ((x/100)*(x/100)) + (x/100);
    }

    public static void bestFirstSearch(Graph g) {
        PriorityQueue<PriVertex> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[g.Vertices.size()];

        double cost = 0;
        // Start from the first vertex
        pq.add(new PriVertex(g.Vertices.get(0), hn(0.0)));
        System.out.print("BFS Path: ");
        while (!pq.isEmpty()) {
            PriVertex priVertex = pq.poll();

            if (priVertex == null) {
                break; // Exit loop if priority queue is empty
            }

            Vertex v = priVertex.vertex;
            if (!visited[g.getIndex(v)]) {
                visited[g.getIndex(v)] = true;
                System.out.print(v.name + " ");

                // Add unvisited neighbors to the priority queue
                for (Edge e : v.neighbours) {
                    if (!visited[g.getIndex(e.dst)]) {
                        // Calculate the heuristic value h(n) = data * data
                        cost+= hn(e.weight);
                        pq.add(new PriVertex(e.dst, hn(e.weight)));
                    }
                }
            }
        }
        System.out.println();
        System.out.println("cost:"+String.format("%.2f",cost));
    }

    public static void UCS(Graph g) {
        PriorityQueue<PriVertex> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[g.Vertices.size()];
        Arrays.fill(visited, false);
        double cost = 0;
        System.out.print("UCS Path: ");

        // Start from the first vertex
        pq.add(new PriVertex(g.Vertices.get(0), 0.0));
        while (!pq.isEmpty()) {
            PriVertex priVertex = pq.poll();
            if (priVertex == null) {
                break; // Exit loop if priority queue is empty
            }

            Vertex v = priVertex.vertex;
            if (!visited[g.getIndex(v)]) {
                visited[g.getIndex(v)] = true;
                System.out.print(v.name +" ");

                // Add unvisited neighbors to the priority queue
                for (Edge e : v.neighbours) {
                    if (!visited[g.getIndex(e.dst)]) {
                        cost+=e.weight/100;
                        pq.add(new PriVertex(e.dst, cost));
                    }
                }
            }
        }
        System.out.println();
        System.out.println("cost:"+String.format("%.2f",cost));
    }

    public static void Astar(Graph g) {
        PriorityQueue<PriVertex> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[g.Vertices.size()];
        Arrays.fill(visited, false);
        double cost = 0;

        // Start from the first vertex
        pq.add(new PriVertex(g.Vertices.get(0), 0.0));
        System.out.print("A star path: ");
        while (!pq.isEmpty()) {
            PriVertex priVertex = pq.poll();
            if (priVertex == null) {
                break; // Exit loop if priority queue is empty
            }

            Vertex v = priVertex.vertex;
            if (!visited[g.getIndex(v)]) {
                visited[g.getIndex(v)] = true;
                System.out.print(v.name +" ");

                // Add unvisited neighbors to the priority queue
                for (Edge e : v.neighbours) {
                    if (!visited[g.getIndex(e.dst)]) {
                        cost+= f(e.weight);
                        pq.add(new PriVertex(e.dst, cost));
                    }
                }
            }
        }
        System.out.println();
        System.out.println("cost:"+String.format("%.2f",cost));
    }


}
