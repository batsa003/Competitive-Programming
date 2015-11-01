import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class POJ1065 {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T=in.nextInt();
        for(int c=0; c<T; c++){
            int n= in.nextInt();
            Pair[] arr= new Pair[n];
            for(int i=0; i<n; i++){
                arr[i]= new Pair(in.nextInt(),in.nextInt());
            }
            Arrays.sort(arr);
            TreeSet<Integer> set=  new TreeSet();
            for(int i=0; i<n; i++){
                int a= arr[i].y;
                if(set.isEmpty() || set.first()>a){
                    set.add(a);
                }else{
                    int low= set.lower(a+1);
                    set.remove(low);
                    set.add(a);
                }
            }
            out.println(set.size());
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