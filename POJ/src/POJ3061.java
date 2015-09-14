import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class POJ3061{
    static InputReader in;
    static PrintWriter out;
    static class Pair implements Comparable<Pair>{
        int val,ind;

        Pair(int v, int i){
            val=v;
            ind=i;
        }

        @Override
        public int compareTo(Pair o) {
            return val-o.val;
        }
    }

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T= in.nextInt();
        StringBuilder sb= new StringBuilder();
        for(int count=0; count<T; count++){
            int N=in.nextInt();
            int S= in.nextInt();
            TreeSet<Pair> set= new TreeSet();
            int sum=0;
            for(int i=1; i<=N; i++){
                sum+=in.nextInt();
                set.add(new Pair(sum,i));
            }

            int min=Integer.MAX_VALUE;
            for(Pair p: set){
                int val=p.val;
                Pair low = set.lower(new Pair((val - S+1),0));
                if(low!=null){
                    min= Math.min(min, p.ind-low.ind);
                }
            }
            if(min!= Integer.MAX_VALUE){
                out.println(min);
            }else{
                out.println(0);
            }
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
