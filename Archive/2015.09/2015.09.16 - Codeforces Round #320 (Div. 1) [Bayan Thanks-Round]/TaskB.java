package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k= in.nextInt();
        int x= in.nextInt();
        long[] a = new long[n];
        long[] pre= new long[n];
        long cur=0;
        for(int i=0; i<n; i++){
            a[i]= in.nextInt();
            cur|=a[i];
            pre[i]=cur;
        }
        cur=0;
        long[] suf= new long[n];
        for(int j=(n-1); j>=0; j--){
            cur|=a[j];
            suf[j]=cur;
        }
        long ans=0;
        for(int num=0; num<n; num++){
            long c=a[num];
            for(int j=0; j<k; j++){
                c*=x;
            }
            if(num>=1) c|=pre[num-1];
            if(num<(n-1)) c|= suf[num+1];
            ans = Math.max(ans,c);
        }
        out.println(ans);
    }
}
