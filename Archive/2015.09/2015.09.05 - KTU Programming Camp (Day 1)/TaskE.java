package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskE {
    static long[][] ar;
    static int n;

    static long dif(int i, int j){
        long s= 0;
        for(int x=0; x<n; x++){
            s-= ar[i][x];
        }
        for(int x=0; x<n; x++){
            s+= ar[j][x];
        }
        return s;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n= in.nextInt();
        ar= new long[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                ar[i][j]=in.nextLong();
            }
        }
        if(n%2==0) {
            long s = 0;
            for (int i = 0; i < n; i++) {
                s += ar[i][n - 1 - i];
                s+= dif(0,i);
            }
            ar[0][0]= (s/n);
        }else{
            long s=0;
            for(int i=0; i<n; i++){
                s+= ar[i][n-1-i];
                if(i!=(n/2)){
                    s+=dif(0,i);
                }
            }
            ar[0][0]=(s/(n-1));
        }
        long sum=0;
        for(int i=0; i<n; i++){
            sum+= ar[0][i];
        }
        for(int j=1; j<n; j++){
            long s= sum;
            for(int i=0; i<n; i++){
                s-= ar[j][i];
            }
            ar[j][j]=s;
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                out.print(ar[i][j] + " ");
            }
            out.println();
        }


    }
}
