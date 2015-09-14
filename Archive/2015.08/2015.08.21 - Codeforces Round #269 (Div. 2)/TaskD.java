package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int m= in.nextInt();
        if(m==1){
            out.println(n);
            return;
        }
        int[] T= new int[n-1];
        int p= in.nextInt();
        for(int i=0; i<(n-1); i++){
            int q= in.nextInt();
            T[i]= q-p;
            p=q;
        }
        int[] P= new int[m-1];
        p= in.nextInt();
        for(int i=0; i<(m-1); i++){
            int q= in.nextInt();
            P[i]= q-p;
            p=q;
        }

        p=0;
        int hit=0;
        int[] kmp= kmpTable(P);
        for(int i=0; i<T.length; i++){
            while(p>=0 && P[p] != T[i]){
                p=kmp[p];
            }
            if(++p == P.length){
                // P matches T[i-m+1, ... i]
                p=kmp[p];
                hit++;
            }
        }
        out.println(hit);
    }

    static int[] kmpTable(int[] str){
        int n= str.length;
        int[] kmp =new int[n+1];
        kmp[0]= -1; kmp[1]=0;
        for(int i=2,j=0; i<=n; i++){
            while(j>0 && str[i-1] != str[j]){
                j=kmp[j];
            }
            kmp[i]= str[i-1]==str[j] ? ++j : 0;
        }
        return kmp;
    }


}
