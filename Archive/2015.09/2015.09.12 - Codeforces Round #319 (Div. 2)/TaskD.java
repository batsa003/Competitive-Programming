package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] p = new int[n+1];
        for(int i=1; i<=n; i++){
            p[i]=in.nextInt();
        }

        boolean[] vis = new boolean[n+1];
        int[] size= new int[n+1];
        for(int i=1; i<=n; i++){
            if(p[i]==i){
                out.println("YES");
                for(int j=1; j<=n; j++){
                    if(j!=i){
                        out.println(i + " " + j);
                    }
                }
                return;
            }
        }
        for(int node=1; node<=n; node++){
            if(!vis[node]) {
                int count = 1;
                int cur = p[node];
                vis[node] = true;
                while (cur != node) {
                    vis[cur] = true;
                    count++;
                    cur=p[cur];
                }
                size[cur]=count;
                if(count>1 && count%2==1){
                    out.println("NO");
                    return;
                }
            }
        }
        for(int i=1; i<=n; i++){
            if(size[i]==2){
                int u=i;
                int pu= p[u];
                out.println("YES");
                out.println(u + " " + pu);
                for(int j=1; j<=n; j++){
                    if(j!=u && j!=pu && size[j]>1){
                        out.println(u + " " + j);
                        int cur=p[j];
                        int ind=1;
                        while(cur!=j){
                            if(ind%2==1){
                                out.println(p[u] + " " + cur);
                            }else{
                                out.println(u + " " + cur);
                            }
                            cur=p[cur];
                            ind++;
                        }
                    }
                }
                return;
            }
        }
        out.println("NO");
    }
}
