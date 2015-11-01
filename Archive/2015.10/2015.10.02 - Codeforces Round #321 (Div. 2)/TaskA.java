package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] ar= new int[n-1];
        int cur= in.nextInt();
        int curLength=1;
        int ans=1;
        for(int i=0; i<n-1; i++){
            int c= in.nextInt();
            if(c>=cur){
                curLength++;
                ans= Math.max(curLength,ans);
            }else{
                curLength=1;
            }
            cur=c;
        }
        out.println(ans);

    }
}
