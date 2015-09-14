package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class TaskF {

    static int n,m,mod,zeros,ones;
    static long[][] dp;
    static boolean[][] was;

    static long dp(int x, int y){
        if(was[x][y]){
            return dp[x][y];
        }
        was[x][y]=true;
        int numberOfRowsToFill= ((n-m)- ((ones-y)+(zeros-x)*2)/2);
       // System.out.println(x + " " +y + " " + numberOfRowsToFill);
        long ans=0;
        if(numberOfRowsToFill==1){
            if(x==0 && y==2){
                ans=1;
            }
        }else{
            if(y>=2){
                ans= (ans+ dp(x,y-2)*y*(y-1)/2)%mod;
            }
            if(x>=1){
                ans= (ans + x*y*dp(x-1,y))%mod;
            }
            if(x>=2){
               ans= (ans+ x*(x-1)/2*dp(x-2,y+2))%mod;
                // System.out.println("There we go");
            }
        }
        dp[x][y]=ans;
        return ans;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
         n= in.nextInt();
         m =in.nextInt();
        mod= in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<m; i++){
            String s= in.next();
            for(int j=0; j<n; j++){
                if(s.charAt(j)=='1'){
                    ar[j]++;
                }
            }
        }
        if(m==n){
            out.println(1);
            return;
        }
        zeros=0;
        ones=0;
        for(int i=0; i<n; i++){
            if(ar[i]==0) zeros++;
            if(ar[i]==1) ones++;
        }
        dp= new long[501][501];
        was= new boolean[501][501];
        out.println(dp(zeros,ones));

    }
}
