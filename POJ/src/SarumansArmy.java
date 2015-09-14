import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class SarumansArmy{
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        StringBuilder sb= new StringBuilder();
        while(true){
            int R =in.nextInt();
            int n= in.nextInt();
            if(R== -1 && n==-1){
                break;
            }
            TreeSet<Integer> set= new TreeSet();
            for(int i=0; i<n; i++){
                set.add(in.nextInt());
            }
            int current= set.first();
            int count=0;
            while(true){
                int temp = set.lower(current+R+1);
                count++;
                if(set.last() <= (temp+R)){
                    break;
                }else{
                    current=set.higher(temp+R);
                }
            }
            sb.append(count + "\n");
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
