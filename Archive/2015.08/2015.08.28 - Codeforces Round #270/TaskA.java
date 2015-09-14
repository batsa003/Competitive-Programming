package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        if(n%4==0){
            out.println((n/2) + " " + (n/2));
        }else if(n%4==1){
            out.println( (n-9) + " " + 9);
        }else if(n%4==2){
            out.println( ((n-2)/2) + " " + ((n+2)/2));
        }else{
            out.println( (n-9) + " " + 9);
        }
    }
}
