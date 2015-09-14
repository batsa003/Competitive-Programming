import java.util.ArrayList;

/**
 * Created by Bat-Orgil on 8/6/2015.
 */
// INITIALIZATION:
    /* Say n is number of nodes, m is number of edges.
     tin= new int[n];
     fup = new int[n];
     used= new boolean[n];
     timer=0;
     bridge= new boolean[m];
     G = new ArrayList<Edge>[n];
     For all edge (from,to, cost, ind) where 0<= ind <m, do this:
          G[from].add( new Edge(to,cost,ind));
          G[to].add ( new Edge(from,cost,ind));*/


// to fill up the bridge[]; where bridge[i] returns if edge is bridge or not, call dfs(s,-1);
// IMPORTANT:  call dfs(s,-1) and s MUST BE in the connected component. The graph is assumed to be connected.
// If it has several SCC, then we have to call dfs(s, -1) for all of the nodes (AS LONG AS s is not used), in order to check all of the edges to be a bridge or not.


public class Bridge {

    ArrayList<Edge>[] G;
    boolean[] used;
    int[] tin, fup;
    int timer = 0;
    boolean[] bridge;


    void dfs(int v, int p) {
        used[v] = true;
        tin[v] = fup[v] = timer++;

        for (Edge e : G[v]) {
            int to = e.to;
            if (to == p)
                continue;
            if (used[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    bridge[e.ind] = true;
                }
            }
        }
    }

   static class Edge {

        public Edge(int to, int cost, int ind) {
            this.to = to;
            this.cost = cost;
            this.ind = ind;
        }

        int to, cost, ind;
    }

}

