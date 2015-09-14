import java.util.ArrayList;


// 1. lca(int v) with v is the number of nodes
// 2. add v-1 edges to the graph with addEdge method
// 3. init(int root) is gonna initialize with root=root;
// 4. Nodes are numbered from 0,1, ... , n-1
//  5. lca(u,v) is gonna give lca of u,v in log(n) time.


class LCA {
    int[] depth;
    int parent[][];
    ArrayList<Integer>[] G;
    int root;
    int log;
    int V;


    LCA(int V){
        this.V=V;
        G= new ArrayList[V];
        for(int i=0; i<V; i++){
            G[i]=new ArrayList<Integer>();
        }
        for(log=0; (1<<log)<V; log++);
    }

    void dfs(int v, int p, int d){
        parent[0][v]=p;
        depth[v]=d;
        for(int i=0; i<G[v].size();i++){
            if(G[v].get(i)!=p){
                dfs(G[v].get(i),v,d+1);
            }
        }
    }

    void init(int root){
        parent= new int[log][V];
        depth= new int[V];
        this.root=root;

        //dfs through parents[2^0] and depth
        dfs(root,-1,0);

        for(int k=0; k+1<log; k++){
            for(int v=0; v<V; v++){
                if(parent[k][v]<0){
                    parent[k+1][v]=-1;
                }else{
                    parent[k+1][v]=parent[k][parent[k][v]];
                }
            }
        }

    }

    int lca(int u, int v){
        if(depth[u] >depth[v]){
            u^=v;
            v^=u;
            u^=v;
        }

        //Now v is on the deeper level

        for(int k=0; k<log; k++){
            if(( (depth[v]-depth[u])>>k &1)==1){
                v=parent[k][v];
            }
        }

        if(u==v ) return u;

        for(int k=log-1; k>=0; k--){
            if(parent[k][u] != parent[k][v]){
                u=parent[k][u];
                v=parent[k][v];
            }
        }
        return parent[0][u];
    }
    void addEdge(int u, int v){
        G[u].add(v);
        G[v].add(u);
    }
}