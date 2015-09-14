package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskC {
    static int n;
    static int m;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String vals= in.next();
        n=in.nextInt();
        m=in.nextInt();
        int[] pos1= new int[m];
        boolean[] t1= new boolean[m];
        int[] pos2= new int[m];
        boolean[] t2= new boolean[m];
        for(int i=0; i<m; i++){
            pos1[i]= in.nextInt()-1;
            t1[i]= (in.next().equals("V")) ? true:false;
            pos2[i]= in.nextInt()-1;
            t2[i]= (in.next().equals("V")) ? true:false;
        }
        boolean[] charVal= new boolean[vals.length()];
        for(int i=0; i<vals.length(); i++) charVal[i]= (vals.charAt(i)=='V') ? true:false;

        String str= in.next();
        boolean[] vow= new boolean[n];
        for(int i=0; i<n; i++) vow[i]= charVal[(str.charAt(i)-'a')];

        for(int ind=(n-1);ind>=0; ind--){

            //"Fix until ind"
            //If it doesn't work then go down;
            //If it works, go until (n-2) and change the next characters if necessary to find lexicographically minimum ans.

            SCC scc= new SCC(2*n);
            for(int i=0; i<m; i++){

            }


        }
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
