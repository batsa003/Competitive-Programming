package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<n; i++){
            ar[i]=in.nextInt();
        }
        TreeSet<Integer> locals= new TreeSet();

        for(int i=0; i<n; i++){
            if(i==0 || i==(n-1)){
                locals.add(i);
                continue;
            }

            if( (ar[i-1] <= ar[i] && ar[i] >= ar[i+1]) || (( ar[i-1] >= ar[i] )&& (ar[i]<=ar[i+1]))){
                locals.add(i);
            }
        }
        long[] dp = new long[n];
        for(int i=1; i<n; i++){
            int loc= locals.lower(i);

            if(loc-1>=0){
                dp[i]= Math.max(dp[i], dp[loc-1]+Math.abs(ar[loc]-ar[i]));
            }else{
                dp[i]= Math.max(dp[i], Math.abs(ar[loc]-ar[i]));
            }
            dp[i]= Math.max(dp[i], dp[loc] + Math.abs(ar[loc+1]-ar[i]));
           // out.println("dp[" + i + "]=" + dp[i]);
        }

        out.println(dp[n-1]);

    }
}
