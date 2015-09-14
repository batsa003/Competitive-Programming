import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class POJ2431 {
    static InputReader in;
    static PrintWriter out;

    static class trunk implements Comparable<trunk> {
        int dist;
        int fuel;

        trunk(int d, int f) {
            dist = d;
            fuel = f;
        }

        @Override
        public int compareTo(trunk o) {
            return o.dist- dist;
        }
    }

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N =in.nextInt();
        int[] A= new int[N];
        int[] B= new int[N];
        trunk[] ar= new trunk[N];
        for(int i=N-1; i>=0; i--) {
            int dist = in.nextInt();
            int fuel = in.nextInt();

            ar[i]= new trunk(dist,fuel);
        }
        Arrays.sort(ar);
        for(int i=0; i<N; i++){
            A[i] = ar[i].dist;
            B[i] = ar[i].fuel;
        }
        int L =in.nextInt();
        int fuel =in.nextInt();
        for(int i=0; i<N; i++){
            A[i]= L-A[i];
        }
        int pos=0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int count=0;
        for(int i=0; i<N; i++){
            if(A[i] <= pos+fuel){
                pq.add(-B[i]);
                //out.println("PQ at i= " + i + " is: " + pq.toString());
            }else{
                if(pq.isEmpty()){
                    out.println(-1);
                    return;
                }
                if((pos+fuel)>=L){
                    out.println(count);
                    return;
                }
                int f= -pq.poll();
                pos +=fuel;
                //out.println("position changes to: " + pos + "at i = " + i);
                i--;
                fuel=f;
                count++;
            }
        }
        pos+=fuel;
        if(pos >= L) {
            out.println(count);
            return;
        }
        while(!pq.isEmpty()){
            pos+= -pq.poll();
            count++;
            if(pos>=L){
                out.println(count);
                return;
            }
        }
        out.println(-1);
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
