package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.TreeSet;

public class TaskC {
    static class Pair implements Comparable<Pair> {

        int a,b;
        Pair(int aa, int bb){
            a=aa;
            b=bb;
        }

        @Override
        public int compareTo(Pair o) {
            if(a> o.a){
                return 1;
            }else if(a < o.a){
                return -1;
            }else{
                if(b>o.b){
                    return 1;
                }else if( b< o.b){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int s= in.nextInt();
        if(s==0){
            out.println("1 0");
        }else if(s==2 || s==5 ){
            out.println("Impossible");
            return;
        }else if(s==1){
            out.println("2 1");
            out.println("1 2");
        }else if(s==3){
            out.println("3 3");
            out.println("1 2\n2 3\n1 3");
        }else if(s==4){
            out.println("3 2");
            out.println("1 2\n2 3");
        }else if(s==6){
            out.println("4 6");
            out.println("1 2\n1 3\n1 4\n2 3\n2 4\n3 4");
        }else if(s==7){
            out.println("4 5");
            out.println("1 2\n2 3\n3 4\n4 1\n1 3");
        }else if(s==8){
            out.println("4 4");
            out.println("1 2\n2 3\n3 4\n4 1");
        }else if(s==9){
            out.println("4 3");
            out.println("1 2\n1 3\n1 4");
        }else{
            int k=5;
            for(; k<10000; k++){
                if(k*(k-1)/2 > s){
                    break;
                }
            }
            k--;
            int m= k*(k-1)/2;
            int need= s-(k*(k-1)/2);
            TreeSet<Pair> set= new TreeSet();
            for(int i=1; i<=(k-1); i++){
                for(int j=i+1; j<=k; j++){
                    set.add(new Pair(i,j));
                }
            }

            for(int i=1; i<=need; i++){
                int j= (i+2);
                if(j>k){
                    j-=k;
                    set.remove(new Pair(j,i));
                }else {
                    set.remove(new Pair(i, j));
                }
            }
            out.println(k + " " + set.size());
            for(Pair p : set) {
                out.println(p.a + " " + p.b);
            }
        }
    }
}
