package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskF {
    static int[] x,y;
    static String[] sign;
    static int n;
    static int k;
    static long[][] dp;

    boolean check(int a, int b, int i, int j){

        for(int c=0; c<k; c++){
            if(x[c]!=a && x[c]!=b && y[c]!=a && y[c]!=b) continue;

            long val1=0;
            long val2=0;
            if(x[c]==a || x[c]==b){
                val1=1;
            }else if( i<=x[c] && x[c]<=j){
                val1=2;
            }

            if(y[c]==a || y[c]==b){
                val2=1;
            }else if( i<=y[c] && y[c]<=j){
                val2=2;
            }
            String s= sign[c];
            if(val1 >val2){
                if(s.equals("<") || s.equals("=") || s.equals("<=")){
                    return false;
                }
            }else if(val1 < val2){
                if(s.equals(">") || s.equals(">=") || s.equals("=")){
                    return false;
                }
            }else{
                if(!s.equals(">")||s.equals("<") ){
                    return false;
                }
            }
        }
       // System.out.println("Check a,b,i,j true:" + a + " " + b + " " + i + " " + j);
        return true;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n=in.nextInt();
        k =in.nextInt();
        x= new int[k];
        sign= new String[k];
        y= new int[k];
        for(int i=0; i<k; i++){
            x[i]=in.nextInt();
            sign[i]=in.next();
            y[i]=in.nextInt();
        }

        dp = new long[36][36];
        dp[1][2*n]=1;
        for(int i=1; i<=(2*n-1); i++){
            for(int j= (2*n); j>=(i+1); j--){
                if((j-i)%2==0) continue;

                if(i>2){
                    int a=i-2;
                    int b=i-1;
                    if( check(a,b,i,j)) dp[i][j]+=dp[i-2][j];
                }
                if(i>1 && j<(2*n)){
                    if( check(i-1,j+1,i,j)) dp[i][j]+=dp[i-1][j+1];
                }
                if(j<(2*n-1)) {
                    if (check(j + 1, j + 2, i, j)) dp[i][j] += dp[i][j + 2];

                }
                System.out.println("dp[" + i + "][" + j +"]=" + dp[i][j]);
            }
        }
        /*for(int i=1; i<=2*n; i++){
            for(int j=i; j<=2*n; j++){
                System.out.println("dp[" + i + "][" + j +"]=" + dp[i][j]);
            }
        }*/

        long ans=0;

        I:
        for(int i=1; i<=(2*n-1); i++){
            for(int j=0; j<k; j++) {
                if ((x[j] == i && y[j] == i + 1) || (x[j] == i + 1 && y[j] == i)) {
                    if (sign[j].equals(">") || sign[j].equals("<")) {
                        continue I;
                    }
                }
            }
            ans+=dp[i][i+1];
            System.out.println(i + " " + ans);
        }
        out.println(ans);
    }
}
