import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ2186{
    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int N= in.nextInt();
        int M=in.nextInt();
        SCC scc= new SCC(N);
        for(int i=0; i<M; i++){
            int A= in.nextInt()-1;
            int B= in.nextInt()-1;
            scc.addEdge(A,B);
        }

        int n= scc.scc();

        int u=0;
        int num=0;
        for(int i=0; i<N; i++){
            if( scc.cmp[i]==n-1){
                u=i;
                num++;
            }
        }

        scc.used=new boolean[N];
        scc.rdfs(u,0);
        for(int i=0; i<N; i++){
            if(!scc.used[i]){
                num=0;
                break;
            }
        }

        out.println(num);
        out.close();
        System.exit(0);

    }

}
class InputReader {
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

class SCC {
    ArrayList<Integer> G[];
    ArrayList<Integer> rG[];
    boolean[] used;
    ArrayList<Integer> vs;
    int n;
    int[] cmp;

    SCC(int n){
        this.n=n;
        G= new ArrayList[n];
        rG= new ArrayList[n];
        for(int i=0; i<n; i++){
            G[i]=new ArrayList<Integer>();
            rG[i]= new ArrayList<Integer>();
        }
        vs= new ArrayList<Integer>();
        cmp= new int[n];
        used= new boolean[n];
    }

    void dfs(int v){
        used[v]=true;
        for(int i=0; i<G[v].size(); i++){
            if(!used[G[v].get(i)]){
                dfs(G[v].get(i));
            }
        }
        vs.add(v);
    }

    void rdfs(int v, int k){
        used[v]=true;
        cmp[v]=k;
        for(int i=0; i<rG[v].size(); i++){
            if(!used[rG[v].get(i)]){
                rdfs(rG[v].get(i),k);
            }
        }
    }

    void addEdge(int from, int to){
        G[from].add(to);
        rG[to].add(from);
    }

    int scc(){
        used= new boolean[n];
        for(int i=0; i<n; i++){
            if(!used[i]){
                dfs(i);
            }
        }
        used= new boolean[n];
        int k=0;
        for(int i=vs.size()-1; i>=0; i--){
            if(!used[vs.get(i)]){
                rdfs(vs.get(i),k);
                k++;
            }
        }
        return k;
    }
}
