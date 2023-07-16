package Sub_Project_02;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Properties_of_Facebook_Gemsec_Graph {
    static EdgeWeightedGraph G1, G2, G3;
    static EdgeWeightedDigraph DG1, DG2, DG3;

    public static void ReadEdgeWeightGraph() {
        In in1 = new In("src/Sub_Project_02/01_Government.txt");
        In in2 = new In("src/Sub_Project_02/02_Politicians.txt");
        In in3 = new In("src/Sub_Project_02/03_Public_figure.txt");
        G1 = new EdgeWeightedGraph(in1);
        G2 = new EdgeWeightedGraph(in2);
        G3 = new EdgeWeightedGraph(in3);
    }

    public static void ReadEdgeWeightDigraph() {
        In in1 = new In("src/Sub_Project_02/01_Government.txt");
        In in2 = new In("src/Sub_Project_02/02_Politicians.txt");
        In in3 = new In("src/Sub_Project_02/03_Public_figure.txt");
        DG1 = new EdgeWeightedDigraph(in1);
        DG2 = new EdgeWeightedDigraph(in2);
        DG3 = new EdgeWeightedDigraph(in3);
    }

    private static void exitProgram() {
        System.out.println("Exiting program.....");
        System.exit(0);
    }

    public static void main(String[] args) {
        ReadEdgeWeightGraph();
        ReadEdgeWeightDigraph();

        Scanner obj = new Scanner(System.in);
        int operationChoice = 1;

        while (true) {
            System.out.println("Enter the operation you want to perform (MST or Shortest Distance Path):");
            System.out.println("1.MST");
            System.out.println("2.Get Shortest Distance from src to all the nodes");
            System.out.println("3.Generate Page Ranking");
            System.out.println("4.Community Detection");
            System.out.println("5.Exit");
            System.out.print("Enter your choice: ");
            operationChoice = obj.nextInt();
            System.out.println();
            if (operationChoice == 1) {
                System.out.println("Choose the MST algorithm:");
                System.out.println("1.Prim");
                System.out.println("2.Kruskal");
                System.out.println("3.Exit");
                int mstAlgorithmChoice = obj.nextInt();
                if (mstAlgorithmChoice == 3)
                    exitProgram();
                calculateMST(mstAlgorithmChoice);
            } else if (operationChoice == 2) {
                System.out.println("Choose the shortest path algorithm:");
                System.out.println("1.BellmanFord");
                System.out.println("2.Dijkstra");
                System.out.println("3.Exit");
                int shortestPathAlgorithmChoice = obj.nextInt();
                if (shortestPathAlgorithmChoice == 3)
                    exitProgram();
                calculateShortestDistance(shortestPathAlgorithmChoice);
            } else if (operationChoice == 3) {
                System.out.println("Choose Graph:");
                System.out.println("1.Government");
                System.out.println("2.Politicians");
                System.out.println("3.Public_Figure");
                System.out.println("4.Exit");
                int graphChoice = obj.nextInt();
                if (graphChoice == 4) {
                    System.out.println("Exiting program....");
                    System.exit(0);
                } else if (graphChoice < 1 || graphChoice > 4) {
                    System.out.println("Invalid input!!!");
                    continue;
                }
                GenerateRank(graphChoice);

            } else if (operationChoice == 4) {
                System.out.println("Choose Graph:");
                System.out.println("1.Government");
                System.out.println("2.Politicians");
                System.out.println("3.Public_Figure");
                System.out.println("4.Exit");
                int graphChoice = obj.nextInt();
                if (graphChoice == 4) {
                    System.out.println("Exiting program....");
                    System.exit(0);
                } else if (graphChoice < 1 || graphChoice > 4) {
                    System.out.println("Invalid input!!!");
                    continue;
                }
                DetectCommunity(graphChoice);
            } else if (operationChoice == 5) {

                exitProgram();
            } else {
                System.out.println("Invalid operation choice.");
            }

        }
    }

    private static void calculateMST(int mstAlgorithmChoice) {
        if (mstAlgorithmChoice == 1) {
            PrimMST mst1 = new PrimMST(G1);
            PrimMST mst2 = new PrimMST(G2);
            PrimMST mst3 = new PrimMST(G3);
            System.out.println("Properties of combined MST are ->");
            double first = mst1.weight();
            double second = mst2.weight();
            double third = mst3.weight();
            double avg = (first + second + third) / 3.0d;
            double min = Math.min(Math.min(first, second), third);
            double max = Math.max(Math.max(first, second), third);
            double median = first + second + third - min - max;
            System.out.printf("Average MST Weight = %.2f\n", avg);
            System.out.println("Median MST Weight = " + median);
            System.out.println("Minimum MST Weight = " + min);
            System.out.println("Maximum MST Weight = " + max);
            System.out.println();
        } else {
            KruskalMST mst1 = new KruskalMST(G1);
            KruskalMST mst2 = new KruskalMST(G2);
            KruskalMST mst3 = new KruskalMST(G3);
            System.out.println("Properties of combined MST are ->");
            double first = mst1.weight();
            double second = mst2.weight();
            double third = mst3.weight();
            double avg = (first + second + third) / 3.0d;
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
        if (shortestPathAlgorithmChoice == 1) {
            // System.out.println("Properties of combined Distance is ->");
            System.out.println("Choose Graph:");
            System.out.println("1.Government");
            System.out.println("2.Politicians");
            System.out.println("3.Public_Figure");
            System.out.println("4.Exit");
            int choice = scanner.nextInt();
            graphCall(choice);
            int s = scanner.nextInt();
            if (!validateSourceVertex(choice, s)) {
                System.out.println("Invalid vertex entered!!!");

                return;
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
            BellmanFordSP sp1 = new BellmanFordSP(G, s);
            // print negative cycle
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
                    } else {
                        StdOut.printf("%d to %d           no path\n", s, v);
                    }
                }
            }
            System.out.println();
        } else {
            System.out.println("Choose Graph:");
            System.out.println("1.Government");
            System.out.println("2.Politicians");
            System.out.println("3.Public_Figure");
            System.out.println("4.Exit");
            int choice = scanner.nextInt();
            graphCall(choice);

            if (choice == 4) {
                exitProgram();
            }
            EdgeWeightedGraph EWG = null;
            if (choice == 1) {
                EWG = G1;
            } else if (choice == 2) {
                EWG = G2;
            } else if (choice == 3) {
                EWG = G3;
            } else {
                exitProgram();
            }
            int s = scanner.nextInt();

            if (!validateSourceVertex(choice, s)) {
                System.out.println("Invalid vertex entered!!!");

                return;
            }
            DijkstraUndirectedSP sp1 = new DijkstraUndirectedSP(EWG, s);
            for (int t = 0; t < EWG.V(); t++) {
                if (sp1.hasPathTo(t)) {
                    StdOut.printf("%d to %d (%.2f)  ", s, t, sp1.distTo(t));
                    for (Edge e : sp1.pathTo(t)) {
                        StdOut.print(e + "   ");
                    }
                    StdOut.println();
                } else {
                    StdOut.printf("%d to %d         no path\n", s, t);
                }
            }
        }
        System.out.println();

    }

    private static void graphCall(int choice) {
        if (choice == 1) {
            System.out.println("Enter the source vertex (0-7056)");
        } else if (choice == 2) {
            System.out.println("Enter the source vertex (0-5907)");
        } else if (choice == 3) {
            System.out.println("Enter the source vertex (0-11564)");
        }
    }

    private static boolean validateSourceVertex(int choice, int s) {
        int maxVertex;
        if (choice == 1) {
            maxVertex = 7056;
        } else if (choice == 2) {
            maxVertex = 5907;
        } else if (choice == 3) {
            maxVertex = 11564;
        } else {
            return false;
        }
        if (s > maxVertex || s < 0) {
            return false;
        }
        return true;
    }

    private static void GenerateRank(int graphChoice) {
        String filename = "";
        if (graphChoice == 1) {
            filename = "src/Sub_Project_02/01_Government.txt";
        } else if (graphChoice == 2) {
            filename = "src/Sub_Project_02/02_Politicians.txt";
        } else if (graphChoice == 3) {
            filename = "src/Sub_Project_02/03_Public_figure.txt";
        } else {
            System.out.println("Invalid choice !!!");
            return;
        }
        PageRank pageRank = new PageRank(filename);
        pageRank.Run(filename);
    }

    private static void DetectCommunity(int graphChoice) {
        String filename = "";
        if (graphChoice == 1) {
            filename = "src/Sub_Project_02/01_Government.txt";
        } else if (graphChoice == 2) {
            filename = "src/Sub_Project_02/02_Politicians.txt";
        } else if (graphChoice == 3) {
            filename = "src/Sub_Project_02/03_Public_figure.txt";
        } else {
            System.out.println("Invalid choice !!!");
            return;
        }
        CommunityDetection cd = new CommunityDetection();
        cd.Run(filename);
    }
}
