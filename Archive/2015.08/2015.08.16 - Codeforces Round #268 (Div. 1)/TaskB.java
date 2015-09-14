package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int a= in.nextInt();
        int b= in.nextInt();
        HashMap<Integer,Integer> map = new HashMap();
        HashMap<Integer,Integer> rMap= new HashMap();
        for(int i=0; i<n; i++){
            int c= in.nextInt();
            map.put(c,i);
            rMap.put(i,c);
        }
        SCC scc= new SCC(2*n); // ~x =  x + n

        for(int i=0; i<n; i++){
            int p = rMap.get(i);
            if( map.containsKey(a-p)){
                int l= map.get(a-p);
               // System.out.println(i + " A-> " + (l));

                scc.addEdge(i,l);
                scc.addEdge(l+n, i+n);
            }else{
                scc.addEdge(i, i+n);
            }

            if( map.containsKey(b-p)){
                int l =map.get(b-p);
                //System.out.println(i + " B-> " + (l));

                scc.addEdge(i+n,l+n);
                scc.addEdge(l,i);
            }else{
                scc.addEdge(i+n, i);
            }
        }

        scc.scc();
        int[] comp = scc.cmp;
        for(int i=0; i<n; i++){
            if(comp[i]==comp[i+n]){
                out.println("NO");
                return;
            }
        }
        /*for(int i=0; i<2*n; i++){
            System.out.println("Comp[" + i + "]=" + comp[i]);
        }*/
        out.println("YES");
        for(int i=0; i<n; i++){
            if(comp[i]>comp[i+n]){
                out.print("0 ");
            }else{
                out.print("1 ");
            }
        }

    }
}


class SCC {
    ArrayList<Integer>[] G;
    ArrayList<Integer>[] rG;
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
