import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

//0 is source, n-1 is the sink
// flow() method is gonna maximum flow;
// flow() might overflow and become negative

public class POJ1273MAXFLOW {
    static class Edge {
        int fr, to;
        long flow, cap;
        Edge rev;

        Edge(int fr, int to, long cap) {
            this.fr = fr;
            this.to = to;
            this.cap = cap;
        }
    }

    static class Flow {
        int n;
        ArrayList<Edge>[] g;

        Flow(int n) {
            this.n = n;
            g = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<Edge>();
            }
            q = new int[n];
            h = new int[n];
            cur = new int[n];
        }

        void addEdge(int fr, int to, long cap) {
            Edge e1 = new Edge(fr, to, cap);
            Edge e2 = new Edge(to, fr, 0);
            e1.rev = e2;
            e2.rev = e1;
            g[fr].add(e1);
            g[to].add(e2);
        }

        int[] h;
        int[] cur;
        int[] q;

        boolean bfs() {
            int qIt = 0, qSz = 0;
            q[qSz++] = 0;
            Arrays.fill(h, -1);
            h[0] = 0;
            while (qIt < qSz) {
                int v = q[qIt++];
                for (Edge e : g[v]) {
                    if (e.flow == e.cap)
                        continue;
                    if (h[e.to] == -1) {
                        h[e.to] = h[e.fr] + 1;
                        q[qSz++] = e.to;
                    }
                }
            }
            return h[n - 1] != -1;
        }

        long dfs(int v, long flow) {
            if (v == n - 1 || flow == 0)
                return flow;
            for (; cur[v] < g[v].size(); cur[v]++) {
                Edge e = g[v].get(cur[v]);
                if (h[e.to] != h[e.fr] + 1 || e.flow == e.cap)
                    continue;
                long add = dfs(e.to, Math.min(flow, e.cap - e.flow));
                if (add == 0)
                    continue;
                e.flow += add;
                e.rev.flow -= add;
                return add;
            }
            return 0;
        }

        long flow() {
            long res = 0;
            while (bfs()) {
                Arrays.fill(cur, 0);
                while (true) {
                    long add = dfs(0, Long.MAX_VALUE);
                    if (add == 0)
                        break;
                    res += add;
                }
            }
            return res;
        }
    }

    static Scanner in;
    static PrintWriter out;

    void solve(){
        in= new Scanner(System.in);
        out= new PrintWriter(System.out,true);
        StringBuilder sb= new StringBuilder();
        while(in.hasNext()){
            int N= in.nextInt();
            int M =in.nextInt();
            Flow f= new Flow(M);
            for(int i=0; i<N; i++){
                int si=in.nextInt()-1;
                int ei=in.nextInt()-1;
                int ci=in.nextInt();
                f.addEdge(si,ei,ci);
            }
            long ans= f.flow();
            sb.append(ans + "\n");
        }
        out.println(sb);
        out.close();
        System.exit(0);
    }
    public static void main(String[] args) {
        in= new Scanner(System.in);
        out= new PrintWriter(System.out,true);
        StringBuilder sb= new StringBuilder();
        while(in.hasNext()){
            int N= in.nextInt();
            int M =in.nextInt();
            Flow f= new Flow(M);
            for(int i=0; i<N; i++){
                int si=in.nextInt()-1;
                int ei=in.nextInt()-1;
                int ci=in.nextInt();
                f.addEdge(si,ei,ci);
            }
            long ans= f.flow();
            sb.append(ans + "\n");
        }
        out.println(sb);
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
