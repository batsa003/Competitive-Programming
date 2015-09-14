package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    class Pair implements Comparable<Pair>{
        int ind,val;

        Pair(int aa, int bb){
            ind=aa;
            val=bb;
        }

        @Override
        public int compareTo(Pair o) {
            if(val!= o.val){
                return val-o.val;
            }else{
                return ind-o.ind;
            }
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        Pair[] ar= new Pair[n];
        int[] freq= new int[2001];

        for(int i=0; i<n; i++){
            ar[i]= new Pair(i, in.nextInt());
            freq[ ar[i].val]++;
        }

        Arrays.sort(ar);
        Pair[] ar2= ar.clone();
        Pair[] ar3= ar.clone();

        boolean set=false;
        for(int i=1; i<=2000; i++){
            if(freq[i]>2 && !set){
                int j;
                for(j=0; j<n; j++){
                    if(ar[j].val==i){
                        break;
                    }
                }
                Pair temp= ar[j];
                ar2[j]= ar2[j+1];
                ar2[j+1]=temp;
                ar3[j]=ar3[j+2];
                ar3[j+2]=temp;

                set=true;
            }
        }

        int val1=-1;
        int val2= -1;
        if(!set) {
            boolean possible = false;
            for (int i = 1; i <= 2000; i++) {
                if (freq[i] == 2) {
                    if (val1 == -1) {
                        val1 = i;
                    } else if (val2 == -1) {
                        possible = true;
                        val2 = i;
                        break;
                    }
                }
            }

            if (possible) {
                set=true;
                int ind1=-1;
                int ind2=-1;
                for(int i=0; i<n; i++){
                    if(ar[i].val==val1 && ind1==-1){
                        ind1=i;
                    }
                    if(ar[i].val==val2 && ind2==-1){
                        ind2=i;
                    }
                }

                Pair temp= ar2[ind1];
                ar2[ind1]=ar2[ind1+1];
                ar2[ind1+1]=temp;

                temp= ar3[ind2];
                ar3[ind2]=ar3[ind2+1];
                ar3[ind2+1]=temp;
            }
        }

        if(!set){
            out.println("NO");
        }else{
            out.println("YES");
            for(int i=0; i<n; i++){
                out.print((ar[i].ind+1) + " ");
            }
            out.println();
            for(int i=0; i<n; i++){
                out.print((ar2[i].ind+1) + " ");
            }
            out.println();
            for(int i=0; i<n; i++){
                out.print((ar3[i].ind+1) + " ");
            }
        }


    }
}
