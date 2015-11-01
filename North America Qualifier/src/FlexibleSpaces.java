import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class FlexibleSpaces {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int w= in.nextInt();
        int p =in.nextInt();
        int[] ar= new int[p+2];
        for(int i=0; i<p; i++){
            ar[i]=in.nextInt();
        }
        ar[p]=w;
        ar[p+1]=0;
        Arrays.sort(ar);
        TreeSet<Integer> set= new TreeSet();
        for(int i=0; i<ar.length; i++){
            for(int j=i+1; j<ar.length; j++){
                set.add( ar[j]-ar[i]);
            }
        }
        for(int c : set){
            out.print(c + " ");
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