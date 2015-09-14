package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeSet;

public class TaskC {
    static int[] from; // index of the last letter of the P, starting from from.
    static String s;
    static String P;
    static int n;
    static int p;
    static int INF=1000000;

    void fill(){
        TreeSet<Integer>[] chars= new TreeSet[26];
        for(int i=0; i<26; i++){
            chars[i]= new TreeSet();
        }

        for(int i=0; i<n; i++){
            chars[ s.charAt(i)-'a'].add(i);
        }
        Arrays.fill(from, -1);
        FOR:
        for(int i=(n-1); i>=0; i--){
            if(s.charAt(i)!= P.charAt(0)){
                continue;
            }
            int cur=i;
            for(int j=1; j<p; j++){
                if( chars[P.charAt(j)-'a'].higher(cur)==null){
                    continue FOR;
                }else{
                    cur=chars[P.charAt(j)-'a'].higher(cur);
                }
            }
            from[i]=cur;
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        s= in.next();
         P= in.next();
        n= s.length();
        p = P.length();

        from= new int[n];
        fill();

        int[][] dp = new int[n/p+1][n];
        for(int i=1; i<dp.length; i++){
            Arrays.fill(dp[i],INF);
        }

        for(int m=1; m<=(n/p); m++){
            for(int j=(n-1); j>=0; j--){
                if(s.charAt(j)!=P.charAt(0)){
                    if(j+1<n){
                        dp[m][j]=dp[m][j+1];
                    }
                }else{
                    int one= from[j];
                    if(one==-1){
                        if(j+1<n){
                            dp[m][j]=dp[m][j+1];
                        }
                    }else{
                        if(j+1<n){
                            dp[m][j]= Math.min(dp[m][j],dp[m][j+1]);
                        }
                        // number of removal of chars;
                        int pos= one-j+1-p;
                        if(one==n-1 && m!=1){
                            continue;
                        }
                        if(m==1){
                            dp[m][j]= Math.min(pos,dp[m][j]);
                        }else{
                            pos+= dp[m-1][one+1];
                            dp[m][j]= Math.min(pos,dp[m][j]);
                        }
                    }
                }
            }
        }

        /*for(int i=1; i<=(n/p); i++){
            // Min number of removal to get i pattern
            dp[i][0]
        }*/
        int max=0;
        for(int i=0; i<dp.length; i++){
            if(dp[i][0]<INF){
                max=Math.max(i,max);
            }
        }
       /* for(int i=0; i<dp.length; i++){
            System.out.println("dp[" + i + " ][0]=" + dp[i][0]);
        }
        for(int i=0; i<from.length; i++){
            System.out.println(from[i]);
        }
*/
        /*int j=1;
        int[] mins= new int[max+1];
        for(int i=0; i<mins.length; i++){
            mins[i]= dp[i][0];
        }*/
        for(int i=0; i<=n; i++){
            if(i> (n-max*p)){
                int j=i;
                j-= (n-max*p);
                int k= (int) Math.ceil((double)j/p);
                out.print( (max-k) + " " );
            }else{
                int j=0;
                while(dp[j][0]<=i){
                    j++;
                    if(j==(max+1)){
                        break;
                    }
                }
                if(j==(max+1)) {
                    out.print(max + " ");
                }
                else{
                    out.print((j-1) + " ");
                }
            }
        }

    }
}
