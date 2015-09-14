import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class POJ3057 {
    static InputReader in;
    static PrintWriter out;
    static String[] field;
    static int[][][][] dist; // dist[x][y] contains  the shortest path length from x,y to all other entries through .
    static LinkedList<Integer> dX,dY,pX,pY;
    static int[] dx= {-1,0,0,1};
    static int[] dy= {0,-1,1,0};
    static int X,Y;


    //INITIALIZE!!!!
    int n;
    ArrayList<Integer>[] G;
    int match[];// match=-1 means it is not matched, otherwise it shows the match
    boolean used[];
    // n is the number of nodes
    // nodes must be 0 ... n-1

    void addEdge(int u, int v) {
        G[u].add(v);
        G[v].add(u);
    }

    boolean dfs(int v) {
        used[v] = true;
        for (int i = 0; i < G[v].size(); i++) {
            int u = G[v].get(i);
            int w = match[u];
            if (w < 0 || !used[w] && dfs(w)) {
                match[v] = u;
                match[u] = v;
                return true;
            }
        }
        return false;
    }



    static void bfs(int x, int y, int[][] d){

        LinkedList<Integer> qx= new LinkedList();
        LinkedList<Integer> qy= new LinkedList();
        d[x][y]=0;
        qx.add(x);
        qy.add(y);
        while(!qx.isEmpty()){
            x=qx.remove();
            y= qy.remove();

            for(int k=0; k<4; k++){
                int x2= x+dx[k];
                int y2= y+dy[k];

                if(0<=x2 && x2<X && 0<=y2 && y2<Y && field[x2].charAt(y2)=='.' && d[x2][y2]==0){
                    d[x2][y2]= d[x][y]+1;
                    qx.add(x2);
                    qy.add(y2);
                }

            }
        }
    }
    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T=in.nextInt();
        for(int count=0; count<T; count++){
            X= in.nextInt();
            Y= in.nextInt();
            field= new String[X];
            for(int i=0; i<X; i++){
                field[i]=in.next();
            }
            dist= new int[X][Y][X][Y];
            int n= (X-1)*(Y-1);

            for(int i=0; i<X; i++){
                for(int j=0; j<Y; j++){
                    if(field[i].charAt(j)=='D'){
                        dX.add(i);
                        dY.add(j);
                        bfs(i,j,dist[i][j]);
                    }else{
                        if(field[i].charAt(j)=='.'){
                            pX.add(i);
                            pY.add(j);
                        }
                    }
                }
            }

            int d= dX.size();
            int p= pX.size();


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
