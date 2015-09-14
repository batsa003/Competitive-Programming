import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordNegativeCycle {
    // Detects the negative cycle
    // Graph 0,1,..., n-1
    // graph[i] contains list of edges, such that for each edge j, and cost C- > directed edge i,j with cost C
    // findNegativeCycle(graph) returns the list of nodes that forms the negative cycle.


    static final int INF = Integer.MAX_VALUE / 2;

    public static class Edge {
        int v, cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static boolean bellmanFord(List<Edge>[] graph, int s, int[] dist, int[] pred) {
        Arrays.fill(pred, -1);
        Arrays.fill(dist, INF);
        dist[s] = 0;
        int n = graph.length;
        boolean updated = false;

        // At the end of the k-th step, we must have found a shortest path from S to a node if it only contains <= k edges;

        for (int step = 0; step < n; step++) {
            updated = false;
            for (int u = 0; u < n; u++) {
                if (dist[u] == INF) continue;
                for (Edge e : graph[u]) {
                    if (dist[e.v] > dist[u] + e.cost) {
                        dist[e.v] = dist[u] + e.cost;
                        dist[e.v] = Math.max(dist[e.v], -INF);
                        pred[e.v] = u;
                        updated = true;
                    }
                }
            }
            if (!updated)
                break;
        }
        // if updated is true then a negative cycle exists
        return updated == false;
    }

    public static int[] findNegativeCycle(List<Edge>[] graph) {
        int n = graph.length;
        int[] pred = new int[n];
        Arrays.fill(pred, -1);
        int[] dist = new int[n];
        int last = -1;
        for (int step = 0; step < n; step++) {
            last = -1;
            for (int u = 0; u < n; u++) {
                if (dist[u] == INF) continue;
                for (Edge e : graph[u]) {
                    if (dist[e.v] > dist[u] + e.cost) {
                        dist[e.v] = Math.max(dist[u] + e.cost, -INF);
                        dist[e.v] = Math.max(dist[e.v], -INF);
                        pred[e.v] = u;
                        last = e.v;
                    }
                }
            }
            if (last == -1)
                return null;
        }
        for (int i = 0; i < n; i++) {
            last = pred[last];
        }
        int[] p = new int[n];
        int cnt = 0;
        for (int u = last; u != last || cnt == 0; u = pred[u]) {
            p[cnt++] = u;
        }
        int[] cycle = new int[cnt];
        for (int i = 0; i < cycle.length; i++) {
            cycle[i] = p[--cnt];
        }
        return cycle;
    }

    // Usage example
    /*public static void main(String[] args) {
        List<Edge>[] graph = new List[4];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(1, 1));
        graph[1].add(new Edge(0, 1));
        graph[1].add(new Edge(2, 1));
        graph[2].add(new Edge(3, 10));
        graph[3].add(new Edge(1, 1));
        int[] cycle = findNegativeCycle(graph);
    }*/
}