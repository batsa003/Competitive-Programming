package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String a= in.next().toLowerCase();
        String b= in.next().toLowerCase();
        if(a.equals(b)){
            out.println(0);
        }else if(a.compareTo(b)>0){
            out.println(1);
        }else{
            out.println(-1);
        }
    }
}
