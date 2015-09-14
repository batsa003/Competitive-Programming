package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k = in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<n; i++){
            ar[i]=in.nextInt();
        }
        Arrays.sort(ar);
        long ans=0;
        for(int j=(n-1); j>=0; j-=k){
            ans+= (ar[j]-1)*2;
        }
        out.println(ans);
    }
}
