package created;

import java.util.ArrayList;

public class WalkOverATree {
    static ArrayList<Integer>[] G;
    //static int[] depth;
    static int max=0;

    void dfs(int node, int depth){
        //System.out.println("node,depth" + node + " " + depth);
        max= Math.max(depth,max);
        for(int i=0; i<G[node].size(); i++){
            int child= G[node].get(i);
            dfs(child,depth+1);
        }
    }

    public int maxNodesVisited(int[] parent, int L) {
        int n= parent.length+1;
        G = new ArrayList[n];
        for(int i=0; i<n; i++){
            G[i]= new ArrayList<Integer>();
        }
        for(int i=0; i<(n-1); i++){
            G[parent[i]].add(i+1);
        }
        max=0;
        dfs(0,0);
       // System.out.println("max=" + max);
        if(L<=max){
            return (L+1);
        }else{
            int ans=max+1;
            L-=max;
            ans+= L/2;
            ans= Math.min(n,ans);
           // System.out.println(ans);
            return ans;
        }
    }
}
