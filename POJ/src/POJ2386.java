import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ2386 {
    static InputReader in;
    static PrintWriter out;
    static int N;
    static int M;
    static int[][] color;
    static boolean[][] occupied;

    public static void dfs(int x, int y, int col){
        //out.println("DFS ON (X,Y): " + x + " " +y + " with color: " + col);
        color[x][y]=col;
        int[] DX= {-1,0,1};
        int[] DY= {-1,0,1};
        for(int dx : DX){
            for(int dy:DY){
                int a= x+dx;
                int b= y+dy;
                if(a<N && a>=0 && b<M && b>=0 && occupied[a][b] && color[a][b]==0) {
                    dfs(a,b,col);
                }
            }
        }
    }
    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        N=in.nextInt();
        M= in.nextInt();
        color= new int[N][M];
        occupied= new boolean[N][M];
        for(int i=0; i<N; i++){
            String s= in.next();
            for(int j=0; j<M; j++){
                if(s.charAt(j)=='W'){
                    occupied[i][j]=true;
                }
            }
        }
        int col=1;
        for(int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                if (occupied[i][j] && color[i][j] == 0) {
                    dfs(i, j, col);
                    col++;
                }
            }
        }
        out.println(col-1);



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
