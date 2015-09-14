package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n= in.nextLong();
        long sum=0;
        long cur=0;
        long count=0;

        if(n%3==0){
            sum=15;
            cur=8;
        }else if(n%3==1){
            sum=7;
            cur=5;
        }else{
            sum=2;
            cur=2;
        }

        while(true){
            if(sum>n){
                break;
            }
            count++;
            cur+=3;
            sum+=cur;
            cur+=3;
            sum+=cur;
            cur+=3;
            sum+=cur;
        }
        out.println(count);
    }
}
