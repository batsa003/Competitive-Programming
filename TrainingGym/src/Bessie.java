/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Bessie {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T= in.nextInt();
        int W= in.nextInt();
        int[] ar= new int[T+1];
        for(int i=1; i<=T; i++){
            ar[i]= in.nextInt();
        }
        int[][][] dp = new int[2][W+1][T+1];
        int cur=0;
        for(int i=1; i<=T; i++){
            if(ar[i]==1){
                cur++;
            }else{
                cur=0;
            }
            dp[0][0][i]= Math.max(dp[0][0][i-1],cur);
        }

        for(int i=1; i<=W; i++){
            for(int j=1; j<=T; j++){
                dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);

                for(int k=j-1; k>=1; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + (j - k));
                }
            }

        }
        out.println(dp[W][T]);



        out.close();
        System.exit(0);

    }
    static
    class InputReader {
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
    }
}*/
