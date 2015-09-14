package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n= in.nextLong();
        if(n%2==1){
            out.println( -1L*(n+1)/2);
        }else{
            out.println( n/2);
        }
    }
}
