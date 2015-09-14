import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SegmentTreeMinMax{
    static InputReader in;
    static PrintWriter out;
    static  int[] A;
    static int[] min;
    static int[] max;

    public static int max(int l, int r, int a, int b, int n){
        if(r<a || b<l) return 0;
        if(l <= a && b<=r) return max[n];

        int m =(a+b)/2;
        int max1= max(l,r,a,m,2*n);
        int max2= max(l,r,m+1,b,2*n+1);
        return Math.max(max1,max2);
    }

    //Answer of the overlapping part of [l,r] with [a,b] where the node number of [a,b]=n;
    public static int min(int l, int r, int a, int b, int n){
        if(r<a || l>b){
            return Integer.MAX_VALUE;
        }
        if(l <= a && b<= r){
            return min[n];
        }
        int m =(a+b)/2;
        int min1= min(l,r,a,m,2*n);
        int min2= min(l,r,m+1,b,2*n+1);
        return Math.min(min1,min2);
    }
    public static void init(int a, int b, int n){
        if(a==b){
            min[n]=A[a];
            max[n]=A[a];
        }else{
            int m = (a+b)/2;
            init(a,m,2*n);
            init(m+1,b,2*n+1);
            min[n]= Math.min(min[2*n],min[2*n+1]);
            max[n]= Math.max(max[2*n],max[2*n+1]);
        }
    }
    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        StringBuilder sb= new StringBuilder();
        int N= in.nextInt();
        int Q=in.nextInt();
        A= new int[N+1];
        for(int i=1; i<=N; i++){
            A[i]=in.nextInt();
        }
        min = new int[4*N];
        max= new int[4*N];
        init(1,N,1);

        for(int i=0; i<Q; i++){
            int l= in.nextInt();
            int r= in.nextInt();
            sb.append((max(l,r,1,N,1)- min(l,r,1,N,1)) + "\n");
        }
        out.println(sb);
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
