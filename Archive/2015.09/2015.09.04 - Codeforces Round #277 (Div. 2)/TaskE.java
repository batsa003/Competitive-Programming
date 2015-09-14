package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE {

    private int[] LIS(int n, int[] a) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;
        int cn = 1;
        int[] longest = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = cn;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (dp[mid] >= a[i]) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            longest[i] = l + 1;
            if (r + 1 > cn) cn = r + 1;
            dp[r] = a[i];
        }
        return longest;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] a= new int[n];
        for(int i=0; i<n; i++){
            a[i]=in.nextInt();
        }

        int[] f1= LIS(n, a);

        int[] b= new int[n];
        for(int i=0; i<n; i++){
            b[i]=-a[n-1-i];
        }
        int[] temp = LIS(n,b);
        int[] f2= new int[n];
        for(int i=0; i<n; i++){
            f2[i]= temp[n-1-i];
        }

        int L=0;
        int[] count= new int[n+1];
        for(int i=0; i<n; i++){
            L= Math.max(f1[i],L);
        }
        for(int i=0; i<n; i++){
            if((f1[i]+f2[i]-1)==L) {
                count[f1[i]]++;
            }
        }
/*
        System.out.println(Arrays.toString(f1));
        System.out.println(Arrays.toString(f2));

        System.out.println(Arrays.toString(count));*/

        for(int i=0; i<n; i++){
            if( f1[i]+f2[i]-1<L){
                out.print("1");
            }else if(count[f1[i]]==1){
                out.print("3");
            }else{
                out.print("2");
            }
        }

    }
}
