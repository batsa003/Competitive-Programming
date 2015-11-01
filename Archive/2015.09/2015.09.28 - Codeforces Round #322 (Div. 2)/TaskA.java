package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a= in.nextInt();
        int b= in.nextInt();
        out.println(Math.min(a,b) + " " + (Math.max(a,b)-Math.min(a,b))/2);
    }
}
