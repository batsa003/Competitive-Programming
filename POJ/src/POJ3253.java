import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class POJ3253 {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int n =in.nextInt();
        PriorityQueue<Integer> PQ= new PriorityQueue();
        for(int i=0; i<n; i++){
            PQ.add(in.nextInt());
        }
        long cost=0;
        while(PQ.size() >1){
            int a= PQ.poll();
            int b= PQ.poll();
            //out.println("POLL: " + a + " " + b);
            cost+= (a+b);
            PQ.add((a+b));
        }
        out.println(cost);
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
