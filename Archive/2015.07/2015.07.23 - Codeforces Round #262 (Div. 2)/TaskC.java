package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int m =in.nextInt();
        int w= in.nextInt();
        int[] ar= new int[n+1];
        for(int i=1; i<=n; i++){
            ar[i]=in.nextInt();
        }
        int low=1;
        int high= 2000000000;
        while((high-low)>1){
            int mid= (high+low)/2;
            //Does mid as a shortest flower works?
            int need=0;
            int cur=0;
            int[] d= new int[n+1];
            int i=1;
            for(; i<=(n-w+1); i++) {
                int x= ar[i] + cur;
                if(x<mid){
                    //if(mid==3) out.println((mid-x) + " when i=" + i);
                    need+=mid-x;
                    cur+=mid-x;
                    d[i]=mid-x;
                }
                if(i>=w){
                    cur-=d[i-w+1];
                }
            }

            int maxNeed=0;
            for(;i<=n;i++){
                int t= mid-(ar[i]+cur);
                maxNeed= Math.max(maxNeed, t);
                if(i>=w){
                    cur-= d[i-w+1];
                }
            }
            need+= maxNeed;

            if(need>m){
                high=mid;
            }else{
                low=mid;
            }
        }
        out.println(low);

    }
}