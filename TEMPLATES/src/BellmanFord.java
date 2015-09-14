import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class BellmanFord {
    // Shortest path from start to end
    // Numbered from 1 to N.


    private int inf = 1000010;

    public int bellman(Edge[] graph, int start, int end, int n) {//-1 if negative cycle, //-2 if inf
        int dist[] = new int[n+1];
        fill(dist, inf);
        dist[start] = 0;

        for(int i=1; i<=n; i++) {
            for(Edge e: graph) {
                dist[e.to] = min(dist[e.from] + e.weight, dist[e.to]);
            }
        }

        if(hasNegCycle(graph, dist, end)) {
            return -1;
        } else if(dist[end] == inf){
            return  -2;
        } else {
            return dist[end];
        }
    }

    public boolean hasNegCycle(Edge[] graph, int[] dist, int end) {
        int curr = dist[end];

        for(Edge e: graph) {
            dist[e.to] = min(dist[e.from] + e.weight, dist[e.to]);
        }

        return dist[end] < curr;
    }

    public class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

}
