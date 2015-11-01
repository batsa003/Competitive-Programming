package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] h= new int[n];
        for(int i=0; i<n; i++){
            h[i]=in.nextInt();
        }
        int[] max = new int[n];
        max[n-1]= h[n-1];
        for(int i=n-2; i>=0; i--){
            max[i] = Math.max(h[i], max[i+1]);
        }

        for(int i=0; i<(n-1); i++){
            if(h[i] > max[i+1]){
                out.print("0 ");
            }else{
                out.print((max[i+1]-h[i]+1) + " ");
            }
        }
        out.print(0);
    }
}
