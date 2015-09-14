package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class TaskE {
    static class Pair implements Comparable<Pair>{
        int x,y,i;

        Pair(int xx, int yy,int i){
            x=xx;
            y=yy;
            this.i=i;
        }

        @Override
        public int compareTo(Pair o) {
            return y-o.y;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        ArrayList<Pair>[] sec= new ArrayList[1001];
        for(int i=0; i<sec.length; i++){
            sec[i]= new ArrayList();
        }

        for(int i=0; i<n; i++){
            int x= in.nextInt();
            int y= in.nextInt();
            int ind=x/1000;
            sec[ind].add(new Pair(x,y,(i+1)));
        }
        for(int i=0; i<sec.length; i++){
            Collections.sort(sec[i]);
        }
        for(int i=0; i<sec.length; i++){
            if(i%2==0){
                for(Pair p : sec[i]){
                    out.print(p.i + " ");
                }
            }else{
                for(int j=sec[i].size()-1;j>=0; j--){
                    out.print(sec[i].get(j).i + " ");
                }
            }
        }


    }
}
