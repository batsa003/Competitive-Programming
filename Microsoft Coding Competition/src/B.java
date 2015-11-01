import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class B {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        for(int i=0; i<10; i++){
            int n= in.nextInt();
            boolean[] vis= new boolean[500001];
            int count=0;
            vis[1]=true;
            boolean isHappy=false;
            while(true){
                if(n==1){
                    isHappy=true;
                    break;
                }else if(vis[n]){
                    break;
                }else{
                    vis[n]=true;
                    int res= 0;
                    String s= String.valueOf(n);
                    for(int j=0; j<s.length(); j++){
                        int a= Integer.parseInt(""+s.charAt(j));
                        res+=a*a;
                    }
                    n=res;
                    count++;
                }
            }
            if(isHappy){
                out.print("happy " + count);
            }else{
                out.print("unhappy " + count);
            }
            out.println();
        }
        out.println();

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