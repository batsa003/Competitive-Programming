package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskC {
    static ArrayList<Integer>[] G;
    static boolean[] del;
    static int[] leg;

    static void dfs(int node, int par){
        if( G[node].size()<=2){
            del[node]=true;
            for(int i=0; i<G[node].size(); i++){
                int c= G[node].get(i);
                if(c!=par){
                    dfs(c,node);
                }
            }
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        G= new ArrayList[n];
        for(int i=0; i<n; i++){
            G[i] = new ArrayList();
        }
        for(int i=0; i<(n-1); i++){
            int a= in.nextInt()-1;
            int b= in.nextInt()-1;
            G[a].add(b);
            G[b].add(a);
        }
        del = new boolean[n];
        leg= new int[n];
        for(int i=0; i<n; i++){
            if(G[i].size()==1){
                dfs(i,-1);
            }
        }

        for(int i=0; i<n; i++){
            int c=0;
            for(int j=0; j<G[i].size(); j++){
                if(del[G[i].get(j)]) c++;
            }
            c= Math.min(2,c);
            leg[i]=c;
        }

        for(int i=0; i<n; i++){
            int count=0;

            for(int j=0; j<G[i].size(); j++){
                int neighbor= G[i].get(j);
                if(!del[neighbor] && (G[neighbor].size()-leg[neighbor]>1)){
                    count++;
                }
            }
            if(count>2){
                out.println("No");
                return;
            }
        }
        out.println("Yes");


    }
}
