package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    static int mod= 1000000007;

    static long expo(long a, int b){
        if (b==1)
            return a%mod;
        if (b==2)
            return (a*a)%mod;

        if (b%2==0){
            return expo(expo(a,b/2),2)%mod;
        }
        else{
            return (a*expo(expo(a,(b-1)/2),2))%mod;
        }
    }


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        long[][] choose= new long[n+1][n+1];
        long[] dp =new long[n+1];
        if(n==1){ out.println(1); return;}
        if(n==2){ out.println(3); return;}


        for(int i=1; i<=n; i++){
            choose[i][0]=choose[i][i]=1;
            for(int j=1; j<i; j++){
                choose[i][j]= (choose[i-1][j]+choose[i-1][j-1])%mod;
            }
        }
        dp[2]=1;
        dp[0]=1;
        dp[1]=0;
        dp[3]=1;
        for(int i=4; i<=n; i++){
            for(int j=1; j<=(i-1); j++){
                dp[i]= (dp[i] + (choose[i-1][j])*dp[i-1-j])%mod;
            }
        }

        long ans=1;
        for(int i=1; i<=n; i++){
            ans= (ans*2)%mod;
        }

        ans= (ans-1+mod)%mod;
        for(int i=1; i<=(n-1); i++){
            ans =  (ans+(((choose[n][i]* dp[n-i])%mod)*(expo(2,i)+mod-1)))%mod;
        }

        out.println(ans);
    }
}
