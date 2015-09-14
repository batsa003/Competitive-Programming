package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class TaskE {
    static double eps=0.000000001;

    static class Pair implements Comparable<Pair>{
        double val;
        int ind;

        Pair(double v, int i){
            val=v;
            ind= i;
        }


        @Override
        public int compareTo(Pair o) {
            return (int) Math.signum(val-o.val);
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int N= in.nextInt();
        Pair[] ar= new Pair[N];
        for(int i=1; i<=N; i++){
            ar[i-1]= new Pair(in.nextDouble(), i);
        }
        Arrays.sort(ar);
        LinkedList<Integer> indices= new LinkedList();

        boolean noPos=true;
        for(int i=N-1; i>=0; i--){
            if(ar[i].val>1){
                indices.add(ar[i].ind);
                noPos=false;
            }
        }
        boolean noNegMul=true;
        for(int i=0; i<N; i++){
            if(ar[i].val>=0) break;
            if(i<(N-1) && ar[i+1].val<0 && (ar[i].val*ar[i+1].val)>1+eps){
                indices.add(ar[i].ind);
                indices.add(ar[i+1].ind);
                i++;
                noNegMul=false;
            }
        }
        if(noPos){
            if(noNegMul){
                double bigPos=0;
                double bigMul=-1;
                if(ar[N-1].val >=0){
                    bigPos=ar[N-1].val;
                }

                if( ar[0].val<0 && ar[1].val<0){
                    bigMul= ar[0].val*ar[1].val;
                }

                if(bigPos+eps>bigMul){
                    out.println(2);
                    int a=ar[0].ind;
                    int b=ar[1].ind;
                    if(a>b){
                        out.println(b + " " + a);
                    }else{
                        out.println(a + " " + b);
                    }
                    return;

                }else{
                    out.println(1);
                    out.println((ar[N-1].ind));
                    return;
                }
            }
        }
        Collections.sort(indices);
        out.println(indices.size());
        for(int i : indices){
            out.print(i + " ");
        }
    }
}
