package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n=in.nextInt();
        int ans=0;
        if(n<=2){
            out.println(0);
            return;
        }else {
            int cur=0;
            for(int i=3; i<= 15000; i++){
                cur+=i;
                if(cur>n){
                    out.println(i-3);
                    return;
                }
            }

        }
    }
}
