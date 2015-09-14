package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int N= in.nextInt();
        int[] ar= new int[N+1];
        for(int i=1; i<=N; i++){
            ar[i]=in.nextInt();
        }
        Arrays.sort(ar);

        for(int i=N; i>=2; i--){ // Can we make everything equal to ar[i] using the indices above i?
            int ans=(N-i);
            long extra=0;
            long need=0;
            for(int j=N; j>i; j--){
                extra+= ar[j]-ar[i];
            }
            for(int j=i-1; j>=1; j--){
                need+= ar[i]-ar[j];
            }
            if(extra>=need){
                out.println(ans);
                return;
            }
        }
        out.println(N-1);
    }
}
