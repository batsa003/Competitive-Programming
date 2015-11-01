package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a= in.nextInt()+1;
        int b= 1;
        while(true){
            if(Integer.toString(a).contains("8")){
                out.println(b);
                return;
            }else{
                b++;
                a++;
            }
        }
    }
}
