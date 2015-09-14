import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Sumsets{
    static InputReader in;
    static PrintWriter out;
    static int add(int a, int b){
        a+=b;
        if(a>=mod){
            a-=mod;
        }
        return a;
    }

    static int mod=1000000000;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N= in.nextInt();
        int[][] dp = new int[21][N+1];
        for(int i=0; i<=N; i++){
            dp[0][i]=1;
        }

        for(int j=1; j<=20; j++){
            for(int i=0; i<=N; i++){
                dp[j][i]= add(dp[j][i], dp[j-1][i]);
                if(i >= (1<<j)){
                    dp[j][i] = add(dp[j][i],dp[j][i-(1<<j)]);
                }
            }
        }
        out.println(dp[20][N]);

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