package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskC {
    static boolean[] hasCat;
    static List<Integer>[] G;
    static boolean[] vis;
    static int ans;
    static int m;

    static void dfs(int node, int cats){
        vis[node]=true;
        if(hasCat[node]){
            cats++;
        }else{
            cats=0;
        }
        //System.out.println("Node= " + node + ", cats= " + cats);
        if(cats>m) return;
        if( G[node].size()==1 && vis[G[node].get(0)]){
            ans++;
        }
        for(int i=0; i<G[node].size(); i++){
            int c= G[node].get(i);
            if(!vis[c]){
                dfs(c,cats);
            }
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        m =in.nextInt();
        G= new List[n];
        hasCat= new boolean[n];
        for(int i=0; i<n; i++) {
            if (in.nextInt() == 1) {
                hasCat[i] = true;
            }
            G[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<n-1; i++){
            int a= in.nextInt()-1;
            int b= in.nextInt()-1;
            G[a].add(b);
            G[b].add(a);
        }
        vis = new boolean[n];
        ans=0;
        dfs(0,0);
        out.println(ans);
    }
}
