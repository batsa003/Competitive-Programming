package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int m = in.nextInt();
        int[][] ar= new int[m][n];
        int[] count= new int[n];
        for(int i=0; i<m; i++){
            int max=0;
            for(int j=0; j<n; j++){
                ar[i][j]=in.nextInt();
                max= Math.max(max, ar[i][j]);
            }

            for(int j=0; j<n; j++){
                if(ar[i][j]==max){
                    count[j]++;
                    break;
                }
            }
        }
        int max=0;
        for(int i=0; i<n; i++){
            max= Math.max(max, count[i]);
        }

        for(int i=0; i<n; i++){
            if(max== count[i]){
                out.println(i+1);
                return;
            }
        }


    }
}
