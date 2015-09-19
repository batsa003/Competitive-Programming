package created;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class OrderOfOperations {
    int INF=1000000000;
    static int[] dp;

    public int minTime(String[] s) {
        int n= s.length;
        int m = s[0].length();
        dp = new int[1<<m];
        Arrays.fill(dp, INF);
        dp[0]=0;
        int[] bits= new int[n];
        for(int i=0; i<n; i++){
            bits[i]= Integer.parseInt(s[i],2);
        }
        for(int mask1=0; mask1<(1<<m); mask1++) {
            if(dp[mask1]>=INF){
                continue;
            }
            for(int str : bits){
                int mask2= mask1 | str;
                int add= Integer.bitCount(mask2)- Integer.bitCount(mask1);
                dp[mask2]= Math.min(dp[mask2], dp[mask1]+add*add);
            }
        }
        int ans=0;
        for(int i=0; i<n; i++){
            ans|=bits[i];
        }
        return dp[ans];
    }
}
