import java.util.ArrayList;
import java.util.Arrays;

/*
1. create BipartiteMatching(int n) - number of nodes
2. add edges using addEdge(u,v)
3. BipartiteMatching() returns the maximum matching
4. match[i] returns the matching of the node i.   0<= i < n


*/


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