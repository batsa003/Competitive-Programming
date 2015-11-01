package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s= in.next();
        int count=0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='4' || s.charAt(i)=='7'){
                count++;
            }
        }
        if(count==4 || count==7){
            out.println("YES");
        }else{
            out.println("NO");
        }
    }
}
