import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

@SuppressWarnings("unchecked")
public class NarrowArtGallery {
    static InputReader in;
    static PrintWriter out;
    static int inf = 1000000;


    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        while(true){
            int N= in.nextInt();
            int K =in.nextInt();
            if(N==0 && K==0) break;
            int[][] val = new int[2][N];
            for(int i=0; i<N; i++){
                val[0][i]=in.nextInt();
                val[1][i]= in.nextInt();
            }
            int[][][] dp = new int[3][N][K+1];
            int cur=0;
            for(int n=0; n<N; n++){
                cur+= val[0][n];
                cur+= val[1][n];
                for(int m=0; m<3; m++){
                    dp[m][n][0]=cur;
                }
                dp[0][n][0]-= val[1][n];
                dp[1][n][0]-= val[0][n];
            }

            for(int k=1; k<=K; k++){
                for(int n=0; n<N; n++){
                    for(int m=2; m>=0; m--){
                        if(n+1<k){
                            dp[m][n][k]=-inf;
                            continue;
                        }
                        if(n==0){
                            if(k==1){
                                int val1= val[0][0];
                                int val2= val[1][0];
                                if(m==2){
                                    dp[m][n][k]= Math.max(val1,val2);
                                }else if(m==1){
                                    dp[m][n][k]= val2;
                                }else{
                                    dp[m][n][k]= val1;
                                }
                            }
                            continue;
                        }

                        int ans=0;
                        if(m==2){
                            ans = Math.max(ans, dp[2][n - 1][k] + val[0][n] + val[1][n]);
                            ans = Math.max(ans, dp[1][n - 1][k - 1] + val[0][n]);
                            ans = Math.max(ans, dp[0][n - 1][k - 1] + val[1][n]);
                        }else{
                            ans= Math.max(ans, dp[2][n-1][k]+val[0][n]+val[1][n]);
                            ans= Math.max(ans, dp[m][n-1][k-1]+val[m^1][n]);
                        }
                        dp[m][n][k]=ans;
                    }
                }
            }
            for(int k=0; k<=K; k++){
                for(int n=0; n<N; n++){
                    for(int m=0; m<3; m++){
                        System.out.println("dp[" + m + "][" + n + "][" + k + "]=" + dp[m][n][k]);
                    }
                }
            }

            out.println(dp[2][N - 1][K]);
        }

        out.close();
        System.exit(0);

    }












    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}