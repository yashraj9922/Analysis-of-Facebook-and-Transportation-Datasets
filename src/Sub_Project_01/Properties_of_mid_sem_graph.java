package src.Sub_Project_01;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Properties_of_mid_sem_graph {
    static EdgeWeightedGraph G1, G2, G3;
    static  EdgeWeightedDigraph DG1, DG2, DG3;

    public static void ReadEdgeWeightGraph(){
        In in1 = new In("src/Sub_Project_01/Graph_01.txt");
        In in2 = new In("src/Sub_Project_01/Graph_02.txt");
        In in3 = new In("src/Sub_Project_01/Graph_03.txt");
        G1 = new EdgeWeightedGraph(in1);
        G2 = new EdgeWeightedGraph(in2);
        G3 = new EdgeWeightedGraph(in3);
    }
    public static void ReadEdgeWeightDigraph() {
        In in1 = new In("src/Sub_Project_01/Graph_01.txt");
        In in2 = new In("src/Sub_Project_01/Graph_02.txt");
        In in3 = new In("src/Sub_Project_01/Graph_03.txt");
        DG1 = new EdgeWeightedDigraph(in1);
        DG2 = new EdgeWeightedDigraph(in2);
        DG3 = new EdgeWeightedDigraph(in3);
    }
    private static  void exitProgram() {
        System.out.println("Exiting program.....");
        System.exit(0);
    }
    public static void main(String[] args) {
        ReadEdgeWeightGraph();
        ReadEdgeWeightDigraph();

        Scanner scanner = new Scanner(System.in);
        int operationChoice = 1;

        while(true) {
            System.out.println("Enter the operation you want to perform (MST or Shortest Distance Path):");
            System.out.println("1.MST");
            System.out.println("2.Print all Shortest Path");
            System.out.println("3.Shortest Path between two nodes");
            System.out.println("4.Exit");
            System.out.print("Enter your choice: ");
            operationChoice = scanner.nextInt();
            System.out.println();
            if (operationChoice == 1) {
                System.out.println("Enter the MST algorithm:");
                System.out.println("1.Prim");
                System.out.println("2.Kruskal");
                System.out.println("3.Exit");
                int mstAlgorithmChoice = scanner.nextInt();
                if(mstAlgorithmChoice == 3) exitProgram();
                calculateMST(mstAlgorithmChoice);
            } else if (operationChoice == 2) {
                System.out.println("Enter the shortest path algorithm:");
                System.out.println("1.BellmanFord");
                System.out.println("2.Dijkstra");
                System.out.println("3.Exit");
                int shortestPathAlgorithmChoice = scanner.nextInt();
                if (shortestPathAlgorithmChoice == 3) exitProgram();
                ShortestDistance(shortestPathAlgorithmChoice);
            } else if (operationChoice == 3) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter source: ");
                int src = sc.nextInt();
                System.out.println("Enter Destination: ");
                int dest = sc.nextInt();
                ShortestDistance(src, dest);
            } else if (operationChoice == 4) {
                exitProgram();
            } else {
                System.out.println("Invalid operation choice.");
            }
        }
    }
    private static void calculateMST(int mstAlgorithmChoice) {
        if(mstAlgorithmChoice == 1){
            PrimMST mst1 = new PrimMST(G1);
            PrimMST mst2 = new PrimMST(G2);
            PrimMST mst3 = new PrimMST(G3);
            System.out.println("Properties of combined MST are ->");
            double first = mst1.weight();
            double second = mst2.weight();
            double third = mst3.weight();
            double avg = (first + second + third)/3.0d;
            double min = Math.min(Math.min(first, second), third);
            double max = Math.max(Math.max(first, second), third);
            double median = first + second + third - min - max;
            System.out.printf("Average MST Weight = %.2f\n", avg);
            System.out.println("Median MST Weight = " + median);
            System.out.println("Minimum MST Weight = " + min);
            System.out.println("Maximum MST Weight = " + max);
            System.out.println();
        }
        else{
            KruskalMST mst1 = new KruskalMST(G1);
            KruskalMST mst2 = new KruskalMST(G2);
            KruskalMST mst3 = new KruskalMST(G3);
            System.out.println("Properties of combined MST are ->");
            double first = mst1.weight();
            double second = mst2.weight();
            double third = mst3.weight();
            double avg = (first + second + third)/3.0d;
            double min = Math.min(Math.min(first, second), third);
            double max = Math.max(Math.max(first, second), third);
            double median = first + second + third - min - max;
            System.out.printf("Average MST Weight = %.2f\n", avg);
            System.out.println("Median MST Weight = " + median);
            System.out.println("Minimum MST Weight = " + min);
            System.out.println("Maximum MST Weight = " + max);
            System.out.println();
        }
    }
    private static void calculateShortestDistance(int shortestPathAlgorithmChoice) {
        Scanner scanner = new Scanner(System.in);
        if(shortestPathAlgorithmChoice == 1) {
            
            System.out.println("Properties of combined Distance is ->");
            System.out.println("Choose Graph:");
            System.out.println("1.Graph_01");
            System.out.println("2.Graph_02");
            System.out.println("3.Graph_03");
            System.out.println("4.Exit");
            int choice = scanner.nextInt();
            System.out.println("Enter the source vertex (0-5 vertices)");
            int s = scanner.nextInt();
            if(s>5){
                System.out.println("Invalid vertex entered!!!");
                return;
            }
            BellmanFordSP sp1 = new BellmanFordSP(DG1,s);
            BellmanFordSP sp2 = new BellmanFordSP(DG2,s);
            BellmanFordSP sp3 = new BellmanFordSP(DG3,s);
            EdgeWeightedDigraph G = null;
            if(choice == 1){
                G = DG1;
            } else if (choice == 2) {
                G = DG2;
            } else if(choice == 3) {
                G = DG3;
            } else {
                exitProgram();
            }
            //  print negative cycle
            if (sp1.hasNegativeCycle()) {
                for (DirectedEdge e : sp1.negativeCycle())
                    StdOut.println(e);
            }

            // print shortest paths
            else {
                for (int v = 0; v < G.V(); v++) {
                    if (sp1.hasPathTo(v)) {
                        StdOut.printf("%d to %d (%5.2f)  ", s, v, sp1.distTo(v));
                        for (DirectedEdge e : sp1.pathTo(v)) {
                            StdOut.print(e + "   ");
                        }
                        StdOut.println();
                    }
                    else {
                        StdOut.printf("%d to %d           no path\n", s, v);
                    }
                }
            }
            System.out.println();
        }
        else {
            System.out.println("Choose Graph:");
            System.out.println("1.Graph_01");
            System.out.println("2.Graph_02");
            System.out.println("3.Graph_03");
            System.out.println("4.Exit");
            int choice = scanner.nextInt();
            if(choice == 4){
                exitProgram();
            }
            EdgeWeightedGraph G = null;
            if(choice == 1){
                G = G1;
            } else if (choice == 2) {
                G = G2;
            } else if(choice == 3){
                G = G3;
            } else{
                exitProgram();
            }
            System.out.println("Enter the source vertex (0-5 vertices)");
            int s = scanner.nextInt();
            if(s>5){
                System.out.println("Invalid vertex entered!!!");
                return;
            }
            DijkstraUndirectedSP sp1 = new DijkstraUndirectedSP(G1,s);
            DijkstraUndirectedSP sd2 = new DijkstraUndirectedSP(G2,s);
            DijkstraUndirectedSP sd3 = new DijkstraUndirectedSP(G3,s);
            for (int t = 0; t < G.V(); t++) {
                if (sp1.hasPathTo(t)) {
                    StdOut.printf("%d to %d (%.2f)  ", s, t, sp1.distTo(t));
                    for (Edge e : sp1.pathTo(t)) {
                        StdOut.print(e + "   ");
                    }
                    StdOut.println();
                }
                else {
                    StdOut.printf("%d to %d         no path\n", s, t);
                }
            }
        }
        System.out.println();
    }

    private static void ShortestDistance(int shortestPathAlgorithmChoice) {
        Scanner scanner = new Scanner(System.in);
        if (shortestPathAlgorithmChoice == 1) {
            System.out.println("Properties of combined Distance is ->");
            System.out.println("Choose Graph:");
            System.out.println("1.Graph_01");
            System.out.println("2.Graph_02");
            System.out.println("3.Graph_03");
            System.out.println("4.Exit");
            int choice = scanner.nextInt();
            if (choice == 4) {
                exitProgram();
            }
            EdgeWeightedDigraph G = null;
            if (choice == 1) {
                G = DG1;
            } else if (choice == 2) {
                G = DG2;
            } else if (choice == 3) {
                G = DG3;
            } else {
                exitProgram();
            }
            System.out.println("Enter the source vertex (0-5 vertices)");
            int src = scanner.nextInt();
            System.out.println("Enter the destination vertex (0-5 vertices)");
            int dest = scanner.nextInt();
            if (src > 5 || dest > 5) {
                System.out.println("Invalid vertex entered!!!");
                return;
            }
            BellmanFordSP sp = new BellmanFordSP(G, src);

            // Print shortest path
            if (sp.hasPathTo(dest)) {
                System.out.printf("Shortest distance from %d to %d: %.2f\n", src, dest, sp.distTo(dest));
                System.out.print("Shortest path: ");
                for (DirectedEdge e : sp.pathTo(dest)) {
                    System.out.print(e + " ");
                }
                System.out.println();
            } else {
                System.out.printf("No path exists from %d to %d\n", src, dest);
            }
        } else {
            // Rest of the code for Dijkstra's algorithm
        }
        System.out.println();
    }
    private static  void ShortestDistance(int src, int dest) {
        DijkstraUndirectedSP sd1 = new DijkstraUndirectedSP(G1,src);
        DijkstraUndirectedSP sd2 = new DijkstraUndirectedSP(G2,src);
        DijkstraUndirectedSP sd3 = new DijkstraUndirectedSP(G3,src);

        double min = Integer.MAX_VALUE;

        if(sd1.hasPathTo(dest)) min =  Math.min(min, sd1.distTo(dest));
        if(sd2.hasPathTo(dest)) min =  Math.min(min, sd2.distTo(dest));
        if(sd3.hasPathTo(dest)) min =  Math.min(min, sd3.distTo(dest));

        if(min == Integer.MAX_VALUE) System.out.println("No path exists!!!");
        else {
            System.out.println("Shortest path between " + src + " and " + dest + " is " + min);
        }
    }
}