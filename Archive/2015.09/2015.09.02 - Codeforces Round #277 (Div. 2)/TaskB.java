package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m =in.nextInt();
        int n= in.nextInt();
        int[][] A= new int[m][n];
        int[][] B= new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                B[i][j]=in.nextInt();
                Arrays.fill(A[i], 1);
            }
        }
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(B[i][j]==0){

                    for(int k=0; k<n; k++){
                        A[i][k]=0;
                    }
                    for(int k=0; k<m; k++){
                        A[k][j]=0;
                    }
                }
            }
        }
        for(int i=0; i<m; i++){
            FOR:
            for(int j=0; j<n; j++){
                if(B[i][j]==1){

                    for(int k=0; k<n; k++){
                        if(A[i][k]==1){
                            continue FOR;
                        }
                    }

                    for(int k=0; k<m; k++){
                        if(A[k][j]==1){
                            continue FOR;
                        }
                    }
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                out.print(A[i][j] + " ");
            }
            out.println();
        }

    }
}
