import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class POJ2456 {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N= in.nextInt();
        int C=in.nextInt();
        TreeSet<Integer> set= new TreeSet();
        for(int i=0; i<N; i++){
            set.add(in.nextInt());
        }

        int low=0;
        int high=1000000001;
        WHILE:
        while((high-low)>1){
            int mid= (low+high)/2;
            int cur=Integer.MIN_VALUE;
            for(int i=0; i<C; i++) {
                cur+= (mid-1);
                if(set.higher(cur)==null){
                    high=mid;
                    continue WHILE;
                }else{
                    cur=set.higher(cur);
                }
            }
            low=mid;
        }
        out.println(low);
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
