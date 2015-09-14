package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {

    int[][] P = { {0,1,2} , {0, 2, 1} , {1,0,2} , {1,2,0} , {2,0,1} , {2,1,0}};

    long dist2(int[] x, int[] y){
        long ans=0;
        for(int i=0; i<3; i++){
            ans+= (long)(x[i]-y[i])*(x[i]-y[i]);
        }
        return ans;
    }

    int[] M = {0,1,1,1,2,2,2,3};
    boolean check(int[][] ar){
        for(int i=0; i<8; i++){
            long[] d= new long[8];
            for(int j=0; j<8; j++){
                d[j] = dist2( ar[i], ar[j]);

            }
            Arrays.sort(d);
            long b= d[1];
            if(b==0) return false;
            for(int k=0; k<8; k++){
                if(d[k] != M[k]*b){
                    return false;
                }
            }
        }
        return true;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[][] a= new int[8][3];
        for(int i=0; i<8; i++){
            for(int j=0; j<3; j++){
                a[i][j]=in.nextInt();
            }
        }
        int[][] b= new int[8][3];
        b[0]= a[0].clone();
        for(int num=0; num<279936; num++){
            int temp=num;
            for(int i=1; i<8; i++){
                int[] p = P[temp%6];
                temp/=6;
                for(int k=0; k<3; k++){
                    b[i][k]= a[i][p[k]];
                }
            }
            if(check(b)){
                out.println("YES");
                for(int i=0; i<8; i++){
                    for(int j=0; j<3; j++){
                        out.print(b[i][j] + " ");
                    }
                    out.println();
                }
                return;
            }
        }
        out.println("NO");
    }
}
