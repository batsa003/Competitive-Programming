import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ1742 {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        while(true) {
            int n= in.nextInt();
            int m = in.nextInt();
            if(n==0 && m==0){
                break;
            }
            int[] a= new int[n];
            int[] c= new int[n];
            for(int i=0; i<n; i++){
                a[i]=in.nextInt();
            }
            for(int i=0; i<n; i++){
                c[i]= in.nextInt();
            }
            int[] dp = new int[m + 1];
            Arrays.fill(dp, -1);
            dp[0]=0;
            for(int i=0; i<n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (dp[j] >= 0) {
                        dp[j] = 0;
                        continue;
                    }
                    if (j >= a[i]) {
                        if (dp[j - a[i]] != c[i] && dp[j - a[i]] >= 0) {
                            dp[j] = dp[j - a[i]] + 1;
                        }
                    }
                }
            }
            int count=0;
            for(int i=1; i<=m; i++){
                if(dp[i]>=0){
                    count++;
                }
            }
            out.println(count);
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