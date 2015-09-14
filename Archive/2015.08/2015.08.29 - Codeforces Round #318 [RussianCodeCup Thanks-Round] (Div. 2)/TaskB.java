package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int m =in.nextInt();
        int[] deg= new int[n];
        ArrayList<Integer>[] G= new ArrayList[n];
        for(int i=0; i<n; i++) G[i]=new ArrayList();
        for(int i=0; i<m; i++){
            int a= in.nextInt()-1;
            int b=in.nextInt()-1;
            G[a].add(b);
            G[b].add(a);
            deg[a]++;
            deg[b]++;
        }
        int min= 1000000;
        for(int i=0; i<n; i++){
            for(int j=0; j<G[i].size(); j++){
                for(int k=(j+1); k<G[i].size(); k++){
                    int a= G[i].get(j);
                    int b= G[i].get(k);
                    if(G[a].contains(b)){
                        min= Math.min( deg[i]+deg[a]+deg[b]-6,min);
                    }
                }
            }
        }
        if(min==1000000){
            out.println(-1);
        }else{
            out.println(min);
        }
    }
}
