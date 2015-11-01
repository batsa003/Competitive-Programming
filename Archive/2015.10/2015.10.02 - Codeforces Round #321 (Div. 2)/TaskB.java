package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeSet;

public class TaskB {
    static class Pair implements Comparable<Pair>{
        int m,s,i;

        Pair(int mm, int ss,int ind){
            m=mm;
            s=ss;
            i=ind;
        }
        @Override
        public int compareTo(Pair o) {
            if(m!=o.m) return m-o.m;
            else{
                return i-o.i;
            }
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int d= in.nextInt();
        Pair[] p= new Pair[n];
        TreeSet<Pair> set= new TreeSet();
        for(int i=0; i<n; i++){
            p[i] = new Pair(in.nextInt(),in.nextInt(),0);
        }
        Arrays.sort(p);
        for(int i=0; i<n; i++){
            set.add(new Pair(p[i].m,p[i].s,i));
        }
        long[] pre= new long[n];
        pre[0]=p[0].s;
        for(int i=1; i<n; i++){
            pre[i]= pre[i-1]+p[i].s;
        }
        long max=0;
        for(int i=0; i<n; i++){
            Pair high = set.lower(new Pair(p[i].m+d,0,0));
            if(i>0){
                max= Math.max(max, pre[high.i] - pre[(i-1)]);
            }else{
                max= Math.max(max, pre[high.i]);
            }
        }
        out.println(max);
    }
}
