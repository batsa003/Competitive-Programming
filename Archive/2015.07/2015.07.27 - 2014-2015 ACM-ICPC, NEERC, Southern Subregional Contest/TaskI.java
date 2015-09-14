package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

public class TaskI {
    public void solve(int testNumber, InputReader in, PrintWriter out) {


        int n= in.nextInt();
        int[] p = new int[n];
        for(int i=0; i<n; i++){
            p[i]=in.nextInt();
        }
        Arrays.sort(p);
        int a= p[n-1];
        int count=0;
        for(int i=0; i<(n-1); i++){
            if(a>=p[i]){
                count++;
                a-=p[i];
            }else{
                break;
            }
        }
        out.println(count+1);


    }
}
