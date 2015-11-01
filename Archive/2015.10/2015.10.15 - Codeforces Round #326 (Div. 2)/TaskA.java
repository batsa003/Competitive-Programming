package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int min = Integer.MAX_VALUE;
        int tot=0;
        for(int i=0; i<n; i++){
            int a= in.nextInt();
            int p = in.nextInt();
            min = Math.min(p,min);
            tot+= a*min;
        }
        out.println(tot);

    }
}
