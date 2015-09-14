package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TaskD {
    static ArrayList<Integer>[] adj;
    static Edge[][] G;
    static int[][] d;
    static int n;

    static boolean[] vis;
   // static int[] dist1= new int[n];

    boolean dfs(int node, int to, int dist){
        vis[to]=true;
        //dist1[to]=dist;
        if(d[node][to]!=dist){
            return false;
        }
        for(int i=0; i<adj[to].size(); i++){
            int child= adj[to].get(i);
           // System.out.println("child= " + child + " d[" + to+ "][" + child+ "]=" + d[to][child]);
            if(!vis[child]){
               // System.out.println("dfs call node, child , dist =" + node+ " "+ child +" "+ (dist+d[to][child]));
                if (!dfs(node, child, (dist+d[to][child]))){
               //     System.out.println("DFS false=" + node + " " + child + " " + (dist+d[to][child]));
                    return false;
                }
            }
        }
        return true;
    }
    static void construct(int node, ArrayList<Edge> subnodes){
        if(subnodes.size()==0) return;
       // System.out.println("CONSTRUCT root at=" + node);
       // System.out.println("subnodes= ");
        //for( Edge sub : subnodes) System.out.println(sub.to);
        Collections.sort(subnodes);
        ArrayList<Edge>[] t= new ArrayList[n];
        for(int i=0; i<t.length; i++) t[i]= new ArrayList();
        SUB:
        for(Edge sub : subnodes){

            for(int i=0; i<adj[node].size(); i++){
                int child= adj[node].get(i);
                if(d[node][sub.to]==d[node][child]+d[child][sub.to]){
                    t[child].add(new Edge(d[child][sub.to],sub.to));
                    continue SUB;
                }
            }
            adj[node].add(sub.to);
            adj[sub.to].add(node);
           // System.out.println("EDge ("+node+","+sub.to+")");
        }
        for(int i=0; i<adj[node].size(); i++){
            construct(adj[node].get(i), t[adj[node].get(i)]);
        }

    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n= in.nextInt();
        d= new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                d[i][j]= in.nextInt();
                if(i==j){
                    if(d[i][j]!=0){
                        out.println("NO");
                        return;
                    }
                }
                if(j<i){
                    if(d[i][j]!=d[j][i]){
                        out.println("NO");
                        return;
                    }
                }

                if(i!=j && d[i][j]==0){
                    out.println("NO");
                    return;
                }
            }
        }
        adj =new ArrayList[n];
        for(int i=0; i<n; i++) adj[i]= new ArrayList<Integer>();

        ArrayList<Edge> subnodes= new ArrayList();
        for(int i=1; i<n; i++){
            subnodes.add(new Edge(d[0][i], i));
        }
        construct(0,subnodes);

       /* for(int i=0; i<3; i++){
            System.out.println("adj[" + i + "]=" + adj[i].toString());
        }*/

        vis= new boolean[n];
        for(int i=0; i<n; i++){
            Arrays.fill(vis,false);
            if(!dfs(i,i,0)){
                out.println("NO");
                return;
            }
        }
        out.println("YES");


    }




    static class Edge implements Comparable<Edge>{
        int w,to;

        Edge(int weight, int to){
            w=weight;
            this.to=to;
        }


        @Override
        public int compareTo(Edge o) {
            return w-o.w;
        }
    }
}
