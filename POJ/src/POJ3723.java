import java.io.*;
import java.util.*;

/**
 * Created by Bat-Orgil on 6/20/2015.
 */
public class POJ3723 {

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out =new PrintWriter(System.out);
        int T = in.nextInt();
        for (int count = 0; count < T; count++) {
            int N = in.nextInt();
            int M = in.nextInt();
            int R = in.nextInt();
            LinkedList<Edge> G = new LinkedList<Edge>();
            for (int i = 0; i < R; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                int w = in.nextInt();
                G.add(new Edge(a,(b+N), (10000-w)));
            }

            LinkedList<Edge> MST= Kruskal(G, N+M);
            long answer=0;
            for(Edge edge: MST){
                answer+= edge.w;
            }
            answer+= 10000* (N+M-MST.size());
            out.println(answer);
        }
        out.close();
        System.exit(0);
    }

    public static LinkedList<Edge> Kruskal(List<Edge> G, int N) {      // Edges G, int N- number of nodes.
        Collections.sort(G);
        LinkedList<Edge> edgesMST = new LinkedList<Edge>();
        DSU dsu = new DSU(N);
        long sumSpan = 0;
        while (G.size() != 0 && edgesMST.size() < (N - 1)) {
            Edge lightest = G.remove(0);
            if (!dsu.same(lightest.f, lightest.to)) {
                edgesMST.add(lightest);
                dsu.unite(lightest.f, lightest.to);
            }
        }
        return edgesMST;
    }

    public static class Edge implements Comparable<Edge> {
        int f, to, w;

        Edge(int from, int t, int ww) {
            f = from;
            to = t;
            w = ww;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }

    public static class DSU {
        int par[];
        int rank[];

        DSU(int n) {
            par = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                par[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (par[x] == x) {
                return x;
            } else {
                return par[x] = find(par[x]);
            }
        }

        void unite(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) return;

            if (rank[x] < rank[y]) {
                par[x] = y;
            } else {
                par[y] = x;
                if (rank[x] == rank[y]) rank[x]++;
            }
        }

        boolean same(int x, int y) {
            return find(x) == find(y);
        }

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