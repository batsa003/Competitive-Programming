import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ3276 {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N =in.nextInt();
        int[] ar= new int[N+1];
        for(int i=1; i<=N; i++){
            String s= in.next();
            if(s.equals("B")){
                ar[i]=0;
            }else{
                ar[i]=1;
            }
        }
        int K=-1;
        int count=Integer.MAX_VALUE;
        kk:
        for(int k=1; k<=N; k++){
            int[] f= new int[N+1];
            int sum=0;
            int i=1;
            for(i=1; i<=(N-(k-1)); i++){
                if((sum+ar[i])%2==0){
                    f[i]=1;
                }
                if(i<=(k-1)){
                    sum+= f[i];
                }else{
                    sum+=f[i];
                    sum-=f[i-k+1];
                }
            }
            while(i<=N){
                if((ar[i] + sum)%2==0){
                    continue kk;
                }
                if((i-k+1)>0) sum-= f[i-k+1];
                i++;
            }

            int c=0;
            for(int j=0; j<f.length; j++){
                c+=f[j];
            }
            //out.println("k,count=" + k + " " + c);

            if(c<count){
                K=k;
                count=c;
            }
        }

        out.println(K + " " + count);
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
