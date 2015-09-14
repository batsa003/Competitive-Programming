package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n=in.nextInt();
        int m =in.nextInt();
        ArrayList<Integer>[] G= new ArrayList[n];

        for(int i=0; i<n; i++){
            G[i]= new ArrayList();
        }

        for(int i=0; i<m; i++){
            int a= in.nextInt()-1;
            int b= in.nextInt()-1;
            G[a].add(b);
        }

        long ans=0;
        for(int node=0; node<n; node++) {
            long[] ways = new long[n];
            for (int neighbor : G[node]) {
                for(int k : G[neighbor]){
                    ways[k]++;
                }
            }

            for(int i=0; i<n; i++){
                if(i!=node){
                    ans+= ways[i]*(ways[i]-1)/2;
                }
            }
        }
        out.println(ans);


    }
}
