import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ1064{
    static InputReader in;
    static PrintWriter out;
    static DecimalFormat dc= new DecimalFormat();

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int n =in.nextInt();
        int k= in.nextInt();
        double[] ar= new double[n];
        double max=0.00;
        for(int i=0; i<n; i++){
            ar[i]=in.nextDouble();
            if(ar[i]>max){
                max=ar[i];
            }
        }
        double low=0.00;
        double high=max+0.01;
        for(int j=0; j<100; j++){
            double mid=(low+high)/2.0;
            int t=0;
            for(int i=0; i<n; i++){
                t+=(int) (ar[i]/mid);
            }
            if(t>=k){
                low=mid;
            }else{
                high=mid;
            }
        }
        System.out.printf("%.2f", ((int)(100*low))/100.0);



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
        public double nextDouble(){ return Double.parseDouble(next());}
    }
}
