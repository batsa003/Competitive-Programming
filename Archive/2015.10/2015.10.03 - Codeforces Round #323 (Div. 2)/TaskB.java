package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] a = new int[n];
        for(int i=0; i<n; i++){
            a[i] = in.nextInt();
        }
        int change=0;
        boolean left=true;
        boolean[] taken= new boolean[n];
        int cur=0;
        int count=0;
        WHILE:
        while(true){
            if(left){
                for(int i=0; i<n; i++){
                    if(cur>=a[i] && !taken[i]){
                        cur++;
                        count++;
                        taken[i]=true;
                        if(count==n){
                            break WHILE;
                        }
                    }
                }
                left=false;
                change++;
            }else{
                for(int j=n-1; j>=0; j--){
                    if(cur>=a[j] && !taken[j]){
                        cur++;
                        count++;
                        taken[j]=true;
                        if(count==n){
                            break WHILE;
                        }
                    }
                }
                left=true;
                change++;
            }
        }
        out.println(change);
    }
}
