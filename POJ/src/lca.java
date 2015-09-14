import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Bat-Orgil on 6/14/2015.
 */
public class lca {
    static List<Integer>[] adj;
    static int depth[];
    static int[][] anc;
    static int lg;

    static void dfs(int node, int d){
        depth[node]=d;
        for(int child: adj[node]){
            if(depth[child] ==0) dfs(child,d+1);

        }
    }

    static int goUp(int x, int d) {
        for(int c= lg-1; c>=0; c--){
            if(d>= (1<<c)){
                x= anc[x][c];
                d-= (1<<c);
            }
        }
        return x;
    }
    static int lca(int x, int y){
        if(depth[x] <depth[y]){
            y= goUp(y, depth[y]-depth[x]);
        }else{
            x= goUp(x, depth[x]-depth[y]);
        }
        if(x==y) return x;
        //System.out.println("x: " + x + " y: " + y);

        for(int c= lg-1; c>=0; c--){
            if(anc[x][c] != anc[y][c]){
                x= anc[x][c];
                y= anc[y][c];
            }
        }
        return anc[x][0];
    }

    public static void main(String[] args){
        Scanner in= new Scanner(System.in);
        int T= Integer.parseInt(in.nextLine());
        int N= Integer.parseInt(in.nextLine());
        StringBuilder sb= new StringBuilder();
        int num=0;
        while(num<T){
            adj =new List[N+1];
            for(lg=0; (1<<lg)<N; lg++);
            anc= new int[N+1][lg];
            depth= new int[N+1];
            for(int i=1; i<=N; i++){
                adj[i]= new ArrayList<Integer>();
            }
            for(int i=0; i<N-1; i++){
                String[] s= in.nextLine().split(" ");
                int a= Integer.parseInt(s[0]);
                int b= Integer.parseInt(s[1]);
                anc[b][0]=a;
                adj[a].add(b);
            }
            int root=0;
            for(int i=1; i<=N; i++){
                if(anc[i][0] ==0){
                    root=i;
                    break;
                }
            }
            for(int i=1; i<lg; i++) {
                for(int node=1; node<=N; node++) {
                    if(anc[node][i-1] !=0) {
                        anc[node][i] = anc[anc[node][i - 1]][i - 1];
                    }
                }
            }

            dfs(root, 0);

            while(in.hasNext()) {
                String[] s = in.nextLine().split(" ");
                if(s.length==1){
                    N= Integer.parseInt(s[0]);
                    break;
                }else{
                    int a= Integer.parseInt(s[0]);
                    int b= Integer.parseInt(s[1]);
                    int ans= lca(a,b);
                    sb.append(ans + "\n");
                }
            }
            num++;
        }
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);
        pw.close();
        System.exit(0);
    }

}
