import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class BellmanFordShortestPath {
    // O(V*E)
    // Shortest path from start to end
    // Detects negative cycle.
    // Edge can be negative.
    // Nodes are numbered from 1, 2, ... , n


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


    /*public static void main(String[] args){
        Edge[] G= new Edge[5];
        G[0]= new Edge(0,1,10);
        G[1]= new Edge(0,2,20);
        G[2]=new Edge(0,3,30);
        G[3]= new Edge(0,4,40);
        G[4]=new Edge(1,4,25);
        System.out.println( bellman(G,0,4,5));


    }*/
}
