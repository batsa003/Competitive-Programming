package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class TaskD {
    static int[] longest;
    static boolean[] vis;
    static ArrayList<Integer>[] G;

    static int dfs(int node){
        vis[node]=true;
        int val=0;
        if(G[node].size()!=0) {
            for (int i = 0; i < G[node].size(); i++) {
                int child = G[node].get(i);
                if (!vis[child]) val = Math.max(val, dfs(child));
                else val= Math.max(val, longest[child]);
            }
            val++;
        }
        longest[node]=val;
        return val;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k= in.nextInt();

        int[][] ar= new int[k][n+1]; // Pos of k-th permutation, number i
        for(int kk=0; kk<k; kk++){
            for(int i=1; i<=n; i++){
                ar[kk][in.nextInt()]=i;
            }
        }

        G= new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            G[i]= new ArrayList<Integer>();
        }

        int[] degIn= new int[n+1];
        for(int i=1; i<=n; i++){
            FOR:
            for(int j=1; j<=n; j++){
                for(int K=0; K<k; K++){
                    if(ar[K][i] >= ar[K][j]){
                        continue FOR;
                    }
                }
                G[i].add(j);
                degIn[j]++;
                //out.println(i + " " + j);
            }
        }

        longest= new int[n+1];
        vis= new boolean[n+1];

/*
        LinkedList<Integer> Q= new LinkedList();
        for(int i=1; i<=n; i++){
            if(degIn[i]==0){
                Q.add(i);
            }
        }
        Stack<Integer> st= new Stack();

        while(!Q.isEmpty()){
            int a= Q.remove();
            st.add(a);
            for(int i=0; i<G[a].size(); i++){
                int neighbor= G[a].get(i);
                degIn[neighbor]--;
                if(degIn[neighbor]==0) Q.add(neighbor);
            }
        }

        while(!st.isEmpty()){
            dfs(st.pop());
        }*/

        for(int i=1; i<=n; i++){
            dfs(i);
        }

        int max=0;
        for(int i=1; i<=n; i++){
            max= Math.max(max, longest[i]);
        }
        out.println(max+1);
    }
}
