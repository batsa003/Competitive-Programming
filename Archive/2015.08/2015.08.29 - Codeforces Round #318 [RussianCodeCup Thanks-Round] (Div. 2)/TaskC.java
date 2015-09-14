package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n=in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<n; i++){
            int c=in.nextInt();
            while(c%2==0){
                c/=2;
            }
            while(c%3==0){
                c/=3;
            }
            ar[i]=c;
        }
        for(int i=1; i<n; i++){
            if(ar[i]!=ar[0]){
                out.println("No");
                return;
            }
        }
        out.println("Yes");
    }
}
