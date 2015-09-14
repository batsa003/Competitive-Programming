package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<n; i++){
            ar[i]=in.nextInt();
        }
        out.println(n);
        for(int i=0; i<n; i++){

            int min=i;
            for(int j=i; j<n; j++){
                if(ar[j]<ar[min]){
                    min=j;
                }
            }
            out.println(i + " " + min);
            int temp= ar[i];
            ar[i]=ar[min];
            ar[min]=temp;
        }

    }
}
