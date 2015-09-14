package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s= in.next();
        int n= s.length();

        long min1=0;
        int cur=0;
        for(int i=0; i<n; i++){
            if(s.charAt(i)=='1'){
                min1+= (i-cur);
                cur++;
            }
        }
        long min2=0;
        cur=0;
        for(int i=0; i<n; i++){
            if(s.charAt(i)=='2'){
                min2+= (i-cur);
                cur++;
            }
        }
        out.println(Math.min(min1,min2));
    }
}
