package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskE {
    int[] depth;
    int[][] parent;
    ArrayList<Integer>[] G;
    int root;
    int log;
    int V;
    int[] cost;


    void LCA(int V){
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

    int dfs(int node){
        int c=cost[node];
        for(int i=0; i<G[node].size(); i++){
            if(G[node].get(i)!= parent[0][node]){
                c+= dfs(G[node].get(i));
            }
        }
        cost[node]=c;
        return c;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n=in.nextInt();
        LCA(n);
        int[] a= new int[n-1];
        int[] b= new int[n-1];
        for(int i=0; i<n-1; i++){
            a[i]=in.nextInt()-1;
            b[i]= in.nextInt()-1;
            addEdge(a[i],b[i]);
        }
        init(0);
        int q= in.nextInt();
        cost= new int[n];
        for(int c=0; c<q; c++){
            int x= in.nextInt()-1;
            int y= in.nextInt()-1;
            int anc= lca(x,y);
            cost[x]+=1;
            cost[y]+=1;
            cost[anc]-=2;
        }

        dfs(0);

        for(int i=0; i<n-1; i++){
            if(a[i]== parent[0][b[i]]){
                out.print(cost[b[i]] + " ");
            }else{
                out.print(cost[a[i]]+ " ");
            }
        }
    }
}