package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {




    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int t = in.nextInt();
        if(n>=2){
            String s= Integer.toString(t);
            while(s.length()<n){
                s+="0";
            }
            out.println(s);
        }else{
            if(t<=9){
                out.println(t);
            }else{
                out.println(-1);
            }
        }
    }
}
