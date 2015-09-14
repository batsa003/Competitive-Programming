import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class POJ3734 {
    static InputReader in;
    static PrintWriter out;
    static int mod=10007;
    static int z;
    static HashMap<Integer, Integer> map;
    static int[][][] dp;
    static boolean[][][] was;

    static int dp(int p1, int p2, int n){
        if(map.containsKey(n) && was[p1][p2][map.get(n)]){
            return dp[p1][p2][map.get(n)];
        }
        if(!map.containsKey(n)){
            map.put(n,z);
            z++;
        }
        if(n==1){
            if(p1==0 && p2==0){
                return 2;
            }else if((p1==1 &&p2==0) || (p1==0 && p2==1)){
                return 1;
            }else{
                return 0;
            }
        }
        int answer=0;

        int[] par= {0,1};
        for(int x:par){
            for(int y:par){
                int temp1= dp(x,y,n/2);
                int temp2= dp( (p1+2-x)%2, (p2+2-y)%2,n-n/2);
                answer+= (temp1*temp2)%mod;
            }
        }
        answer%=mod;
        dp[p1][p2][map.get(n)]=answer;
        was[p1][p2][map.get(n)]=true;
        //System.out.println("dp p1,p2,n =" + p1 + " " + p2 +" " + n +" equals " + answer);
        return answer;
    }

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T=in.nextInt();
        StringBuilder sb= new StringBuilder();
        for(int c=0; c<T; c++){
            int N =in.nextInt();
            map=new HashMap<Integer,Integer>();
            dp = new int[2][2][150];
            was= new boolean[2][2][150];
            z=0;

            sb.append(dp(0,0,N) + "\n");
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
