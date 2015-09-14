import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class POJ2559HISTOGRAM{
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        StringBuilder sb= new StringBuilder();
        while(true){
            int n= in.nextInt();
            if(n==0) break;
            int[] ar= new int[n];
            for(int i=0; i<n; i++){
                ar[i]=in.nextInt();
            }

            int[] l= new int[n];
            int[] r= new int[n];

            Stack<Integer> st= new Stack();
            for(int i=0; i<n; i++){
                while(!st.isEmpty() && ar[st.peek()]>=ar[i]){
                    st.pop();
                }

                if(st.isEmpty()){
                    l[i]=0;
                }else{
                    l[i]= st.peek()+1;
                }
                st.push(i);
            }
            st.clear();
            for(int i=n-1; i>=0; i--){
                while(!st.isEmpty() && ar[st.peek()]>=ar[i]){
                    st.pop();
                }

                if(st.isEmpty()){
                    r[i]=n;
                }else{
                    r[i]=st.peek();
                }
                st.push(i);
            }

            long max=0;
            for(int i=0; i<n; i++){
                max=Math.max(max, (long)(r[i]-l[i])*1L*ar[i]);
            }
            sb.append(max + "\n");
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
