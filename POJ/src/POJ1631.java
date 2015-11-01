import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class POJ1631{
    static InputReader in;
    static PrintWriter out;

    private static int[] LIS(int n, int[] a) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;
        int cn = 1;
        int[] longest = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = cn;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (dp[mid] >= a[i]) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            longest[i] = l + 1;
            if (r + 1 > cn) cn = r + 1;
            dp[r] = a[i];
        }
        return longest;
    }

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T=in.nextInt();
        for(int c=0; c<T; c++){
            int n= in.nextInt();
            int[] p = new int[n];
            for(int i=0; i<n; i++ ){
                p[i]=in.nextInt();
            }
            int[] ans = LIS(n,p);
            int max=0;
            for(int i=0; i<n; i++){
                max= Math.max(ans[i],max);
            }
            out.println(max);
        }

        out.close();
        System.exit(0);

    }

    static class Pair implements Comparable<Pair>{
        int x,y;
        Pair(int xx, int yy){
            x=xx;
            y=yy;
        }


        @Override
        public int compareTo(Pair o) {
            if(x!=o.x){
                return x-o.x;
            }else{
                return y-o.y;
            }
        }
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