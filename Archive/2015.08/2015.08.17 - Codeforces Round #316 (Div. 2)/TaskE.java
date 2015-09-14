package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class TaskE {
    static char[][] field;
    static int n,m;
    static int mod=1000000007;




    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n= in.nextInt();
        m =in.nextInt();
        field= new char[n+1][m+1];
        for(int i=1; i<=n; i++){
            String s= in.next();
            for(int j=0; j<m; j++){
                field[i][j+1]= s.charAt(j);
            }
        }
        long[][][] dp = new long[2][501][501];

        for(int x=1; x<=n; x++){
            for(int i=n; i>=1; i--){
                for(int j=m; j>=1; j--) {
                    int y = m - (i - 1 + j - 1 - (n - x));
                    long ans = 0;
                    if (y <= 0 || y > m) {
                        ans=0;
                    }else if (field[i][j] != field[x][y]) {
                        ans=0;
                    }else if(i>x || j > y){
                        ans=0;
                    }else if (i == x && y == j) {
                        ans = 1;
                    } else if ((i == x && Math.abs(y - j) == 1) || (y == j && Math.abs(i - x) == 1)) {
                        ans = 1;
                    } else {
                        if(i+1<=n) {
                            ans+= dp[x%2][i+1][j];
                            ans+= dp[(x+1)%2][i+1][j];
                        }
                        if(j+1<=m) {
                            ans+= dp[x%2][i][j+1];
                            ans+= dp[(x+1)%2][i][j+1];
                        }
                        ans= ans%mod;
                    }
                    dp[x%2][i][j]=ans;
                }
            }
        }
        out.println(dp[n%2][1][1]);
    }
}
