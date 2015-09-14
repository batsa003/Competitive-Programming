import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class POJ3686{
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T= in.nextInt();
        for(int c=0; c<T; c++){
            int N= in.nextInt();
            int M =in.nextInt();
            int[][] z= new int[N+1][M+1];
            for(int i=1; i<=N; i++){
                for(int j=1; j<=M; j++){
                    z[i][j]= in.nextInt();
                }
            }

            MinCostFlow f= new MinCostFlow(M*N+M+N+3);
            int s= M*N+N+M+1;
            int t= M*N+N+M+2;
            for(int i=1; i<=N; i++){
                f.addEdge(s,M*N+i,1,0);
            }
            for(int i=1; i<=M; i++){
                f.addEdge(i,t,N,0);
            }
            for(int i=1; i<=M; i++){
                for(int j=1; j<=N; j++) {
                    f.addEdge((i - 1) * N +j, i,1,0);
                }
            }

            for(int i=1; i<=N; i++){
                for(int j=1; j<=M; j++){
                    for(int k=1; k<=N; k++){
                        f.addEdge(M*N+i, (j-1)*N+k,1,k*z[i][j]);
                    }
                }
            }

            double ans= (double)f.minCostFlow(s,t,N)*1.0/N;
            System.out.printf("%.6f\n", ans);
            //System.out.println(f.minCostFlow(s,t,N));
        }
        out.close();
        System.exit(0);

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}

class MinCostFlow {
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