import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class POJ2785{
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);

        int n= in.nextInt();
        HashMap<Long, Integer> map = new HashMap();

        long[][] ar= new long[n][4];
        for(int i=0; i<n; i++){
            for(int j=0; j<4;j++){
                ar[i][j]=in.nextInt();
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                long c= ar[i][0] + ar[j][1];
                if(map.containsKey(c)) {
                    map.put(c, map.get(c)+1);
                }else{
                    map.put(c,1);
                }
            }
        }
        long answer=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                long c= -(ar[i][2] + ar[j][3]);
                if(map.containsKey(c)){
                    answer+=map.get(c);
                }
            }
        }
        out.println(answer);
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
