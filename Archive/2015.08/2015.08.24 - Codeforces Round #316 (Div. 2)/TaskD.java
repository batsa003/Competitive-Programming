package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TaskD {
    static int n,m;
    static int[] tin;
    static int[] tout;
    static ArrayList<Integer>[] G;
    static ArrayList<Integer>[] nodesAtLevel;

    static int[][] pref;
    static HashSet<Integer> pow2;
    static int timer=0;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n= in.nextInt();
        m = in.nextInt();
        G = new ArrayList[n+1];
        tin= new int[n+1];
        tout= new int[n+1];
        nodesAtLevel = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            G[i]= new ArrayList<Integer>();
        }
        for(int i=1; i<=n; i++) nodesAtLevel[i]= new ArrayList<Integer>();

        pow2= new HashSet<Integer>();
        for(int i=0; i<26; i++){
            pow2.add(1<<i);
        }


        for(int i=2; i<=n; i++){
            int j = in.nextInt();
            G[j].add(i);
        }

        String s=  in.next();
        dfs(1,1);
        pref= new int[n+1][];
        /*for(int i=1; i<nodesAtLevel.length; i++){
            System.out.println("Nodes at level i=" + i + " is: " + nodesAtLevel[i].toString());
        }*/

        for(int i=1; i<=n; i++){
            pref[i]= new int[nodesAtLevel[i].size()];
            int cur=0;
            for(int j=0; j< nodesAtLevel[i].size(); j++){
                int node= nodesAtLevel[i].get(j);
                cur^= 1<< ( s.charAt(node-1)-'a');
                pref[i][j]=cur;
            }
        }
        //StringBuilder sb= new StringBuilder();
        for(int count=0; count<m; count++){
            int v= in.nextInt();
            int h= in.nextInt();
            if(go(v,h)){
                out.println("Yes\n");
            }else{
                out.println("No\n");
            }
        }
        //out.println(sb);
    }

    static int binarySearchHigher(ArrayList<Integer> list, int val){
        int l=0;
        int r= list.size()-1;
        if(tin[list.get(0)]>val){
            return 0;
        }else if(tin[list.get(r)]<val){
            return -1;
        }else {
            while (r-l>1) {
                int mid= (l+r)/2;
                if(tin[list.get(mid)]>val){
                    r=mid;
                }else{
                    l=mid;
                }
            }
            return r;
        }
    }

    static int binarySearchLower(ArrayList<Integer> list, int outVal){
        int low=0;
        int high =list.size()-1;
        if(tin[list.get(high)]<outVal){
            return high;
        }else if(tin[list.get(low)]>outVal){
            return -1;
        }else{
            while(high-low>1){
                int mid= (low+high)/2;
                if(tin[list.get(mid)]>outVal){
                    high=mid;
                }else{
                    low=mid;
                }
            }
            return low;
        }
    }

    static boolean go(int v, int h){
        ArrayList<Integer> nodes= nodesAtLevel[h];
        if(nodes.isEmpty()){
            return true;
        }
        int left=binarySearchHigher(nodes,tin[v]);
        int right=binarySearchLower(nodes, tout[v]);
        int a=0;
        if(left!=-1 && right!=-1){
            a= (left==0)? 0: pref[h][left-1];
            a^= pref[h][right];
        }

        return a==0 || pow2.contains(a);


    }

    static void dfs(int node, int depth){
        tin[node]=++timer;
        nodesAtLevel[depth].add(node);
        for(int i=0; i<G[node].size(); i++){
            dfs(G[node].get(i), depth+1);
        }
        tout[node]=++timer;
    }


}
