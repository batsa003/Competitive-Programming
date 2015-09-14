package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] ar= new int[n];
        int count=0;
        int first= in.nextInt();
        for(int i=1; i<n; i++){
            ar[i]=in.nextInt();
        }
        Arrays.sort(ar);
        while(true){
            if(ar[n-1]>=first){
                count++;
                ar[n-1]--;
                first++;
                Arrays.sort(ar);
            }else{
                break;
            }
        }
        out.println(count);

    }
}
