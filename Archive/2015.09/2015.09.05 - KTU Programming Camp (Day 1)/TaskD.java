package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskD {
    static int[] dp;
    static boolean[] was;
    static int N;
    static int[] ar;


    static int dp(int mask){
        if(was[mask]){
            return dp[mask];
        }
        int ans=0;
        ArrayList<Integer> set= new ArrayList();
        for(int i=0; i<N; i++){
            if(( (1<<i)& mask)!=0){
                set.add(i);
            }
        }
        if(set.size()<=2){
            ans=0;
        }else {
            int sz = set.size();
            for (int i = 0; i < sz; i++) {
                for (int j = i + 1; j < sz; j++) {
                    for (int k = j + 1; k < sz; k++) {
                        int x= set.get(i);
                        int y= set.get(j);
                        int z= set.get(k);
                        int a = ar[x];
                        int b = ar[y];
                        int c = ar[z];
                        if (a + b > c && a + c > b && b + c > a) {
                            int  m= mask ^ (1<<x);
                            m^= (1<<y);
                            m^=(1<<z);
                            ans= Math.max(ans, 1+dp(m));
                        }
                    }
                }
            }
        }
        was[mask]=true;
        dp[mask]=ans;
        return ans;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        N= in.nextInt();
        ar= new int[N];
        for(int i=0; i<N; i++){
            ar[i]=in.nextInt();
        }
        dp= new int[1<<N];
        was= new boolean[1<<N];
        out.println(dp((1<<N)-1));

    }
}
