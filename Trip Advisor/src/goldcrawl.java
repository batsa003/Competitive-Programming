import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Bat-Orgil on 9/19/2015.
 */
public class goldcrawl {

    static int w,h;
    static int count=0;
    static boolean[] vis;
    static Scanner in;
    static ArrayList<Integer>[] G;

    public static void dfs(int x, int y){
        vis[x*w+y]=true;
        System.out.println("(" + x + "," + y + ")");
        String[] t= in.next().split(" ");
        if(t[0].equals("DONE")){
            return;
        }
        if(t[1].equals("GOLD")){
            count++;
        }
        for(int neighbor : G[x*w+y]){
            if(!vis[neighbor]){
                dfs(neighbor/w,neighbor%w);
                System.out.println("(" + x + "," + y + ")");
                String[] ss= in.next().split(" ");
                if(ss[0].equals("DONE")){
                    return;
                }
                if(ss[1].equals("GOLD")){
                    count++;
                }
            }
        }
    }
    public static void main(String[] args){
        w= Integer.parseInt(args[0]);
        h= Integer.parseInt(args[1]);
        in= new Scanner(System.in);
        char[][] field = new char[2*h+1][2*w+1];
        for(int i=0; i<=2*h; i++){
            String s= in.next();
            for(int j=0; j<=2*w; j++){
                field[i][j]= s.charAt(j);
            }
        }
        G= new ArrayList[w*h];
        for(int i=0; i<h*w; i++){
            G[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<=(h-2); i++){
            for(int j=0; j<=(w-2); j++){
                if(field[2*i+2][2*j+1]!='-'){
                    G[i*w+j].add((i+1)*w+j);
                    G[i*w+w+j].add(i*w+j);
                }
                if(field[2*i+1][2*j+2]!='|'){
                    G[i*w+j].add(i*w+j+1);
                    G[i*w+j+1].add(i*w+j);
                }
            }
        }
        String position= in.next();
        String[] ppp= position.substring(1,position.length()-1).split(",");
        int posX= Integer.parseInt(ppp[0]);
        int posY= Integer.parseInt(ppp[1]);

        vis = new boolean[h*w];
        dfs(posX, posY);
    }
}
