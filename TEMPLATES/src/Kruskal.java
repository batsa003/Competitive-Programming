import java.util.*;



/*  Let G be given graph. Let G' be a graph such that G'.V= G.V and G'.E is subset of G.E. G' is a spanning tree iff G' is a tree.
*   MSP is a spanning tree with a minimum sum of edge weight.
*
*
*
*
*
*
*
* */




public class Kruskal {

    public static LinkedList<Edge> Kruskal(List<Edge> G, int N){      // Edges G, int N- number of nodes.
        Collections.sort(G);
        LinkedList<Edge> edgesMST= new LinkedList<Edge>();
        DSU dsu = new DSU(N);
        long sumSpan=0;
        while(G.size()!= 0 && edgesMST.size()<(N-1)){
            Edge lightest= G.remove(0);
            if(!dsu.same(lightest.f, lightest.to)){
                edgesMST.add(lightest);
                dsu.unite(lightest.f, lightest.to);
            }
        }
        return edgesMST;
    }

    public class Edge implements Comparable<Edge>{
        int f, to ,w;
        Edge(int from, int t, int ww){
            f=from;
            to=t;
            w=ww;
        }

        @Override
        public int compareTo(Edge o) {
            return w-o.w;
        }
    }

    public static class DSU {
        int par[];
        int rank[];
        DSU (int n){
            par= new int[n];
            rank= new int[n];
            for(int i=0; i<n; i++){
                par[i]=i;
                rank[i]=0;
            }
        }

        int find(int x){
            if(par[x]==x){
                return x;
            }else{
                return par[x]= find(par[x]);
            }
        }

        void unite(int x, int y){
            x= find(x);
            y= find(y);
            if(x==y) return;

            if( rank[x] <rank[y]){
                par[x]=y;
            }else{
                par[y]=x;
                if(rank[x]==rank[y]) rank[x]++;
            }
        }

        boolean same(int x, int y){
            return find(x)==find(y);
        }

    }

}
