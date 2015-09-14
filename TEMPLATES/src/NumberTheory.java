import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Bat-Orgil on 9/2/2015.
 */
public class NumberTheory {

    static int MOD=1000000007;
    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }
    /* This loop can calculate the reverse of the number i up to MAXN
        REVFACT[1] = REVFACT[0] = 1;
        REV[1] = 1;
        for (int i = 2; i < MAXN; i++) {
            REV[i] = mul(REV[MOD % i], MOD - MOD / i);
            REVFACT[i] = mul(REV[i], REVFACT[i - 1]);
        }
    *
    *
    * */



    public static long binomialCoefficient(int n, int m, long mod) {
        if (m < 0 || m > n)
            return 0;
        if (2 * m > n)
            m = n - m;
        long result = 1;
        for (int i = n - m + 1; i <= n; i++)
            result = result * i % mod;
        return result * BigInteger.valueOf(factorial(m, mod)).modInverse(BigInteger.valueOf(mod)).longValue() % mod;
    }



    public static long factorial(int n, long mod) {
        long result = 1;
        for (int i = 2; i <= n; i++)
            result = result * i % mod;
        return result % mod;
    }

    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++)
            result *= i;
        return result;
    }



 /*   // Euler's Totient Function
    public static long phi(long n) {// make sure to generate primes
        long temp = n;
        for (int i = 0; prime[i] * prime[i] <= temp && i < idx; i++)
            if (temp % prime[i] == 0) {
                n -= n / prime[i];
                while (temp % prime[i] == 0)
                    temp /= prime[i];
            }
        return temp == 1 ? n : n / temp * (temp - 1);
    }
*/
    // N choose K
    public static long comb(int n, int k) {
        int a = Math.min(k, n - k);
        long res = 1;
        for (int i = 1; i <= a; i++) {
            res *= n--;
            res /= i;
        }
        return res;
    }

    // Greatest Common Divisor
    public static long gcd(long x, long y) {
        for (; x != 0; x ^= y, y ^= x, x ^= y, x %= y);
        return y;
    }

    // Inverse of N mod M;
    public static long inv(long N, long M) {
        long x = 0, lastx = 1, y = 1, lasty = 0, q, t, a = N, b = M;
        while (b != 0) {
            q = a / b; t = a % b; a = b; b = t;
            t = x; x = lastx - q * x; lastx = t;
            t = y; y = lasty - q * y; lasty = t;
        }
        return (lastx + M) % M;
    }

    // Chinese Remainder Theorem
    // all mods are pairwise coprime
    public static long CRT(long[] vals, long[] mods) {
        long prodall = 1;
        for (long j : mods)
            prodall *= j;

        long ret = 0;
        for (int i = 0; i < vals.length; i++) {
            long ni = mods[i], ai = vals[i];
            ret = (ret + ai * prodall / ni % prodall * inv(prodall / ni % ni, ni)) % prodall;
        }
        return ret;
    }

    public static int generator(int p) {
        ArrayList<Integer> fact = new ArrayList<>();
        int phi = p - 1;
        int n = phi;
        for (int i = 2; i * i <= n; ++i)
            if (n % i == 0) {
                fact.add(i);
                while (n % i == 0)
                    n /= i;
            }
        if (n > 1) fact.add(n);

        for (int res = 2; res <= p; ++res) {
            int i;
            for (i = 0; i < fact.size() && mod_exp(res, phi / fact.get(i), p) != 1; ++i) ;
            if (i == fact.size())
                return res;
        }

        return -1;
    }

    public static long mod_exp(long b, long e, long mod) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1)
                res = (res * b) % mod;
            b = (b * b) % mod;
            e >>= 1;
        }
        return res;
    }

    public static long[][] generateBinomialCoefficients(int n, long module) {
        long[][] result = new long[n + 1][n + 1];
        if (module == 1)
            return result;
        for (int i = 0; i <= n; i++) {
            result[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
                if (result[i][j] >= module)
                    result[i][j] -= module;
            }
        }
        return result;
    }

    public static long[] generateBinomialRow(int n, long module) {
        long[] result = generateReverse(n + 1, module);
        result[0] = 1;
        for (int i = 1; i <= n; i++)
            result[i] = result[i - 1] * (n - i + 1) % module * result[i] % module;
        return result;
    }

    public static long[] generateReverse(int upTo, long module) {
        long[] result = new long[upTo];
        if (upTo > 1)
            result[1] = 1;
        for (int i = 2; i < upTo; i++)
            result[i] = (module - module / i * result[((int) (module % i))] % module) % module;
        return result;
    }

}

/**
 * Prime Siever
 *
 * @author Lewin
 *
 */

// initialize len.
class PrimeSieve {
    public static boolean[] isPrime;
    public static int[] prime;
    public static int idx, len;

    private static void generatePrimes() {
        isPrime = new boolean[len + 1];
        prime = new int[len / 2];
        isPrime[2] = true;
        prime[idx++] = 2;
        int i;
        for (i = 3; i <= len; i += 2)
            isPrime[i] = true;
        for (i = 3; i * i <= len; i += 2) {
            if (isPrime[i]) {
                prime[idx++] = i;
                for (int j = i * i; j <= len; j += 2 * i)
                    isPrime[j] = false;
            }
        }
        for (; i <= len; i += 2)
            if (isPrime[i])
                prime[idx++] = i;
    }
}
