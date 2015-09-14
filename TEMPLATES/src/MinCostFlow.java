import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


/*
1. First, construct MinCostFlow (n) with n is the number of nodes
2. Add all of the edges to the graph with addEdge(from,to,capacity,cost) method
3. Mincost(s,t,f) returns the minimum cost
    // of flow f from s to t
    // if impossible, returns -1


*/


public class MinCostFlow {
    class Edge {
        int to, cap, cost, rev;

        Edge(int to, int cap, int cost, int rev) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.rev = rev;
        }
    }

    class Pair implements Comparable<Pair> {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.first != o.first) {
                return this.first - o.first;
            } else {
                return this.second - o.second;
            }
        }
    }

    int n;
    int INF = (int) 2e9;
    ArrayList<Edge>[] G;
    int[] h, dist, prevv, preve;

    // n is the number of nodes
    // nodes must be 0 ... n-1

    MinCostFlow(int n) {
        this.n = n;
        G = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<Edge>();
        }
        h = new int[n];
        dist = new int[n];
        prevv = new int[n];
        preve = new int[n];
    }

    void addEdge(int from, int to, int cap, int cost) {
        G[from].add(new Edge(to, cap, cost, G[to].size()));
        G[to].add(new Edge(from, 0, -cost, G[from].size() - 1));
    }

    // returns the minimum cost
    // of flow f from s to t
    // if impossible, returns -1
    // In some problems,

    int minCostFlow(int s, int t, int f) {
        int res = 0;
        Arrays.fill(h, 0);
        while (f > 0) {
            PriorityQueue<Pair> que = new PriorityQueue<Pair>();
            Arrays.fill(dist, INF);
            dist[s] = 0;
            que.add(new Pair(0, s));
            while (!que.isEmpty()) {
                Pair p = que.poll();
                int v = p.second;
                if (dist[v] < p.first) continue;
                for (int i = 0; i < G[v].size(); i++) {
                    Edge e = G[v].get(i);
                    if (e.cap > 0 && dist[e.to] > dist[v] + e.cost + h[v] - h[e.to]) {
                        dist[e.to] = dist[v] + e.cost + h[v] - h[e.to];
                        prevv[e.to] = v;
                        preve[e.to] = i;
                        que.add(new Pair(dist[e.to], e.to));
                    }
                }
            }
            if (dist[t] == INF) {
                return -1;
            }
            for (int v = 0; v < n; v++) h[v] += dist[v];

            int d = f;
            for (int v = t; v != s; v = prevv[v]) {
                d = Math.min(d, G[prevv[v]].get(preve[v]).cap);
            }
            f -= d;
            res += d * h[t];
            for (int v = t; v != s; v = prevv[v]) {
                Edge e = G[prevv[v]].get(preve[v]);
                e.cap -= d;
                G[v].get(e.rev).cap += d;
            }
        }

        return res;
    }
}