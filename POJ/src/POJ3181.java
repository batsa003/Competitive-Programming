import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ3181 {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N =in.nextInt();
        int K = in.nextInt();
        BigInteger[][] dp = new BigInteger[2][N+1];
        Arrays.fill(dp[0], new BigInteger("0"));
        dp[0][0]= new BigInteger("1");
        for(int i=0; i<K; i++){
            for(int j=0; j<=N; j++){
                dp[(i+1)%2][j]= dp[i%2][j];
                if(j>=(i+1)){
                    dp[(i+1)%2][j]= dp[(i+1)%2][j].add(dp[(i+1)%2][j-(i+1)]);
                }
                //System.out.println("dp[" + (i+1) +"][" + j + "]= " + dp[(i+1)%2][j]);
            }
        }
        out.println(dp[K%2][N].toString());

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