package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskI {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int W= in.nextInt();
        int L= in.nextInt();
        int w= in.nextInt();
        int l =in.nextInt();
        int qw= W/w;
        int rw= W%w;
        int ql= L/l;
        int rl= L%l;
        int count= qw*ql;
        int opt1=0;
        int opt2=0;
        // option 1:
        if(rl!=0) {
            int c1 = l / rl; // we need qw times this piece, we can make c piece from each tile.
            opt1 +=(int)Math.ceil((double) qw / c1);
            opt2 += (int)Math.ceil( Math.ceil((double)W/w)/(double)c1);
        }
        if(rw!=0){
            int c2 = w / rw;
            opt1+= (int) Math.ceil( Math.ceil((double)L/l)/(double)c2);
            opt2+= (int) Math.ceil( (double)ql/c2);
        }
        count+= Math.min(opt1,opt2);
        out.println(count);
    }
}
