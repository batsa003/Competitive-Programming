package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a= in.nextInt();
        int b = in.nextInt();
        if(a<b){
            out.println(-1);
        }else if(a==b){
            out.println(b);
        }else {
            double ans = (double)(a - b) / (2.0 * (int) (((double) (a - b) / (b*2.0))));
            ans = Math.min(ans, (double)(a + b) / (2.0 * (int) ((a + b) / (2.0*b))));
            out.println(ans);
        }
    }
}
