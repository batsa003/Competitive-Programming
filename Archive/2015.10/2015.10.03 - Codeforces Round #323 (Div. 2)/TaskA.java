package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        boolean[] hor= new boolean[n];
        boolean[] ver = new boolean[n];
        int ans=0;
        for(int i=1; i<=n*n; i++){
                int x= in.nextInt()-1;
                int y= in.nextInt()-1;
                if(!hor[x] && !ver[y]){
                    hor[x]=true;
                    ver[y]=true;
                    out.print(i + " ");
                }
        }

    }
}
