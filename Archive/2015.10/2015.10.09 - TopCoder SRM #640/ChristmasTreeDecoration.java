package created;

public class ChristmasTreeDecoration {
    public int solve(int[] col, int[] x, int[] y) {
        int n= col.length;
        DSU dsu = new DSU(n);
        for(int i=0; i<x.length; i++){
            if(col[x[i]-1]!=col[y[i]-1]){
                dsu.unite(x[i]-1,y[i]-1);
            }
        }
        int count=0;
        for(int i=0; i<n; i++){
           if(i==dsu.find(i)){
               count++;
           }
        }

        return count-1;
    }
}

class DSU {
    int[] par;
    int[] rank;
    DSU(int n){
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
