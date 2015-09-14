import java.util.Arrays;

/**
 * Created by Bat-Orgil on 9/4/2015.
 */
public class LIS {
    // n is the size of the array a;
    // returns an array which contains the longest increasing subsequence ending at that position.

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
}
