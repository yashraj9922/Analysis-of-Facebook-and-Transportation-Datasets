package Sub_Project_02;
import edu.princeton.cs.algs4.*;

public class Closeness_Centrality2 {
    public static void main(String[] args) {
        In in1 = new In("src/Sub_Project_02/01_Government.txt");
        In in2 = new In("src/Sub_project_02/02_Politicians.txt");
        In in3 = new In("src/Sub_project_02/03_Public_figure.txt");
        EdgeWeightedGraph g1 = new EdgeWeightedGraph(in1);
        EdgeWeightedGraph g2 = new EdgeWeightedGraph(in2);
        EdgeWeightedGraph g3 = new EdgeWeightedGraph(in3);

        float avg_closeness_centrality1 = calculateAverageClosenessCentrality(g1);
        float avg_closeness_centrality2 = calculateAverageClosenessCentrality(g2);
        float avg_closeness_centrality3 = calculateAverageClosenessCentrality(g3);

        System.out.println("Governments have a closeness_centrality factor of " + avg_closeness_centrality1);
        System.out.println("Politicians have  a closeness_centrality factor of " + avg_closeness_centrality2);
        System.out.println("Public_figures have a closeness_centrality factor of " + avg_closeness_centrality3);
    }

    private static float calculateAverageClosenessCentrality(EdgeWeightedGraph graph) {
        float sum = 0.0f;
        int totalNodes = graph.V();

        for (int i = 0; i < totalNodes; i++) {
            float shortestPathSum = 0.0f;
            DijkstraUndirectedSP dijkstra = new DijkstraUndirectedSP(graph, i);

            for (int j = 0; j < totalNodes; j++) {
                if (dijkstra.hasPathTo(j)) {
                    shortestPathSum += dijkstra.distTo(j);
                }
            }

            float closenessCentrality = (totalNodes - 1) / shortestPathSum;
            sum += closenessCentrality;
        }

        return sum / totalNodes;
    }
}
