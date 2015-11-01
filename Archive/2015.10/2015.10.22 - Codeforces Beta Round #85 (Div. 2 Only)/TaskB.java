package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int N= in.nextInt();
        int x= in.nextInt();
        int y= in.nextInt();
        if(x==N/2){
            if(y==N/2 || y== N/2+1){
                out.println("NO");
            }else{
                out.println("YES");
            }
        }else if(x==N/2+1){
            if(y==N/2 || y==N/2+1){
                out.println("NO");
            }else{
                out.println("YES");
            }
        }else{
            out.println("YES");
        }
    }
}
