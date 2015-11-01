import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class EightQueens {
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        boolean[][] field = new boolean[8][8];
        int count=0;
        for(int i=0; i<8; i++){
            String s= in.next();
            for(int j=0; j<8; j++){
                field[i][j] = (s.charAt(j)== '*');
                if(field[i][j]) count++;
            }
        }

        if(count!=8){
            out.println("invalid");
            return;
        }
        for(int i=0; i<8;i++){
            for(int j=0; j<8; j++){
                if(field[i][j]){
                    int[] dx= {0, 0, 1 ,-1,1,1,-1,-1};
                    int[] dy= {1,-1,0,0,1,-1,1,-1};
                    for(int k=0; k<dx.length; k++){
                        int X= dx[k];
                        int Y= dy[k];
                        for(int z=1; z<=8; z++){
                            if( 0<= i+X*z && i+X*z <8 && 0<=j+Y*z && j+Y*z<8){
                                if(field[i+X*z][j+Y*z]){
                                    out.println("invalid");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        out.println("valid");






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