package Sub_Project_02;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import java.util.*;

public class CommunityDetection {
    private static final double MIN_DELTA_Q = 0.0001;

    public static List<Set<Integer>> detectCommunities(Graph graph) {
        List<Set<Integer>> communities = new ArrayList<>();
        Map<Integer, Integer> nodeCommunityMap = new HashMap<>();

        // Initially, each node is in its own community
        for (int v = 0; v < graph.V(); v++) {
            Set<Integer> community = new HashSet<>();
            community.add(v);
            communities.add(community);
            nodeCommunityMap.put(v, v);
        }

        double deltaQ = Double.POSITIVE_INFINITY;
        while (deltaQ >= MIN_DELTA_Q) {
            deltaQ = 0;

            // Calculate the change in modularity (deltaQ) by moving each node to its neighboring communities
            for (int v = 0; v < graph.V(); v++) {
                int currCommunity = nodeCommunityMap.get(v);
                double maxDeltaQ = 0;
                int newCommunity = currCommunity;

                // Find the neighboring communities of the current node
                Set<Integer> neighboringCommunities = new HashSet<>();
                for (int w : graph.adj(v)) {
                    int neighborCommunity = nodeCommunityMap.get(w);
                    neighboringCommunities.add(neighborCommunity);
                }

                // Calculate the modularity change for each neighboring community
                for (int neighborCommunity : neighboringCommunities) {
                    double deltaQv = calculateModularityChange(graph, v, currCommunity, neighborCommunity);
                    if (deltaQv > maxDeltaQ) {
                        maxDeltaQ = deltaQv;
                        newCommunity = neighborCommunity;
                    }
                }

                // Update the community assignment and modularity
                if (maxDeltaQ > 0) {
                    communities.get(currCommunity).remove(v);
                    communities.get(newCommunity).add(v);
                    nodeCommunityMap.put(v, newCommunity);
                    deltaQ += maxDeltaQ;
                }
            }
        }

        return communities;
    }

    private static double calculateModularityChange(Graph graph, int node, int currCommunity, int newCommunity) {
        int degrees = graph.degree(node);
        int degreesInCurrCommunity = countEdgesWithinCommunity(graph, node, currCommunity);
        int degreesInNewCommunity = countEdgesWithinCommunity(graph, node, newCommunity);
        int m = graph.E(); // Total number of edges

        double modularityChange = (2.0 * (degreesInNewCommunity - degreesInCurrCommunity)) / (2.0 * m) - Math.pow((double) degrees / (2.0 * m), 2);
        return modularityChange;
    }

    private static int countEdgesWithinCommunity(Graph graph, int node, int community) {
        int count = 0;
        for (int w : graph.adj(node)) {
            if (community == w) {
                count++;
            }
        }
        return count;
    }

//    public static void main(String[] args) {
//        String filename = "src/Sub_Project_02/01_Government.txt"; // Replace with the actual file path
//        In in = new In(filename);
//        Graph graph = new Graph(in);
//        List<Set<Integer>> communities = detectCommunities(graph);
//
//        // Print the detected communities
//        int counter = 0;
//        for (int i = 0; i < communities.size(); i++) {
//            if(communities.get(i).isEmpty()) continue;
//            System.out.println("Community " + (++counter) + ": " + communities.get(i));
//        }
//    }

    public void Run(String name) {
        String filename = "";
        filename = name;
        In in = new In(filename);
        Graph graph = new Graph(in);
        List<Set<Integer>> communities = detectCommunities(graph);

        // Print the detected communities
        int counter = 0;
        long size = 0;
        for (int i = 0; i < communities.size(); i++) {
            if(communities.get(i).isEmpty()) continue;
            System.out.println("Community " + (++counter) + ": " + communities.get(i));
            size = Math.max(communities.get(i).size(), size);
        }
        System.out.println("Size of the largest community is " + size);
    }
}




