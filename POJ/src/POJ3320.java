import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class POJ3320{
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int P=in.nextInt();
        int[] ar= new int[P];
        HashSet<Integer> set= new HashSet();
        for(int i=0; i<P; i++){
            ar[i]=in.nextInt();
            set.add(ar[i]);
        }
        int i=0;
        int s= set.size();
        HashMap<Integer,Integer> map= new HashMap();
        int ans=Integer.MAX_VALUE;
        for(int j=0; j<P; j++){
            if(map.containsKey(ar[j])){
                map.put(ar[j], map.get(ar[j])+1);
            }else{
                map.put(ar[j],1);
            }

            if(map.keySet().size()==s){
                while(map.get(ar[i])>1){
                    map.put(ar[i],map.get(ar[i])-1);
                    i++;
                }
                ans=Math.min(ans, j-i+1);
            }
        }
        out.println(ans);
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
