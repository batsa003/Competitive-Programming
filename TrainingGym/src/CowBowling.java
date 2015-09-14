import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CowBowling {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N= in.nextInt();
        int[][] dp = new int[N+1][N+1];
        int[][] f= new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=i; j++){
                f[i][j]=in.nextInt();
            }
        }

        for(int i=N; i>=1; i--){
            for(int j=1; j<=i; j++){
                if(i==N){
                    dp[i][j]=f[i][j];
                }else{
                    dp[i][j]= f[i][j]+ Math.max(dp[i+1][j],dp[i+1][j+1]);
                }
            }
        }
        out.println(dp[1][1]);

        out.close();
        System.exit(0);

    }

}
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