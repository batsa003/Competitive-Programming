import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class D {
    static Scanner in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new Scanner(System.in);
        out= new PrintWriter(System.out,true);
        int N= Integer.parseInt(in.nextLine());
        int[] value= new int[51];
        int[] weight= new int[51];
        int M= 0;
        while(in.hasNext()){
            String[] s= in.nextLine().split(",");
            M++;
            int i = Integer.parseInt(s[0]);
            int v= Integer.parseInt(s[1]);
            int w= Integer.parseInt(s[2]);
            value[i-1]= v;
            weight[i-1]=w;
        }
        int[] ansMinutes= new int[N];
        int ansMaxScore = 0;
        for(int exclude=0; exclude<N; exclude++){
            for(int min=1; min<Math.min(N+1,weight[exclude]); min++){
                int ans = (int )( 1.0*value[exclude]*min/(double)weight[exclude]);
                int totalWeight= N-min;

                int[][] dp = new int[M+1][totalWeight];

                for(int i=1; i<M; i++){

                }

                // dp [M][totalWeight] add the score
            }
        }

        //solve the case there's no exclude



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