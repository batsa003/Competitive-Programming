import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class POJ3616 {
    static InputReader in;
    static PrintWriter out;

    static class Interval implements Comparable<Interval>{
        int start,end,eff;

        Interval(int s, int ee, int ef){
            start=s;
            end=ee;
            eff=ef;
        }


        @Override
        public int compareTo(Interval o) {
            return end-o.end;
        }
    }

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N =in.nextInt();
        int M =in.nextInt();
        int R= in.nextInt();
        ArrayList<Interval> intvals= new ArrayList();
        for(int i=0; i<M; i++){
            intvals.add(new Interval(in.nextInt(),in.nextInt(),in.nextInt()));
        }
        Collections.sort(intvals);
        long[] dp = new long[N+1];
        int cur=0;
        for(int i=1; i<=N; i++){
            dp[i]= Math.max(dp[i],dp[i-1]);
            while( cur<M && intvals.get(cur).end==i){
                int start= intvals.get(cur).start;
                int eff= intvals.get(cur).eff;
                if(start-R<0){
                    dp[i]= Math.max(dp[i],eff);
                }else {
                    dp[i] = Math.max( dp[start-R]+eff,dp[i]);
                }
                cur++;
            }
        }
        out.println(dp[N]);



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