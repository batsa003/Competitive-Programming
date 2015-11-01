package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] cnt= new int[1000002];
        for(int i=0; i<n; i++){
            cnt[in.nextInt()]++;
        }
        int ans=0;
        for(int i=0; i<cnt.length-1; i++){
            if(cnt[i]%2==1){
                ans++;
            }
            cnt[i+1]+= cnt[i]/2;
        }
        int w= cnt[cnt.length-1];
        String t= Integer.toBinaryString(w);
        int s=0;
        for(int i=0; i<t.length(); i++){
            if(t.charAt(i)=='1'){
                s++;
            }
        }
        ans+= s;
        out.println(ans);
    }
}
