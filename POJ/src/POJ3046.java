import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ3046{
    static InputReader in;
    static PrintWriter out;
    static int MOD=1000000;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T= in.nextInt();
        int A=  in.nextInt();
        int S= in.nextInt();
        int B= in.nextInt();
        int[] N= new int[T];
        for(int i=0; i<A; i++){
            N[in.nextInt()-1]++;
        }

        long[][] dp = new long[2][A+1];
        dp[0][0]=1;
        long ans=0;
        for(int t=0; t<T; t++){
            dp[(t+1)%2][0]=1;
            for(int j=1; j<=A; j++){
                dp[(t+1)%2][j]= (dp[t%2][j] + dp[(t+1)%2][j-1])%MOD;
                if(j-1-N[t]>=0){
                    dp[(t+1)%2][j]= (dp[(t+1)%2][j]- dp[t%2][j-1-N[t]]+MOD)%MOD;
                }
               // System.out.println("dp(" + (t+1) + ")(" + j + ")= " + dp[(t+1)%2][j]);
            }
        }
        for(int i=S; i<=B; i++){
            ans= (ans+dp[T%2][i])%MOD;
        }
        out.println(ans);
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
    }
}