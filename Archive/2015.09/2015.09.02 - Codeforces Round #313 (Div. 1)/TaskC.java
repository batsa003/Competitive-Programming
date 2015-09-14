package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class TaskC {
    static long mod=1000000007;
    static long[] fac,inv,invfac;

    static long mul(long a, long b){
        return (a*b)%mod;
    }

    public static long binomialCoefficient(int n, int m, long mod) {
        return mul(mul(fac[n],invfac[m]),invfac[n-m]);
    }
    static long ways(int x1, int y1, int x2, int y2){
        if(x1<=x2 && y1<=y2){
            int a= (x2-x1);
            int b= (y2-y1);
            return binomialCoefficient(a+b,a,mod);
        }else{
            return 0;
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int h= in.nextInt();
        int w= in.nextInt();
        int n=in.nextInt();
        Pair[] B= new Pair[n+1];
        for(int i=0; i<n; i++) {
            B[i] = new Pair(in.nextInt(), in.nextInt());
        }
        B[n]= new Pair(h,w);
        fac= new long[h+w+5];
        fac[0]=fac[1]=1;
        for(int i=2; i<fac.length; i++){
            fac[i]= (fac[i-1]*i)%mod;
        }

        invfac= new long[h+w+5];
        BigInteger modB= BigInteger.valueOf(mod);
        for(int i=0; i<invfac.length; i++){
            invfac[i] = BigInteger.valueOf(fac[i]).modInverse(modB).intValue();
        }

        Arrays.sort(B);
        long[] dp= new long[n+1];
        dp[0]= ways(1,1,B[0].first,B[0].second);
        for(int i=1; i<=n; i++){
            long ans= ways(1,1,B[i].first,B[i].second);
            for(int j=0; j<i; j++){
                ans=  (ans- (dp[j]*ways(B[j].first,B[j].second,B[i].first,B[i].second))%mod+mod)%mod;
            }
            dp[i]=ans;
        }
        out.println(dp[n]);
    }

    static class Pair implements Comparable<Pair>{
        int first,second;

        Pair(int f, int s){
            first=f;
            second=s;
        }


        @Override
        public int compareTo(Pair o) {
            if(first!=o.first){
                return first-o.first;
            }else{
                return second-o.second;
            }
        }
    }
}
