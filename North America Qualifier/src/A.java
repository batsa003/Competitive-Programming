import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class A {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int t=1;
        while(true){
            int n= in.nextInt();
            if(n==0){
                break;
            }
            int[] c= new int[n+1];
            int sum=0;
            for(int i=1; i<=n; i++){
                c[i]=in.nextInt();
                sum+=c[i];
            }
            long[][] dp = new long[n+1][31];
            Arrays.fill(dp[1], 1);
            // i numbers sum evaluate to j
            for(int i=1; i<=n; i++){
                for(int j=0; j<31; j++){
                    for(int k=0; k<=j; k++){
                        dp[i][j]+= dp[i-1][k];
                    }
                }
            }
            long[][] dp2= new long[n+1][31];
            // dp2[i][s]= i, i+1, ... , n critics evaluate to exactly sum s where it is less than c[1], ... , c[n]
            for(int i=n; i>=1; i--){
                for(int s=0; s<31; s++){
                    if(i==n){
                        if(s<=c[i]) dp2[i][s]= 1;
                        continue;
                    }

                    for(int k=0; k<=Math.min(c[i]-1,s); k++){
                        dp2[i][s]+= dp[n-i][s-k];
                    }
                    if(s>=c[i]){
                        dp2[i][s] += dp2[i + 1][s - c[i]];
                    }
                }
            }
            long ans=0;
            for(int s=0; s<sum; s++){
                ans+= dp[n][s];
            }
            ans+= dp2[1][sum];
            out.println("Case " + t + ": "  +ans);
            t++;
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
    }
}