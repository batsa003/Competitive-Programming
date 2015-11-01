package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class TaskD {
    static long mod =1000000007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        long L = in.nextLong();
        int K =in.nextInt();
        int l= (int) Math.min(L,n*K);
        int[] a= new int[n];
        int[] A= new int[n];
        HashMap<Integer,Integer> map = new HashMap();
        HashMap<Integer,Integer> map2= new HashMap();
        for(int i=0; i<n; i++){
            A[i]=in.nextInt();
            a[i]=A[i];
        }
        Arrays.sort(a);
        for(int i=0; i<n; i++){
            map.put(a[i], i);
        }
        for(int i=n-1; i>=0; i--){
            map2.put(a[i],i);
        }
        long[][] dp = new long[n][K+1];
        for(int i=0; i<n; i++){
            dp[i][0]=1;
            dp[i][1]=i+1;
        }
        main:
        for(int k=2; k<=K; k++){
            for(int i=0; i<n; i++){
                if( n*(k-1)>=l){
                    break main;
                }

                if(i==0){
                    int j= i;
                    while(j+1<n && a[j+1]==a[j]){
                        j++;
                    }
                    dp[0][k] = dp[j][k-1];
                    continue;
                }
                if(a[i]==a[i-1]){
                    dp[i][k]=(dp[i-1][k] + dp[ map2.get(a[i])][k-1])%mod;
                    continue;
                }
                int j= i;
                while(j+1<n && a[j+1]==a[j]){
                    j++;
                }
                dp[i][k]= (dp[i-1][k] + dp[j][k-1])%mod;
            }
        }
        long t = (L/n);
        long ans=0;
        long s=0;
        if(L%n==0){
            s=t;
        }else{
            s=t+1;
        }

        for(int k=1; k<=Math.min(K, s); k++){
            ans = (ans+(dp[n-1][k]*(t-k+1)))%mod;
            for(int i=0; i<L%n; i++){
                ans = (ans + dp[map.get(A[i])][k-1])%mod;
            }
        }
        out.println(ans);

    }
}
