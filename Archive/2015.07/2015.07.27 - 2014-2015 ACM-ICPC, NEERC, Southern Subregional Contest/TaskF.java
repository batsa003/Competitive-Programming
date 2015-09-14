package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k =in.nextInt();
        int[] ar = new int[n+1];
        int sum=0;
        for(int i=1; i<=n; i++){
            ar[i]=in.nextInt();
            sum+=ar[i];
        }
        if(k >= Math.ceil((double)n/2.0)){
            out.println(sum);
            return;
        }

        int[] pref= new int[n+1];
        int[] suf= new int[n+1];//max

        int cur=0;
        for(int i=1; i<=n; i++){
            cur+= ar[i];
            if(i>k){
                 cur-=ar[i-k];
            }
            if(i>=k){
                pref[i-k+1]= cur;
            }
        }

        int max=Integer.MIN_VALUE;
        for(int i=(n-k+1); i>=1; i--){
            max= Math.max(max, pref[i]);
            suf[i]=max;
        }
        int ans=0;
        for(int i=1; i<=(n-2*k+1); i++){
            ans= Math.max(ans, pref[i] + suf[i+k]);
        }

        out.println(ans);
    }
}
