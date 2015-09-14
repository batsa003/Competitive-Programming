package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class TaskC {



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
        int n= in.nextInt();
        boolean[] isPrime= generatePrimalityTable(1001);
        LinkedList<Integer> ans = new LinkedList();
        for(int i=2; i<=n; i++){
            if(isPrime[i]){
                int j=i;
                while(j<=n){
                    ans.add(j);
                    j*=i;
                }
            }
        }
        out.println(ans.size());
        for(int i : ans){
            out.print(i + " ");
        }


    }
}
