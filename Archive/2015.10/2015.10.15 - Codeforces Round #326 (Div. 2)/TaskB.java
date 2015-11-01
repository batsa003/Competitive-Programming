package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {

    public static boolean[] generatePrimalityTable(int upTo) {
        boolean[] isPrime = new boolean[upTo];
        if (upTo < 2)
            return isPrime;
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < upTo; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < upTo; j += i)
                    isPrime[j] = false;
            }
        }
        return isPrime;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        boolean[] primes = generatePrimalityTable(1000000);
        long n= in.nextLong();
        long max=1;
        for(int i=2; i<primes.length; i++){
            if(primes[i] && (n % i)==0){
                max*=i;
                while(n%i==0){
                    n/=i;
                }
            }
        }
        if( n>1000000){
            max*=n;
        }
        out.println(max);
    }
}
