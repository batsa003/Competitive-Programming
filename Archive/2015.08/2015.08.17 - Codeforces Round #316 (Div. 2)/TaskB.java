package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int m =in.nextInt();
       /* int half= n/2;
        if(m<=half){
            ans=m+1;
        }else{
            ans=m-1;
        }*/
        int ans=-1;
        if(n==1){
            ans=1;
        }else if(n==2){
            if(m==1) ans=2;
            if(m==2) ans=1;
        }else if(n%2==0){
            int half= n/2;
            if(m <= half){
                ans=m+1;
            }else{
                ans=m-1;
            }
        }else{
            int half=n/2;
            if(m<=half){
                ans=m+1;
            }else{
                ans=m-1;
            }
        }

        out.println(ans);

    }
}
