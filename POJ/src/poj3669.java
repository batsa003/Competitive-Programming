import java.io.*;
import java.util.*;

public class poj3669 {
    static int M;
    static boolean[][] dang= new boolean[305][305];
    static boolean[][] vis= new boolean[305][305];
    static int[][] time= new int[305][305];
    static int max= 304;

    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args){
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        M =in.nextInt();
        for(int i=0; i<=max; i++){
            Arrays.fill(time[i],10000);
        }

        for(int i=0; i<M; i++){
            int x=in.nextInt();
            int y=in.nextInt();
            int t= in.nextInt();
            int[] dx= {0,0,0,1,-1};
            int[] dy= {0,1 ,-1,0,0};
            for(int j=0; j<5; j++){
                int xx= x+dx[j];
                int yy= y+dy[j];
                if(0<=xx && xx<=max && 0<=yy && yy<=max){
                    time[xx][yy]=Math.min(time[xx][yy],t);
                }
            }
        }

        LinkedList<State> Q= new LinkedList();
        Q.add(new State(0,0,0));

        while(!Q.isEmpty()){
            State st= Q.remove();
            if(vis[st.x][st.y]) continue;
            vis[st.x][st.y]=true;
            if(time[st.x][st.y]<=st.t) continue;
            if(time[st.x][st.y]>1000){
                out.println(st.t);
                return;
            }

            int[] dx= {0,0,1,-1};
            int[] dy= {1 ,-1,0,0};
            for(int c=0; c<4; c++){
                int i= st.x+dx[c];
                int j= st.y+dy[c];
                if(0<=i && i<=max && 0<=j && j<=max && !vis[i][j]&& time[i][j]>(st.t+1)){
                    Q.add(new State(i,j,st.t+1));
                }
            }
        }
        out.println(-1);

        out.close();
        System.exit(0);
    }
    static class Pair{
        int x,y;
        Pair(int xx, int yy){
            x=xx;
            y=yy;
        }
    }
    static class State {
        int x,y,t;
        State(int xx, int yy, int tt){
            x=xx;
            y=yy;
            t=tt;
        }
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
