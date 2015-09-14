import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
1. Create Dijkstra(n) where the nodes are numbered from 0 to n-1
2. Add edges to the graph by addEdge(f,t,w) method
3. B= dijkstra(s) returns a list such that B[i] returns the shortest path from s to i.
 */




public class Dijkstra {

    class Edge {
        int to;
        long cost;
        Edge(int to, long cost) {
            this.to   = to;
            this.cost = cost;
        }
    }
    class Pair implements Comparable<Pair> {
        long first;
        int second;
        Pair(long first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public int compareTo(Pair o) {
            if (this.first == o.first) return 0;
            return (this.first < o.first) ? -1 : 1;
        }
    }
    int n;
    long INF = (long)1e15;
    ArrayList<Edge>[] G;
    int[] P;

    // n is the number of nodes
    // nodes must be 0 ... n-1
    Dijkstra(int n) {
        this.n = n;
        G = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<Edge>();
        }
        P = new int[n];
    }

    void addEdge(int from, int to, long cost) {
        G[from].add(new Edge(to,cost));
    }

    // returns an array dist
    // s.t. dist[t] = minimum distance from s to t
    long[] dijkstra(int s) {
        PriorityQueue<Pair> que = new PriorityQueue<Pair>();
        long[] dist  = new long[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        que.add(new Pair(0,s));
        while (!que.isEmpty()) {
            Pair p = que.poll();
            int v = p.second;
            if (dist[v] < p.first) continue;
            for (int i = 0; i < G[v].size(); i++) {
                int u = G[v].get(i).to;
                long alt = dist[v] + G[v].get(i).cost;
                if (alt < dist[u]) {
                    P[u] = v;
                    dist[u] = alt;
                    que.add(new Pair(alt,u));
                }
            }
        }
        return dist;
    }

}
