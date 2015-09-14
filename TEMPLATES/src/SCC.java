import java.util.ArrayList;
import java.util.LinkedList;


// KOSARAJU'S ALGORITHM

// 1. Initialize the scc with int n, number of nodes.
// 2. Add edges with addEdge(from,to) method
// 3. scc returns the number of SCC in the graph
// 4. cmp[v] returns the index of the component of vertex v. It will be an integer from 0 to (n-1)


// The vertices in cmp are in topological order. If we shrink the nodes in the same SCC, and the vertices was in the
// order 0,1, 2, ... ,(n-1), then it is the topological sorting order. Any edge from u to v will be u<v. 0 has 0 indegree
// , (n-1) has zero outdegree.



/*2SAT ALGORITHM OR 2 SATISFIABILITY:
    FOR 2 SATISFIABILITY, LET a1, a2, ... , an be the expressions, and evaluating expression will be s= (a v b)^ c^ (d v e) ^...
    s expression has 2 properties:
    1. Each paranthesis has at most 2 literal values connected with OR
    2. All of the parenthesized values must have conjunction AND

    To find one of the possible answers for the expression "s" to be true, we construct nodes a1, ~a1, ... , an, ~an
    and if we write (a v b) = ( ~a -> b ) ^ (~b -> a)
    , then for all a -> b, we add an directed edge a -> b to the graph. And it means if a is true, b must be true.

    FIND SSC->

    - All literals in SSC must have the same T/F value.
    - If x and ~x belong to the same SSC(cmp), it is impossible to make the expression true.

    - Otherwise, THE SOLUTION IS:
    - SSC that contains X literal comes after SCC that contains ~X in topological order <-> X is True. ( False otherwise)
    - X is True <-> comp[x]>comp[~x]


*/

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