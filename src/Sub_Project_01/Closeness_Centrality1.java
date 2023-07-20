package Sub_Project_01;

import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;

public class Closeness_Centrality1 {
    public static void main(String[] args) {
        In in1 = new In("src/Sub_Project_01/Datasets/Graph_01.txt");
        In in2 = new In("src/Sub_Project_01/Datasets/Graph_02.txt");
        In in3 = new In("src/Sub_Project_01/Datasets/Graph_03.txt");
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
            avg_closeness_centrality1 += 1.0f/avg1;
        }
        avg_closeness_centrality1 /= 1.0f*g1.V();



        for(int i = 0 ; i < g2.V(); i++) {
            float sum2=0.0f,avg2;
            DijkstraUndirectedSP d2 = new DijkstraUndirectedSP(g2,i);
            for (int t = 0; t < g2.V(); t++) {
                if (d2.hasPathTo(t)) {
                    sum2 += d2.distTo(t);
                } else sum2 += 0.0f;
            }
            avg2=sum2/(g2.V() * 1.0f);
            avg_closeness_centrality2 += 1.0f/avg2;
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
            avg3=sum3/(g3.V() * 1.0f);
            avg_closeness_centrality3 += 1.0f/avg3;
        }
        avg_closeness_centrality3 /= 1.0f * g3.V();
        float avg_avg = (avg_closeness_centrality1 + avg_closeness_centrality2 + avg_closeness_centrality3) / 3.0f;
        float minimum = Math.min(avg_closeness_centrality3,Math.min(avg_closeness_centrality1,avg_closeness_centrality2));
        float maximum = Math.max(avg_closeness_centrality3,Math.max(avg_closeness_centrality1,avg_closeness_centrality2));
        String max_transport = "", min_transport = "";
        if(maximum == avg_closeness_centrality1)
                max_transport = "Roadways";
        else if(maximum == avg_closeness_centrality2)
                max_transport = "Railways";
        else
                max_transport = "Subways";
        if(minimum == avg_closeness_centrality1)
            min_transport = "Roadways";
        else if(minimum == avg_closeness_centrality2)
            min_transport = "Railways";
        else
            min_transport = "Subways";
        System.out.println("Average of all : " + avg_avg);
        System.out.println("Min of all : " + minimum + "("+min_transport + ")");
        System.out.println("Max of all: " + maximum + "("+max_transport + ")");

        float median  = (avg_closeness_centrality1 + avg_closeness_centrality2 + avg_closeness_centrality3) - maximum - minimum;
        System.out.println("Median of all : " + median);
    }
}
