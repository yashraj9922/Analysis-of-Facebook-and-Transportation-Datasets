package Sub_Project_02;

import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;

public class PlotTables_2 {
    public static void main(String[] args) {
        In in1 = new In("01_Government.txt");
        In in2 = new In("02_Politicians.txt");
        In in3 = new In("03_Public_figure.txt");
        EdgeWeightedGraph g1 = new EdgeWeightedGraph(in1);
        EdgeWeightedGraph g2 = new EdgeWeightedGraph(in2);
        EdgeWeightedGraph g3 = new EdgeWeightedGraph(in3);
        float avg_closeness_centrality1 = 0.0f;
        float avg_closeness_centrality2 = 0.0f;
        float avg_closeness_centrality3 = 0.0f;
        for(int i = 0 ; i < g1.V(); i++) {
            float sum1=0.0f,avg1;
            DijkstraUndirectedSP d1 = new DijkstraUndirectedSP(g1,i);
            for (int t = 0; t < g1.V(); t++) {
                if (d1.hasPathTo(t)) {
                    sum1 += d1.distTo(t);
                } else sum1 += 0.0f;
            }
            avg1=sum1/(g1.V() * 1.0f);
            avg_closeness_centrality1 += avg1;
        }
        avg_closeness_centrality1 /= 1.0f * g1.V();
        for(int i = 0 ; i < g2.V(); i++) {
            float sum2=0.0f,avg2;
            DijkstraUndirectedSP d2 = new DijkstraUndirectedSP(g2,i);
            for (int t = 0; t < g2.V(); t++) {
                if (d2.hasPathTo(t)) {
                    sum2 += d2.distTo(t);
                } else sum2 += 0.0f;
            }
            avg2= sum2 / (g2.V() * 1.0f);
            avg_closeness_centrality2 += avg2;
        }
        avg_closeness_centrality2 /= 1.0f * g2.V();
        for(int i = 0 ; i < g3.V(); i++) {
            float sum3=0.0f,avg3;
            DijkstraUndirectedSP d3 = new DijkstraUndirectedSP(g3,i);
            for (int t = 0; t < g3.V(); t++) {
                if (d3.hasPathTo(t)) {
                    sum3 += d3.distTo(t);
                } else sum3 += 0.0f;
            }
            avg3= sum3 / (g3.V() * 1.0f);
            avg_closeness_centrality3 += avg3;
        }
        avg_closeness_centrality3 /= (1.0f*g3.V());

            System.out.println("Governments are closer to each other by a closeness_centrality factor of " + avg_closeness_centrality1);
            System.out.println("Politicians are more centralized and most easily connect all places by a closeness_centrality factor of " + avg_closeness_centrality2);
            System.out.println("Public_figures are more centralized and most easily connect all places by a closeness_centrality factor of " + avg_closeness_centrality3);
    }
}
