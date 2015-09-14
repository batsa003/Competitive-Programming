import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class POJ1330{

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        StringBuilder sb= new StringBuilder();
        int T= in.nextInt();
        for(int c=0; c<T; c++){
            int N= in.nextInt();
            LCA lca= new LCA(N);
            int[] inDeg= new int[N];
            for(int i=0; i<N-1; i++){
                int a= in.nextInt();
                int b= in.nextInt();
                inDeg[b-1]++;
                lca.addEdge(a-1,b-1);
            }

            int root=-1;
            for(int i=0; i<N; i++){
                if(inDeg[i]==0){
                    root=i;
                    break;
                }
            }
            lca.init(root);

            int a= in.nextInt();
            int b= in.nextInt();
            sb.append((lca.lca(a-1,b-1)+1) + "\n");
        }

        PrintWriter out = new PrintWriter(System.out);
        out.println(sb);
        out.close();
        System.exit(0);
    }
}
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