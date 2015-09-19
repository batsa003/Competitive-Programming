import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ1979{
    static InputReader in;
    static PrintWriter out;
    static boolean[][] field;
    static boolean[][] vis;
    static int w,h;

    static int dfs(int x, int y){
        vis[x][y]=true;
        int ans=1;
        int[] dx= {0,0,1,-1};
        int[] dy= {1, -1,0,0};
        for(int i=0; i<4; i++){
            int X= x+dx[i];
            int Y= y+dy[i];
            if( 0<=X && X<h && 0<=Y && Y<w && !vis[X][Y] && field[X][Y]){
                ans+=dfs(X,Y);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        while(true){
            w= in.nextInt();
            h= in.nextInt();
            if(w==0 && h==0){
                break;
            }
            field= new boolean[h][w];
            vis = new boolean[h][w];
            int startx=0;
            int starty=0;
            for(int i=0; i<h; i++){
                String s= in.next();
                for(int j=0; j<w; j++){
                    if(s.charAt(j)=='@'){
                        startx=i;
                        starty=j;
                    }else if (s.charAt(j)=='.'){
                        field[i][j]=true;
                    }
                }
            }
            out.println(dfs(startx,starty));
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