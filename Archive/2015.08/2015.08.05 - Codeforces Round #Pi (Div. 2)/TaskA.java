package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<n; i++){
            ar[i]=in.nextInt();
        }


        for(int i=0; i<n; i++){
            int c=Integer.MAX_VALUE;
            if(i>0) c= Math.min(c, ar[i]-ar[i-1]);
            if(i<(n-1)) c= Math.min(c, ar[i+1]-ar[i]);
            out.print(c +" ");

            int t= Integer.MIN_VALUE;
            if(i==0){
                t= ar[n-1]-ar[0];
            }else if( i==n-1){
                t=ar[n-1]-ar[0];
            }else{
                t= Math.max( ar[i]-ar[0],t);
                t= Math.max(t, ar[n-1]-ar[i]);
            }
            out.println(t);
        }


    }
}
