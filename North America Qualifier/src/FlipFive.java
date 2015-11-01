import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class FlipFive {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);

        int p = in.nextInt();
        for(int aa=0; aa<p; aa++){
            int[][] field = new int[3][3];
            //0 is white.
            for(int i=0; i<3; i++){
                String s= in.next();
                for(int j=0; j<3; j++){
                    field[i][j] = (s.charAt(j)=='.') ? 0 : 1;
                }
            }
            int min=9;

            for(int mask=0; mask<(1<<9); mask++){
                int[][] ar=  new int[3][3];
                int[] dx= {0, 0, 1 ,-1,0};
                int[] dy= {1,-1,0,0,0};
                int c=0;
                for(int i=0; i<9; i++){
                    if((mask & (1<<i)) !=0){
                        c++;
                        int x= i/3;
                        int y= i%3;
                        for(int k=0; k<5; k++){
                            int a= x+dx[k];
                            int b= y+dy[k];
                            if(0<=a && a<3 && 0<=b && b<3){
                                ar[a][b] ^=1;
                            }
                        }
                    }
                }
                boolean el= true;
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        if(ar[i][j]!=field[i][j]){
                            el=false;
                        }
                    }
                }
                if(el){
                    min= Math.min(c,min);
                }
            }


            out.println(min);
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