import java.util.*;
import java.io.*;
import java.text.*;

import static java.lang.Math.min;
import static java.util.Arrays.*;

//Retrieved from:
//https://github.com/joker23/ACM/blob/master/poj/3169.java
public class POJ3169SystemOfConstraints {

    private Scanner in;
    private StringTokenizer st;
    private PrintWriter out;

    private int inf = 1000010;
    private DecimalFormat fmt = new DecimalFormat("0.0000000000");


    public void solve() throws Exception {
        int n = in.nextInt();
        int ml = in.nextInt();
        int md = in.nextInt();

        Edge[] graph = new Edge[n + ml + md - 1];

        int count = 0;

        // going backwards costs nothing!
        for(int i=1; i<n; i++) {
            graph[count++] = new Edge(i + 1, i, 0);
        }

        while(ml --> 0) {
            graph[count++] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }

        while(md --> 0) {
            int u = in.nextInt();
            int v = in.nextInt();

            graph[count++] = new Edge(v, u, - in.nextInt());
        }

        out.println(bellman(graph, 1, n, n));

    }

    public int bellman(Edge[] graph, int start, int end, int n) {
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

    public POJ3169SystemOfConstraints() {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
    }

    public void end() {
        try {
            this.out.flush();
            this.out.close();
            this.in.close();
        } catch (Exception e){
            //do nothing then :)
        }
    }

    public static void main(String[] args) throws Exception {
        POJ3169SystemOfConstraints solver = new POJ3169SystemOfConstraints();
        solver.solve();
        solver.end();
    }
}