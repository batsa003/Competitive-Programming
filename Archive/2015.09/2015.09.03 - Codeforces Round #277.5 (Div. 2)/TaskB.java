package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] b= new int[n];
        for(int i=0; i<n; i++){
            b[i]=in.nextInt();
        }
        int m = in.nextInt();
        int[] g= new int[m];
        for(int i=0; i<m; i++){
            g[i]=in.nextInt();
        }
        BipartiteMatching BM = new BipartiteMatching(n+m);

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(Math.abs(b[i]-g[j])<=1){
                    BM.addEdge(i,j+n);
                }
            }
        }
        out.println(BM.bipartiteMatching());

    }
}


class BipartiteMatching
{
    int n;
    ArrayList<Integer>[] G;
    int[] match;// match=-1 means it is not matched, otherwise it shows the match
    boolean[] used;
    // n is the number of nodes
    // nodes must be 0 ... n-1

    BipartiteMatching(int n) {
        this.n = n;
        G = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<Integer>();
        }
        match = new int[n];
        used = new boolean[n];
    }
    void addEdge(int u, int v) {
        G[u].add(v);
        G[v].add(u);
    }

    boolean dfs(int v) {
        used[v] = true;
        for (int i = 0; i < G[v].size(); i++) {
            int u = G[v].get(i);
            int w = match[u];
            if (w < 0 || !used[w] && dfs(w)) {
                match[v] = u;
                match[u] = v;
                return true;
            }
        }
        return false;
    }

    int bipartiteMatching() {
        int res = 0;
        Arrays.fill(match, -1);
        for (int v = 0; v < n; v++) {
            if (match[v] < 0) {
                Arrays.fill(used, false);
                if (dfs(v)) {
                    res ++;
                }
            }
        }
        return res;
    }

}