import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ3009 {
    static InputReader in;
    static PrintWriter out;
    static int w,h,step;
    static int[][] field;

    static boolean dfs(int x, int y){
        if(step==11){
            return false;
        }
        step++;
        for(int j=y-1; j>=0; j--){
            if(field[x][j]==3){
                return true;
            }else if(field[x][j]==1){
                field[x][j]=0;
                if(dfs(x,j+1)){
                    return true;
                }
                field[x][j]=1;
                step--;
                break;
            }
        }

        for(int j=y; j<w; j++){
            if(field[x][j]==3){
                return true;
            }else if(field[x][j]==1){
                field[x][j]=0;
                if(dfs(x,j+1)){
                    return true;
                }
                field[x][j]=1;
                step--;
                break;
            }
        }

        for(int i=0; i<x; i++){
            if(field[i][y]==3){
                return true;
            }else if(field[i][y]==1){
                field[i][y]=0;
                if(dfs(i+1,y)){
                    return true;
                }
                field[i][y]=1;
                step--;
                break;
            }
        }

        for(int i=x+1; i<h; i++){
            if(field[i][y]==3){
                return true;
            }else if(field[i][y]==1){
                field[i][y]=0;
                if(dfs(i+1,y)){
                    return true;
                }
                field[i][y]=1;
                step--;
                break;
            }
        }
        return false;
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
            field = new int[h][w];
            int x= 0;
            int y=0;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    field[i][j]= in.nextInt();
                    if(field[i][j]==2){
                        x=i;
                        y=j;
                    }
                }
            }
            dfs(x,y);
            if(step==11){
                out.println(-1);
            }else{
                out.println(step);
            }
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