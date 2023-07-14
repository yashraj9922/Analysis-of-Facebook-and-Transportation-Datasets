package Sub_Project_01;

import java.io.*;
import edu.princeton.cs.algs4.*;

public class MST2 {
    private static final int vertexCount = 6;

    public void averageMST(EdgeWeightedGraph g1, EdgeWeightedGraph g2, EdgeWeightedGraph g3) throws IOException{
        PrimMST mst1 = new PrimMST(g1);
        PrimMST mst2 = new PrimMST(g2);
        PrimMST mst3 = new PrimMST(g3);
        FileWriter fw1 = new FileWriter("MST1.txt");
        BufferedWriter bf1 = new BufferedWriter(fw1);

        for(Edge e : mst1.edges())
        {
            String s = e.toString();
            bf1.write(s);
            bf1.newLine();
        }
        bf1.close();
        fw1.close();
        fw1 = new FileWriter("MST2.txt");
        bf1 = new BufferedWriter(fw1);
        for(Edge e : mst1.edges())
        {
            String s = e.toString();
            bf1.write(s);
            bf1.newLine();
        }
        bf1.close();
        fw1.close();
        fw1 = new FileWriter("MST3.txt");
        bf1 = new BufferedWriter(fw1);
        for(Edge e : mst1.edges())
        {
            String s = e.toString();
            bf1.write(s);
            bf1.newLine();
        }
        bf1.close();
        fw1.close();
        In in1 = new In("MST1.txt");
        In in2 = new In("MST2.txt");
        In in3 = new In("MST3.txt");
        EdgeWeightedGraph eg1 = new EdgeWeightedGraph(in1);
        EdgeWeightedGraph eg2 = new EdgeWeightedGraph(in2);
        EdgeWeightedGraph eg3 = new EdgeWeightedGraph(in3);
        mst1 = new PrimMST(eg1);
        mst2 = new PrimMST(eg2);
        mst3 = new PrimMST(eg3);
        EdgeWeightedGraph combinedGraph = mergeGraphs(eg1, eg2, eg3);
        
        PrimMST mst4 = new PrimMST(combinedGraph);

        System.out.println("Average MST:");
        printMST(mst4);

        double averageMSTWeight = mst4.weight();
        double minMSTWeight = Math.min(Math.min(mst1.weight(), mst2.weight()), mst3.weight());
        double maxMSTWeight = Math.max(Math.max(mst1.weight(), mst2.weight()), mst3.weight());
        double medianMSTWeight = mst3.weight();

        System.out.println("Average MST Weight: " + averageMSTWeight);
        System.out.println("Minimum MST Weight: " + minMSTWeight);
        System.out.println("Maximum MST Weight: " + maxMSTWeight);
        System.out.println("Median MST Weight: " + medianMSTWeight);
    }

    private EdgeWeightedGraph mergeGraphs(EdgeWeightedGraph g1, EdgeWeightedGraph g2, EdgeWeightedGraph g3) 
    {
        EdgeWeightedGraph combinedGraph = new EdgeWeightedGraph(vertexCount);
        for (Edge edge : g1.edges()) {
            combinedGraph.addEdge(edge);
        }
        for (Edge edge : g2.edges()) {
            combinedGraph.addEdge(edge);
        }
        for (Edge edge : g3.edges()) {
            combinedGraph.addEdge(edge);
        }
        return combinedGraph;
    }

    private void printMST(PrimMST mst) {
        for (Edge edge : mst.edges()) {
            int either = edge.either() + 1;
            int other = edge.other(either - 1) + 1;
            double weight = edge.weight();
            System.out.println(either + " - " + other + " (" + weight + ")");
        }
    }

    public static void main(String[] args) throws IOException {
        EdgeWeightedGraph[] graphs = new EdgeWeightedGraph[3];
        In in = new In("src\\Sub_Project_01\\Datasets\\Graph_01.txt");
        In in1 = new In("src\\Sub_Project_01\\Datasets\\Graph_02.txt");
        In in2 = new In("src\\Sub_Project_01\\Datasets\\Graph_03.txt");
        graphs[0] = new EdgeWeightedGraph(in);
        graphs[1] = new EdgeWeightedGraph(in1);
        graphs[2] = new EdgeWeightedGraph(in2);
        MST2 mst = new MST2();
        mst.averageMST(graphs[0], graphs[1], graphs[2]);
    }
}