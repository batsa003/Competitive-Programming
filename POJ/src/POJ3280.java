import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.Arrays;
        import java.util.StringTokenizer;


public class POJ3280 {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N =in.nextInt();
        int M =in.nextInt();
        char[] s= in.next().toCharArray();
        int[] add= new int[26];
        int[] del = new int[26];
        int[][] dp = new int[M][M];
        for(int i=0;i<N; i++){
            char c= in.next().charAt(0);
            add[c-'a']= in.nextInt();
            del[c-'a']= in.nextInt();
        }

        for(int l=1; l<M; l++){
            for(int i=0; i<M-l; i++){
                int j= i+l;
                if(s[i]==s[j]){
                    dp[i][j]=dp[i+1][j-1];
                }else {
                    int ans = dp[i + 1][j] + add[s[i] - 'a'];
                    ans = Math.min(dp[i][j - 1] + add[s[j] - 'a'], ans);
                    ans = Math.min(dp[i][j - 1] + del[s[j] - 'a'], ans);
                    ans = Math.min(ans, dp[i + 1][j] + del[s[i] - 'a']);
                    dp[i][j] = ans;
                }
            }
        }
        out.println(dp[0][M-1]);

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