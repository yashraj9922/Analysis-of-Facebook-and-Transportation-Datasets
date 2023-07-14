package Sub_Project_02;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class PageRank {
    private static final double DAMPING_FACTOR = 0.85;
    private static final double EPSILON = 0.0001;

    private final int numPages;
    private final Digraph graph;
    private double[] ranks;

    public PageRank(String filename) {
        In in = new In(filename);
        numPages = in.readInt();
        graph = new Digraph(numPages);

        // Skip the second line with the invalid node value
        in.readLine();
        in.readLine();

        while (!in.isEmpty()) {
            int from = in.readInt();
            int to = in.readInt();
            double weight = in.readDouble();

            graph.addEdge(from, to);
        }
    }

    public void calculateRanks() {
        int[] outDegrees = calculateOutDegrees();
        ranks = new double[numPages];

        // Initialize ranks
        double initialRank = 1.0 / numPages;
        for (int i = 0; i < numPages; i++) {
            ranks[i] = initialRank;
        }

        double[] nextRanks = new double[numPages];
        while (true) {
            // Calculate next ranks
            for (int page = 0; page < numPages; page++) {
                double rank = 0.0;
                for (int connectedPage : graph.adj(page)) {
                    rank += ranks[connectedPage] / outDegrees[connectedPage];
                }
                nextRanks[page] = (1.0 - DAMPING_FACTOR) / numPages + DAMPING_FACTOR * rank;
            }

            // Check convergence
            if (isConverged(ranks, nextRanks)) {
                break;
            }

            // Update ranks
            System.arraycopy(nextRanks, 0, ranks, 0, numPages);
        }
    }

    private int[] calculateOutDegrees() {
        int[] outDegrees = new int[numPages];
        for (int page = 0; page < numPages; page++) {
            outDegrees[page] = graph.outdegree(page);
        }
        return outDegrees;
    }

    private boolean isConverged(double[] ranks, double[] nextRanks) {
        for (int i = 0; i < numPages; i++) {
            if (Math.abs(ranks[i] - nextRanks[i]) > EPSILON) {
                return false;
            }
        }
        return true;
    }

    public double[] getRanks() {
        return ranks;
    }

    public  static void Run(String name) {
        String filename = name;
        PageRank pageRank = new PageRank(filename);
        pageRank.calculateRanks();

        double[] ranks = pageRank.getRanks();

        // Create a list of nodes with their corresponding ranks
        List<NodeRank> nodeRanks = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            if (!Double.isInfinite(ranks[i])) {
                nodeRanks.add(new NodeRank(i, ranks[i]));
            }
        }

        // Sort the nodes in descending order based on their ranks
        Collections.sort(nodeRanks, Collections.reverseOrder());

        // Assign ranks to the nodes excluding Infinity
        for (int i = 0; i < nodeRanks.size(); i++) {
            nodeRanks.get(i).rank = i + 1;
        }

        // Display the most popular nodes with their ranks
        for (NodeRank nodeRank : nodeRanks) {
            StdOut.printf("Node %d: Rank %.0f\n", nodeRank.node, nodeRank.rank);
        }
    }

    // Helper class to store node and rank information
    static class NodeRank implements Comparable<NodeRank> {
        int node;
        double rank;

        public NodeRank(int node, double rank) {
            this.node = node;
            this.rank = rank;
        }

        @Override
        public int compareTo(NodeRank other) {
            return Double.compare(rank, other.rank);
        }
    }
}