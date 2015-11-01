package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k = in.nextInt();
        int[] a= new int[n];
        LinkedList<Integer>[] cnt= new LinkedList[10];
        for(int i=0; i<10 ;i++){
            cnt[i] = new LinkedList<Integer>();
        }
        for(int i=0; i<n; i++){
            a[i] = in.nextInt();
            cnt[a[i]%10].add(a[i]);
        }
        for(int i=9; i>0; i--){
            while(!cnt[i].isEmpty() && k>=(10-i)) {
                k -= 10 - i;
                cnt[0].add((cnt[i].remove() + (10 - i)));
            }
        }
        int ans=0;
        for(int i=0; i<10; i++){
            for(int j : cnt[i]){
                if(k>=10){
                    if(k >= (100-j)){
                        k-= (100-j);
                        ans+=10;
                    }else{
                        ans+= (j+k)/10;
                        k=0;
                    }
                }else{
                    ans+= j/10;
                }
            }
        }
        out.println(ans);
    }
}
