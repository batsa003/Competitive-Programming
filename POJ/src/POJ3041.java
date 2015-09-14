import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Bat-Orgil on 7/18/2015.
 */
public class POJ3041 {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int N =in.nextInt();
        int K =in.nextInt();
        BipartiteMatching bm= new BipartiteMatching(2*N);
        for(int i=0; i<K; i++){
            int r= in.nextInt();
            int c=in.nextInt();
            bm.addEdge(r-1, c+N-1);
        }
        System.out.println(bm.bipartiteMatching());

    }

    static class BipartiteMatching
    {
        int n;
        ArrayList<Integer>[] G;
        int match[];// match=-1 means it is not matched, otherwise it shows the match
        boolean used[];
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
}
