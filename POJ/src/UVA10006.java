import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class UVA10006 {
    static InputReader in;
    static PrintWriter out;
    static long n;
    public static long pow(long a, long N){
        if(N==0) return 1;
        if(N==1) return a;
        else{
            long answer=1;
            while(N>0){
                if(N%2==1){
                    answer= (answer*a)%n;
                }
                a=(a*a)%n;
                N=N/2;
            }
            return answer;
        }
    }
    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        //StringBuilder sb= new StringBuilder();
        while(true){
            n= in.nextLong();
            if(n==0) break;
            boolean isPrime=true;
            for(int i=2; i<= Math.ceil(Math.sqrt(n)); i++){
                if(n %i ==0){
                    isPrime=false;
                    break;
                }
            }
            if(isPrime){
                out.println(n + " is normal.");
            }else{
                boolean isCarmichael=true;
                for(int a=2; a<=(n-1); a++){
                    long b= pow(a,n);
                    if((b-a)%n !=0){
                        isCarmichael=false;
                        break;
                    }
                }
                if(isCarmichael){
                    out.println("The number " + n + " is a Carmichael number.");
                }else{
                    out.println(n + " is normal.");
                }
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
