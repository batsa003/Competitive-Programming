package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskC {
    static long mod= 1000000007;

    // d -> s1...sk. Then that means, d will turn into what s1...sk turned into in next operations
    // Thus, we if there is only one query, then we're good.
    // If there are two queries, we can see what happens in the last query, and in the first query, we can recursively calculate depending on what second query
    // turns the values into.
    // For example, 535.  1st - 3 -> 55, 2nd - 5 -> 156;
    // This means that the second query turns all digit 5 into 156. shift[5]= 1000; ( turns into 3 digit number);
    // The first query turns the digit 3 into 55 or  val[5]* shift[(next digit) =5] + val[5], or 156* 1000 + 156;
    // Therefore, the answer is 156 156156 156;


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s= in.next();
        int m= in.nextInt();
        int[] left= new int[m];
        String[] right= new String[m];

        for(int i=0; i<m; i++){
            String t= in.next();
            left[i]= t.charAt(0)-'0';
            right[i]= t.substring(3);
        }

        long[] val= new long[10];
        long[] shift= new long[10];
        for(int i=0; i<10; i++){
            val[i]=i;
            shift[i]=10;
        }

        for(int i=m-1; i>=0; i--){
            long newVal=0;
            long newShift=1;

            for(int j=0; j<right[i].length(); j++){
                int c= right[i].charAt(j)-'0';
                newVal= ( newVal*shift[c] + val[c])%mod;
                newShift= (newShift*shift[c])%mod;
            }
            val[left[i]]=newVal;
            shift[left[i]]= newShift;
        }
        long v=0;
        for(int i=0; i<s.length(); i++){
            v = (v*shift[s.charAt(i)-'0'] + val[s.charAt(i)-'0'])%mod;
        }
        out.println(v);

    }
}
