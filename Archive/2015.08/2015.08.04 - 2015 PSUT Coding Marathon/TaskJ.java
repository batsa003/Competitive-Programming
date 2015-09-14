package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskJ {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        int N= in.nextInt();
        int[] ar= new int[N];
        for(int i=0; i<N; i++){
            String s1= in.next();
            int h1= Integer.parseInt(s1.substring(0, 2));
            int m1= Integer.parseInt(s1.substring(3, 5));
            int sec1= Integer.parseInt(s1.substring(6, 8));
            ar[i]= h1*3600 + m1 * 60 + sec1;
        }

        Arrays.sort(ar);
        int low=0;
        int high=100000;
        WHILE:
        while( (high-low)>1){
            int mid= (high+low)/2;

            int cur=mid;// Cur, number of people

            if(cur>= N){
                high=mid;
                continue WHILE;
            }
            int[] Out =new int[cur];
            for(int i=0; i<cur; i++){
                Out[i]= ar[i]+900;
            }

            for(int i=cur; i<N; i++){
                int worker= i%cur;

                if(Out[worker]<=ar[i]){
                    Out[worker]=ar[i]+900;
                }else{
                    if(Out[worker] + 899-ar[i] > 10800){
                        low=mid;
                        continue WHILE;
                    }else{
                        Out[worker]= Out[worker] + 900;
                    }
                }
            }

            high=mid;
            //            if(10800 < (pair.val+899)-customer.val){
        }
        out.println(high);
    }
}
