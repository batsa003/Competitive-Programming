import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ3279 {
    static InputReader in;
    static PrintWriter out;
    static int[][] nums;
    static boolean[][] flipped;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int M=in.nextInt();
        int N=in.nextInt();
        nums= new int[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N;j++){
                nums[i][j]=in.nextInt();
            }
        }
        int[][] boo= new int[M][N];
        int answer=Integer.MAX_VALUE;
        MAASK:
        for(int mask=0; mask< (1<<N); mask++){
            flipped= new boolean[M][N];
            for(int j=0; j<N; j++){
                if((mask & (1<<j))!=0){
                    flipped[0][j]=true;
                }
            }

            int[] DX={-1,1};
            for(int row=1; row<M; row++){
                for(int col=0; col<N; col++){
                    int r= row-1;
                    int num = nums[r][col];
                    if(flipped[r][col]) num++;
                    for(int dx:DX){
                        int dy=0;
                            if((r+dx)>=0 && (r+dx)<M && (col+dy)>=0 && (col+dy)<N && flipped[r+dx][col+dy]){
                                num++;
                            }
                    }

                    for(int dx:DX){
                        int dy=0;
                        if((r)>=0 && (r)<M && (col+dx)>=0 && (col+dx)<N && flipped[r+dy][col+dx]){
                            num++;
                        }
                    }
                    if(num%2==1){
                        flipped[row][col]=true;
                    }
                }
            }
            /*if(mask==0){
                out.println("when mask=0");
            for(int i=0; i<M; i++){
                for(int j=0; j<N; j++){
                    if(flipped[i][j]){
                        boo[i][j]=1;
                    }else{
                        boo[i][j]=0;
                    }
                    out.print(boo[i][j]  + " ");
                }
                out.println();
            }}*/

            for(int col=0; col<N;col++){
                int r=M-1;
                int num=nums[r][col];
                if(flipped[r][col]) num++;
                for(int dx:DX){
                    int dy=0;
                    if((r+dx)>=0 && (r+dx)<M && (col+dy)>=0 && (col+dy)<N && flipped[r+dx][col+dy]){
                        num++;
                    }
                }

                for(int dx:DX){
                    int dy=0;
                    if((r)>=0 && (r)<M && (col+dx)>=0 && (col+dx)<N && flipped[r+dy][col+dx]){
                        num++;
                    }
                }
                if(num%2!=0){
                    continue MAASK;
                }
            }
            int ans=0;
            for(int i=0; i<M; i++){
                for(int j=0; j<N; j++){
                    if(flipped[i][j]){
                        ans++;
                    }
                }
            }
            if(ans<answer){
                for(int i=0; i<M; i++){
                    for(int j=0; j<N; j++){
                        if(flipped[i][j]){
                            boo[i][j]=1;
                        }else{
                            boo[i][j]=0;
                        }
                    }
                }
                answer=ans;
            }

        }
        if(answer==Integer.MAX_VALUE){
            out.println("IMPOSSIBLE");
        }else{
            for(int i=0; i<M; i++){
                for(int j=0; j<N; j++){
                out.print(boo[i][j] + " " );
                }
                out.println();
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
