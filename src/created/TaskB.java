package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    static long mod=1000000007;

    static long exp(long a, int e){
        long ans=1;

        while(e>0){
            if((e&1)!=0){
                ans = (ans*a)%mod;
            }
            a*=a;
            a%=mod;
            e=e>>1;
        }
        return ans;

    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        out.println((exp(3,3*n)-exp(7,n)+mod)%mod);

    }
}
