package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    //Each number can may go away because of one of the following reasons:
    // 1. Its height decreases one by one
    // 2.  One of its neighbor disappears, so that it disappears one step after its neighbor.

    // Let L be the time it goes away, depending on its left neighbor disappear time, Let R be the time it goes away because of a right neighbor
    // Consider L case. Then L[i+1]= Min( L[i]+1, height[i+1]);
    // in the R case, R[i-1]= Min( R[i]+1, height[i-1]);
    // for every height, it may disappear because of its height, or from its left side, heights starts to disappear, or from its right side, heights disappears
    // Since if it was left side, it must start from leftmost, or one of the height that disappears by itself. This is taken into account.


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] h= new int[n+2];
        for(int i=1; i<=n; i++){
            h[i]=in.nextInt();
        }
        int[] best= h.clone();
        for(int i=1; i<=n; i++){
            best[i]= Math.min(best[i-1]+1, best[i]);
        }
        for(int i=n; i>=1; i--){
            best[i]= Math.min(best[i+1]+1, best[i]);
        }
        int max=0;
        for(int i=1; i<=n; i++){
            max= Math.max(best[i],max);
        }
        out.println(max);
    }
}
