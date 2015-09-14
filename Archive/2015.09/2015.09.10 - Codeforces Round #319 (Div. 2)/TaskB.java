package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    static boolean[][][] dp;
    static boolean[][][] was;
    static int m;
    static int[] x;
    static long big;

    static int add(long a, long b) {
        long c= a + b + big;
        return (int)(c%m);
    }

    static boolean dp(int num, int mod, int nonempty) {
        if (was[nonempty][num][mod]) {
            return dp[nonempty][num][mod];
        } else if (mod == 0 && nonempty == 0) {
            return true;
        } else if (mod == 0 && x[num] >= m) {
            return true;
        } else if (num == 1) {
            if (mod <= x[1] && mod >= 1) {
                return true;
            } else {
                return false;
            }
        }
        was[nonempty][num][mod] = true;
        boolean ans = dp(num - 1, mod, nonempty);
        for (int i = 1; i <= Math.min(x[num],m-1); i++) {
          //  System.out.println("i,num,mod" + i + " " + num + " " + mod + "=" + add(mod,-num*i));
            if (dp(num - 1, add(mod,-num *i), 1) | dp(num - 1, add(mod, -num * i), 0)) {
                ans = true;
                break;
            }
        }
        dp[nonempty][num][mod] = ans;
        // System.out.println("nonempty,num,mod=ans" + nonempty + " " + num + " " + mod + " =" + ans);

        return ans;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        m = in.nextInt();
        x = new int[m];
        for (int i = 0; i < n; i++) {
            x[in.nextInt() % m]++;
        }
        dp = new boolean[2][m][m];
        was = new boolean[2][m][m];
        big= m *1000000000L;
        if (x[0] >= 1) {
            out.println("YES");
            return;
        }

        if (dp(m - 1, 0, 1)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
