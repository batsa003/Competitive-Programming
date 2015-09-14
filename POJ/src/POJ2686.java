import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class POJ2686 {
    static InputReader in;
    static PrintWriter out;
    static List<Integer>[] adj;
    static double INF=1000000000;


    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        StringBuilder sb= new StringBuilder();
        whiile:
        while(true) {
            int n = in.nextInt(); //at most n roads
            int m = in.nextInt();
            int p = in.nextInt();
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            if(n==0 && m==0 && p==0){
                break whiile;
            }
            int[] t = new int[n];
            for (int i = 1; i <= n; i++) {
                t[i-1] = in.nextInt();
            }
            adj = new ArrayList[m];
            for (int i = 1; i <= m; i++) {
                adj[i-1] = new ArrayList<Integer>();
            }

            int[][] w = new int[31][31];
            //List<LinkedList<Integer>>[][][] dp = new List[m + 1][m + 1][n + 1];
            double[][] dp = new double[1<<n][m];
            for(int i=0; i<(1<<n); i++){
                Arrays.fill(dp[i],INF);
            }

            for (int c = 0; c < p; c++) {
                int x = in.nextInt()-1;
                int y = in.nextInt()-1;
                int z = in.nextInt();
                adj[x].add(y);
                adj[y].add(x);
                w[x][y] = z;
                w[y][x] = z;
            }
            dp[((1<<n)-1)][a]=0;
            for(int S=(1<<n)-1; S>=0; S--){
                for(int u=0; u<m; u++){
                    if(dp[S][u]!=INF){

                        for(int i=0; i<n; i++){
                            //if( (S>>i)==1){
                            if((S & (1<<i)) !=0){
                                for(int v : adj[u]){
                                    dp[S & ~(1<<i)][v]= Math.min(dp[S & ~(1<<i)][v], dp[S][u] + (double)w[u][v]/t[i]);
                                }
                            }
                        }
                    }
                }
            }

            double ans=INF;
            for(int i=0; i<(1<<n); i++){
                ans= Math.min(ans,dp[i][b]);
            }

            if(ans!= INF) {
                sb.append(ans + "\n");
            }else{
                sb.append("IMPOSSIBLE\n");
            }
        }
        out.println(sb.toString());
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
