package created;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TheKingsFactorization {

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

    public long[] getVector(long N, long[] primes) {
        boolean[] table = generatePrimalityTable(1000000);
        ArrayList<Long> ar= new ArrayList();
        for(int i=0; i<primes.length ;i++){
            ar.add(primes[i]);
            N/= primes[i];
        }
        for(int i=2; i<table.length; i++){
            if(!table[i]) continue;
            if(N==1){
                break;
            }
            while(N%i==0){
                N/=i;
                ar.add((long)i);
            }
        }
        if(N!=1){
            ar.add(N);
        }
        Collections.sort(ar);
        long[] ans = new long[ar.size()];
        for(int i=0; i<ar.size(); i++){
            ans[i]= ar.get(i);
        }
        return ans;
    }
}
